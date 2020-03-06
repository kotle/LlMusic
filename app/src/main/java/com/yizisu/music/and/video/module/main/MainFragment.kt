package com.yizisu.music.and.video.module.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizisu.basemvvm.utils.setCircleImageFromRes

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.LocalMusicUtil
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment(), MusicEventListener {
    companion object {
        fun create(): MainFragment {
            return MainFragment()
        }
    }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_main
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        MusicService.addMusicEventListener(this)
    }

    override fun onDestroy() {
        MusicService.removeMusicEventListener(this)
        super.onDestroy()
    }

    override fun onPlayerModelChange(playerModel: PlayerModel) {
        super.onPlayerModelChange(playerModel)
        bottomBlurIv.updateCover(playerModel)
    }
}
