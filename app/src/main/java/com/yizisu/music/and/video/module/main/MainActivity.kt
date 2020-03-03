package com.yizisu.music.and.video.module.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.module.local_music.LocalMusicActivity
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.LocalMusicUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseUiActivity() {
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_main
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
    }

    override fun getClickView(): List<View?>? {
        return listOf(toLocalMusic)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            toLocalMusic -> {
                LocalMusicActivity.start(this)
            }
            else -> {
            }
        }
    }
}
