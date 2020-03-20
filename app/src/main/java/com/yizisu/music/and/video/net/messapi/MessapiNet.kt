package com.yizisu.music.and.video.net.messapi

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import com.yizisu.music.and.video.bean.migu.SearchMiguBean
import okhttp3.Request

//文档地址 https://messoer.github.io/mess-api-doc/#/music/migu?id=%e6%90%9c%e7%b4%a2
private const val API_BASE_URL = "https://v1.itooi.cn/"

const val MESSAPI_MIGU_SEARCH = "migu/search"

/**
 * 发送网络请求
 */
fun String.sendMessapiHttp(
    params: MutableMap<String, String>,
    isGet: Boolean = true
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