package com.yizisu.music.and.video.bean

import android.net.Uri
import androidx.lifecycle.Observer
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanValue
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerLiveBean
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.video.bean.baidu.SongInfoBaiduBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import com.yizisu.playerlibrary.helper.PlayerModel
import java.util.*

class SongModel(val song: LocalMusicBean) : PlayerModel() {
    override fun callMediaUri(uriCall: (Uri?, Throwable?) -> Unit) {
        when (song.sourceType) {
            LocalMusicBean.SOURCE_TYPE_BAIDU -> {
                queryBaiduUrl(uriCall, song)
            }
            LocalMusicBean.SOURCE_TYPE_NETEASE -> {
//                queryNetneaseUrl(uriCall, song)
                uriCall.invoke(Uri.parse("http://music.163.com/song/media/outer/url?id=${song.id}.mp3"),null)
            }
            LocalMusicBean.SOURCE_TYPE_LOCAL -> {
                uriCall.invoke(Uri.parse(song.path), null)
            }
            else -> {
                uriCall.invoke(Uri.parse(song.path), null)
            }
        }
    }

    /**
     * 获取百度的歌曲信息
     */
    private val searchViewModel by lazy { SearchViewModel() }
    private val baiduSongInfoData by lazy { createLiveBean<SongInfoBaiduBean>() }
    private fun queryBaiduUrl(uriCall: (Uri?, Throwable?) -> Unit, modelSong: LocalMusicBean) {
        var observable: Observer<LiveBeanValue<SongInfoBaiduBean>>? = null
        observable = Observer {
            when (it.status) {
                LiveBeanStatus.START -> {

                }
                LiveBeanStatus.SUCCESS -> {
                    val song = it.value?.songinfo
                    val bitrate = it.value?.bitrate
                    if (song != null && bitrate != null) {
                        modelSong.path = bitrate.fileLink
                        modelSong.coverUrl = song.picSmall
                        modelSong.lrcUrl = song.lrclink
                        //数据请求成功
                        uriCall.invoke(Uri.parse(bitrate.fileLink), null)
                    } else {
                        uriCall.invoke(null, Throwable("请求成功，未返回结果"))
                    }
                    observable?.let {
                        baiduSongInfoData.removeObserver(it)
                    }
                }
                LiveBeanStatus.FAIL -> {
                    uriCall.invoke(null, Throwable(it.errorMsg))
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
     * 获取网易云的歌曲信息
     */
    private val neteaseSongInfoData by lazy { createLiveBean<SongInfoNeteaseBean>() }

    private fun queryNetneaseUrl(uriCall: (Uri?, Throwable?) -> Unit, modelSong: LocalMusicBean) {
        var observable: Observer<LiveBeanValue<SongInfoNeteaseBean>>? = null
        observable = Observer {
            when (it.status) {
                LiveBeanStatus.START -> {

                }
                LiveBeanStatus.SUCCESS -> {
//                    val song = it.value?.songinfo
//                    val bitrate = it.value?.bitrate
//                    if (song != null && bitrate != null) {
//                        modelSong.path = bitrate.fileLink
//                        modelSong.coverUrl = song.picSmall
//                        modelSong.lrcUrl = song.lrclink
//                        //数据请求成功
//                        uriCall.invoke(Uri.parse(bitrate.fileLink), null)
//                    } else {
//                        uriCall.invoke(null, Throwable("请求成功，未返回结果"))
//                    }
                    observable?.let {
                        neteaseSongInfoData.removeObserver(it)
                    }
                }
                LiveBeanStatus.FAIL -> {
                    uriCall.invoke(null, Throwable(it.errorMsg))
                    observable?.let {
                        neteaseSongInfoData.removeObserver(it)
                    }
                }
            }
        }
        neteaseSongInfoData.observeForever(observable)
        searchViewModel.songInfoByNetease(modelSong.id.toString(), neteaseSongInfoData)
    }
}