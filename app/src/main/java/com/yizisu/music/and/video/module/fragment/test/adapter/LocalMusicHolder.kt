package com.yizisu.music.and.video.module.fragment.test.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.utils.textFrom
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.LocalMusicBean

class LocalMusicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_local_music
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val songDes = itemView.findViewById<TextView>(R.id.songDesTv)

    fun setData(bean: SongInfoTable) {
        songName.textFrom(bean.name)
        songDes.textFrom((bean.des))
    }
}