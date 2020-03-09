package com.yizisu.music.and.video.viewmodel

import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.createOkHttpCall
import com.yizisu.music.and.video.baselib.base.sendHttp
import com.yizisu.music.and.video.bean.LrcBean
import com.yizisu.music.and.video.bean.netease.LrcNeteaseBean
import com.yizisu.music.and.video.net.netease.NETEAST_SONG_LRC
import com.yizisu.music.and.video.net.netease.sendNeteaseHttp

class LrcViewModel : BaseViewModel() {

    /**
     * 从歌词迷查询歌词
     */
    val lrcData = createLiveBean<LrcBean>()

    fun queryLrc(name: String?, singer: String?) {
        name ?: return
        if (lrcData.tag == name) {
            return
        }
        lrcData.tag = name
        val url = if (singer == null) {
            "http://gecimi.com/api/lyric/${name}"
        } else {
            "http://gecimi.com/api/lyric/${name}/${singer}"
        }
        url.sendHttp(mutableMapOf(), true)
            .async(lrcData.createOkHttpCall())
    }

    /**
     * 从网易云查询歌词
     */
    val lrcNeteaseData = createLiveBean<LrcNeteaseBean>()

    fun queryLrcNetease(songId: String) {
        NETEAST_SONG_LRC.sendNeteaseHttp(
            mutableMapOf(
                "id" to songId
            )
        ).async(
            lrcNeteaseData.createOkHttpCall()
        )
    }
}