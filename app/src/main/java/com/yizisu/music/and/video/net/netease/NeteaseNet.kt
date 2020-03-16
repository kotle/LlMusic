package com.yizisu.music.and.video.net.netease

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import okhttp3.Request

private const val API_BASE_URL = "http://music.163.com/api/"
const val NETEAST_SEARCH = "search/pc"
const val NETEAST_SONG_INFO = "song"
const val NETEAST_SONG_LRC = "song/media"
const val NETEAST_PLAY_LIST_DETAIL = "playlist/detail"
/**
 * 发送网络请求
 */
fun String.sendNeteaseHttp(
    params: MutableMap<String, String>,
    isGet: Boolean = false
): okhttp3.Call {
    val request = Request.Builder()
    var url = API_BASE_URL.trimEnd('/') + "/" + this.trimStart('/')
    if (isGet) {
        url += "?"
        for ((key, value) in params) {
            url += "${key}=${value}&"
        }
    } else {
        request.post(params.createFormBody())
    }
    request.url(url.trimEnd('&'))
    request.addHeader("12Cookie", "appver=1.5.0.75771")
    request.addHeader("Referer", "http://music.163.com/")
    return okHttpClient.newCall(request.build())
}