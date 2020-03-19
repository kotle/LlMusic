package com.yizisu.music.and.roomdblibrary

import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable

/**
 * 创建一个本地歌单
 */
fun createNewLocalAlbum(id: Long, name: String) {
    DbHelper.insetOrUpdateAlbum(
        AlbumInfoTable(
            id,
            id.toString(),
            DbCons.SOURCE_DB,
            DbCons.TYPE_FREE,
            null,
            null,
            name,
            null,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
    )
}

fun createNormalAlbum(name: String, des: String?) {
    val album = AlbumInfoTable(
        null,
        DbCons.ALBUM_ID_NORMAL.toString(),
        DbCons.SOURCE_DB,
        DbCons.TYPE_FREE,
        null,
        null,
        name,
        des,
        System.currentTimeMillis(),
        System.currentTimeMillis()
    )
    DbHelper.insetAlbum(album)
}

/**
 * 初始化并且创建默认歌单
 */
internal fun checkAndInitDefaultAlbum() {
    Thread {
        createNewLocalAlbum(DbCons.ALBUM_ID_DOWNLOADED, "已下载")
        createNewLocalAlbum(DbCons.ALBUM_ID_HEART, "我喜欢")
        createNewLocalAlbum(DbCons.ALBUM_ID_CURRENT, "当前播放")
        createNewLocalAlbum(DbCons.ALBUM_ID_LOCAL, "本地歌曲")
        createNewLocalAlbum(DbCons.ALBUM_ID_RECENT, "最近播放")
    }.start()
}