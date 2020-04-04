package com.yizisu.music.and.video.net.nodejs

import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import com.yizisu.music.and.video.bean.messapi.SearchNodeJsMiguBean
import okhttp3.Request
//咪咕 https://github.com/jsososo/MiguMusicApi
//网易云 https://github.com/Binaryify/NeteaseCloudMusicApi
//QQ音乐 https://github.com/jsososo/QQMusicApi
private const val API_BASE_URL = "http://api.migu.jsososo.com/"

const val NODEJS_MIGU_SEARCH = "search"
const val NODEJS_MIGU_SONG_INFO = "song/url"
const val NODEJS_MIGU_ALBUM_INFO = "album"
const val NODEJS_MIGU_LYRIC = "lyric"

/**
 * 发送网络请求
 */
fun String.sendNodeJsMiguHttp(
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

/**
 * 生成 咪咕songid
 */
fun miguGenId(it: SearchNodeJsMiguBean.DataBean.ListBean): String {
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