package com.yizisu.music.and.video.module.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.view.simpleFragmentPagerAdapter
import com.yizisu.basemvvm.widget.BaseImageView
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.module.search.SearchMusicActivity
import com.yizisu.music.and.video.service.music.MusicService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseUiActivity() {
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_main
    }

    private val windowCoverIv by lazy {
        BaseImageView(app).apply {
            scaleType = ImageView.ScaleType.FIT_XY
            setImageResource(R.drawable.bg_launcher_window)
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        window
            .decorView
            .findViewById<FrameLayout>(android.R.id.content)
            .addView(windowCoverIv)
        MusicService.bindService(this)
        transparentStatusBar()
        homeVp.adapter = simpleFragmentPagerAdapter(
            mutableListOf(MainFragment.create())
        )
        windowCoverIv.post {
            windowCoverIv.animate().alpha(0f)
                .withEndAction {
                    windowCoverIv.gone()
                }
                .setDuration(1000)
                .start()
        }
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
        return listOf(searchTv, windowCoverIv)
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
