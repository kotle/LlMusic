package com.yizisu.music.and.video.dialog

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.utils.textFrom
import com.yizisu.basemvvm.view.simpleRcvAdapter
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_select_play_list.*

class SelectPlayListDialog : BaseDialog() {
    companion object {
        fun show(
            appCompatActivity: AppCompatActivity?,
            fiflt: AlbumInfoTable?,
            selectAlbum: Function1<AlbumInfoTable, Unit>
        ): SelectPlayListDialog? {
            appCompatActivity ?: return null
            return SelectPlayListDialog().apply {
                onSelectAlbum = selectAlbum
                fifltAlbumInfoTable = fiflt
                show(appCompatActivity)
            }
        }
    }

    private var fifltAlbumInfoTable: AlbumInfoTable? = null
    private var onSelectAlbum: Function1<AlbumInfoTable, Unit>? = null

    override fun getContentResOrView(): Any? {
        return R.layout.dialog_select_play_list
    }


    private val adapter by lazy {
        simpleRcvAdapter<AlbumInfoTable>(
            allPlayListRcv,
            R.layout.rcv_item_local_music,
            null
        ) { holder, position, itemData ->
            holder.itemView.findViewById<TextView>(R.id.songNameTv)?.textFrom(itemData.title)
            holder.itemView.findViewById<TextView>(R.id.songDesTv)?.textFrom(itemData.des)
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            onSelectAlbum?.invoke(itemData)
            dismiss()
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        AppData.allAlbumData.registerOnSuccess {
            adapter.refreshList(it.filter {
                if (fifltAlbumInfoTable?.dbId == it.dbId) {
                    false
                } else {
                    it.id != DbCons.ALBUM_ID_CURRENT.toString()
                            && it.id != DbCons.ALBUM_ID_LOCAL.toString()
                            && it.id != DbCons.ALBUM_ID_RECENT.toString()
                }
            }.toMutableList())
        }
    }

    override fun getClickView(): List<View?>? {
        return listOf(dialogCreateTv, dialogCancelTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            dialogCreateTv -> {
                CreatePlayListDialog.show(appCompatActivity, null)
            }
            dialogCancelTv -> {
                dismiss()
            }
        }
    }
}