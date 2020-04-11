package com.yizisu.music.and.video.module.fragment.home


import android.content.ContextWrapper
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.mvvm.MvvmPopupWindow
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerOnSuccessLiveBean
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.widget.BaseLinearLayout
import com.yizisu.basemvvm.widget.BaseTextView
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.video.AppData

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.dialog.CreatePlayListDialog
import com.yizisu.music.and.video.dialog.ImportPlayListDialog
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.fragment.home.adapter.HomeItemImageAdapter
import com.yizisu.music.and.video.module.play_list_detail.PlayListDetailActivity
import com.yizisu.music.and.video.utils.dbViewModel
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
            PlayListDetailActivity.start(appCompatActivity, itemData)
        }
        adapter.setOnItemLongClickListener { itemView, position, itemData ->
            CreatePlayListDialog.show(appCompatActivity, itemData)
        }
        recentPlayRcv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        refreshAllAlbum()
    }

    override fun initViewModel() {
        super.initViewModel()
        registerOnSuccessLiveBean(AppData.allAlbumData) {
            val playLists = it.filter {
                it.id != DbCons.ALBUM_ID_LOCAL.toString()
                        && it.id != DbCons.ALBUM_ID_RECENT.toString()
                        && it.id != DbCons.ALBUM_ID_DOWNLOADED.toString()
                        && it.id != DbCons.ALBUM_ID_CURRENT.toString()
                        && it.id != DbCons.ALBUM_ID_HEART.toString()
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
        return listOf(createPlayListTv, importPlayListTv, morePlayListTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            createPlayListTv -> {
                CreatePlayListDialog.show(appCompatActivity, null)
            }
            importPlayListTv -> {
                ImportPlayListDialog.show(appCompatActivity)
            }
            morePlayListTv -> {
                showPopup(view)
            }
        }
    }

    /**
     * 显示Popup
     */
    private fun showPopup(it: View?) {
        val ctx = it?.context ?: return
        var popupWindow: PopupWindow? = null
        val rootView = BaseLinearLayout(
            ctx
        ).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dip(16), 0, dip(16), 0)
            setBackgroundResource(R.drawable.bg_dialog_play_list_8_r)
            addView(BaseTextView(ctx).apply {
                text = "长按歌单可编辑"
                setLines(1)
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.GRAY)
                textSize = 12f
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                setText(R.string.create_play_list)
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    popupWindow?.dismiss()
                    onSingleClick(createPlayListTv)
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            addView(BaseTextView(ctx).apply {
                setText(R.string.import_playlist)
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(Color.BLACK)
                setOnClickListener {
                    popupWindow?.dismiss()
                    onSingleClick(importPlayListTv)
                }
            }, LinearLayout.LayoutParams(dip(120), dip(40)))
            /*       addView(BaseTextView(ctx).apply {
                       text = "查看更多"
                       gravity = Gravity.CENTER_VERTICAL
                       setTextColor(Color.BLACK)
                       setOnClickListener {
                           popupWindow?.dismiss()
                       }
                   }, LinearLayout.LayoutParams(dip(120), dip(40)))*/
        }
        popupWindow = MvvmPopupWindow(
            rootView, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            showAsDropTopOrBottom(it, true, 0, dip(8))
        }
    }

}
