package com.yizisu.music.and.video.module.search.adapter

import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.Point
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.mvvm.MvvmPopupWindow
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.widget.BaseLinearLayout
import com.yizisu.basemvvm.widget.BasePopupWindow
import com.yizisu.basemvvm.widget.BaseTextView
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.utils.dbViewModel
import com.yizisu.music.and.video.view.MusicJumpView

class SearchHolder(itemView: View, val adapter: SearchAdapter) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_search_music
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val songDes = itemView.findViewById<TextView>(R.id.songDesTv)
    private val songSource = itemView.findViewById<TextView>(R.id.songSourceTv)
    private val songCover = itemView.findViewById<ImageView>(R.id.songCoverIv)
    private val editEt = itemView.findViewById<ImageView>(R.id.editEt)
    private val musicJumpFl = itemView.findViewById<FrameLayout>(R.id.musicJumpFl)

    init {
        editEt.setOnClickListener {
            showPopup(it)
        }
    }


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


    /**
     * 显示Popup
     */
    private fun showPopup(it: View?) {
        var ctx = it?.context ?: return
        if (ctx !is AppCompatActivity) {
            if (ctx is ContextWrapper) {
                ctx = ctx.baseContext
            }
        }
        val song = adapter.datas[layoutPosition]
        val touch = intArrayOf(0, 0)
        val screenPoint = Point()
        it.display.getRealSize(screenPoint)
        it.getLocationInWindow(touch)
        val isShowBottom = touch[1] < screenPoint.y / 2
        var popupWindow: PopupWindow? = null
        val rootView = BaseLinearLayout(
            ctx
        ).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dip(16), 0, dip(16), 0)
            setBackgroundResource(R.drawable.bg_dialog_play_list_8_r)
            addView(BaseTextView(ctx).apply {
                text = "歌曲：${song.name}"
                setLines(1)
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                textSize = 12f
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = "添加到歌单"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    popupWindow?.dismiss()
                    SelectPlayListDialog.show(ctx.safeGet()) {
                        launchThread {
                            DbHelper.addSongToAlbum(song, it)
                            if (it.dbId == DbCons.ALBUM_ID_HEART) {
                                dbViewModel.queryHeartList()
                            }
                            "${song.name} 已经添加到 ${it.title}".toast(Toast.LENGTH_LONG)
                        }
                    }
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                text = "批量操作"
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    SearchAdapter.toAllSongPage(this, adapter.datas)
                    popupWindow?.dismiss()
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
        }
        popupWindow = MvvmPopupWindow(
            rootView, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            if (isShowBottom) {
                animationStyle = R.style.PopupWindowScaleEndTopIn
                showAtLocation(it, Gravity.TOP or Gravity.END, dip(16), touch[1] + it.height)
            } else {
                animationStyle = R.style.PopupWindowScaleEndTopInToTop
                showAtLocation(it, Gravity.BOTTOM or Gravity.END, dip(16), screenPoint.y - touch[1])
            }

        }
    }
}