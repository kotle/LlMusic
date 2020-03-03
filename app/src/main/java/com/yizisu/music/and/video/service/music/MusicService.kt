package com.yizisu.music.and.video.service.music

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.MediaSession2Service
import android.media.browse.MediaBrowser
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import android.view.KeyEvent
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBusInterface
import com.yizisu.basemvvm.printBundle
import com.yizisu.basemvvm.utils.isThis
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.video.cons.BusCode.SERVICE_PLAY_LIST
import com.yizisu.music.and.video.module.fragment.LocalMusicFragment
import com.yizisu.music.and.video.module.local_music.LocalMusicActivity
import com.yizisu.music.and.video.utils.registerSession
import com.yizisu.music.and.video.utils.sendNotify
import com.yizisu.music.and.video.utils.unregisterSession
import com.yizisu.playerlibrary.SimplePlayer
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener

class MusicService : Service(), MessageBusInterface, SimplePlayerListener {
    companion object {
        private var isStartMusicService = false
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PRE = "ACTION_PRE"
        fun start(context: Context) {
            context.startService(Intent(context, MusicService::class.java))
        }

        fun startPlay(
            models: MutableList<PlayerModel>,
            index: Int = 0,
            isPlayWhenReady: Boolean = true
        ) {
            //判断服务是否启动
            if (!isStartMusicService) {
                start(app)
            }
            MessageBus.post(
                SERVICE_PLAY_LIST, PlayModelBean(
                    models, index, isPlayWhenReady
                ), true, MusicService::class.java
            )
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private val musicReceiver = MusicReceiver()

    override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
        super.onPlay(playStatus, playerModel)
        notifyByReceiver(player.getCurrentModel())
    }

    override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
        super.onPause(playStatus, playerModel)
        notifyByReceiver(player.getCurrentModel())
    }

    override fun onError(throwable: Throwable, playerModel: PlayerModel?) {
        super.onError(throwable, playerModel)
        "播放出错:${playerModel.safeGet<LocalMusicFragment.SongModel>()?.song?.song}".toast()
        player.next()
    }

    /**
     * 通知栏按键广播
     */
    inner class MusicReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_PLAY -> {
                    player.play()
                }
                ACTION_PAUSE, AudioManager.ACTION_AUDIO_BECOMING_NOISY -> {
                    player.pause()
                }
                ACTION_NEXT -> {
                    player.play()
                    player.next()
                }
                ACTION_PRE -> {
                    player.play()
                    player.previous()
                }

            }
        }
    }

    /**
     * 发送通知栏
     */
    private fun notifyByReceiver(playerModel: PlayerModel?) {
        playerModel.safeGet<LocalMusicFragment.SongModel>()?.song?.apply {
            sendNotify(path, song, singer, 19, player.isPlaying())
        }
    }

    /**
     * messageBus数据类型
     */
    private data class PlayModelBean(
        val models: MutableList<PlayerModel>,
        val index: Int = 0,
        val isPlayWhenReady: Boolean = true
    )

    /**
     * 播放器对象
     */
    private val player by lazy {
        SimplePlayer(this).apply {
            setRepeatMode(SimplePlayer.LOOP_MODO_LIST)
        }
    }

    override fun onCreate() {
        super.onCreate()
        player.setMediaSession(registerSession())
        player.addPlayerListener(this)
        MessageBus.register(this)
        registerReceiver(musicReceiver, IntentFilter().apply {
            addAction(ACTION_PLAY)
            addAction(ACTION_PAUSE)
            addAction(ACTION_PRE)
            addAction(ACTION_NEXT)
            //耳机断开
            addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        })
        isStartMusicService = true
    }


    override fun onDestroy() {
        unregisterReceiver(musicReceiver)
        player.removePlayerListener(this)
        MessageBus.unRegister(this)
        isStartMusicService = false
        unregisterSession()
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
        if (SERVICE_PLAY_LIST == code) {
            event.isThis<PlayModelBean> {
                if (isPlayWhenReady) {
                    player.prepareAndPlay(models, index)
                } else {
                    player.prepare(models, index)
                }
            }
        }
    }

    override fun onPlayerModelChange(playerModel: PlayerModel) {
        super.onPlayerModelChange(playerModel)
        notifyByReceiver(playerModel)
    }

    override fun onTick(playerModel: PlayerModel) {

    }


}
