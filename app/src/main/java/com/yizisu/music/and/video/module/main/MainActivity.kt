package com.yizisu.music.and.video.module.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.view.simpleFragmentPagerAdapter
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.module.search.SearchMusicActivity
import com.yizisu.music.and.video.service.music.MusicService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseUiActivity() {
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_main
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        MusicService.bindService(this)
        transparentStatusBar()
        homeVp.adapter = simpleFragmentPagerAdapter(
            mutableListOf(MainFragment.create())
        )
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        goHome()
    }

    override fun onDestroy() {
        MusicService.unBindService(this)
        super.onDestroy()
    }

    override fun getClickView(): List<View?>? {
        return listOf(searchTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            searchTv -> {
                SearchMusicActivity.start(this)
            }
            else -> {
            }
        }
    }
}
