package com.yizisu.music.and.video.module.full_video

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.yizisu.basemvvm.logI
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.playerlibrary.IYzsPlayer
import com.yizisu.playerlibrary.PlayerFactory
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener
import kotlinx.android.synthetic.main.activity_full_video.*
import java.io.Serializable

class FullVideoPortraitActivity : FullVideoActivity() {
    companion object {
        private const val KEY_VIDEO_DATA = "KEY_VIDEO_DATA"
        fun start(appCompatActivity: AppCompatActivity?, bean: FullVideoData) {
            appCompatActivity?.navigateTo<FullVideoPortraitActivity>{
                it.putExtra(KEY_VIDEO_DATA, bean)
            }
        }
    }
}
