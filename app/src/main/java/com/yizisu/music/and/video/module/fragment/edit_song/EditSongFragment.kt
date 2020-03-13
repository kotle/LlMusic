package com.yizisu.music.and.video.module.fragment.edit_song


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_edit_song.*


class EditSongFragment : BaseFragment() {
    private val adapter = EditSongAdapter()
    override fun getContentResOrView(inflater: LayoutInflater): Any? = R.layout.fragment_edit_song

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        editSongToolbar.setNavigationOnClickListener {
            appCompatActivity?.onBackPressed()
        }
        editSongRcv.adapter = adapter
        editSongToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.selectAll -> {
                    adapter.datas.forEach {
                        it.isCheck = true
                    }
                    adapter.notifyDataSetChanged()
                }
                R.id.selectRever -> {
                    adapter.datas.forEach {
                        it.isCheck = !it.isCheck
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            true
        }
    }

    fun setTitle(title: CharSequence?) {
        editSongToolbar.title = title
    }

    fun setAdapterDatas(list: MutableList<SongInfoTable>) {
        adapter.refreshList(list.map {
            EditSongAdapter.EditSongBean(it, false)
        }.toMutableList())
    }

    /**
     * 获取选中的选项
     */
    fun getSelectSongs(): MutableList<SongInfoTable> {
        return adapter.datas.filter {
            it.isCheck
        }.map {
            it.song
        }.toMutableList()
    }

    /**
     * 获取未选中的选项
     */
    fun getUnSelectSongs(): MutableList<SongInfoTable> {
        return adapter.datas.filter {
            !it.isCheck
        }.map {
            it.song
        }.toMutableList()
    }
}
