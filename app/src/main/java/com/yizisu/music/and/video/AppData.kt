package com.yizisu.music.and.video

import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.music.and.video.bean.LocalMusicBean

object AppData {
    //本地音乐
    val localMusicData by lazy { createLiveBean<MutableList<LocalMusicBean>>() }
}