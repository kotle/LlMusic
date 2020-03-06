package com.yizisu.music.and.video.module.main


import android.os.Bundle
import android.view.LayoutInflater
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.view.simpleFragmentPagerAdapter
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseUiActivity() {
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_main
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        transparentStatusBar()
        homeVp.adapter = simpleFragmentPagerAdapter(
            mutableListOf(MainFragment.create())
        )
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        goHome()
    }
}
