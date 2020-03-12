package com.yizisu.music.and.video.module.fragment.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.yizisu.basemvvm.utils.gone
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.visible
import com.yizisu.basemvvm.widget.BaseLinearLayout
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.createNormalAlbum
import com.yizisu.music.and.video.AppData

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.dialog.CreatePlayListDialog
import com.yizisu.music.and.video.module.fragment.home.adapter.HomeItemImageAdapter
import com.yizisu.music.and.video.module.play_list_detail.PlayListDetailActivity
import com.yizisu.music.and.video.utils.refreshAllAlbum
import kotlinx.android.synthetic.main.fragment_recent_play.*

class RecentPlayFragment : BaseFragment() {

    private val adapter = HomeItemImageAdapter()
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_recent_play
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            PlayListDetailActivity.start(appCompatActivity, itemData.dbId)
        }
        recentPlayRcv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        refreshAllAlbum()
    }

    override fun initViewModel() {
        super.initViewModel()
        AppData.allAlbumData.registerOnSuccess {
            val playLists = it.filter {
                it.id == DbCons.ALBUM_ID_NORMAL
            }.toMutableList()
            if (playLists.isNullOrEmpty()) {
                noPlayListHintTv.visible()
            } else {
                noPlayListHintTv.gone()
            }
            adapter.refreshList(playLists.asReversed())
        }
    }

    override fun getClickView(): List<View?>? {
        return listOf(createPlayListTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            createPlayListTv -> {
                CreatePlayListDialog.show(appCompatActivity)
            }
        }
    }


}
