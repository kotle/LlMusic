package com.yizisu.music.and.video.module.fragment.test.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.utils.setImageGlide
import com.yizisu.basemvvm.utils.textFrom
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.LocalMusicBean

class LocalMusicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_local_music
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val coverIv = itemView.findViewById<ImageView>(R.id.coverIv)

    fun setData(bean: LocalMusicBean) {
        songName.textFrom(bean.title)
        coverIv.setImageGlide(bean.path)
    }
}