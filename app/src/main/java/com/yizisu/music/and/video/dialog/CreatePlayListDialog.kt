package com.yizisu.music.and.video.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.createNormalAlbum
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.utils.refreshAllAlbum
import kotlinx.android.synthetic.main.dialog_create_play_list.*

class CreatePlayListDialog : BaseDialog() {
    companion object {
        fun show(appCompatActivity: AppCompatActivity?): CreatePlayListDialog? {
            appCompatActivity ?: return null
            return CreatePlayListDialog().apply {
                show(appCompatActivity)
            }
        }
    }

    override fun getContentResOrView(): Any? {
        return R.layout.dialog_create_play_list
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        appCompatActivity?.showKeyboard(titleEt)
    }

    override fun getClickView(): List<View?>? {
        return listOf(dialogOkTv, dialogCancelTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            dialogCancelTv -> {
                dismiss()
            }
            dialogOkTv -> {
                val title = titleEt.text.toString()
                if (title.isBlank()) {
                    hintTv.visible()
                    return
                } else {
                    titleEt.invisible()
                }
                createNewPlayList(title, desEt.text.toString())
            }
        }
    }

    private fun createNewPlayList(name: String, des: String?) {
        showLoadingView()
        launchThread {
            createNormalAlbum(name, des)
            refreshAllAlbum()
            runOnUi {
                dismiss()
            }
        }
    }
}