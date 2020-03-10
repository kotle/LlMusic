package com.yizisu.music.and.video.viewmodel

import android.content.Context
import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.start
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.createOkHttpCall
import com.yizisu.music.and.video.baselib.base.sendHttp
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.TestVideoBean
import com.yizisu.music.and.video.utils.LocalMusicUtil

class LocalMusicViewModel : BaseViewModel() {
    /**
     * 查询本地音乐
     */
    val localMusicData by lazy { createLiveBean<MutableList<LocalMusicBean>>() }

    fun queryLocalMusic() {
        localMusicData.start()
        launchThread {
            localMusicData.success(LocalMusicUtil.getMusicInfo(app))
        }
    }

    fun queryLocalVideo() {
        localMusicData.start()
        launchThread {
            //            localVideoData.success(LocalMusicUtil.getVideoInfo(app))
        }
    }

    val testVideoData = createLiveBean<TestVideoBean>()
    fun queryTestVideo() {
        " http://api.m.mtime.cn/PageSubArea/TrailerList.api"
            .sendHttp(mutableMapOf(), true)
            .async(testVideoData.createOkHttpCall())
    }
}