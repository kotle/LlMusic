package com.yizisu.music.and.video.module.fragment.edit_song

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable

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