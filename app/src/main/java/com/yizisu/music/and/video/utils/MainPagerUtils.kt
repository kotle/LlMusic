package com.yizisu.music.and.video.utils

import android.graphics.drawable.TransitionDrawable
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.viewmodel.DbViewModel
import com.yizisu.playerlibrary.helper.PlayerModel

/**
 * 更新封面
 */
fun ImageView.updateCover(
    playerModel: SongModel?,
    transition: DrawableTransitionOptions? = DrawableTransitionOptions.withCrossFade()
) {
    val coverUrl = playerModel?.song?.coverFilePath
        ?: playerModel?.song?.coverUrlPath
        ?: R.drawable.default_cover_icon
    setImageGlide(
        coverUrl,
        R.drawable.default_cover_icon,
        transition = transition
    )
}

/**
 * 将歌曲添加到最近播放列表
 */
val dbViewModel by lazy { DbViewModel() }
//防止调用过快，导致多次插入
private var isRunThread = false

fun SongModel.saveToRecentDb() {
    if (isRunThread) {
        return
    }
    val album = AppData.dbRecentAlbumData.data ?: return
    isRunThread = true
    launchThread {
        //先移除再添加，这样最近播放就会把这首排在最前
        DbHelper.removeSongToAlbum(this@saveToRecentDb.song, album)
        DbHelper.addSongToAlbum(this@saveToRecentDb.song, album)
        dbViewModel.queryRecentPlayList()
        isRunThread = false
    }
}

/**
 * 替换当前播放列表
 * 删除所有数据，
 * 将新的数据插入
 */
fun repelaceCurrentList(list: MutableList<SongInfoTable>?) {
    if (list.isNullOrEmpty()) {
        return
    }
    launchThread {
        //这里只需要保存到数据库，不需要赋值查询，当每次打开app，查询一次数据库，
        //获取当前播放列表，因为AppData一个播放列表集合
        AppData.dbCurrentAlbumData.data?.apply {
            DbHelper.removeSongsFromAlbum(songInfoTables, dbId)
            list.forEach {
                DbHelper.addSongToAlbum(it, this)
            }
        }
    }

}

/**
 * 设置是否是我喜欢的歌曲
 */
fun setIsHeart(iv: ImageView) {
    val current = AppData.currentPlaySong.data?.song ?: return
    val heartAlbum = AppData.dbHeartAlbumData.data ?: return
    launchThread {
        val resId = if (DbHelper.isSongInAlbum(current, heartAlbum)) {
            R.drawable.heart_red
        } else {
            R.drawable.heart_empty
        }
        runOnUi {
            iv.setImageGlide(
                resId
                , transition = DrawableTransitionOptions.withCrossFade(400)
            )
        }
    }
}

/**
 * 喜欢或者不喜欢歌曲
 */
fun heartIvClick() {
    val current = AppData.currentPlaySong.data?.song ?: return
    val heartAlbum = AppData.dbHeartAlbumData.data ?: return
    launchThread {
        if (DbHelper.isSongInAlbum(current, heartAlbum)) {
            DbHelper.removeSongToAlbum(current, heartAlbum)
        } else {
            DbHelper.addSongToAlbum(current, heartAlbum)
        }
        dbViewModel.queryHeartList()
    }
}

/**
 * 获取所有歌单
 */
fun refreshAllAlbum() {
    launchThread {
        AppData.allAlbumData.success(
            DbHelper.queryAllAlbumByDbId()
                ?: mutableListOf()
        )
    }
}
