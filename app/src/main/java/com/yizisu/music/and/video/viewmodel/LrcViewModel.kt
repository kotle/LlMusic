package com.yizisu.music.and.video.viewmodel

import com.yizisu.basemvvm.gson
import com.yizisu.basemvvm.logI
import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.tryError
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.baselib.base.BaseViewModel
import com.yizisu.music.and.video.baselib.base.sendHttp
import com.yizisu.music.and.video.bean.LrcBean
import com.yizisu.music.and.video.bean.migu.LrcMiguBean
import com.yizisu.music.and.video.bean.netease.LrcNeteaseBean
import com.yizisu.music.and.video.net.migu.getMiguCId
import com.yizisu.music.and.video.net.migu.sendMiguHttp
import com.yizisu.music.and.video.net.netease.NETEAST_SONG_LRC
import com.yizisu.music.and.video.net.netease.sendNeteaseHttp
import kotlinx.coroutines.Job

class LrcViewModel : BaseViewModel() {
    companion object {


        /**
         * 文本歌词
         */
        val lrcStringData by lazy { createLiveBean<String?>() }
        /**
         *
         */
        val lrcFileData by lazy { createLiveBean<String?>() }
    }

    val currentSong: SongInfoTable?
        get() = AppData.currentPlaySong.data?.song
    /**
     * 从歌词迷查询歌词链接
     * 最后通过lrcStringData传递出去
     */
    private var oldLrcJob: Job? = null

    fun queryLrc() {
        val song = currentSong ?: return
        if (oldLrcJob?.isCancelled == false) {
            oldLrcJob?.cancel()
        }
        oldLrcJob = launchThread {
            tryError {
                val singer = song.des
                val name = song.name
                val url = if (singer.isNullOrBlank()) {
                    "http://gecimi.com/api/lyric/${name}"
                } else {
                    "http://gecimi.com/api/lyric/${name}/${singer}"
                }
                val response = url.sendHttp(mutableMapOf(), true).await()
                val result = response.body()?.string()
                if (!result.isNullOrEmpty()) {
                    val lrcBean = gson.fromJson<LrcBean>(result, LrcBean::class.java)
                    if (!lrcBean.result.isNullOrEmpty()) {
                        val lrcString =
                            lrcBean.result[0].lrc.sendHttp(mutableMapOf(), true).await().body()
                                ?.string()
                        successGetLrc(song, lrcString)
                    }
                }
            }
        }
    }


    private fun successGetLrc(song: SongInfoTable, lrcString: String?) {
        if (!lrcString.isNullOrEmpty()) {
            if (currentSong == song) {
                lrcStringData.success(lrcString)
            } else {
                logI("之前的请求的歌曲：${song.name},现在的歌曲是：${currentSong?.name}")
            }
            //保存歌词
            song.lrcString = lrcString
            DbHelper.insetOrUpdateSong(song)
        }
    }

    /**
     * 从网易云查询歌词文本
     * 最后通过lrcStringData传递出去
     */
    fun queryLrcNetease() {
        val song = currentSong ?: return
        if (oldLrcJob?.isCancelled == false) {
            oldLrcJob?.cancel()
        }
        oldLrcJob = launchThread {
            tryError {
                val result = NETEAST_SONG_LRC.sendNeteaseHttp(
                    mutableMapOf(
                        "id" to song.id.toString()
                    )
                ).await().body()?.string()
                if (!result.isNullOrEmpty()) {
                    val bean = gson.fromJson<LrcNeteaseBean>(result, LrcNeteaseBean::class.java)
                    val lrcString = bean.lyric
                    if (!lrcString.isNullOrEmpty()) {
                        successGetLrc(song, lrcString)
                    }
                }
            }
        }
    }

    /**
     * 从咪咕查询歌词文本
     * 最后通过lrcStringData传递出去
     */
    fun queryLrcMigu() {
        val song = currentSong ?: return
        if (oldLrcJob?.isCancelled == false) {
            oldLrcJob?.cancel()
        }
        oldLrcJob = launchThread {
            tryError {
                val result = "http://migu.w0ai1uo.org/lyric".sendMiguHttp(
                    mutableMapOf(
                        "cid" to getMiguCId(song.id)
                    )
                ).await().body()?.string()
                if (!result.isNullOrEmpty()) {
                    val bean = gson.fromJson<LrcMiguBean>(result, LrcMiguBean::class.java)
                    val lrcString = bean.data
                    if (!lrcString.isNullOrEmpty()) {
                        successGetLrc(song, lrcString)
                    }
                }
            }
        }
    }

    fun queryLrcMessapiMigu() {
        val song = currentSong ?: return
        val lrcPath = song.lrcUrlPath ?: return
        if (oldLrcJob?.isCancelled == false) {
            oldLrcJob?.cancel()
        }
        oldLrcJob = launchThread {
            tryError {
                val result = lrcPath.sendMiguHttp(
                    mutableMapOf()
                ).await().body()?.string()
                if (!result.isNullOrEmpty() && !result.contains("暂无歌词")) {
                    successGetLrc(song, result)
                }else{
                    queryLrc()
                }
            }
        }
    }
}