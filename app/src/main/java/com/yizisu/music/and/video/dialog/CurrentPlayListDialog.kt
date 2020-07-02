package com.yizisu.music.and.video.dialog

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanWrap
import com.yizisu.basemvvm.mvvm.mvvm_helper.wrapOnSuccessWithCall
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.service.music.MusicService
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

    override fun isCanceledOnTouchOutside(): Boolean {
        return true
    }

    private val adapter = SearchAdapter(AppData.dbCurrentAlbumData.data)

    override fun getContentResOrView(): Any? {
        return R.layout.dialog_current_play_list
    }

    override fun getObserverLiveBean(): List<LiveBeanWrap>? {
        return listOf(
            AppData.dbCurrentAlbumData.wrapOnSuccessWithCall {
                if (it.songInfoTables.isNullOrEmpty()) {
                    dismiss()
                } else {
                    setAdapter(it.songInfoTables)
                }
            },
            AppData.currentPlaySong.wrapOnSuccessWithCall {
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
        )
    }


    private fun setAdapter(list: MutableList<SongInfoTable>?) {
        adapter.refreshList(list)
        scrollCurrent()
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        appCompatActivity?.getScreenSize()?.y?.let { screenHeight ->
            currentPlayListRcv.layoutParams.also { lp ->
                lp.height = screenHeight * 2 / 3
            }
        }
        //滚动动画结束
        fun finishListener(isReset: Boolean) {
            if (!isReset) {
                dismiss()
            }
        }
        scrollDownView.setOnStopScrollListener { parent, target, unConsumedY ->
            val isReset = unConsumedY <= target.height / 3
            scrollDownView.stop(isReset, finishListener = ::finishListener)
        }
        currentPlayListRcv?.setHasFixedSize(true)
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