package com.yizisu.music.and.video.net.dongwo

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import okhttp3.Request


private const val API_BASE_URL = "http://api.lmwljz.com/"

const val DONGWO_ACCESSTOKEN=""
/**
 * 发送网络请求
 */
fun String.sendDongwoHttp(
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
    return okHttpClient.newCall(request.build())
}