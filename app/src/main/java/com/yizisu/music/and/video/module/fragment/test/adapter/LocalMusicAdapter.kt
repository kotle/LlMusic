package com.yizisu.music.and.video.module.fragment.test.adapter

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.LocalMusicBean

class LocalMusicAdapter : BaseRcvAdapter<SongInfoTable, LocalMusicHolder>() {
    override fun getItemLayoutRes(): Int = LocalMusicHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): LocalMusicHolder = LocalMusicHolder(itemView)

    override fun onBindViewHolder(
        holder: LocalMusicHolder,
        position: Int,
        itemData: SongInfoTable
    ) {
        holder.setData(itemData)
    }

}