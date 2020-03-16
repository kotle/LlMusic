package com.yizisu.music.and.video.viewmodel

import android.net.Uri
import android.text.TextUtils
import android.webkit.URLUtil
import androidx.core.text.TextUtilsCompat
import androidx.core.text.isDigitsOnly
import com.google.android.exoplayer2.util.UriUtil
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.fail
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.createOkHttpCall
import com.yizisu.music.and.video.bean.netease.PlayListNeteaseBean
import com.yizisu.music.and.video.net.netease.NETEAST_PLAY_LIST_DETAIL
import com.yizisu.music.and.video.net.netease.sendNeteaseHttp

class PlayListViewModel : BaseViewModel() {

    /**
     * 获取网易云歌单详情
     */
    val neteasePlayListData = createLiveBean<PlayListNeteaseBean>()

    fun netneasePlayListDetail(id: String?) {
        id ?: return
        if (TextUtils.isDigitsOnly(id) && id.isNotBlank()) {
            startRequest(id)
            return
        }
        val ids = id.split("/")
        var isFind = false
        ids.forEach {
            if (!isFind) {
                if (TextUtils.isDigitsOnly(it) && it.isNotBlank()) {
                    isFind = true
                    startRequest(it)
                }
            }
        }
    }

    private fun startRequest(id: String) {
        NETEAST_PLAY_LIST_DETAIL.sendNeteaseHttp(
            mutableMapOf("id" to id)
        ).async(neteasePlayListData.createOkHttpCall())
    }

}