package com.yizisu.music.and.video.service.music

import android.R
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import com.yizisu.basemvvm.app


class MusicService2 : MediaBrowserServiceCompat() {
    /**
     * 浏览服务
     */
    private val browser by lazy {
        MediaBrowserCompat(
            this,
            ComponentName(this, MusicService2::class.java),//绑定浏览器服务
            browserCall,
            Bundle()
        )
    }
    private val browserCall = object : MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            super.onConnected()
            connectBrowser()
            MediaControllerCompat(this@MusicService2,session).apply {
                registerCallback(
                    object :MediaControllerCompat.Callback(){
                        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
                            super.onPlaybackStateChanged(state)
                        }

                        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
                            super.onMetadataChanged(metadata)
                        }
                    }
                )
            }
        }

        override fun onConnectionSuspended() {
            super.onConnectionSuspended()
        }

        override fun onConnectionFailed() {
            super.onConnectionFailed()
        }
    }

    private fun connectBrowser() {
        browser.subscribe(browser.root, object : MediaBrowserCompat.SubscriptionCallback() {
            override fun onChildrenLoaded(
                parentId: String,
                children: MutableList<MediaBrowserCompat.MediaItem>
            ) {
                super.onChildrenLoaded(parentId, children)
            }
        })
    }

    /****************************************************************************************/

    private val session by lazy {
        MediaSessionCompat(app, "MediaSessionCompat").apply {
            setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
            )
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onMediaButtonEvent(mediaButtonEvent: Intent?): Boolean {
                    return super.onMediaButtonEvent(mediaButtonEvent)
                }

                override fun onPlay() {
                    super.onPlay()
                }

                override fun onPlayFromUri(uri: Uri?, extras: Bundle?) {
                    super.onPlayFromUri(uri, extras)
                }

                override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {
                    super.onPlayFromMediaId(mediaId, extras)
                }
            })
            setPlaybackState(
                PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                    .build()
            )
            isActive = true
        }
    }

    /****************************************************************************************/
    override fun onCreate() {
        super.onCreate()
        browser.connect()
        //将token赋值给服务
        this@MusicService2.sessionToken = session.sessionToken
    }


    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {

        //将信息从当前线程中移除，允许后续调用sendResult方法
        //将信息从当前线程中移除，允许后续调用sendResult方法
        result.detach()

        //我们模拟获取数据的过程，真实情况应该是异步从网络或本地读取数据
        //我们模拟获取数据的过程，真实情况应该是异步从网络或本地读取数据
        val metadata =
            MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, "什么元数据id")
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, "圣诞歌")
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "WEIJIALE")
                .build()
        val mediaItems: ArrayList<MediaBrowserCompat.MediaItem> =
            ArrayList()
        mediaItems.add(createMediaItem(metadata))

        //向Browser发送数据
        //向Browser发送数据
        result.sendResult(mediaItems)
    }

    private fun createMediaItem(metadata: MediaMetadataCompat): MediaBrowserCompat.MediaItem {
        return MediaBrowserCompat.MediaItem(
            metadata.description,
            MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
        )
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        return BrowserRoot("666", Bundle())
    }
}