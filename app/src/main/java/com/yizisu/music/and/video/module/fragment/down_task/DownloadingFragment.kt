package com.yizisu.music.and.video.module.fragment.down_task


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.utils.DownSongWithNotification
import kotlinx.android.synthetic.main.fragment_downloading.*

class DownloadingFragment : BaseFragment() {
    override fun getContentResOrView(inflater: LayoutInflater): Any? = R.layout.fragment_downloading

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        downloadingRcv.adapter = DownloadingAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        DownSongWithNotification.downloadingList.forEach {
            it.listener = null
        }
    }
}
