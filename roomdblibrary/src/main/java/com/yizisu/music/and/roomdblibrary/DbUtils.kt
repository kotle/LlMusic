package com.yizisu.music.and.roomdblibrary

import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable

/**
 * 创建一个我喜欢的歌单
 */
private const val HEART_ALBUM_LOCAL_ID = 1

/**
 * 创建一个本地歌单
 */
fun createNewLocalAlbum(id: Long?, name: String) {
    DbHelper.insetOrUpdateAlbum(
        AlbumInfoTable(
            id,
            id,
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

/**
 * 初始化并且创建默认歌单
 */
internal fun checkAndInitDefaultAlbum() {
    createNewLocalAlbum(DbCons.ALBUM_ID_HEART, "我喜欢")
    createNewLocalAlbum(DbCons.ALBUM_ID_CURRENT, "当前播放")
    createNewLocalAlbum(DbCons.ALBUM_ID_LOCAL, "本地歌曲")
    createNewLocalAlbum(DbCons.ALBUM_ID_RECENT, "最近播放")
}