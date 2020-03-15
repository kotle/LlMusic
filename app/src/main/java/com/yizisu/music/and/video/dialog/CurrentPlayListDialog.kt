package com.yizisu.music.and.video.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanValue
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.service.music.MusicService

class CurrentPlayListDialog : BottomSheetDialog {
    constructor(context: Context) : this(context, R.style.AppTheme)
    constructor(context: Context, theme: Int) : super(context, theme)
    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    companion object {
        fun show(
            appCompatActivity: AppCompatActivity?
        ): CurrentPlayListDialog? {
            appCompatActivity ?: return null
            return CurrentPlayListDialog(appCompatActivity).apply {
                val view = LayoutInflater.from(appCompatActivity).inflate(
                    R.layout.dialog_current_play_list, null, false
                )
                setContentView(view)
                window?.findViewById<View>(R.id.design_bottom_sheet)
                    ?.setBackgroundResource(android.R.color.transparent)
                initUi(view)
                show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        window?.apply {
            transparentStatusBar()
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#96000000")))
        }
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        AppData.currentPlaySong.removeObserver(currentPlaySongObserver)
        AppData.dbCurrentAlbumData.removeObserver(dbCurrentAlbumDataObserver)
    }

    private val adapter = SearchAdapter(AppData.dbCurrentAlbumData.data)
    private val dbCurrentAlbumDataObserver = Observer<LiveBeanValue<AlbumInfoTable>> {
        if (it.value?.songInfoTables.isNullOrEmpty()) {
            dismiss()
        } else {
            setAdapter(it.value?.songInfoTables)
        }
    }
    private val currentPlaySongObserver = Observer<LiveBeanValue<SongModel?>> {
        it?.value?.song?.apply {
            val title = if (name.length > 16) {
                name.substring(0, 14) + "..."
            } else {
                name
            }
            val desText = if (des.length > 16) {
                des.substring(0, 14) + "..."
            } else {
                des
            }
            currentSongTv?.textFromSpanBean(
                mutableListOf(
                    SpanBean(title + "\n"),
                    SpanBean(
                        desText,
                        textColor = getResColor(R.color.colorTextLight),
                        textSize = 10
                    )
                )
            )
        }
    }
//    override fun getContentResOrView(): Any? = R.layout.dialog_current_play_list


    private fun setAdapter(list: MutableList<SongInfoTable>?) {
        adapter.refreshList(list)
        scrollCurrent()
    }

    private val currentPlayListRcv by lazy { window?.findViewById<RecyclerView>(R.id.currentPlayListRcv) }

    private val currentSongTv by lazy { window?.findViewById<TextView>(R.id.currentSongTv) }

    fun initUi(parent: View) {
        AppData.currentPlaySong.observeForever(currentPlaySongObserver)
        AppData.dbCurrentAlbumData.observeForever(dbCurrentAlbumDataObserver)
        currentPlayListRcv?.adapter = adapter
        adapter.isNeedMusicJumpView = true
        adapter.setOnItemClickListener { itemView, position, itemData ->
            MusicService.startPlay(
                adapter.datas.map {
                    SongModel(it)
                }.toMutableList(), position, false,
                true
            )
            adapter.notifyDataSetChanged()
        }
        parent.findViewById<View>(R.id.currentSongTv)?.setOnClickListener {
            scrollCurrent()
        }
    }


    private fun scrollCurrent() {
        if (AppData.currentPlayIndex >= 0) {
            currentPlayListRcv?.post {
                //用layoutManager的滚动，定位更准确
                //currentPlayListRcv滚动总是在最下面
                currentPlayListRcv?.layoutManager.safeGet<LinearLayoutManager>()
                    ?.scrollToPositionWithOffset(AppData.currentPlayIndex, dip(0))
            }
        }
    }
}