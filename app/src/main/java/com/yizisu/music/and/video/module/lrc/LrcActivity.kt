package com.yizisu.music.and.video.module.lrc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.heartIvClick
import com.yizisu.music.and.video.utils.setIsHeart
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.activity_lrc.*

class LrcActivity : BaseActivity(), MusicEventListener {
    companion object {
        fun start(appCompatActivity: AppCompatActivity?) {
            appCompatActivity?.navigateTo(LrcActivity::class.java)
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
    }

    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(preIv, playOrPauseIv, nextIv, playListIv, lrcFl, lrcView, heartIv)
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
        }
    }


    override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
        super<MusicEventListener>.onPause(playStatus, playerModel)
        setPlayState(playStatus)
    }

    override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
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

    override fun onTick(playerModel: PlayerModel) {
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
}
