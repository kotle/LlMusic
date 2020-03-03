package com.yizisu.music.and.video.module.fragment.adapter

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.video.bean.LocalMusicBean

class LocalMusicAdapter() : BaseRcvAdapter<LocalMusicBean, LocalMusicHolder>() {
    override fun getItemLayoutRes(): Int = LocalMusicHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): LocalMusicHolder = LocalMusicHolder(itemView)

    override fun onBindViewHolder(
        holder: LocalMusicHolder,
        position: Int,
        itemData: LocalMusicBean
    ) {
        holder.setData(itemData)
    }

}