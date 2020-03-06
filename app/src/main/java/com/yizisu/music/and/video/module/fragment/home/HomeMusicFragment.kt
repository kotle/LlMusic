package com.yizisu.music.and.video.module.fragment.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import com.yizisu.basemvvm.utils.navigateWithViews
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.utils.setCircleImageFromRes
import com.yizisu.basemvvm.utils.textFrom

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.module.lrc.LrcActivity
import com.yizisu.music.and.video.module.main.MainActivity
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.LocalMusicUtil
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.fragment_home_music.*

class HomeMusicFragment : BaseFragment(), MusicEventListener {
    private val mainActivity: MainActivity?
        get() = appCompatActivity?.safeGet()
    private var playList: MutableList<PlayerModel>? = null

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_home_music
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        coverIv.setCircleImageFromRes(R.drawable.default_cover_icon)
        MusicService.addMusicEventListener(this)
    }

    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(preIv, playOrPauseIv, nextIv, playListIv, headMusicLl)
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
                CurrentPlayListDialog.show(appCompatActivity, playList)
            }
            headMusicLl -> {
                LrcActivity.start(appCompatActivity)
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
        playerModel.safeGet<SongModel>()?.song?.apply {
            titleTv.textFrom(title)
        }
    }

    override fun onPlayerListChange(playerModels: MutableList<PlayerModel>) {
        super.onPlayerListChange(playerModels)
        playList = playerModels
    }

    override fun onTick(playerModel: PlayerModel) {

    }
}
