package com.yizisu.music.and.video.module.fragment.home


import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.music.and.video.AppData

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.fragment.home.adapter.HomeItemImageAdapter
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.LocalMusicViewModel
import kotlinx.android.synthetic.main.fragment_recent_play.*

class RecentPlayFragment : BaseFragment() {

    private val adapter = HomeItemImageAdapter()
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_recent_play
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            MusicService.startPlay(
                adapter.datas.map {
                    SongModel(it)
                }.toMutableList(), position, true
            )
        }
        recentPlayRcv.adapter = adapter
    }
}
