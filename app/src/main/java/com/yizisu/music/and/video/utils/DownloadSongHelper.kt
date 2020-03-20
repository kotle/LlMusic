package com.yizisu.music.and.video.utils

import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.DownFileWithNotification
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.fragment.search.SearchFragment
import java.util.*

class DownloadSongHelper(
    private val appCompatActivity: AppCompatActivity?,
    private val songModel: SongModel?, albumId: Long?
) {
    companion object {
        private val audioType =
            listOf("MP3", "OGG", "WAV", "APE", "CDA", "AU", "MIDI", "MAC", "AAC", "FLAC")
        var notifyId = 1234
    }

    private val downHelper = DownFileWithNotification()
    private var downloadAlbumId = albumId

    init {
        downHelper.apply {
            val song = songModel?.song ?: return@apply
            notifyTitle = "${song.name}-${song.des}"
            notifyContent = "正在下载"
            iconRes = R.mipmap.logo
            downloadCompleteListener = {
                if (!it?.savePath.isNullOrEmpty()) {
                    if (song.playFilePath.isNullOrEmpty()) {
                        song.playFilePath = it?.savePath
                        DbHelper.insetOrUpdateSong(song)
                        DbHelper.addDownloadSongToAlbum(
                            song,
                            AppData.dbDownloadAlbumData.data?.dbId
                        )
                        dbViewModel.queryDownloadList()
                        //现在完成通知更新一下
                        AppData.currentPlaySong.apply {
                            if (songModel.song == data?.song) {
                                data?.let {
                                    success(it)
                                }
                            }
                        }
                    }
                    if (it?.isNewDownloadFile == true) {
                        "${song.name}-${song.des} 已经下载完成".toast()
                    } else {
                        "${song.name}-${song.des} 已经下载，无需重复下载".toast()
                    }
                }
            }
        }
    }

    private fun selectPlayList(song: SongInfoTable, start: Function0<Unit>) {
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
                start.invoke()
            }
        } else {
            downloadAlbumId = id
            DbHelper.addDownloadSongToAlbum(song, downloadAlbumId)
            start.invoke()
        }
    }

    fun startDown() {
        val song = songModel?.song ?: return
        selectPlayList(song) {
            getDownloadUrl(song)
        }
    }

    private fun getDownloadUrl(song: SongInfoTable) {
        songModel?.callMediaUri { uri, throwable, b ->
            if (uri != null) {
                val url = uri.toString()
                var nameType = url.split(".").last()
                if (nameType.isEmpty() || !audioType.contains(nameType.toUpperCase(Locale.getDefault()))) {
                    nameType = "mp3"
                }
                downHelper.startDown(
                    app,
                    url,
                    notifyId++,
                    null,
                    null,
                    "${song.name}-${song.des}.${nameType}",
                    Environment.DIRECTORY_MUSIC,
                    SearchFragment.titles[song.source]?.toString()
                )
                "开始下载 《${song.name}》".toast()
            } else {
                "获取《${song.name}》下载地址出错".toast()
            }
        }
    }
}