package com.yizisu.music.and.video.cons

object BusCode {
    /**
     * 用服务播放音乐
     */
    const val SERVICE_PLAY_LIST = 0
    /**
     * 添加播放器监听
     */
    const val ADD_MUSIC_EVENT_LISTENER = 1
    /**
     * 移除播放器监听
     */
    const val REMOVE_MUSIC_EVENT_LISTENER = 2
    /**
     * 移动进度条的比例
     */
    const val SEEK_MUSIC_EVENT = 3

    /**
     * 传递编辑的歌曲
     */
    const val EDIT_SONG = 4

    /**
     * 传递歌单
     */
    const val ALBUM_INFO = 5

    /**
     * 需要刷新歌单
     */
    const val REFRESH_PLAY_LIST_DETAIL=6
}