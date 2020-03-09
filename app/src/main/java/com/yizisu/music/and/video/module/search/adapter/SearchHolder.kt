package com.yizisu.music.and.video.module.search.adapter

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.dongwo.SearchBean

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_search_music
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val songDes = itemView.findViewById<TextView>(R.id.songDesTv)
    private val songSource = itemView.findViewById<TextView>(R.id.songSourceTv)
    private val songCover = itemView.findViewById<ImageView>(R.id.songCoverIv)

    fun setData(bean: SearchBean.DataBean) {
        songName.textFrom(bean.title)
        when (bean.fee) {
            //VIP
            1L -> {
                songDes.textFromSpanBean(
                    mutableListOf(
                        SpanBean("[VIP] ", textColor = Color.RED),
                        SpanBean(bean.author)
                    )
                )
            }
            else -> {
                songDes.textFrom((bean.author))
            }
        }
        songSource.textFrom("${getResString(R.string.song_source)}：${bean.type}")
        songCover.setImageGlide(bean.pic, R.drawable.default_cover_icon, 4)
    }
}