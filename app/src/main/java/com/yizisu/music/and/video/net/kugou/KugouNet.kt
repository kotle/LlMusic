package com.yizisu.music.and.video.net.kugou

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import okhttp3.Request

private const val API_BASE_URL = "http://mobilecdn.kugou.com/api/v3/"
const val KUGOU_SEARCH =API_BASE_URL+ "search/song"
//hash=CB7EE97F4CC11C4EA7A1FA4B516A5D97
const val KUGOU_SONG_INFO="https://www.kugou.com/yy/index.php"

/**
 * 发送网络请求
 */
fun String.sendKugouHttp(
    params: MutableMap<String, String>,
    isGet: Boolean = true
): okhttp3.Call {
    params["format"]="json"
    val request = Request.Builder()
    var url = this.trimStart('/')
    if (isGet) {
        url += "?"
        for ((key, value) in params) {
            url += "${key}=${value}&"
        }
    } else {
        request.post(params.createFormBody())
    }
    request.url(url.trimEnd('&'))
    request.addHeader("Cookie", "kg_mid=fd73d6fda1b05522b9a34754c90a3185")
    return okHttpClient.newCall(request.build())
}