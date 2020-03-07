package com.yizisu.music.and.video.service.music

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

object MusicServiceConnection: ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

    }
}