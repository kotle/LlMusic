package com.yizisu.music.and.video.module.fragment.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.utils.LocalMusicUtil

class HomeItemImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.item_home_image
    }

    private val coverIv = itemView.findViewById<ImageView>(R.id.coverIv)
    private val titleTv = itemView.findViewById<TextView>(R.id.titleTv)
    private val desTv = itemView.findViewById<TextView>(R.id.desTv)

    /**
     * 设置数据
     */
    fun setData(bean: AlbumInfoTable) {
        val lp = itemView.layoutParams as RecyclerView.LayoutParams
        if (layoutPosition == 0) {
            lp.marginStart = dip(12)
        } else {
            lp.marginStart = 0
        }
        val url = bean.filePath ?: bean.urlPath
        coverIv.setImageGlide(url, R.drawable.default_cover_icon, radius = dip(1))
        titleTv.textFrom(bean.title)
        desTv.textFrom(bean.des)
    }
}