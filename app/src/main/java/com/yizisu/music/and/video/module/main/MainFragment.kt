package com.yizisu.music.and.video.module.main

import android.os.Bundle
import android.view.LayoutInflater
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.utils.setBlurView
import com.yizisu.music.and.video.utils.updateCover
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment() {
    companion object {
        fun create(): MainFragment {
            return MainFragment()
        }
    }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_main
    }
}
