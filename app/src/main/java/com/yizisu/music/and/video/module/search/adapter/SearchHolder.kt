package com.yizisu.music.and.video.module.search.adapter

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.view.MusicJumpView

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_search_music
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val songDes = itemView.findViewById<TextView>(R.id.songDesTv)
    private val songSource = itemView.findViewById<TextView>(R.id.songSourceTv)
    private val songCover = itemView.findViewById<ImageView>(R.id.songCoverIv)
    private val musicJumpFl = itemView.findViewById<FrameLayout>(R.id.musicJumpFl)
    fun setData(bean: SongInfoTable, keywords: String?, isNeedMusicJumpView: Boolean = false) {
        //添加当前播放的跳动音符
        if (isNeedMusicJumpView
            && AppData.currentPlayIndex == adapterPosition
        ) {
            musicJumpFl.addView(
                MusicJumpView(itemView.context).apply {
                    pointerColor = getResColor(R.color.colorAccent)
                    pointerNum = 5
                    pointerWidth = 6f
                }, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
                )
            )
        } else {
            musicJumpFl.removeAllViews()
        }
        if (keywords.isNullOrBlank()) {
            setUi(bean)
        } else {
            setUi(bean)
        }
    }

    private fun setUi(bean: SongInfoTable) {
        songName.textFrom(bean.name)
        when (bean.type) {
            //VIP
            DbCons.TYPE_VIP -> {
                songDes.textFromSpanBean(
                    mutableListOf(
                        SpanBean("[VIP] ", textColor = Color.RED),
                        SpanBean(bean.des)
                    )
                )
            }
            else -> {
                songDes.textFrom((bean.des))
            }
        }
//        songSource.textFrom("${getResString(R.string.song_source)}：${bean.type}")
        songCover.setImageGlide(bean.coverUrlPath, R.drawable.default_cover_icon, 4)
    }

}