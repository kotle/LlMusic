package com.yizisu.music.and.video.module.play_list_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.launchUi
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.cons.BusCode
import com.yizisu.music.and.video.dialog.SelectPlayListDialog
import com.yizisu.music.and.video.module.add_song_to_album.ImportSongActivity
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.utils.LocalMusicUtil
import com.yizisu.music.and.video.utils.dbViewModel
import kotlinx.android.synthetic.main.activity_play_list_detail.*

class PlayListDetailActivity : BaseUiActivity() {
    companion object {
        fun start(appCompatActivity: AppCompatActivity?, albumDbId: AlbumInfoTable?) {
            albumDbId ?: return
            appCompatActivity?.navigateTo(PlayListDetailActivity::class.java)
            MessageBus.post(BusCode.ALBUM_INFO, albumDbId, true, PlayListDetailActivity::class.java)
        }
    }

    private val adapter by lazy { SearchAdapter(currentAlbumInfoTable) }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_play_list_detail
    }

    override fun isNeedToolbar(): Boolean {
        return true
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    private var currentAlbumInfoTable: AlbumInfoTable? = null
    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        getSwitchView()?.apply {
            isCanSwipeRefresh = true
            setOnRefreshListener {
                refreshData()
            }
        }
        getToolbar()?.apply {
            setTheme(R.style.ToolBarMenu)
        }
    }

    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
        MessageBus.clearStickyValue(event)
        when (code) {
            BusCode.ALBUM_INFO -> {
                event.safeGet<AlbumInfoTable>()?.let {
                    currentAlbumInfoTable = it
                    playListDetailRcv.adapter = adapter
                    refreshData()
                }
            }
            BusCode.REFRESH_PLAY_LIST_DETAIL -> {
                refreshData()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (currentAlbumInfoTable?.id == DbCons.ALBUM_ID_HEART.toString()
            || currentAlbumInfoTable?.id == DbCons.ALBUM_ID_NORMAL.toString()
        ) {
            menuInflater.inflate(R.menu.menu_play_list_detail, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.importSonsMenu -> {
                SelectPlayListDialog.show(this, currentAlbumInfoTable) {
                    ImportSongActivity.start(this, currentAlbumInfoTable, it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //查询本地音乐
    private var localSongs: MutableList<SongInfoTable>? = null

    private fun refreshData() {
        val album = currentAlbumInfoTable ?: return
        showLoadingView()
        launchThread {
            if (album.dbId == DbCons.ALBUM_ID_LOCAL && localSongs == null) {
                localSongs = LocalMusicUtil.getSongInfos(this@PlayListDetailActivity).asReversed()
                DbHelper.removeAllSongsFromAlbum(DbCons.ALBUM_ID_LOCAL)
                DbHelper.addSongToAlbum(localSongs, album)
                AppData.dbLocalAlbumData.success(album)
            }
            album.resetSongInfoTables()
            val songs = album.songInfoTables?.asReversed()
            if (!songs.isNullOrEmpty()) {
                val firstSong = songs[0]
                if (!firstSong.coverFilePath.isNullOrBlank()) {
                    album.filePath = firstSong.coverFilePath
                }
                if (!firstSong.coverUrlPath.isNullOrBlank()) {
                    album.urlPath = firstSong.coverUrlPath
                }
                DbHelper.insetOrUpdateAlbum(album)
            }
            refreshList(album, songs)
        }
    }

    private fun refreshList(
        album: AlbumInfoTable,
        songs: MutableList<SongInfoTable>?
    ) {
        launchUi {
            if (songs.isNullOrEmpty()) {
                showOtherView("真的忍心让我寂寞着嘛")
            } else {
                showContentView()
            }
            title = album.title
            getSwitchView()?.stopRefresh()
            adapter.refreshList(songs)

        }
    }
}
