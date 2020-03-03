package com.yizisu.music.and.video

import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.LocalVideoBean

object AppData {
    //本地音乐
    val localMusicData by lazy { createLiveBean<MutableList<LocalMusicBean>>() }
    //本地视频
    val localVideoData by lazy { createLiveBean<MutableList<LocalMusicBean>>() }
}