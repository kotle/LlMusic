package com.yizisu.music.and.video

import com.yizisu.basemvvm.mvvm.mvvm_helper.createLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.isThis
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.LocalVideoBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.utils.repelaceCurrentList
import com.yizisu.music.and.video.utils.saveToRecentDb
import com.yizisu.playerlibrary.helper.PlayerModel

object AppData {
    //后面可以考虑歌曲的状态用liveData做全局回调,不用每个类用回调实现
    //每次回调可能需要创建新的对象，否则同一个对象可能不会走liveData的回调
    // (经过测试同一个对象每次给liveData赋值会走回调)
//    val playerStatus by lazy {  }
    //当前播放的歌曲
    val currentPlaySong by lazy {
        createLiveBean<SongModel?>().apply {
            observeForever {
                it.value?.saveToRecentDb()
            }
        }
    }
    //当前播放的列表
    val currentPlayList by lazy {
        createLiveBean<MutableList<PlayerModel>?>().apply {
            observeForever {
                val newList = mutableListOf<SongInfoTable>()
                data?.forEach {
                    it.isThis<SongModel> {
                        newList.add(song)
                    }
                }
                repelaceCurrentList(newList)
            }
        }
    }

    //我喜欢 歌单
    val dbHeartAlbumData by lazy { createLiveBean<AlbumInfoTable>() }
    //当前正在播放列表
    val dbCurrentAlbumData by lazy { createLiveBean<AlbumInfoTable>() }
    //播放记录列表
    val dbRecentAlbumData by lazy { createLiveBean<AlbumInfoTable>() }
    //数据库本地音乐列表
    val dbLocalAlbumData by lazy { createLiveBean<AlbumInfoTable>() }
}