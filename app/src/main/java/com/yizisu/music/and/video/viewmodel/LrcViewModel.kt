package com.yizisu.music.and.video.viewmodel

import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.createOkHttpCall
import com.yizisu.music.and.video.baselib.base.sendHttp
import com.yizisu.music.and.video.bean.LrcBean

class LrcViewModel : BaseViewModel() {

    /**
     * 从歌词迷查询歌词
     */
    val lrcData = createLiveBean<LrcBean>()

    fun queryLrc(name: String?, singer: String?) {
        lrcData.tag = name
        name ?: return
        val url = if (singer == null) {
            "http://gecimi.com/api/lyric/${name}"
        } else {
            "http://gecimi.com/api/lyric/${name}/${singer}"
        }
        url.sendHttp(mutableMapOf(), true)
            .async(lrcData.createOkHttpCall())
    }
}