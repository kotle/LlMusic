package com.yizisu.music.and.video.utils

import android.app.PendingIntent
import android.os.Environment
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.utils.DownFileWithNotification
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.module.fragment.search.SearchFragment
import com.yizisu.music.and.video.module.main.MainActivity
import java.util.*

class DownloadSongHelper(private val song: SongInfoTable) {
    companion object {
        private val audioType =
            listOf("MP3", "OGG", "WAV", "APE", "CDA", "AU", "MIDI", "MAC", "AAC", "FLAC")
        var notifyId = 1234
        private val downList = mutableListOf<SongInfoTable>()
    }

    private val downHelper = DownFileWithNotification()

    init {
        downHelper.apply {
            notifyTitle = "${song.name}-${song.des}"
            notifyContent = "正在下载"
            downloadCompleteListener = {
                if (!it?.savePath.isNullOrEmpty()) {
                    if (song.playFilePath.isNullOrEmpty()) {
                        song.playFilePath = it?.savePath
                        DbHelper.insetOrUpdateSong(song)
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
        val url = song.playUrlPath ?: return
        var nameType = url.split(".").last()
        if (nameType.isEmpty() || !audioType.contains(nameType.toUpperCase(Locale.getDefault()))) {
            nameType = "mp3"
        }
        downHelper.startDown(
            app,
            song.playUrlPath,
            notifyId++,
            null,
            null,
            "${song.name}-${song.des}.${nameType}",
            Environment.DIRECTORY_MUSIC,
            SearchFragment.titles[song.source]?.toString()
        )
    }
}