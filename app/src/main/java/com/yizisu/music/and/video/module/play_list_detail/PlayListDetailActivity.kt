package com.yizisu.music.and.video.module.play_list_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.launchUi
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.utils.LocalMusicUtil
import kotlinx.android.synthetic.main.activity_play_list_detail.*

class PlayListDetailActivity : BaseUiActivity() {
    companion object {
        fun start(appCompatActivity: AppCompatActivity?, albumDbId: Long) {
            appCompatActivity?.navigateTo(PlayListDetailActivity::class.java) {
                it.putExtra("albumDbId", albumDbId)
            }
        }
    }

    private val adapter = SearchAdapter()
    private val albumDbId by lazy { intent.getLongExtra("albumDbId", -1) }
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_play_list_detail
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun isNeedToolbar(): Boolean {
        return true
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun isCanSwipeBack(): Boolean {
        return super.isCanSwipeBack()
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        if (albumDbId < 0) {
            finish()
            return
        }
        playListDetailRcv.adapter = adapter
        refreshData()
        getSwitchView()?.apply {
            isCanSwipeRefresh = true
            setOnRefreshListener {
                refreshData()
            }
        }
    }


    private fun refreshData() {
        showLoadingView()
        launchThread {
            val album = DbHelper.queryAlbumByDbId(albumDbId) ?: return@launchThread
            when (albumDbId) {
                DbCons.ALBUM_ID_LOCAL -> {
                    //查询本地音乐
                    val songs = LocalMusicUtil.getSongInfos(this@PlayListDetailActivity)
                    AppData.dbLocalAlbumData.apply {
                        data?.songInfoTables = songs
                        data?.let {
                            success(it)
                        }
                    }
                    refreshList(album, songs)
                    return@launchThread
                }
                else -> {
                    val songs = album.songInfoTables?.asReversed()
                    if (!songs.isNullOrEmpty()) {
                        val firstSong = songs[0]
                        album.filePath = firstSong.coverFilePath
                        album.urlPath = firstSong.coverUrlPath
                        DbHelper.insetOrUpdateAlbum(album)
                    }
                    refreshList(album, songs)
                }
            }
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
