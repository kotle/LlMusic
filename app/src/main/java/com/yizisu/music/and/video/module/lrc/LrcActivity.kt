package com.yizisu.music.and.video.module.lrc

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.Toast
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.MvvmPopupWindow
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.add_song_to_album.AddSongToAlbumActivity
import com.yizisu.music.and.video.module.fragment.home.HomeMusicFragment
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.dbViewModel
import com.yizisu.music.and.video.utils.heartIvClick
import com.yizisu.music.and.video.utils.setIsHeart
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.SimplePlayer
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.activity_lrc.*
import kotlinx.android.synthetic.main.activity_lrc.currentProgressTv

class LrcActivity : BaseActivity(), MusicEventListener {
    companion object {
        private var currentSpeedIndex = 2
        var currentRepeatModel: Int? = null
            get() {
                return field ?: app.spGet("currentRepeatModel", SimplePlayer.LOOP_MODO_NONE)
            }
            set(value) {
                field = value
                app.spSet("currentRepeatModel", value)
            }

        fun start(appCompatActivity: AppCompatActivity?) {
            appCompatActivity?.navigateTo(LrcActivity::class.java)
        }
    }

    private val speedList = mutableListOf(
        0.5f, 0.75f, 1f, 1.25f, 1.5f, 2.0f
    )

    private fun switchRepeatMode(isToast: Boolean) {
        val message = when (currentRepeatModel) {
            SimplePlayer.LOOP_MODO_NONE -> {
                repeatModeIv.setImageResource(R.drawable.icon_loop_null)
                "顺序播放"
            }
            SimplePlayer.LOOP_MODO_LIST -> {
                repeatModeIv.setImageResource(R.drawable.icon_loop_list)
                "列表循环"
            }
            SimplePlayer.LOOP_MODO_SHUFF -> {
                repeatModeIv.setImageResource(R.drawable.icon_loop_shuffle)
                "随机播放"
            }
            SimplePlayer.LOOP_MODO_SINGLE -> {
                repeatModeIv.setImageResource(R.drawable.icon_loop_single)
                "单曲循环"
            }
            else -> {
                "顺序播放"
            }
        }
        if (isToast) {
            MusicService.setRepeatMode(currentRepeatModel)
            message.toast()
        }
    }

    private fun getNextRepeatMode() {
        when (currentRepeatModel) {
            SimplePlayer.LOOP_MODO_NONE -> {
                currentRepeatModel = SimplePlayer.LOOP_MODO_LIST
            }
            SimplePlayer.LOOP_MODO_LIST -> {
                currentRepeatModel = SimplePlayer.LOOP_MODO_SHUFF
            }
            SimplePlayer.LOOP_MODO_SHUFF -> {
                currentRepeatModel = SimplePlayer.LOOP_MODO_SINGLE
            }
            SimplePlayer.LOOP_MODO_SINGLE -> {
                currentRepeatModel = SimplePlayer.LOOP_MODO_NONE
            }
        }
    }

    private val currentPlayMode: PlayerModel?
        get() = AppData.currentPlaySong.data

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_lrc
    }

    override fun initViewModel() {
        super.initViewModel()
        AppData.currentPlaySong.registerOnSuccess {
            it?.song?.apply {
                fullImageIv.updateCover(it)
                setIsHeart(heartIv)
                titleTv.textFrom(name)
                desTv.textFrom(des)
                if (playFilePath.isNullOrEmpty()) {
                    downloadIv.setImageGlide(R.drawable.icon_download)
                } else {
                    downloadIv.setImageGlide(R.drawable.icon_downloaded)
                }
            }
        }
        AppData.dbHeartAlbumData.registerOnSuccess {
            setIsHeart(heartIv)
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        transparentStatusBar()
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        MusicService.addMusicEventListener(this)
        lrcToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        switchRepeatMode(false)
        progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                progressBar?.apply {
                    val ratio = progress.toFloat() / max
                    MusicService.seekRatio(ratio)
                    currentPlayMode?.let {
                        currentProgressTv.text =
                            getCountTimeByLong((it.totalDuration * ratio).toLong())
                    }
                }
            }
        })
        setSpeed(currentSpeedIndex)
    }

    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(
            preIv,
            playOrPauseIv,
            nextIv,
            playListIv,
            lrcFl,
            lrcView,
            heartIv,
            downloadIv,
            repeatModeIv,
            addToPlayList,
            iconMoreListTv,
            speedTv
        )
    }


    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            preIv -> {
                MusicService.sendBroadcastReceiver(this, MusicService.ACTION_PRE)
            }
            playOrPauseIv -> {
                MusicService.sendBroadcastReceiver(
                    this,
                    if (isPlaying) MusicService.ACTION_PAUSE else MusicService.ACTION_PLAY
                )
            }
            nextIv -> {
                MusicService.sendBroadcastReceiver(this, MusicService.ACTION_NEXT)
            }
            heartIv -> {
                heartIvClick()
            }
            playListIv -> {
                CurrentPlayListDialog.show(this)
            }
            lrcFl, lrcView -> {
                if (coverIv.isVisible()) {
                    coverIv.invisible()
                    lrcView.visible()
                } else {
                    coverIv.visible()
                    lrcView.invisible()
                }
            }
            downloadIv -> {
                val songModel = AppData.currentPlaySong.data ?: return
                HomeMusicFragment.startDownload(this, songModel)
            }
            repeatModeIv -> {
                getNextRepeatMode()
                switchRepeatMode(true)
            }
            addToPlayList -> {
                val song = AppData.currentPlaySong.data?.song ?: return
                SelectPlayListDialog.show(this, null) {
                    launchThread {
                        DbHelper.addSongToAlbum(song, it)
                        if (it.dbId == DbCons.ALBUM_ID_HEART) {
                            dbViewModel.queryHeartList()
                        }
                        "${song.name} 已经添加到 ${it.title}".toast(Toast.LENGTH_LONG)
                    }
                }
            }
            iconMoreListTv -> {
                val songs = AppData.dbCurrentAlbumData.data?.songInfoTables ?: return
                toAllSongPage(songs)
            }
            speedTv -> {
                setSpeed(++currentSpeedIndex)
//                MvvmPopupWindow(
//                    FrameLayout(this).apply {
//                        setBackgroundColor(Color.WHITE)
//                    },
//                    this.dip(100),
//                    this.dip(200)
//                ).showAsDropTopOrBottom(view,true)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSpeed(index: Int) {
        val i = index % speedList.count()
        val speed = speedList[i]
        speedTv.text = "X$speed"
        MusicService.setSpeed(speed)
    }

    override fun onPause(playStatus: Boolean, playerModel: SongModel?) {
        super<MusicEventListener>.onPause(playStatus, playerModel)
        setPlayState(playStatus)
    }

    override fun onPlay(playStatus: Boolean, playerModel: SongModel?) {
        super.onPlay(playStatus, playerModel)
        setPlayState(playStatus)
    }

    private var isPlaying = false
    private fun setPlayState(playStatus: Boolean) {
        isPlaying = playStatus
        if (playStatus) {
            playOrPauseIv.setImageResource(R.drawable.icon_pause)
        } else {
            playOrPauseIv.setImageResource(R.drawable.icon_play)
        }
    }

    override fun onTick(playerModel: SongModel) {
        setProgress(
            playerModel.currentDuration,
            playerModel.currentBufferDuration,
            playerModel.totalDuration
        )
    }

    private fun setProgress(
        currentProgress: Long?,
        bufferProgress: Long?,
        allProgress: Long
    ) {
        if (allProgress <= 0) {
            return
        }
        val max = progressBar.max
        if (currentProgress != null) {
            currentProgressTv.text = getCountTimeByLong(currentProgress)
            progressBar.progress = (currentProgress * max / allProgress).toInt()
        }
        if (bufferProgress != null) {
            progressBar.secondaryProgress = (bufferProgress * max / allProgress).toInt()
        }
        totalProgressTv.text = getCountTimeByLong(allProgress)
    }

    /**
     * 跳转到批量操作界面
     */
    private fun toAllSongPage(datas: MutableList<SongInfoTable>) {
        AddSongToAlbumActivity.start(
            this,
            datas,
            AppData.dbCurrentAlbumData.data
        )
    }
}
