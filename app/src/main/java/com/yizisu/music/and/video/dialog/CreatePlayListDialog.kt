package com.yizisu.music.and.video.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.createNormalAlbum
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.utils.refreshAllAlbum
import kotlinx.android.synthetic.main.dialog_create_play_list.*

class CreatePlayListDialog : BaseDialog() {
    companion object {
        fun show(
            appCompatActivity: AppCompatActivity?,
            itemData: AlbumInfoTable?
        ): CreatePlayListDialog? {
            appCompatActivity ?: return null
            return CreatePlayListDialog().apply {
                oldAlbumInfo = itemData
                show(appCompatActivity)
            }
        }
    }

    private var oldAlbumInfo: AlbumInfoTable? = null

    override fun getContentResOrView(): Any? {
        return R.layout.dialog_create_play_list
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        titleTv.textFrom(if (oldAlbumInfo == null) R.string.create_play_list else R.string.edit_play_list)
        oldAlbumInfo?.let {
            titleEt.hint = it.title
            desEt.hint = it.des
            dialogDeleteTv.visible()
        }
        appCompatActivity?.showKeyboard(titleEt)
    }

    override fun getClickView(): List<View?>? {
        return listOf(dialogOkTv, dialogCancelTv, dialogDeleteTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            dialogCancelTv -> {
                dismiss()
            }
            dialogDeleteTv -> {
                deleteMyAlbum()
            }
            dialogOkTv -> {
                val old = oldAlbumInfo
                val title = titleEt.text.toString()
                val des = desEt.text.toString()
                if (old == null) {
                    if (title.isBlank()) {
                        hintTv.visible()
                        return
                    } else {
                        titleEt.invisible()
                    }
                    createNewPlayList(title, des)
                } else {
                    if (title.isNotBlank()) {
                        old.title = title
                    }
                    if (des.isNotBlank()) {
                        old.des = des
                    }
                    editNewPlayList()
                }
            }
        }
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    /**
     * 删除歌单
     */
    private fun deleteMyAlbum() {
        showLoadingView()
        oldAlbumInfo?.let {
            launchThread {
                DbHelper.deleteAlbum(it.dbId)
                refreshAllAlbum()
                switchToUi {
                    dismiss()
                }
            }
        }
    }

    /**
     * 创建新歌单
     */
    private fun createNewPlayList(name: String, des: String?) {
        showLoadingView()
        launchThread {
            createNormalAlbum(name, des)
            refreshAllAlbum()
            switchToUi {
                dismiss()
            }
        }
    }

    /**
     * 编辑歌单
     */
    private fun editNewPlayList() {
        showLoadingView()
        launchThread {
            oldAlbumInfo?.let {
                DbHelper.insetOrUpdateAlbum(it)
                refreshAllAlbum()
            }
            switchToUi {
                dismiss()
            }
        }
    }
}