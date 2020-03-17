package com.yizisu.music.and.video.module.add_song_to_album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongWithAlbum
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.cons.BusCode
import com.yizisu.music.and.video.module.fragment.edit_song.EditSongFragment
import com.yizisu.music.and.video.module.play_list_detail.PlayListDetailActivity
import com.yizisu.music.and.video.utils.dbViewModel
import kotlinx.android.synthetic.main.activity_import_song.*
import kotlinx.android.synthetic.main.activity_test_db.*

class ImportSongActivity : BaseUiActivity() {
    private data class ImportSongBean(
        val source: AlbumInfoTable,
        val target: AlbumInfoTable
    )

    companion object {
        fun start(
            appCompatActivity: AppCompatActivity?,
            source: AlbumInfoTable?,
            target: AlbumInfoTable?
        ) {
            appCompatActivity ?: return
            source ?: return
            target ?: return
            appCompatActivity.navigateTo(ImportSongActivity::class.java)
            MessageBus.post(
                BusCode.ALBUM_INFO,
                ImportSongBean(source, target),
                true,
                ImportSongActivity::class.java
            )
        }
    }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_import_song
    }

    private val fg: EditSongFragment
        get() = editSongFg as EditSongFragment

    override fun isNeedToolbar(): Boolean {
        return false
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
    }

    override fun getClickView(): List<View?>? {
        return listOf(addAlbumBt)
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    private var sourceAlbum: AlbumInfoTable? = null
    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            addAlbumBt -> {
                val album = sourceAlbum ?: return
                val songs = fg.getSelectSongs()
                if (songs.isNotEmpty()) {
                    addAlbumBt.isEnabled = false
                    showLoadingView()
                    launchThread {
                        DbHelper.addSongToAlbum(songs, album)
                        if (album.id == DbCons.ALBUM_ID_HEART.toString()) {
                            dbViewModel.queryHeartList()
                        }
                        runOnUi {
                            MessageBus.post(
                                BusCode.REFRESH_PLAY_LIST_DETAIL, album, false
                            )
                            R.string.import_song_success.toast()
                            finish()
                        }
                    }
                }
            }
        }
    }

    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
        MessageBus.clearStickyValue(event)
        when (code) {
            BusCode.ALBUM_INFO -> {
                event.safeGet<ImportSongBean>()?.let {
                    sourceAlbum = it.source
                    fg.setTitle(it.target.title)
                    addAlbumBt.text = "导入到 ${it.source.title}"
                    launchThread {
                        val list = it.target.songInfoTables
                        runOnUi {
                            fg.setAdapterDatas(list)
                        }
                    }
                }
            }
        }
    }

}
