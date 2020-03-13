package com.yizisu.music.and.video.module.add_song_to_album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.utils.textFrom
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.baselib.base.ListDialog
import com.yizisu.music.and.video.cons.BusCode
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.fragment.edit_song.EditSongFragment
import com.yizisu.music.and.video.utils.dbViewModel
import kotlinx.android.synthetic.main.activity_add_song_to_album.*

class AddSongToAlbumActivity : BaseUiActivity() {
    companion object {
        fun start(appCompatActivity: AppCompatActivity?, songs: MutableList<SongInfoTable>) {
            MessageBus.post(BusCode.EDIT_SONG, songs, true, AddSongToAlbumActivity::class.java)
            appCompatActivity?.navigateTo(AddSongToAlbumActivity::class.java)
        }
    }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_add_song_to_album
    }

    private val fg: EditSongFragment
        get() = editSongFg as EditSongFragment

    override fun isNeedToolbar(): Boolean {
        return false
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        editSongFg.safeGet<EditSongFragment>()?.setTitle("添加歌曲")
    }

    override fun getClickView(): List<View?>? {
        return listOf(selectPlayList)
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            selectPlayList -> {
                if (fg.getSelectSongs().isEmpty()) {
                    return
                }
                SelectPlayListDialog.show(this) {
                    addNewSongToAlbum(it)
                }
            }
        }
    }

    private fun addNewSongToAlbum(data: AlbumInfoTable) {
        showLoadingView()
        launchThread {
            DbHelper.addSongToAlbum(fg.getSelectSongs(), data)
            if (data.dbId == DbCons.ALBUM_ID_HEART) {
                dbViewModel.queryHeartList()
            }
            finish()
        }
    }

    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
        MessageBus.clearStickyValue(event)
        when (code) {
            BusCode.EDIT_SONG -> {
                event.safeGet<MutableList<SongInfoTable>>()?.let {
                    editSongFg.safeGet<EditSongFragment>()?.setAdapterDatas(it)
                }
            }
        }
    }
}
