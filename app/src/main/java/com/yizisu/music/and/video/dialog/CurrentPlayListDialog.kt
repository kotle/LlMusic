package com.yizisu.music.and.video.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yizisu.basemvvm.mvvm.MvvmBottomSheetDialog
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanValue
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerOnSuccessLiveBean
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseBottomSheetDialog
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.service.music.MusicService
import kotlinx.android.synthetic.main.dialog_current_play_list.*

class CurrentPlayListDialog : BaseBottomSheetDialog() {
    companion object {
        fun show(
            appCompatActivity: AppCompatActivity?
        ): CurrentPlayListDialog? {
            appCompatActivity ?: return null
            return CurrentPlayListDialog().apply {
                show(appCompatActivity)
            }
        }
    }

    override fun isCanceledOnTouchOutside(): Boolean {
        return true
    }

    private val adapter = SearchAdapter(AppData.dbCurrentAlbumData.data)

    override fun getContentResOrView(): Any? {
        return R.layout.dialog_current_play_list
    }

    override fun initViewModel() {
        super.initViewModel()
        registerOnSuccessLiveBean(AppData.dbCurrentAlbumData) {
            if (it.songInfoTables.isNullOrEmpty()) {
                dismiss()
            } else {
                setAdapter(it.songInfoTables)
            }
        }
        registerOnSuccessLiveBean(AppData.currentPlaySong) {
            it?.song?.apply {
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
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setAdapter(list: MutableList<SongInfoTable>?) {
        adapter.refreshList(list)
        scrollCurrent()
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)

        currentPlayListRcv?.adapter = adapter
        adapter.isNeedMusicJumpView = true
        adapter.setOnItemClickListener { itemView, position, itemData ->
            MusicService.startPlay(
                adapter.datas.map {
                    SongModel(it)
                }.toMutableList(), AppData.currentPlayListByAlbumId,
                position, false,
                true
            )
        }
        currentSongTv.setOnClickListener {
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

    override fun onRootViewLayoutParams(lp: FrameLayout.LayoutParams) {
        super.onRootViewLayoutParams(lp)
        lp.marginStart = 0
        lp.marginEnd = 0
        lp.gravity = Gravity.BOTTOM
    }
}