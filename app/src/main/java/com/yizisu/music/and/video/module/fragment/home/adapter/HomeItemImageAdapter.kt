package com.yizisu.music.and.video.module.fragment.home.adapter

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.video.bean.LocalMusicBean

class HomeItemImageAdapter : BaseRcvAdapter<LocalMusicBean, HomeItemImageHolder>() {
    override fun getItemLayoutRes(): Int = HomeItemImageHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): HomeItemImageHolder =
        HomeItemImageHolder(itemView)

    override fun onBindViewHolder(
        holder: HomeItemImageHolder,
        position: Int,
        itemData: LocalMusicBean
    ) {
        holder.setData(itemData)
    }

}