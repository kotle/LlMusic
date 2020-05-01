package com.yizisu.music.and.video.module.local_music


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.yizisu.basemvvm.utils.goHome
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.module.local_music.adapter.LocalPagerAdapter
import kotlinx.android.synthetic.main.activity_local_music.*

class LocalMusicActivity : BaseUiActivity() {
    companion object {
        fun start(appCompatActivity: AppCompatActivity?) {
            appCompatActivity?.navigateTo(LocalMusicActivity::class.java)
        }
    }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_local_music
    }

    override fun isNeedToolbar(): Boolean {
        return true
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        setTitle(R.string.local_video)
    }

    override fun isNeedBackIcon(): Boolean {
        return true
    }

}
