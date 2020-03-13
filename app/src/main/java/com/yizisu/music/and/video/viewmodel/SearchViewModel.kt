package com.yizisu.music.and.video.viewmodel

import androidx.lifecycle.Observer
import com.yizisu.basemvvm.mvvm.mvvm_helper.*
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.bean.SingerInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.createOkHttpCall
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.baidu.SearchBaiduBean
import com.yizisu.music.and.video.bean.baidu.SongInfoBaiduBean
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.bean.netease.SearchNeteaseBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.net.baidu.BAIDU_SEARCH
import com.yizisu.music.and.video.net.baidu.BAIDU_SONG_INFO
import com.yizisu.music.and.video.net.baidu.sendBaiduHttp
import com.yizisu.music.and.video.net.netease.NETEAST_SEARCH
import com.yizisu.music.and.video.net.netease.NETEAST_SONG_INFO
import com.yizisu.music.and.video.net.netease.sendNeteaseHttp
import java.lang.StringBuilder

class SearchViewModel : BaseViewModel() {

    /**
     * 百度搜索
     */
    val baiduSearchData = createLiveBean<SearchBaiduBean>()
    val neteaseSearchData = createLiveBean<SearchNeteaseBean>()
    val localSearchData = createLiveBean<SearchBean>()

//    val searchData = createMediatorLiveBean<SearchBean>().apply {
//        addLiveBeanListener(baiduSearchData, Observer {
//            setLiveDateValue(it.status, baiduToSearchBean(it.value), it.errorMsg, it.errorCode)
//        })
//        addLiveBeanListener(neteaseSearchData, Observer {
//            setLiveDateValue(it.status, neteaseToSearchBean(it.value), it.errorMsg, it.errorCode)
//        })
//    }

    /**
     * 百度搜索结果转为自己的
     */
    fun baiduToSearchBean(bean: SearchBaiduBean?): SearchBean? {
        bean ?: return null
        val searchBean = SearchBean()
        searchBean.songInfoTables = bean.song?.map {
            SongInfoTable().apply {
                name = it.songname
                id = it.songid.toLong()
                source = DbCons.SOURCE_BAIDU
                type = DbCons.TYPE_FREE
                if (!bean.album.isNullOrEmpty()) {
                    coverUrlPath = bean.album[0].artistpic
                }
                des = it.artistname
                searchBean.singerInfoTables = bean.artist?.map {
                    SingerInfoTable().apply {
//                        coverUrlPath = it.artistpic
                        id = it.artistid.toLong()
                        name = it.artistname
                        source = DbCons.SOURCE_BAIDU
                        type = DbCons.TYPE_FREE
                    }
                }
            }
        }
        return searchBean
    }

    //316686 vip music id
    fun neteaseToSearchBean(bean: SearchNeteaseBean?): SearchBean? {
        val songs = bean?.result?.songs ?: return null
        val searchBean = SearchBean()
        searchBean.songInfoTables = songs.map {
            SongInfoTable().apply {
                name = it.name
                id = it.id
                source = DbCons.SOURCE_NETEASE
                type = it.fee.toInt()
                coverUrlPath = it.album.picUrl
                playUrlPath = "http://music.163.com/song/media/outer/url?id=${id}.mp3"
                val singers = StringBuilder()
                searchBean.singerInfoTables = it.artists.map {
                    SingerInfoTable().apply {
                        id = it.id
                        name = it.name
                        source = DbCons.SOURCE_NETEASE
                        type = DbCons.TYPE_FREE
                        singers.append("${it.name},")
                    }
                }
                des = singers.toString().trimEnd(',')
            }
        }
        return searchBean
    }

    fun search(keyword: String?) {
        searchByNetease(keyword)
        searchByBaidu(keyword)
        searchByLocal(keyword)
    }
    /******************************************百度*********************************************/
    /**
     * 搜索百度音乐
     */
    fun searchByBaidu(keyword: String?) {
        if (keyword.isNullOrBlank()) {
            return
        }
        BAIDU_SEARCH.sendBaiduHttp(
            mutableMapOf(
                "query" to keyword
            )
        ).async(baiduSearchData.createOkHttpCall())
    }

    /**
     * 通过id获取百度的音乐信息
     */
    fun songInfoByBaidu(songId: String, data: LiveBean<SongInfoBaiduBean>) {
        BAIDU_SONG_INFO.sendBaiduHttp(
            mutableMapOf(
                "songid" to songId
            )
        ).async(data.createOkHttpCall())
    }

    /******************************************网易云*********************************************/
    fun songInfoByNetease(songId: String, data: LiveBean<SongInfoNeteaseBean>) {
        NETEAST_SONG_INFO.sendNeteaseHttp(
            mutableMapOf(
                "id" to "29984255"
            )
        ).async(data.createOkHttpCall())
    }

    /**
     * 搜索网易云音乐
     */
    fun searchByNetease(keyword: String?) {
        if (keyword.isNullOrBlank()) {
            return
        }
        NETEAST_SEARCH.sendNeteaseHttp(
            mutableMapOf(
                "s" to keyword,
                "offset" to "0",
                "limit" to "50",
                "type" to "1"
            )
        ).async(neteaseSearchData.createOkHttpCall())
    }

    /****************************************/
    fun searchByLocal(keyword: String?) {
        val list = mutableListOf<SongInfoTable>()
        val bean = SearchBean()
        bean.songInfoTables = list
        if (keyword.isNullOrBlank()) {
            localSearchData.success(bean)
            return
        }
        AppData.dbLocalAlbumData.data?.songInfoTables?.forEach {
            if (it.name.contains(keyword) || it.des.contains(keyword) ||
                keyword.contains(it.name) || keyword.contains(it.des)
            ) {
                list.add(it)
            }
        }
        localSearchData.success(bean)
    }
}