package com.yizisu.music.and.video.viewmodel

import com.yizisu.basemvvm.app
import com.yizisu.basemvvm.mvvm.mvvm_helper.start
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.utils.LocalMusicUtil

class LocalMusicViewModel : BaseViewModel() {
    /**
     * 查询本地音乐
     */
    fun queryLocalMusic() {
        AppData.localMusicData.start()
        launchThread {
            AppData.localMusicData.success(LocalMusicUtil.getMusicInfo(app))
        }
    }
}