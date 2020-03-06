package com.yizisu.music.and.video.module.lrc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.activity_lrc.*

class LrcActivity : BaseActivity(), MusicEventListener {
    companion object {
        fun start(appCompatActivity: AppCompatActivity?) {
            appCompatActivity?.navigateTo(LrcActivity::class.java)
        }
    }

    private var playList: MutableList<PlayerModel>? = null
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_lrc
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        transparentStatusBar()
        coverIv.setCircleImageFromRes(R.drawable.default_cover_icon)
        MusicService.addMusicEventListener(this)
    }

    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(preIv, playOrPauseIv, nextIv, coverIv, playListIv)
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
            coverIv -> {
//                CurrentPlayListDialog.show(appCompatActivity, playList)
            }
            playListIv -> {
                CurrentPlayListDialog.show(this, playList)
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


    override fun onPlayerModelChange(playerModel: PlayerModel) {
        super.onPlayerModelChange(playerModel)
        coverIv.updateCover(playerModel, true)
        fullImageIv.updateCover(playerModel, true)
        playerModel.safeGet<SongModel>()?.song?.apply {
            titleTv.textFrom(title)
            desTv.textFrom("${singer}-${album}".trimEnd('-'))
        }
    }

    override fun onPlayerListChange(playerModels: MutableList<PlayerModel>) {
        super.onPlayerListChange(playerModels)
        playList = playerModels
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
