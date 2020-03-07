package com.yizisu.music.and.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerLiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerOnSuccessLiveBean
import com.yizisu.basemvvm.utils.getResColor
import com.yizisu.basemvvm.utils.getResString
import com.yizisu.basemvvm.utils.isVisible
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.music.and.lrclibrary.LrcView
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.LrcViewModel
import com.yizisu.playerlibrary.helper.PlayerModel

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
            lrcData.registerLiveBean(activity) {
                when (it.status) {
                    LiveBeanStatus.START -> {
                    }
                    LiveBeanStatus.SUCCESS -> {
                        val list = it.data?.result
                        if (list.isNullOrEmpty()) {
                            if (lrcData.tag is String) {
                                queryLrcFromNet(lrcData.tag as String, null)
                                lrcData.tag = null
                            } else {
                                reset()
                                setLabel(getResString(R.string.no_lrc).toString())
                            }
                        } else {
                            loadLrcByUrl(list[0].lrc)
                        }
                    }
                    LiveBeanStatus.FAIL -> {
                        setLabel(it.errorMsg)
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
    }

    private var lastSongModel: LocalMusicBean? = null

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

    override fun onPlayerModelChange(playerModel: PlayerModel) {
        super.onPlayerModelChange(playerModel)
        reset()
        lastSongModel = playerModel.safeGet<SongModel>()?.song
        lastSongModel?.apply {
            queryLrcFromNet(title, singer)
        }
    }

    override fun onTick(playerModel: PlayerModel) {
        super.onTick(playerModel)
        updateTime(playerModel.currentDuration)
    }

    /*****************************************************************************/
    fun queryLrcFromNet(name: String?, singer: String? = null) {
        if (isVisible()) {
            lrcViewModel?.queryLrc(name, singer)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE && !hasLrc()) {
            lastSongModel?.let {
                queryLrcFromNet(it.title, it.singer)
            }
        }
    }
}