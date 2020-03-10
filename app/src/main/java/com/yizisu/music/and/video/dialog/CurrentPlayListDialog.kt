package com.yizisu.music.and.video.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.utils.dip
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.utils.transparentStatusBar
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.fragment.test.adapter.LocalMusicAdapter
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

    private val adapter = LocalMusicAdapter()
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
    }

    private fun setAdapter(list: MutableList<SongInfoTable>) {
        currentPlayListRcv.adapter = adapter
        adapter.refreshList(list)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            MusicService.startPlay(
                adapter.datas.map {
                    SongModel(it)
                }.toMutableList(), position, false,
                true
            )
        }
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
}