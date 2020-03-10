package com.yizisu.music.and.video

import android.app.Application
import com.yizisu.basemvvm.initMvvmLib
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.video.service.music.MusicService

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initMvvmLib(this)
        DbHelper.init(this, "music")
    }
}