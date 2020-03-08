package com.yizisu.music.and.video.module.search.adapter

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.video.bean.dongwo.SearchBean

class SearchAdapter:BaseRcvAdapter<SearchBean.DataBean,SearchHolder>() {
    override fun getItemLayoutRes(): Int =SearchHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): SearchHolder= SearchHolder(itemView)

    override fun onBindViewHolder(
        holder: SearchHolder,
        position: Int,
        itemData: SearchBean.DataBean
    ) {
        holder.setData(itemData)
    }
}