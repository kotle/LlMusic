package com.yizisu.music.and.roomdblibrary

import android.content.Context
import com.github.yuweiguocn.library.greendao.MigrationHelper
import com.greendao.gen.*
import com.yizisu.music.and.roomdblibrary.bean.*

object DbHelper {
    private var session: DaoSession? = null
    fun init(context: Context, dbName: String) {
        MigrationHelper.DEBUG = BuildConfig.DEBUG //如果你想查看日志信息，请将DEBUG设置为true
        val updateHelper = UpdateDbHelper(context, dbName, null)
        session = DaoMaster(updateHelper.writableDatabase).newSession()
        checkAndInitDefaultAlbum()
    }

    private val albumInfoTableDao by lazy { session?.albumInfoTableDao }
    private val songInfoTableDao by lazy { session?.songInfoTableDao }
    private val singerInfoTableDao by lazy { session?.singerInfoTableDao }
    private val songWithSingerDao by lazy { session?.songWithSingerDao }
    private val songWithAlbumDao by lazy { session?.songWithAlbumDao }


    /**************************************************************************************/


    fun queryAlbumByDbId(dbId: Long): AlbumInfoTable? {
        val dao = albumInfoTableDao ?: return null
        return dao.loadByRowId(dbId)
    }

    /**
     * 插入歌曲到歌单
     */
    fun addSongToAlbum(song: SongInfoTable, album: AlbumInfoTable) {
        insetOrUpdateSong(song)
        insetOrUpdateAlbum(album)
        //歌曲和歌单做关联
        withSongAndAlbum(song, album)
    }

    /**
     * 查询某个歌曲是否在歌单
     */
    fun isSongInAlbum(song: SongInfoTable, album: AlbumInfoTable): Boolean {
        val dao = songWithAlbumDao ?: return false
        song.dbId ?: return false
        album.dbId ?: return false
        //判断歌曲是否存在
        val old = dao.queryBuilder().where(
            SongWithAlbumDao.Properties.SongId.eq(song.dbId),
            SongWithAlbumDao.Properties.AlbumId.eq(album.dbId)
        ).unique()
        return old != null
    }

    /**
     * 插入歌曲到歌单
     */
    fun removeSongToAlbum(song: SongInfoTable, album: AlbumInfoTable) {
        val dao = songWithAlbumDao ?: return
        song.dbId ?: return
        album.dbId ?: return
        //判断歌曲是否存在
        val old = dao.queryBuilder().where(
            SongWithAlbumDao.Properties.SongId.eq(song.dbId),
            SongWithAlbumDao.Properties.AlbumId.eq(album.dbId)
        ).unique()
        if (old != null) {
            dao.delete(old)
        }
    }

    /**
     * 将歌曲从歌单移除
     */
    fun removeSongsFromAlbum(mutableList: MutableList<SongInfoTable>, albumDbId: Long) {
        val dao = songWithAlbumDao ?: return
        mutableList.forEach {
            dao.queryBuilder().where(
                SongWithAlbumDao.Properties.SongId.eq(it.dbId),
                SongWithAlbumDao.Properties.AlbumId.eq(albumDbId)
            ).unique()?.let {
                dao.delete(it)
            }

        }
    }


    /**
     * 通过dbId更新或者插入歌曲
     */
    fun insetOrUpdateSong(song: SongInfoTable): Long? {
        val dao = songInfoTableDao ?: return null
        //判断歌曲是否存在
        val old = if (song.dbId != null) {
            dao.loadByRowId(song.dbId)
        } else {
            dao.queryBuilder().where(
                SongInfoTableDao.Properties.Id.eq(song.id),
                SongInfoTableDao.Properties.Source.eq(song.source)
            ).unique()
        }
        old?.apply {
            song.createTime = createTime
            song.dbId = dbId
        }
        song.updateTime = System.currentTimeMillis()
        return dao.insertOrReplace(song)
    }

    /**
     * 通过dbId更新或者插入歌单
     */
    fun insetOrUpdateAlbum(album: AlbumInfoTable): Long? {
        val dao = albumInfoTableDao ?: return null
        //判断歌曲是否存在
        val old = if (album.dbId != null) {
            dao.loadByRowId(album.dbId)
        } else {
            dao.queryBuilder().where(
                AlbumInfoTableDao.Properties.Id.eq(album.id),
                AlbumInfoTableDao.Properties.Source.eq(album.source)
            ).unique()
        }
        old?.apply {
            album.createTime = createTime
            album.dbId = dbId
        }
        album.updateTime = System.currentTimeMillis()
        return dao.insertOrReplace(album)
    }

    /**
     * 通过歌曲id和歌单id，插入关联
     * 如果存在不更新
     */
    fun withSongAndAlbum(song: SongInfoTable, album: AlbumInfoTable): Long? {
        val dao = songWithAlbumDao ?: return null
        song.dbId ?: return null
        album.dbId ?: return null
        //判断歌曲是否存在
        val old = dao.queryBuilder().where(
            SongWithAlbumDao.Properties.SongId.eq(song.dbId),
            SongWithAlbumDao.Properties.AlbumId.eq(album.dbId)
        ).unique()
        if (old != null) {
            return old.id
        }
        val time = System.currentTimeMillis()
        return dao.insert(SongWithAlbum(null, album.dbId, song.dbId, time, time))
    }

    /**
     * 通过歌曲id和歌单id，插入关联
     * 如果存在不更新
     */
    fun withSongAndSinger(song: SongInfoTable, singer: SingerInfoTable): Long? {
        val dao = songWithSingerDao ?: return null
        song.dbId ?: return null
        singer.dbId ?: return null
        //判断歌曲是否存在
        val old = dao.queryBuilder().where(
            SongWithSingerDao.Properties.SongId.eq(song.dbId),
            SongWithSingerDao.Properties.SingerId.eq(singer.dbId)
        ).unique()
        if (old != null) {
            return old.id
        }
        val time = System.currentTimeMillis()
        return dao.insert(SongWithSinger(null, singer.dbId, song.dbId, time, time))
    }
}