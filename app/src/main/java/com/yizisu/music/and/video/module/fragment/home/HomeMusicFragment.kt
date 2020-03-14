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
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.video.AppData

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.CurrentPlayListDialog
import com.yizisu.music.and.video.module.lrc.LrcActivity
import com.yizisu.music.and.video.module.main.MainActivity
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.LocalMusicUtil
import com.yizisu.music.and.video.utils.heartIvClick
import com.yizisu.music.and.video.utils.setIsHeart
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.fragment_home_music.*

class HomeMusicFragment : BaseFragment(), MusicEventListener {

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_home_music
    }

    override fun initViewModel() {
        super.initViewModel()
        AppData.currentPlaySong.registerOnSuccess {
            it?.song?.apply {
                titleTv.textFrom(name)
                setIsHeart(heartIv)
            }
        }
        AppData.dbHeartAlbumData.registerOnSuccess {
            setIsHeart(heartIv)
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        MusicService.addMusicEventListener(this)
        lrcView.setDraggable(false, null)
    }

    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(preIv, playOrPauseIv, nextIv, playListIv, headMusicLl,heartIv)
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
            headMusicLl, lrcView -> {
                LrcActivity.start(appCompatActivity)
            }
            heartIv -> {
                heartIvClick()
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
}
