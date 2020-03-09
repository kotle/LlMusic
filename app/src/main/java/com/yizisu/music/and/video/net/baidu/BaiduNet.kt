package com.yizisu.music.and.video.net.baidu

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import okhttp3.Request

private const val API_BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting"

//const val BAIDU_SEARCH = "method=baidu.ting.billboard.billList&type=1&size=10&offset=0"
const val BAIDU_SEARCH = "method=baidu.ting.search.catalogSug"
const val BAIDU_SONG_INFO = "method=baidu.ting.song.play"
/**
 * 发送网络请求
 */
fun String.sendBaiduHttp(
    params: MutableMap<String, String>,
    isGet: Boolean = true
): okhttp3.Call {
    params["from"] = "android"
    params["version"] = "5.6.5.0"
    params["format"] = "json"
    val request = Request.Builder()
    var url = API_BASE_URL.trimEnd('?') + "?" + this.trimStart('?')
    if (isGet) {
        url += "&"
        for ((key, value) in params) {
            url += "${key}=${value}&"
        }
    } else {
        request.post(params.createFormBody())
    }
    request.url(url.trimEnd('&'))
    //模拟pc请求
    request.addHeader("referer", API_BASE_URL)
    request.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    return okHttpClient.newCall(request.build())
}