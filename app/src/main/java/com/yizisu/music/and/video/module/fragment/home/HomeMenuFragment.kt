package com.yizisu.music.and.video.module.fragment.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.module.local_music.LocalMusicActivity
import kotlinx.android.synthetic.main.fragment_home_menu.*


class HomeMenuFragment : BaseFragment() {
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_home_menu
    }

    override fun getClickView(): List<View?>? {
        return listOf(recentAddMusicFl, headMusicFl, localMusicFl)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            recentAddMusicFl -> {
            }
            headMusicFl -> {
            }
            localMusicFl -> {
                LocalMusicActivity.start(appCompatActivity)
            }
        }
    }

}
