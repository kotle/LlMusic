package com.yizisu.music.and.video.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.media.RingtoneManager
import android.os.Build
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.DownloadHelper
import com.yizisu.basemvvm.utils.getResString
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.down_task.DownLoadTaskActivity
import com.yizisu.music.and.video.module.fragment.search.SearchFragment
import java.util.*

object DownSongWithNotification {
    private var NOTIFICATION_ID = 5566
    private val audioType =
        listOf("MP3", "OGG", "WAV", "APE", "CDA", "AU", "MIDI", "MAC", "AAC", "FLAC")
    private val notificationManager by lazy {
        app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    data class DownSongBean(
        val songModel: SongModel?,
        val song: SongInfoTable,
        val albumInfo: AlbumInfoTable?,
        val downloadHelper: DownloadHelper,
        var listener: DownloadHelper.DownListener? = null
    )


    val downloadingList = mutableListOf<DownSongBean>()
    fun startDown(
        appCompatActivity: AppCompatActivity?,
        songModel: SongModel?, albumId: Long?
    ) {
        val song = songModel?.song ?: return
        selectPlayList(appCompatActivity, song, albumId) {
            getDownloadUrl(songModel, song, it)
        }
    }

    /**
     * 获取下载链接
     */
    private fun getDownloadUrl(songModel: SongModel?, song: SongInfoTable, albumId: Long?) {
        songModel?.callMediaUri { uri, throwable, b ->
            if (uri != null) {
                val url = uri.toString()
                val downloadAlbum =
                    if (albumId == null) null else DbHelper.queryAlbumByDbId(albumId)
                var nameType = url.split(".").last()
                if (nameType.isEmpty() || !audioType.contains(
                        nameType.toUpperCase(
                            Locale.getDefault()
                        )
                    )
                ) {
                    nameType = "mp3"
                }
                val downTask = DownloadHelper().apply {
                    setOverlayFile(false)
                    setPathType(
                        Environment.DIRECTORY_MUSIC,
                        SearchFragment.titles[song.source]?.toString()
                    )
                }
                val bean = DownSongBean(songModel, song, downloadAlbum, downTask, null)
                "开始下载 《${song.name}》到《${downloadAlbum?.title}》".toast()
                downTask.start(
                    url, "${song.name}-${song.des}.${nameType}",
                    createCall(bean)
                )
            } else {
                "获取《${song.name}》下载地址出错".toast()
            }
        }
    }

    fun cancel(bean: DownSongBean) {
        bean.downloadHelper.cancel()
    }

    /**
     * 下载结束
     */
    private fun downFinish(bean: DownSongBean) {
        if (downloadingList.contains(bean)) {
            bean.downloadHelper.cancel()
            downloadingList.remove(bean)
            refreshNotify()
        }
    }


    /**
     * 创建一个下载回调
     */
    private fun createCall(
        bean: DownSongBean
    ): DownloadSongListener {
        val song = bean.song
        val downloadAlbum = bean.albumInfo
        val downloadHelper = bean.downloadHelper
        downloadingList.add(bean)
        refreshNotify()
        return object : DownloadSongListener(bean) {
            override fun onComplete(result: DownloadHelper.DownloadBean?) {
                super.onComplete(result)
                if (!result?.savePath.isNullOrEmpty()) {
                    if (song.playFilePath.isNullOrEmpty()) {
                        song.playFilePath = result?.savePath
                        DbHelper.insetOrUpdateSong(song)
                        DbHelper.addDownloadSongToAlbum(
                            song,
                            AppData.dbDownloadAlbumData.data?.dbId
                        )
                        dbViewModel.queryDownloadList()
                        //现在完成通知更新一下
                        AppData.currentPlaySong.apply {
                            if (song == data?.song) {
                                data?.let {
                                    success(it)
                                }
                            }
                        }
                    }
                    if (result?.isNewDownloadFile == true) {
                        "${song.name}-${song.des} 已经在《${downloadAlbum?.title}》下载完成".toast()
                    } else {
                        "${song.name}-${song.des} 已经在《${downloadAlbum?.title}》下载，无需重复下载".toast()
                    }
                }
                downFinish(bean)
            }

            override fun onCancelled(result: DownloadHelper.DownloadBean?) {
                super.onCancelled(result)
                downFinish(bean)
                "下载《${song.name}》取消".toast()
                downloadHelper.deleteDownloadFile()
            }

            override fun onError(error: Throwable) {
                super.onError(error)
                downFinish(bean)
                "下载《${song.name}》出错:${error.message}".toast()
                downloadHelper.deleteDownloadFile()
            }

            /**
             * 在子线程接受进度回调
             * 主线程频繁更新进度会导致卡死
             */
            override fun onProgressUpdateInIoThread(
                downloadSize: Long,
                totalSize: Long,
                progress: Long
            ) {
                super.onProgressUpdateInIoThread(downloadSize, totalSize, progress)
                sendNotify(progress.toInt())
            }
        }
    }

    /**
     * 选择下载的歌单
     */
    private fun selectPlayList(
        appCompatActivity: AppCompatActivity?,
        song: SongInfoTable,
        albumId: Long?,
        start: Function1<Long?, Unit>
    ) {
        var downloadAlbumId = albumId
        if (downloadAlbumId == DbCons.ALBUM_ID_CURRENT) {
            downloadAlbumId = AppData.currentPlayListByAlbumId
        }
        val id = downloadAlbumId
        if (id == null ||
            id < 0 ||
            id == DbCons.ALBUM_ID_CURRENT ||
            id == DbCons.ALBUM_ID_LOCAL ||
            id == DbCons.ALBUM_ID_DOWNLOADED ||
            id == DbCons.ALBUM_ID_RECENT
        ) {
            SelectPlayListDialog.show(appCompatActivity, null) {
                downloadAlbumId = it.dbId
                DbHelper.addDownloadSongToAlbum(song, downloadAlbumId)
                if (downloadAlbumId == DbCons.ALBUM_ID_HEART) {
                    dbViewModel.queryHeartList()
                }
                start.invoke(downloadAlbumId)
            }
        } else {
            downloadAlbumId = id
            DbHelper.addDownloadSongToAlbum(song, downloadAlbumId)
            start.invoke(downloadAlbumId)
        }
    }


    private fun refreshNotify() {
        when (downloadingList.count()) {
            0 -> {
                clearNotify()
            }
            else -> {
                sendNotify(0)
            }
        }
    }

    /**
     * 清除通知
     */
    private fun clearNotify() {
        val id = NOTIFICATION_ID
        notificationManager.cancel(id)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(id.toString())
        }
    }

    @Synchronized
    private fun sendNotify(progress: Int): NotificationCompat.Builder? {
        var isNeedProgress = false
        val title = if (downloadingList.count() == 1) {
            val song = downloadingList.first().song
            isNeedProgress = true
            "${song.name}-${song.des}"
        } else "当前总下载数：${downloadingList.count()}"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(app, NOTIFICATION_ID.toString())
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle(title)
            //使用默认的声音、振动、闪光
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentText("正在下载中...")
//            .setLargeIcon((ContextCompat.getDrawable(context!!, iconRes) as BitmapDrawable).bitmap)
            .setAutoCancel(false)
            .setSound(defaultSoundUri)
            .setOngoing(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    app,
                    100,
                    Intent(app, DownLoadTaskActivity::class.java),
                    0
                )
            )
            .setShowWhen(true)
            .setTicker(title)//在状态栏短暂显示的文字
        if (isNeedProgress) {
            notificationBuilder.setProgress(100, progress, false)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_ID.toString(),
                title,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(
            javaClass.simpleName,
            NOTIFICATION_ID,
            notificationBuilder.build()
        )
        return notificationBuilder
    }

    open class DownloadSongListener(val bean: DownSongBean) :
        DownloadHelper.DownListener() {

        override fun onCancelled() {
            super.onCancelled()
            bean.listener?.onCancelled()

        }

        override fun onComplete(result: DownloadHelper.DownloadBean?) {
            super.onComplete(result)
            bean.listener?.onComplete(result)
        }

        override fun onError(error: Throwable) {
            super.onError(error)
            bean.listener?.onCancelled()

        }

        override fun onCancelled(result: DownloadHelper.DownloadBean?) {
            super.onCancelled(result)
            bean.listener?.onCancelled()
        }

        override fun onProgressUpdate(downloadSize: Long, totalSize: Long, progress: Long) {
            super.onProgressUpdate(downloadSize, totalSize, progress)
            bean.listener?.onProgressUpdate(downloadSize, totalSize, progress)
        }
    }
}