package com.yizisu.music.and.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerOnSuccessLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.lrclibrary.LrcView
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.LrcViewModel
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
            //纯文本歌词
            LrcViewModel.lrcStringData.registerOnSuccessLiveBean(activity) { lrcString ->
                lrcString ?: return@registerOnSuccessLiveBean
                loadLrc(lrcString)
            }
            //文件歌词
            LrcViewModel.lrcFileData.registerOnSuccessLiveBean(activity) { fileLrcPath ->
                saveLrcToDb(fileLrcPath) { song ->
                    loadLrc(File(fileLrcPath!!))
                    song.lrcFilePath = fileLrcPath
                }
            }
        }
    }

    /**
     * 将歌词保存到数据库
     */
    inline fun saveLrcToDb(lrc: Any?, crossinline r: Function1<SongInfoTable, Unit>) {
        lrc ?: return
        AppData.currentPlaySong.data?.song?.let { song ->
            r.invoke(song)
            launchThread {
                DbHelper.insetOrUpdateSong(song)
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
        reset()
    }

    override fun onPlayerModelChange(playerModel: SongModel) {
        super.onPlayerModelChange(playerModel)
        LrcViewModel.lrcFileData.success(null)
        LrcViewModel.lrcStringData.success(null)
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
            if (!model.lrcString.isNullOrEmpty()) {
                loadLrc(model.lrcString)
                return
            }
            if (model.lrcFilePath != null) {
                loadLrc(File(model.lrcFilePath))
                return
            }
            when (model.source) {
                DbCons.SOURCE_NETEASE -> {
                    lrcViewModel?.queryLrcNetease()
                }
                DbCons.SOURCE_MIGU -> {
                    lrcViewModel?.queryLrcMessapiMigu()
                }
                else -> {
                    lrcViewModel?.queryLrc()
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