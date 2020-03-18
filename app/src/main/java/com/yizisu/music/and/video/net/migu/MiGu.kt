package com.yizisu.music.and.video.net.migu

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import com.yizisu.music.and.video.bean.migu.SearchMiguBean
import okhttp3.Request


private const val API_BASE_URL = "http://api.lmwljz.com/"

const val MIGU_SEARCH = "http://migu.w0ai1uo.org/search"

/**
 * 生成 咪咕songid
 */
fun miguGenId(it: SearchMiguBean.DataBean.ListBean): String {
    return "${it.id},${it.cid},${it.album.id}"
}

/**
 *专辑id
 */
fun getMiguAlbumId(songId: String): String {
    return songId.split(",")[2]
}

/**
 * 真正的歌曲id
 */
fun getMiguSongId(songId: String): String {
    return songId.split(",")[0]
}

/**
 * cid
 */
fun getMiguCId(songId: String): String {
    return songId.split(",")[1]
}

/**
 * 发送网络请求
 */
fun String.sendMiguHttp(
    params: MutableMap<String, String>,
    isGet: Boolean = true
): okhttp3.Call {
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
    request.addHeader("Referer", "http://migu.w0ai1uo.org/")
//    request.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    return okHttpClient.newCall(request.build())
}