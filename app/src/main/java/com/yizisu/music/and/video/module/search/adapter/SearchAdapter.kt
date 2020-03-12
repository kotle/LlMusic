package com.yizisu.music.and.video.module.search.adapter

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.service.music.MusicService

class SearchAdapter : BaseRcvAdapter<SongInfoTable, SearchHolder>() {

    var keyWords: String? = null
    var isNeedMusicJumpView=false
    init {
        setOnItemClickListener { itemView, position, itemData ->
            startPlaySearchMusic(position)
        }
    }

    /**
     * 开始播放搜索音乐
     */
    private fun startPlaySearchMusic(position: Int) {
        MusicService.startPlay(datas.map {
            SongModel(it)
        }.toMutableList(), position, true)
    }

    override fun getItemLayoutRes(): Int = SearchHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): SearchHolder = SearchHolder(itemView)

    override fun onBindViewHolder(
        holder: SearchHolder,
        position: Int,
        itemData: SongInfoTable
    ) {
        holder.setData(itemData,keyWords,isNeedMusicJumpView)
    }
}