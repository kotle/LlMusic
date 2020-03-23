package com.yizisu.music.and.video.module.fragment.down_task

import android.view.View
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.music.and.video.utils.DownSongWithNotification

class DownloadingAdapter :
    BaseRcvAdapter<DownSongWithNotification.DownSongBean, DownloadingHolder>(
        DownSongWithNotification.downloadingList
    ) {
    override fun getItemLayoutRes(): Int = DownloadingHolder.LAYOUT_RES

    override fun onCreateViewHolder(itemView: View): DownloadingHolder = DownloadingHolder(itemView)

    override fun onBindViewHolder(
        holder: DownloadingHolder,
        position: Int,
        itemData: DownSongWithNotification.DownSongBean
    ) {
        holder.setData(itemData)
    }
}