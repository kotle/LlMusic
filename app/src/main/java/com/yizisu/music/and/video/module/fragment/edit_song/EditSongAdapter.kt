package com.yizisu.music.and.video.module.fragment.edit_song

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.bean.netease.SongInfoNeteaseBean
import com.yizisu.music.and.video.module.add_song_to_album.AddSongToAlbumActivity
import com.yizisu.music.and.video.service.music.MusicService

class EditSongAdapter : BaseRcvAdapter<EditSongAdapter.EditSongBean, EditSongHolder>() {
    data class EditSongBean(
        val song: SongInfoTable,
        var isCheck: Boolean = false
    )

    override fun getItemLayoutRes(): Int = EditSongHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): EditSongHolder = EditSongHolder(itemView, this)

    override fun onBindViewHolder(
        holder: EditSongHolder,
        position: Int,
        itemData: EditSongBean
    ) {
        holder.setData(itemData)
    }
}