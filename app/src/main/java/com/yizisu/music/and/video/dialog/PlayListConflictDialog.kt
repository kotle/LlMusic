package com.yizisu.music.and.video.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_play_list_conflict.*

class PlayListConflictDialog : BaseDialog() {
    companion object {
        const val SELECT_REPLACE = 1
        const val SELECT_CREATE_NEW = 2
        fun show(appCompatActivity: AppCompatActivity?, text: String, call: Function1<Int, Unit>) {
            appCompatActivity ?: return
            PlayListConflictDialog().apply {
                selectCall = call
                hintText = text
                show(appCompatActivity)
            }
        }
    }

    private var selectCall: Function1<Int, Unit>? = null
    private var hintText: String? = null
    override fun getContentResOrView(): Any? = R.layout.dialog_play_list_conflict

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        hintTv.text = hintText
    }

    override fun getClickView(): List<View?>? {
        return listOf(replaceTv, createNewTv, dialogCancelTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            replaceTv -> {
                selectCall?.invoke(SELECT_REPLACE)
            }
            createNewTv -> {
                selectCall?.invoke(SELECT_CREATE_NEW)
            }
            dialogCancelTv -> {
                dismiss()
            }
        }
    }
}