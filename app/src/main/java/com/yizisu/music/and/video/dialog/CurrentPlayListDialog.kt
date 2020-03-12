package com.yizisu.music.and.video.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.fragment.test.adapter.LocalMusicAdapter
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.dialog_current_play_list.*

class CurrentPlayListDialog : BaseDialog() {
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

    private val adapter = SearchAdapter()
    override fun getContentResOrView(): Any? = R.layout.dialog_current_play_list

    override fun initViewModel() {
        super.initViewModel()
        AppData.currentPlayList.registerOnSuccess {
            if (it.isNullOrEmpty()) {
                dismiss()
            } else {
                setAdapter(it.map {
                    (it as SongModel).song
                }.toMutableList())
            }
        }
        AppData.currentPlaySong.registerOnSuccess {
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
                currentSongTv.textFromSpanBean(
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
    }

    private fun setAdapter(list: MutableList<SongInfoTable>) {
        currentPlayListRcv.adapter = adapter
        adapter.isNeedMusicJumpView = true
        adapter.refreshList(list)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            MusicService.startPlay(
                adapter.datas.map {
                    SongModel(it)
                }.toMutableList(), position, false,
                true
            )
        }
        scrollCurrent()
    }

    override fun onRootViewLayoutParams(lp: FrameLayout.LayoutParams) {
        super.onRootViewLayoutParams(lp)
        lp.marginStart = 0
        lp.marginEnd = 0
        lp.topMargin = dip(240)
        lp.gravity = Gravity.BOTTOM
    }

    override fun isCanceledOnTouchOutside(): Boolean {
        return true
    }

    override fun getClickView(): List<View?>? {
        return listOf(currentSongTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            currentSongTv -> {
                scrollCurrent()
            }
        }
    }

    private fun scrollCurrent() {
        if (AppData.currentPlayIndex >= 0) {
            currentPlayListRcv.post {
                //用layoutManager的滚动，定位更准确
                //currentPlayListRcv滚动总是在最下面
                currentPlayListRcv.layoutManager.safeGet<LinearLayoutManager>()
                    ?.scrollToPositionWithOffset(AppData.currentPlayIndex, dip(0))
            }
        }
    }
}