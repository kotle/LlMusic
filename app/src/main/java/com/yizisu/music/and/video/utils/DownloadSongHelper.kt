package com.yizisu.music.and.video.utils

import android.app.PendingIntent
import android.os.Environment
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.DownFileWithNotification
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.fragment.search.SearchFragment
import com.yizisu.music.and.video.module.main.MainActivity
import java.util.*

class DownloadSongHelper(private val songModel: SongModel?) {
    companion object {
        private val audioType =
            listOf("MP3", "OGG", "WAV", "APE", "CDA", "AU", "MIDI", "MAC", "AAC", "FLAC")
        var notifyId = 1234
        private val downList = mutableListOf<SongInfoTable>()
    }

    private val downHelper = DownFileWithNotification()

    init {
        downHelper.apply {
            val song = songModel?.song ?: return@apply
            notifyTitle = "${song.name}-${song.des}"
            notifyContent = "正在下载"
            downloadCompleteListener = {
                if (!it?.savePath.isNullOrEmpty()) {
                    if (song.playFilePath.isNullOrEmpty()) {
                        song.playFilePath = it?.savePath
                        DbHelper.insetOrUpdateSong(song)
                        DbHelper.addSongToAlbum(song, AppData.dbDownloadAlbumData.data)
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

    fun startDown() {
        val song = songModel?.song ?: return
        songModel.callMediaUri { uri, throwable, b ->
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