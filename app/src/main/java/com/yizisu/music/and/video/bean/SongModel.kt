package com.yizisu.music.and.video.bean

import android.net.Uri
import androidx.lifecycle.Observer
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanValue
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerLiveBean
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.baidu.SongInfoBaiduBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import com.yizisu.playerlibrary.helper.PlayerModel
import java.lang.IllegalArgumentException
import java.util.*

class SongModel(val song: SongInfoTable) : PlayerModel() {
    override fun callMediaUri(uriCall: (Uri?, Throwable?,Boolean) -> Unit) {
        if (song.playFilePath != null) {
            uriCall.invoke(Uri.parse(song.playFilePath), null,false)
            return
        }
        if (song.playUrlPath != null) {
            uriCall.invoke(Uri.parse(song.playUrlPath), null,false)
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
    private fun queryBaiduUrl(uriCall: (Uri?, Throwable?,Boolean) -> Unit, modelSong: SongInfoTable) {
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
                        uriCall.invoke(Uri.parse(bitrate.fileLink), null,true)
                    } else {
                        uriCall.invoke(null, Throwable("请求成功，未返回结果"),false)
                    }
                    observable?.let {
                        baiduSongInfoData.removeObserver(it)
                    }
                }
                LiveBeanStatus.FAIL -> {
                    uriCall.invoke(null, Throwable(it.errorMsg),false)
                    observable?.let {
                        baiduSongInfoData.removeObserver(it)
                    }
                }
            }
        }
        baiduSongInfoData.observeForever(observable)
        searchViewModel.songInfoByBaidu(modelSong.id.toString(), baiduSongInfoData)
    }
}