package com.yizisu.music.and.video.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Binder
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.view.KeyEvent
import androidx.core.app.NotificationCompat
import com.yizisu.basemvvm.printBundle
import com.yizisu.basemvvm.utils.getLastActivityIntent
import com.yizisu.basemvvm.utils.getResString
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.module.lrc.LrcActivity
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.service.music.MusicService.Companion.ACTION_CLOSE
import com.yizisu.music.and.video.service.music.MusicService.Companion.ACTION_NEXT
import com.yizisu.music.and.video.service.music.MusicService.Companion.ACTION_PAUSE
import com.yizisu.music.and.video.service.music.MusicService.Companion.ACTION_PLAY
import com.yizisu.music.and.video.service.music.MusicService.Companion.ACTION_PRE

/**
 * 开启一个前台通知，进程保活
 */
fun Service.sendNotify(
    notificationManager: NotificationManager,
    coverBp: Bitmap?,
    title: String,
    message: String,
    id: Int,
    isPlay: Boolean,
    session: MediaSessionCompat
) {
    val notificationBuilder = NotificationCompat.Builder(this, id.toString())
        .setSmallIcon(R.drawable.music_small_icon)
        .setContentTitle(title)
        .setContentIntent(
            PendingIntent.getActivity(
                this, 100,
                Intent(this, LrcActivity::class.java)
                , PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        //true setColor为背景色,文字自动反色
        //false setColor为文字颜色
        .setColorized(true)
//        .setColor(Color.BLUE)
        .setSubText(getResString(R.string.app_des))
        .setStyle(androidx.media.app.NotificationCompat.DecoratedMediaCustomViewStyle().also {
            it.setShowActionsInCompactView(1, 3, 2)
            it.setMediaSession(session.sessionToken)
//            it.setShowCancelButton(true)
        })
        .addAction(
            R.drawable.small_icon_pre,
            ACTION_PRE,
            createReceiverIntent(ACTION_PRE)
        )
        .addAction(
            if (isPlay) R.drawable.small_icon_pause else R.drawable.small_icon_play,
            if (isPlay) ACTION_PAUSE else ACTION_PLAY,
            createReceiverIntent(if (isPlay) ACTION_PAUSE else ACTION_PLAY)
        )
        .addAction(R.drawable.small_icon_next, ACTION_NEXT, createReceiverIntent(ACTION_NEXT))
        .addAction(R.drawable.small_icon_stop, ACTION_CLOSE, createReceiverIntent(ACTION_CLOSE))
        //使用默认的声音、振动、闪光
//      .setDefaults(Notification.DEFAULT_ALL)
        .setContentText(message)
        .setLargeIcon(coverBp)
        .setAutoCancel(false)
        .setVibrate(null)
        .setShowWhen(false)
        .setOnlyAlertOnce(true)
        //锁屏权限
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//        .setTicker("$title-$message")//在状态栏短暂显示的文字

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            id.toString(),
            "music",
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
    if (isPlay) {
        startForeground(id, notificationBuilder.build())
    } else {
        stopForeground(false)
        notificationManager.notify(id, notificationBuilder.build())
    }
}

/**
 * 创建intent
 */
fun Service.createReceiverIntent(action: String): PendingIntent {
    return PendingIntent.getBroadcast(this, 0, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT)
}


/**
 * 注册监听蓝牙耳机按键
 */
fun MediaSessionCompat.registerSession() {
    //exoPlayer 会覆盖这个方法,所以这里设置无效
    setFlags(
        MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
    )
    //exoPlayer 会覆盖这个方法,所以这里设置无效
    setCallback(mediaCall)
    //exoPlayer 会调用以上两个方法
    isActive = true
}

/**
 * exo player 已经做了默认处理
 */
private val mediaCall = object : MediaSessionCompat.Callback() {
    override fun onMediaButtonEvent(mediaButtonEvent: Intent?): Boolean {
        printBundle(mediaButtonEvent)
        val event = mediaButtonEvent?.extras?.get(Intent.EXTRA_KEY_EVENT) as? KeyEvent
        var action: String? = null
        when (event?.keyCode) {
            KeyEvent.KEYCODE_MEDIA_NEXT -> {
                action = ACTION_NEXT
            }
            KeyEvent.KEYCODE_MEDIA_PREVIOUS -> {
                action = ACTION_PRE
            }
            KeyEvent.KEYCODE_MEDIA_PLAY -> {
                action = ACTION_PLAY
            }
            KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                action = ACTION_PAUSE
            }
        }
        //蓝牙按键的信息推送给广播处理
        action?.let {
            //            musicReceiver.onReceive(this@MusicService, Intent(action))
        }
        return super.onMediaButtonEvent(mediaButtonEvent)
    }
}

/**
 * 取消注册监听蓝牙耳机按键
 */
fun MediaSessionCompat.unregisterSession() {
    setCallback(null)
    isActive = false
    release()
}