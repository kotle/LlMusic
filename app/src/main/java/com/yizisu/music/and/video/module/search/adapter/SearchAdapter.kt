package com.yizisu.music.and.video.module.search.adapter

import android.view.ContextThemeWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.module.add_song_to_album.AddSongToAlbumActivity
import com.yizisu.music.and.video.service.music.MusicService

class SearchAdapter(val album: AlbumInfoTable?) : BaseRcvAdapter<SongInfoTable, SearchHolder>() {

    var keyWords: String? = null
    var isNeedMusicJumpView = false

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

    override fun onCreateViewHolder(itemView: View): SearchHolder =
        SearchHolder(itemView, this, album)

    override fun onBindViewHolder(
        holder: SearchHolder,
        position: Int,
        itemData: SongInfoTable
    ) {
        holder.setData(itemData, keyWords, isNeedMusicJumpView)
    }
}