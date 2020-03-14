package com.yizisu.music.and.video.module.add_song_to_album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.utils.*
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
import com.yizisu.music.and.video.module.play_list_detail.PlayListDetailActivity
import com.yizisu.music.and.video.module.search.adapter.SearchHolder
import com.yizisu.music.and.video.utils.dbViewModel
import kotlinx.android.synthetic.main.activity_add_song_to_album.*

class AddSongToAlbumActivity : BaseUiActivity() {
    private data class SongAndAlbum(
        val songs: MutableList<SongInfoTable>,
        val albumInfoTable: AlbumInfoTable?
    )

    companion object {
        fun start(
            appCompatActivity: AppCompatActivity?,
            songs: MutableList<SongInfoTable>,
            albumInfoTable: AlbumInfoTable?
        ) {
            MessageBus.post(
                BusCode.EDIT_SONG,
                SongAndAlbum(songs, albumInfoTable),
                true,
                AddSongToAlbumActivity::class.java
            )
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

    private var oldAlbumInfoTable: AlbumInfoTable? = null

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
    }

    override fun getClickView(): List<View?>? {
        return listOf(selectPlayList, deleteSongTv)
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
                SelectPlayListDialog.show(
                    this,
                    oldAlbumInfoTable
                ) {
                    addNewSongToAlbum(it)
                }
            }
            deleteSongTv -> {
                deleteSongs()
            }
        }
    }

    /**
     * 删除歌曲
     */
    private fun deleteSongs() {
        if (fg.getSelectSongs().isEmpty()) {
            return
        }
        val album = oldAlbumInfoTable ?: return
        showLoadingView()
        launchThread {
            DbHelper.removeSongsFromAlbum(fg.getSelectSongs(), album)
            if (album.dbId == DbCons.ALBUM_ID_HEART) {
                dbViewModel.queryHeartList()
            }
            if (album.dbId == DbCons.ALBUM_ID_RECENT) {
                dbViewModel.queryRecentPlayList()
            }
            runOnUi {
                MessageBus.post(
                    BusCode.REFRESH_PLAY_LIST_DETAIL, oldAlbumInfoTable, false
                )
                R.string.delete_success.toast()
                finish()
            }
        }
    }

    /**
     * 歌曲添加到歌单
     */
    private fun addNewSongToAlbum(data: AlbumInfoTable) {
        showLoadingView()
        launchThread {
            DbHelper.addSongToAlbum(fg.getSelectSongs(), data)
            if (data.dbId == DbCons.ALBUM_ID_HEART) {
                dbViewModel.queryHeartList()
            }
            runOnUi {
                R.string.add_success.toast()
                finish()
            }
        }
    }

    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
        MessageBus.clearStickyValue(event)
        when (code) {
            BusCode.EDIT_SONG -> {
                event.safeGet<SongAndAlbum>()?.let {
                    editSongFg.safeGet<EditSongFragment>()?.let { fg ->
                        fg.setAdapterDatas(it.songs)
                        oldAlbumInfoTable = it.albumInfoTable
                        editSongFg.safeGet<EditSongFragment>()?.setTitle(
                            if (it.albumInfoTable == null) {
                                deleteSongTv.gone()
                                "添加歌曲到歌单"
                            } else {
                                deleteSongTv.visible()
                                "源歌单:${it.albumInfoTable.title}"
                            }
                        )
                        if (it.albumInfoTable?.id == DbCons.ALBUM_ID_CURRENT ||
                            it.albumInfoTable?.id == DbCons.ALBUM_ID_LOCAL
                        ) {
                            deleteSongTv.gone()
                        }
                    }
                }
            }
        }
    }
}
