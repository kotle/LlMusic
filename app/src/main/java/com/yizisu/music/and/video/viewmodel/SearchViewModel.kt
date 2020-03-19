package com.yizisu.music.and.video.viewmodel

import android.net.Uri
import com.yizisu.basemvvm.mvvm.mvvm_helper.*
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SingerInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.createOkHttpCall
import com.yizisu.music.and.video.bean.baidu.SearchBaiduBean
import com.yizisu.music.and.video.bean.baidu.SongInfoBaiduBean
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.bean.kugou.SearchKugouBean
import com.yizisu.music.and.video.bean.messapi.SearchMessApiMiguBean
import com.yizisu.music.and.video.bean.migu.SearchMiguBean
import com.yizisu.music.and.video.bean.netease.SearchNeteaseBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.net.baidu.BAIDU_SEARCH
import com.yizisu.music.and.video.net.baidu.BAIDU_SONG_INFO
import com.yizisu.music.and.video.net.baidu.sendBaiduHttp
import com.yizisu.music.and.video.net.kugou.KUGOU_SEARCH
import com.yizisu.music.and.video.net.kugou.sendKugouHttp
import com.yizisu.music.and.video.net.messapi.MESSAPI_MIGU_SEARCH
import com.yizisu.music.and.video.net.messapi.sendMessapiHttp
import com.yizisu.music.and.video.net.migu.MIGU_SEARCH
import com.yizisu.music.and.video.net.migu.miguGenId
import com.yizisu.music.and.video.net.migu.sendMiguHttp
import com.yizisu.music.and.video.net.netease.NETEAST_SEARCH
import com.yizisu.music.and.video.net.netease.NETEAST_SONG_INFO
import com.yizisu.music.and.video.net.netease.sendNeteaseHttp
import java.lang.StringBuilder

class SearchViewModel : BaseViewModel() {
    companion object {
        fun neteaseSongsFormat(
            songs: MutableList<SearchNeteaseBean.ResultBean.SongsBean>,
            searchBean: SearchBean? = null
        ): MutableList<SongInfoTable>? {
            return songs.map {
                SongInfoTable().apply {
                    name = it.name
                    id = it.id.toString()
                    source = DbCons.SOURCE_NETEASE
                    type = it.fee.toInt()
                    coverUrlPath = it.album.picUrl
                    playUrlPath = "http://music.163.com/song/media/outer/url?id=${id}"
                    val singers = StringBuilder()
                    it.artists.map {
                        SingerInfoTable().apply {
                            id = it.id.toString()
                            name = it.name
                            des = it.picUrl
                            source = DbCons.SOURCE_NETEASE
                            type = DbCons.TYPE_FREE
                            singers.append("${it.name},")
                        }
                    }
                    des = singers.toString().trimEnd(',')
                }
            }.toMutableList()
        }
    }

    /**
     * 百度搜索
     */
    val baiduSearchData = createLiveBean<SearchBaiduBean>()
    val neteaseSearchData = createLiveBean<SearchNeteaseBean>()
    val localSearchData = createLiveBean<SearchBean>()
    val kugouSearchData = createLiveBean<SearchKugouBean>()
    val miguSearchData = createLiveBean<SearchMiguBean>()
    val messapiMiguSearchData = createLiveBean<SearchMessApiMiguBean>()

    /**
     * 百度搜索结果转为自己的
     */
    fun baiduToSearchBean(bean: SearchBaiduBean?): SearchBean? {
        bean ?: return null
        val searchBean = SearchBean()
        searchBean.songInfoTables = bean.song?.map {
            SongInfoTable().apply {
                name = it.songname
                id = it.songid.toString()
                source = DbCons.SOURCE_BAIDU
                type = DbCons.TYPE_FREE
                if (!bean.album.isNullOrEmpty()) {
                    coverUrlPath = bean.album[0].artistpic
                }
                des = it.artistname
                searchBean.singerInfoTables = bean.artist?.map {
                    SingerInfoTable().apply {
                        des = it.artistpic
                        id = it.artistid.toString()
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
        searchBean.songInfoTables = neteaseSongsFormat(songs, searchBean)
        return searchBean
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
        if (keyword.isNullOrBlank()) {
            return
        }
        val bean = SearchBean()
        launchThread {
            bean.songInfoTables = DbHelper.queryAllSongByKeyword(keyword)?.asReversed()
            localSearchData.success(bean)
        }
    }

    /*********************************************酷狗*****************************************/

    fun searchByKugou(keyword: String?) {
        if (keyword.isNullOrBlank()) {
            return
        }
        KUGOU_SEARCH.sendKugouHttp(
            mutableMapOf(
                "keyword" to keyword,
                "page" to "1",
                "pagesize" to "50",
                "showtype" to "1"
            )
        ).async(kugouSearchData.createOkHttpCall())
    }

    fun kugouToSearchBean(data: SearchKugouBean?): SearchBean? {
        val searchBean = SearchBean()
        searchBean.songInfoTables = data?.data?.info?.map {
            SongInfoTable().apply {
                name = it.songname
                id = it.hash
                source = DbCons.SOURCE_KUGOU
                type = DbCons.TYPE_FREE
                coverUrlPath = null
                playUrlPath = null
                des = it.singername
            }
        }?.toMutableList()
        return searchBean
    }

    /**************************************咪咕********************************/

    fun searchByMigu(keyword: String?) {
        if (keyword.isNullOrBlank()) {
            return
        }
        //text={周杰伦}&pageNo={1}&pageSize=10
        MIGU_SEARCH.sendMiguHttp(
            mutableMapOf(
                "keyword" to keyword
            ), true
        ).async(miguSearchData.createOkHttpCall())
    }

    fun miguToSearchBean(data: SearchMiguBean?): SearchBean? {
        val searchBean = SearchBean()
        searchBean.songInfoTables = data?.data?.list?.map {
            val singers = StringBuilder()
            it.artists.map {
                SingerInfoTable().apply {
                    id = it.id
                    name = it.name
                    des = ""
                    source = DbCons.SOURCE_MIGU
                    type = DbCons.TYPE_FREE
                    singers.append("${it.name},")
                }
            }
            //这里需要存储三个id，歌曲id，cid，歌单id,用逗号隔开
            SongInfoTable(
                null, miguGenId(it), DbCons.SOURCE_MIGU, DbCons.TYPE_FREE,
                singers.toString().trimEnd(','),
                it.name, System.currentTimeMillis(),
                System.currentTimeMillis(),
                null, null, null, null,
                null, null, null, null, null
            )
        }?.toMutableList()
        return searchBean
    }

    /************************************messapi 咪咕*********************************************/
    fun searchByMessapiMigu(keyword: String?) {
        if (keyword.isNullOrBlank()) {
            return
        }
        //type=song&pageSize=2&page=1
        MESSAPI_MIGU_SEARCH.sendMessapiHttp(
            mutableMapOf(
                "keyword" to keyword,
                "type" to "song",
                "pageSize" to "50",
                "page" to "1"
            )
        ).async(messapiMiguSearchData.createOkHttpCall())
    }

    fun messapiMiguToSearchBean(data: SearchMessApiMiguBean?): SearchBean? {
        val searchBean = SearchBean()
        searchBean.songInfoTables = mutableListOf()
        data?.data?.resultList?.forEach { list ->
            list.forEach {
                val singers = StringBuilder()
                it.singers.map {
                    SingerInfoTable().apply {
                        id = it.id
                        name = it.name
                        des = ""
                        source = DbCons.SOURCE_MIGU
                        type = DbCons.TYPE_FREE
                        singers.append("${it.name},")
                    }
                }
                var oldUrl: String? = null
                it.newRateFormats.forEach {
                    if (it.androidUrl != null) {
                        oldUrl = it.androidUrl
                    }
                }
                if (!oldUrl.isNullOrEmpty()) {
                    val playIrl =
                        "http://tyst.migu.cn" + Uri.parse(oldUrl).path
                    searchBean.songInfoTables.add(
                        SongInfoTable(
                            null, it.id, DbCons.SOURCE_MIGU, DbCons.TYPE_FREE,
                            singers.toString().trimEnd(','),
                            it.name, System.currentTimeMillis(),
                            System.currentTimeMillis(),
                            null, null, null, it.imgItems.first()?.img,
                            null, playIrl, null, it.lyricUrl, null
                        )
                    )
                } else {
                    println()
                }
            }
        }
        return searchBean
    }
}