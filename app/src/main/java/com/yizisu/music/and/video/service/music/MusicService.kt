package com.yizisu.music.and.video.service.music

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBusInterface
import com.yizisu.basemvvm.utils.isThis
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.cons.BusCode.ADD_MUSIC_EVENT_LISTENER
import com.yizisu.music.and.video.cons.BusCode.REMOVE_MUSIC_EVENT_LISTENER
import com.yizisu.music.and.video.cons.BusCode.SERVICE_PLAY_LIST
import com.yizisu.music.and.video.utils.registerSession
import com.yizisu.music.and.video.utils.sendNotify
import com.yizisu.music.and.video.utils.unregisterSession
import com.yizisu.playerlibrary.SimplePlayer
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener
import kotlin.system.exitProcess

class MusicService : Service(), MessageBusInterface, SimplePlayerListener {
    /**
     * messageBus数据类型
     */
    private data class PlayModelBean(
        val models: MutableList<PlayerModel>,
        val index: Int = 0,
        var isNewList: Boolean,
        val isPlayWhenReady: Boolean = true
    )

    companion object {
        private var isStartMusicService = false
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PRE = "ACTION_PRE"
        const val ACTION_CLOSE = "ACTION_CLOSE"
        fun start(context: Context) {
            context.startService(Intent(context, MusicService::class.java))
        }

        fun startPlay(
            models: MutableList<PlayerModel>,
            index: Int = 0,
            isNewList: Boolean,
            isPlayWhenReady: Boolean = true
        ) {
            //判断服务是否启动
            MessageBus.post(
                SERVICE_PLAY_LIST, PlayModelBean(
                    models, index, isNewList, isPlayWhenReady
                ), true, MusicService::class.java
            )
        }

        fun addMusicEventListener(listener: MusicEventListener) {
            //判断服务是否启动
            MessageBus.post(
                ADD_MUSIC_EVENT_LISTENER, listener, true, MusicService::class.java
            )
        }

        fun removeMusicEventListener(listener: MusicEventListener) {
            MessageBus.post(
                REMOVE_MUSIC_EVENT_LISTENER, listener, true, MusicService::class.java
            )
        }

        /**
         * 发送广播
         */
        fun sendBroadcastReceiver(context: Context?, action: String) {
            context?.sendBroadcast(Intent(action))
        }
    }

    //MediaSessionCompat
    private val session by lazy { MediaSessionCompat(app, "MediaSessionCompat") }

    //通知栏按钮接收广播
    private val musicReceiver by lazy { MusicReceiver() }

    //播放器对象
    private val player by lazy {
        SimplePlayer(this).apply {
            setAudioForceEnable(true)
            setRepeatMode(SimplePlayer.LOOP_MODO_LIST)
        }
    }

    private val musicEventListener = mutableListOf<MusicEventListener>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
        super.onPlay(playStatus, playerModel)
        notifyByReceiver(player.getCurrentModel())
        player.setHandleWakeLock(true)
    }

    override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
        super.onPause(playStatus, playerModel)
        notifyByReceiver(player.getCurrentModel())
        player.setHandleWakeLock(false)
    }

    override fun onError(throwable: Throwable, playerModel: PlayerModel?) {
        super.onError(throwable, playerModel)
        "播放出错:${playerModel.safeGet<SongModel>()?.song?.title}".toast()
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
                ACTION_CLOSE -> {
                    stopForeground(true)
                    stopSelf()
                    exitProcess(0)
                }

            }
        }
    }

    /**
     * 发送通知栏
     */
    private fun notifyByReceiver(playerModel: PlayerModel?) {
        playerModel.safeGet<SongModel>()?.song?.apply {
            sendNotify(path, title, singer, 19, player.isPlaying(), session)
        }
    }

    override fun onCreate() {
        super.onCreate()
        session.registerSession()
        player.setMediaSession(session) {
            //返回息屏状态下的文字
            return@setMediaSession MediaDescriptionCompat.Builder()
                .setExtras(Bundle().apply {
                    it.safeGet<SongModel>()?.song?.apply {
                        putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                        putString(MediaMetadataCompat.METADATA_KEY_ARTIST, singer)
                    }
                })
                .build()
        }
        player.addPlayerListener(this)
        MessageBus.register(this)
        registerReceiver(musicReceiver, IntentFilter().apply {
            addAction(ACTION_PLAY)
            addAction(ACTION_PAUSE)
            addAction(ACTION_PRE)
            addAction(ACTION_NEXT)
            addAction(ACTION_CLOSE)
            //耳机断开
            addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        })
        isStartMusicService = true
    }


    override fun onDestroy() {
        musicEventListener.clear()
        unregisterReceiver(musicReceiver)
        player.removePlayerListener(this)
        MessageBus.unRegister(this)
        isStartMusicService = false
        session.unregisterSession()
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
        MessageBus.clearStickyValue(event)
        when (code) {
            //播放列表改变
            SERVICE_PLAY_LIST -> {
                event.isThis<PlayModelBean> {
                    if (!isNewList) {
                        player.seekTo(0, index)
                        player.play()
                    } else {
                        if (isPlayWhenReady) {
                            player.prepareAndPlay(models, index)
                        } else {
                            player.prepare(models, index)
                        }
                    }
                }
            }
            //添加监听
            ADD_MUSIC_EVENT_LISTENER -> {
                if (event is MusicEventListener) {
                    if (!musicEventListener.contains(event)) {
                        musicEventListener.add(event)
                        player.addPlayerListener(event)
                        player.getCurrentModel()?.apply {
                            event.onTick(this)
                            event.onPlayerModelChange(this)
                            event.onPlayerListChange(player.getAllPlayModel())
                            if (player.isPlaying()) {
                                event.onPlay(true, this)
                            } else {
                                event.onPause(false, this)
                            }
                        }
                    }
                }
            }
            //移除监听
            REMOVE_MUSIC_EVENT_LISTENER -> {
                if (event is MusicEventListener) {
                    musicEventListener.remove(event)
                    player.removePlayerListener(event)
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
