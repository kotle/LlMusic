package com.yizisu.music.and.video.module.fragment.home


import android.content.ContextWrapper
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.google.android.exoplayer2.offline.Download
import com.yizisu.basemvvm.mvvm.MvvmPopupWindow
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.view.GestureDetectorHelper
import com.yizisu.basemvvm.widget.BaseLinearLayout
import com.yizisu.basemvvm.widget.BaseTextView
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.add_song_to_album.AddSongToAlbumActivity
import com.yizisu.music.and.video.module.lrc.LrcActivity
import com.yizisu.music.and.video.module.main.MainActivity
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.*
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.fragment_home_music.*

class HomeMusicFragment : BaseFragment(), MusicEventListener {
    companion object{
         fun startDownload(appCompatActivity: AppCompatActivity?,songModel: SongModel) {
            if (songModel.song.playFilePath.isNullOrEmpty()) {
                DownloadSongHelper(
                    appCompatActivity, songModel,
                    DbCons.ALBUM_ID_CURRENT
                ).startDown()
            } else {
                "${songModel.song.name}已下载".toast()
            }
        }
    }
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_home_music
    }

    override fun initViewModel() {
        super.initViewModel()
        AppData.currentPlaySong.registerOnSuccess {
            it?.song?.apply {
                titleTv.textFrom(name)
//                setIsHeart(heartIv)
                if (playFilePath.isNullOrEmpty()) {
                    downloadIv.setImageGlide(R.drawable.icon_download)
                } else {
                    downloadIv.setImageGlide(R.drawable.icon_downloaded)
                }
            }
        }
        AppData.dbHeartAlbumData.registerOnSuccess {
            //            setIsHeart(heartIv)
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        MusicService.addMusicEventListener(this)
        lrcView.setDraggable(false, null)
        GestureDetectorHelper(lrcView, true).apply {
            setOnDoubleClickListener {
                onSingleClick(playOrPauseIv)
            }
            setOnClickListener {
                onSingleClick(headMusicLl)
            }
        }
    }


    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(preIv, playOrPauseIv, nextIv, playListIv, headMusicLl, addIv, downloadIv)
    }


    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            preIv -> {
                MusicService.sendBroadcastReceiver(context, MusicService.ACTION_PRE)
            }
            playOrPauseIv -> {
                MusicService.sendBroadcastReceiver(
                    context,
                    if (isPlaying) MusicService.ACTION_PAUSE else MusicService.ACTION_PLAY
                )
            }
            nextIv -> {
                MusicService.sendBroadcastReceiver(context, MusicService.ACTION_NEXT)
            }
            playListIv -> {
                CurrentPlayListDialog.show(appCompatActivity)
            }
            headMusicLl -> {
                LrcActivity.start(appCompatActivity)
            }
            addIv -> {
//                heartIvClick()
                showPopup(view)
            }
            downloadIv -> {
                val songModel = AppData.currentPlaySong.data ?: return
                startDownload(appCompatActivity,songModel)
            }

        }
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

    /**
     * 显示Popup
     */
    private fun showPopup(it: View?) {
        var ctx = it?.context ?: return

        if (ctx !is AppCompatActivity) {
            if (ctx is ContextWrapper) {
                ctx = ctx.baseContext
            }
        }
        val songModel = AppData.currentPlaySong.data ?: return
        val song = songModel.song
        var isDownload = !song.playFilePath.isNullOrBlank()
        val songs = AppData.dbCurrentAlbumData.data?.songInfoTables ?: return
        var popupWindow: PopupWindow? = null
        val rootView = BaseLinearLayout(
            ctx
        ).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dip(16), 0, dip(16), 0)
            setBackgroundResource(R.drawable.bg_dialog_play_list_8_r)
            addView(BaseTextView(ctx).apply {
                text = "歌曲：${song.name}"
                setLines(1)
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.GRAY)
                textSize = 12f
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = "添加到歌单"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    popupWindow?.dismiss()
                    SelectPlayListDialog.show(ctx.safeGet(), null) {
                        launchThread {
                            DbHelper.addSongToAlbum(song, it)
                            if (it.dbId == DbCons.ALBUM_ID_HEART) {
                                dbViewModel.queryHeartList()
                            }
                            "${song.name} 已经添加到 ${it.title}".toast(Toast.LENGTH_LONG)
                        }
                    }
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = "批量操作"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    toAllSongPage(this, songs)
                    popupWindow?.dismiss()
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = if (isDownload) "已下载" else "下载"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(if (isDownload) Color.GRAY else Color.BLACK)
                if (isDownload) {
                    setIcon(R.drawable.icon_tag_downloaded, drawablePaddingDip = 4)
                }
                setOnClickListener {
                    startDownload(appCompatActivity,songModel)
                    popupWindow?.dismiss()
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
        }
        popupWindow = MvvmPopupWindow(
            rootView, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            showAsDropDown(it)
        }
    }

    /**
     * 跳转到批量操作界面
     */
    private fun toAllSongPage(itemView: View, datas: MutableList<SongInfoTable>) {
        var ctx = itemView.context
        if (ctx !is AppCompatActivity) {
            if (ctx is ContextThemeWrapper) {
                ctx = ctx.baseContext
            }
        }
        AddSongToAlbumActivity.start(
            ctx.safeGet<AppCompatActivity>(),
            datas,
            AppData.dbCurrentAlbumData.data
        )
    }
}
