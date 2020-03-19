package com.yizisu.music.and.video.viewmodel

import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.utils.LocalMusicUtil

class DbViewModel : BaseViewModel() {

    /**
     * 查询我喜欢的音乐
     */
    fun queryHeartList() {
        loadDbMusic(DbCons.ALBUM_ID_HEART, AppData.dbHeartAlbumData)
    }

    fun queryCurrentList() {
        loadDbMusic(DbCons.ALBUM_ID_CURRENT, AppData.dbCurrentAlbumData)
    }

    fun queryDownloadList() {
        launchThread {
            DbHelper.removeAllSongsFromAlbum(DbCons.ALBUM_ID_DOWNLOADED)
            val album = DbHelper.queryAlbumByDbId(DbCons.ALBUM_ID_DOWNLOADED) ?: return@launchThread
            val songs = DbHelper.queryAllSongIfFilePathNotEmpty()?.toMutableList()
            DbHelper.addSongToAlbum(songs, album)
            album.resetSongInfoTables()
            album.songInfoTables
            AppData.dbDownloadAlbumData.success(album)
        }
    }

    fun queryRecentPlayList() {
        loadDbMusic(DbCons.ALBUM_ID_RECENT, AppData.dbRecentAlbumData)
    }

    fun queryLocalList() {
        loadDbMusic(DbCons.ALBUM_ID_LOCAL, AppData.dbLocalAlbumData)
    }

    private fun loadDbMusic(id: Long, data: LiveBean<AlbumInfoTable>) {
        launchThread {
            val result = DbHelper.queryAlbumByDbId(id)
            if (result != null) {
                result.resetSongInfoTables()
                result.songInfoTables
                data.success(result)
            }
        }
    }
}