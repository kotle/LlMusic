package com.yizisu.music.and.video.module.fragment.home


import android.Manifest
import android.view.LayoutInflater
import android.view.View
import com.yizisu.basemvvm.utils.textFrom
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.video.AppData

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.play_list_detail.PlayListDetailActivity
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.dbViewModel
import kotlinx.android.synthetic.main.fragment_home_menu.*


class HomeMenuFragment : BaseFragment() {

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_home_menu
    }

    override fun getClickView(): List<View?>? {
        return listOf(recentAddMusicFl, headMusicFl, localMusicFl)
    }

    private var isFirstCreate = true
    override fun initViewModel() {
        super.initViewModel()
        AppData.dbHeartAlbumData.registerOnSuccess {
            heartTv.textFrom("${it.title}(${it.songInfoTables.count()})")
        }
        AppData.dbLocalAlbumData.registerOnSuccess {
            localTv.textFrom("${it.title}(${it.songInfoTables.count()})")
        }
        AppData.dbRecentAlbumData.registerOnSuccess {
            recentTv.textFrom("${it.title}(${it.songInfoTables.count()})")
        }
        AppData.dbCurrentAlbumData.registerOnSuccess {
            //这里会多次调用，只需要初始化调用一次
            if (isFirstCreate) {
                isFirstCreate = false
                MusicService.startPlay(
                    it.songInfoTables.map {
                        SongModel(it)
                    }.toMutableList(),
                    AppData.currentPlayIndex,
                    true,
                    false
                )
            }
        }
    }

    override fun initData() {
        super.initData()
        dbViewModel.apply {
            queryHeartList()
            queryLocalList()
            queryRecentPlayList()
            queryCurrentList()
        }
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            recentAddMusicFl -> {
                PlayListDetailActivity.start(appCompatActivity, AppData.dbCurrentAlbumData.data)
            }
            headMusicFl -> {
                PlayListDetailActivity.start(appCompatActivity, AppData.dbHeartAlbumData.data)
            }
            localMusicFl -> {
                getPermission(
                    mutableListOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    if (it) {
                        PlayListDetailActivity.start(
                            appCompatActivity,
                            AppData.dbLocalAlbumData.data
                        )
                    }
                }
            }
        }
    }

}
