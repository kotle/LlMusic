package com.yizisu.music.and.video.bean

import android.net.Uri
import androidx.lifecycle.Observer
import com.yizisu.basemvvm.gson
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanValue
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerLiveBean
import com.yizisu.basemvvm.utils.asyncOnThread
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.launchUi
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.baidu.SongInfoBaiduBean
import com.yizisu.music.and.video.bean.kugou.DownloadKugouBean
import com.yizisu.music.and.video.bean.kugou.SongInfoKugouBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.net.kugou.KUGOU_SONG_INFO
import com.yizisu.music.and.video.net.kugou.sendKugouHttp
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import java.lang.IllegalArgumentException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class SongModel(val song: SongInfoTable) : PlayerModel() {
    override fun callMediaUri(uriCall: (Uri?, Throwable?, Boolean) -> Unit) {
        if (song.playFilePath != null) {
            uriCall.invoke(Uri.parse(song.playFilePath), null, false)
            return
        }
        if (song.playUrlPath != null) {
            uriCall.invoke(Uri.parse(song.playUrlPath), null, false)
            return
        }
        when (song.source) {
            DbCons.SOURCE_BAIDU -> {
                queryBaiduUrl(uriCall, song)
//                uriCall.invoke(Uri.parse("http://audio04.dmhmusic.com/71_53_T10046722712_128_4_1_0_sdk-cpm/cn/0208/M00/6F/02/ChR461uC3bWAXT8LAFBOio2zSeU812.mp3?xcode=8289fdd990a0ea765fd12efeb96377d4233096f"),null)
            }
            DbCons.SOURCE_NETEASE -> {
//                queryNetneaseUrl(uriCall, song)
                uriCall.invoke(
                    Uri.parse("http://music.163.com/song/media/outer/url?id=${song.id}"),
                    null,
                    false
                )
            }
            DbCons.SOURCE_KUGOU -> {
                queryKugouUrl(uriCall, song)
            }
            else -> {
                throw IllegalArgumentException("没有可以播放的地址")
            }
        }
    }

    /**
     * 获取百度的歌曲信息
     */
    private val searchViewModel by lazy { SearchViewModel() }
    private val baiduSongInfoData by lazy { createLiveBean<SongInfoBaiduBean>() }
    private fun queryBaiduUrl(
        uriCall: (Uri?, Throwable?, Boolean) -> Unit,
        modelSong: SongInfoTable
    ) {
        var observable: Observer<LiveBeanValue<SongInfoBaiduBean>>? = null
        observable = Observer {
            when (it.status) {
                LiveBeanStatus.START -> {

                }
                LiveBeanStatus.SUCCESS -> {
                    val song = it.value?.songinfo
                    val bitrate = it.value?.bitrate
                    if (song != null && bitrate != null) {
                        modelSong.playUrlPath = bitrate.fileLink
                        modelSong.coverUrlPath = song.picBig
                        modelSong.lrcUrlPath = song.lrclink
                        //数据请求成功
                        DbHelper.insetOrUpdateSong(modelSong)
                        uriCall.invoke(Uri.parse(bitrate.fileLink), null, true)
                    } else {
                        uriCall.invoke(null, Throwable("请求成功，未返回结果"), false)
                    }
                    observable?.let {
                        baiduSongInfoData.removeObserver(it)
                    }
                }
                LiveBeanStatus.FAIL -> {
                    uriCall.invoke(null, Throwable(it.errorMsg), false)
                    observable?.let {
                        baiduSongInfoData.removeObserver(it)
                    }
                }
            }
        }
        baiduSongInfoData.observeForever(observable)
        searchViewModel.songInfoByBaidu(modelSong.id.toString(), baiduSongInfoData)
    }

    /**
     * 获取酷狗歌曲信息
     */
    private fun queryKugouUrl(uriCall: (Uri?, Throwable?, Boolean) -> Unit, song: SongInfoTable) {
        launchUi {
//            queryKugouSongDownload(this, song).await()
            val bean = queryKugouSongInfo(this, song).await()
            val data = bean.data
            if (data == null) {
                uriCall.invoke(null, Throwable("获取酷狗音乐信息失败"), false)
            } else {
                data.apply {
                    uriCall.invoke(Uri.parse(playUrl), null, true)
                }
            }
        }
    }

    private fun queryKugouSongInfo(
        scope: CoroutineScope,
        song: SongInfoTable
    ): Deferred<SongInfoKugouBean> {
        return scope.asyncOnThread {
            try {
                //r=play/getdata&hash=CB7EE97F4CC11C4EA7A1FA4B516A5D97
                val results = KUGOU_SONG_INFO.sendKugouHttp(
                    mutableMapOf(
                        "r" to "play/getdata",
                        "hash" to song.id
                    )
                ).execute().body()?.string()
                return@asyncOnThread if (results.isNullOrEmpty()) {
                    SongInfoKugouBean()
                } else {
                    val bean = gson.fromJson(results, SongInfoKugouBean::class.java)
                    song.lrcString = bean.data?.lyrics
                    song.coverUrlPath = bean.data?.img
                    //播放连接有时效性，这里不赋值
                    if (!song.playUrlPath.isNullOrEmpty()) {
                        bean.data?.playUrl = song.playUrlPath
                        song.playUrlPath = null
                    }
                    DbHelper.insetOrUpdateSong(song)
                    bean
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                return@asyncOnThread SongInfoKugouBean()
            }

        }
    }

    private fun queryKugouSongDownload(
        scope: CoroutineScope,
        song: SongInfoTable
    ): Deferred<DownloadKugouBean> {
        return scope.asyncOnThread {
            try {
                val results = "http://trackercdnbj.kugou.com/i/v2/".sendKugouHttp(
                    mutableMapOf(
                        "behavior" to "download",
                        "cmd" to "23",
                        "pid" to "1",
                        "appid" to "1005",
                        "key" to encode(song.id + "kgcloudv2"),
                        "hash" to song.id
                    )
                ).execute().body()?.string()
                return@asyncOnThread if (results.isNullOrEmpty()) {
                    DownloadKugouBean()
                } else {
                    val bean = gson.fromJson(results, DownloadKugouBean::class.java)
                    song.playUrlPath = bean.url
                    bean
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                return@asyncOnThread DownloadKugouBean()
            }

        }
    }
  //md5 加密
   private fun encode(password: String): String {
        try {
            val instance: MessageDigest = MessageDigest.getInstance("MD5")//获取md5加密对象
            val digest: ByteArray = instance.digest(password.toByteArray())//对字符串加密，返回字节数组
            var sb: StringBuffer = StringBuffer()
            for (b in digest) {
                var i: Int = b.toInt() and 0xff//获取低八位有效值
                var hexString = Integer.toHexString(i)//将整数转化为16进制
                if (hexString.length < 2) {
                    hexString = "0" + hexString//如果是一位的话，补0
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

}