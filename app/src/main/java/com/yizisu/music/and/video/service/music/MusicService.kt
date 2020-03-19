package com.yizisu.music.and.video.service.music

import android.app.Activity
import android.app.NotificationManager
import android.app.Service
import android.content.*
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.yizisu.basemvvm.activityList
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBusInterface
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.cons.BusCode
import com.yizisu.music.and.video.cons.BusCode.ADD_MUSIC_EVENT_LISTENER
import com.yizisu.music.and.video.cons.BusCode.REMOVE_MUSIC_EVENT_LISTENER
import com.yizisu.music.and.video.cons.BusCode.SEEK_MUSIC_EVENT
import com.yizisu.music.and.video.cons.BusCode.SERVICE_PLAY_LIST
import com.yizisu.music.and.video.utils.*
import com.yizisu.playerlibrary.SimplePlayer
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener
import kotlin.system.exitProcess

class MusicService : Service(), MessageBusInterface, SimplePlayerListener<SongModel> {
    /**
     * messageBus数据类型
     */
    private data class PlayModelBean(
        val models: MutableList<SongModel>,
        val index: Int = 0,
        var isNewList: Boolean,
        val isPlayWhenReady: Boolean? = true
    )

    companion object {
        private var isStartMusicService = false
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PRE = "ACTION_PRE"
        const val ACTION_CLOSE = "ACTION_CLOSE"
        private const val NOTIFY_MUSIC_ID = 19
        private const val BITMAP_WIDTH_HEIGHT = 300
        //启动服务
        fun start(context: Context) {
            context.startService(Intent(context, MusicService::class.java))
        }

        //绑定服务
        fun bindService(activity: Activity) {
            activity.bindService(
                Intent(activity, MusicService::class.java),
                MusicServiceConnection, Context.BIND_AUTO_CREATE
            )
        }

        //解绑服务
        fun unBindService(activity: Activity) {
            activity.unbindService(MusicServiceConnection)
        }

        //开始播放
        fun startPlay(
            models: MutableList<SongModel>,
            index: Int = 0,
            isNewList: Boolean,
            isPlayWhenReady: Boolean? = true
        ) {
            //判断服务是否启动
            MessageBus.post(
                SERVICE_PLAY_LIST, PlayModelBean(
                    models, index, isNewList, isPlayWhenReady
                ), true, MusicService::class.java
            )
        }

        //添加监听
        fun addMusicEventListener(listener: MusicEventListener) {
            //判断服务是否启动
            MessageBus.post(
                ADD_MUSIC_EVENT_LISTENER, listener, true, MusicService::class.java
            )
        }

        //移除监听
        fun removeMusicEventListener(listener: MusicEventListener) {
            MessageBus.post(
                REMOVE_MUSIC_EVENT_LISTENER, listener, true, MusicService::class.java
            )
        }

        //发送广播
        fun sendBroadcastReceiver(context: Context?, action: String) {
            context?.sendBroadcast(Intent(action))
        }

        //移动进度条
        fun seekRatio(float: Float) {
            MessageBus.post(
                SEEK_MUSIC_EVENT, float, false, MusicService::class.java
            )
        }

        //移动进度条
        fun seekToMs(float: Long) {
            MessageBus.post(
                SEEK_MUSIC_EVENT, float, false, MusicService::class.java
            )
        }
    }

    //MediaSessionCompat
    private val session by lazy { MediaSessionCompat(app, "MediaSessionCompat") }

    //通知栏按钮接收广播
    private val musicReceiver by lazy { MusicReceiver() }

    //播放器对象
    private val player by lazy {
        SimplePlayer<SongModel>(this).apply {
            setRepeatMode(SimplePlayer.LOOP_MODO_LIST)
            setHandleWakeLock(true)
            setAudioForceEnable(true)
        }
    }
    //默认通知栏图标
    private val defaultBigIcon by lazy {
        getResDrawable(R.drawable.default_cover_icon)?.toBitmap(
            BITMAP_WIDTH_HEIGHT,
            BITMAP_WIDTH_HEIGHT,
            Bitmap.Config.RGB_565
        )
    }
    private var lastBigIconBitmap: Bitmap? = null

    private val musicEventListener = mutableListOf<MusicEventListener>()
    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onPlay(playStatus: Boolean, playerModel: SongModel?) {
        super.onPlay(playStatus, playerModel)
        notifyByReceiver(player.getCurrentModel())
    }

    override fun onPause(playStatus: Boolean, playerModel: SongModel?) {
        super.onPause(playStatus, playerModel)
        if (!playStatus) {
            notifyByReceiver(player.getCurrentModel())
        }
    }

    override fun onError(throwable: Throwable, playerModel: SongModel?) {
        super.onError(throwable, playerModel)
        "播放出错:${playerModel.safeGet<SongModel>()?.song?.name}".toast()
        playerModel?.song?.apply {
            //播放出错，清空播放链接
            if (!playUrlPath.isNullOrEmpty() || !playFilePath.isNullOrEmpty()) {
                playFilePath = null
                playUrlPath = null
                DbHelper.insetOrUpdateSong(this)
                AppData.currentPlaySong.success(playerModel)
            }
        }
//        player.next()
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
                    clear()
                }
            }
        }
    }

    private fun closeApp() {
        stopForeground(true)
        notificationManager.cancel(NOTIFY_MUSIC_ID)
        stopSelf()
        finishAllActivity(true)
    }

    /**
     * 发送通知栏
     */
    private fun notifyByReceiver(playerModel: PlayerModel?) {
        tryError {
            playerModel.safeGet<SongModel>()?.song?.apply {
                sendNotify(
                    notificationManager,
                    lastBigIconBitmap ?: defaultBigIcon,
                    name,
                    des,
                    NOTIFY_MUSIC_ID,
                    player.isPlaying(),
                    session
                )
            }
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
                        putString(MediaMetadataCompat.METADATA_KEY_TITLE, name)
                        putString(MediaMetadataCompat.METADATA_KEY_ARTIST, des)
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
        super.onDestroy()
        clear()
    }

    private fun clear() {
        musicEventListener.clear()
        unregisterReceiver(musicReceiver)
        player.removePlayerListener(this)
        MessageBus.unRegister(this)
        isStartMusicService = false
        session.unregisterSession()
        player.onDestroy()
        closeApp()
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        clear()
        return true
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
                    if (!isNewList && player.getAllPlayModel().isNotEmpty()) {
                        player.seekTo(0, index)
                        player.play()
                    } else {
                        if (isPlayWhenReady ?: player.isPlaying()) {
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
            SEEK_MUSIC_EVENT -> {
                event.isThis<Float> {
                    player.seekRatioTo(this)
                }
                event.isThis<Long> {
                    player.seekTo(this)
                }
            }
            //歌单发生了增删改查
            /*       BusCode.REFRESH_PLAY_LIST_DETAIL -> {
                       event.safeGet<AlbumInfoTable>()?.apply {
                           if (dbId == DbCons.ALBUM_ID_CURRENT) {
                               launchThread {
                                   DbHelper.queryAlbumByDbId(dbId)?.apply {
                                       resetSongInfoTables()
                                       val plays = player.getAllPlayModel().map { SongModel(it) }
                                           .toMutableList()
                                       val index = if (AppData.currentPlayIndex < plays.size) {
                                           AppData.currentPlayIndex
                                       } else {
                                           0
                                       }
                                       if (player.isPlaying()) {
                                           player.prepareAndPlay(plays, index)
                                       } else {
                                           player.prepare(plays, index)
                                       }
                                   }
                               }
                           }
                       }
                   }*/
        }
    }

    override fun onPlayerModelChange(playerModel: SongModel) {
        super.onPlayerModelChange(playerModel)
        AppData.currentPlayIndex = player.getCurrentPlayIndex()
        playerModel.isThis<SongModel> {
            AppData.currentPlaySong.success(this)
            //保存到最近播放列表
            saveToRecentDb()
            //下载封面图片
            val url = song.coverFilePath ?: song.coverUrlPath
            if (url == null) {
                lastBigIconBitmap?.recycle()
                lastBigIconBitmap = null
                notifyByReceiver(playerModel)
            } else {
                Glide.with(this@MusicService)
                    .asDrawable()
                    .load(url)
                    .addListener(SimpleGlideLoadListener<Drawable> { drawable, error ->
                        lastBigIconBitmap?.recycle()
                        lastBigIconBitmap = if (drawable == null || error != null) {
                            null
                        } else {
                            drawable.toBitmap(
                                BITMAP_WIDTH_HEIGHT,
                                BITMAP_WIDTH_HEIGHT,
                                Bitmap.Config.RGB_565
                            )
                        }
                        notifyByReceiver(playerModel)
                    })
                    .submit(BITMAP_WIDTH_HEIGHT, BITMAP_WIDTH_HEIGHT)
            }

        }
    }

    override fun onPlayerListChange(playerModels: MutableList<SongModel>) {
        super.onPlayerListChange(playerModels)
        val newList = mutableListOf<SongInfoTable>()
        playerModels.forEach {
            it.isThis<SongModel> {
                newList.add(song)
            }
        }
        repelaceCurrentList(newList)
    }

    override fun onTick(playerModel: SongModel) {

    }


}
