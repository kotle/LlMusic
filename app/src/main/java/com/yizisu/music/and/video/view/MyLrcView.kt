package com.yizisu.music.and.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerOnSuccessLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.lrclibrary.LrcView
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.LrcBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.bean.netease.LrcNeteaseBean
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.LrcViewModel
import com.yizisu.playerlibrary.helper.PlayerModel
import java.io.File

class MyLrcView : LrcView, MusicEventListener {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val lrcViewModel by lazy {
        val activity = context.safeGet<BaseActivity>()
        activity?.getViewModel<LrcViewModel>()?.apply {
            //歌词迷歌词
            LrcViewModel.lrcData.registerOnSuccessLiveBean(activity) {
                val list = it.result
                if (!list.isNullOrEmpty()) {
                    loadLrcByUrl(list[0].lrc)
                    AppData.currentPlaySong.data?.song?.let { song ->
                        song.lrcUrlPath = list[0].lrc
                        launchThread {
                            DbHelper.insetOrUpdateSong(song)
                        }
                    }
                }
            }
            //网易云纯文本歌词
            LrcViewModel.lrcNeteaseData.registerOnSuccessLiveBean(activity) {
                loadLrc(it.lyric)
                AppData.currentPlaySong.data?.song?.let { song ->
                    song.lrcString = it.lyric
                    launchThread {
                        DbHelper.insetOrUpdateSong(song)
                    }
                }
            }
            //文件歌词
            LrcViewModel.lrcFileData.registerOnSuccessLiveBean(activity) {
                loadLrc(File(it))
                AppData.currentPlaySong.data?.song?.let { song ->
                    song.lrcFilePath = it
                    launchThread {
                        DbHelper.insetOrUpdateSong(song)
                    }
                }
            }
        }
    }

    init {
        setLrc()
        setDraggable(true) {
            MusicService.seekToMs(it)
            return@setDraggable true
        }
        lrcViewModel
    }

    private fun setLrc() {
        setNormalColor(getResColor(R.color.colorLightTranslucent))
        setCurrentColor(getResColor(R.color.colorTextDeepBackground))
        setTimelineTextColor(getResColor(R.color.colorTextDeepBackground))
        setTimelineColor(getResColor(R.color.colorTextDeepBackground))
        setTimeTextColor(getResColor(R.color.colorTextDeepBackground))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        MusicService.addMusicEventListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        MusicService.removeMusicEventListener(this)
    }

    override fun onPlayerModelChange(playerModel: SongModel) {
        super.onPlayerModelChange(playerModel)
        reset()
        queryLrcFromNet()
    }

    override fun onTick(playerModel: SongModel) {
        super.onTick(playerModel)
        updateTime(playerModel.currentDuration)
    }

    /*****************************************************************************/
    private fun queryLrcFromNet() {
        val model = AppData.currentPlaySong.data?.song ?: return
        if (lastParentViewVisibility == View.VISIBLE && isVisible()) {
            reset()
            if (model.lrcString != null) {
                LrcViewModel.lrcNeteaseData.success(
                    LrcNeteaseBean().apply {
                        lyric = model.lrcString
                    }
                )
                return
            }
            if (model.lrcFilePath != null) {
                LrcViewModel.lrcFileData.success(model.lrcFilePath)
                return
            }
            if (model.lrcUrlPath != null) {
                LrcViewModel.lrcData.success(
                    LrcBean().apply {
                        result = mutableListOf(LrcBean.ResultBean().apply {
                            lrc = model.lrcUrlPath
                        })
                    }
                )
                return
            }
            when (model.source) {
                DbCons.SOURCE_NETEASE -> {
                    lrcViewModel?.queryLrcNetease(model.id.toString())
                }
                else -> {
                    val lrc = model.lrcUrlPath
                    if (lrc != null) {
                        loadLrcByUrl(lrc)
                    } else {
                        lrcViewModel?.queryLrc(model.name, null)
                    }
                }
            }
        }
    }

    private var lastParentViewVisibility = View.VISIBLE
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        lastParentViewVisibility = visibility
        if (isVisible() && !hasLrc()) {
            queryLrcFromNet()
        }
    }
}