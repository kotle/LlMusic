package com.yizisu.music.and.video.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.yizisu.basemvvm.mvvm.MvvmActivity
import com.yizisu.basemvvm.mvvm.mvvm_helper.NoParamsLifecycleObserver
import com.yizisu.basemvvm.utils.GLIDE_LOAD_RADIUS_CIRCLE
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.utils.setCircleImageFromRes
import com.yizisu.basemvvm.utils.setImageGlide
import com.yizisu.basemvvm.widget.BaseImageView
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.utils.updateCover
import com.yizisu.playerlibrary.helper.PlayerModel

class AutoRotationImageView : BaseImageView, MusicEventListener, NoParamsLifecycleObserver {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        if (AppData.currentPlaySong.data == null) {
            updateCover(null, true)
        }
        context.safeGet<MvvmActivity>()?.apply {
            lifecycle.addObserver(this@AutoRotationImageView)
            AppData.currentPlaySong.registerOnSuccess {
                updateCover(it, true)
            }
        }
    }

    private var anim: ObjectAnimator? = null
    private val lastPlayerModel: PlayerModel?
        get() = AppData.currentPlaySong.data

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        MusicService.addMusicEventListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        MusicService.removeMusicEventListener(this)
        startAnim(false)
    }

    override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
        super.onPlay(playStatus, playerModel)
        if (lastPlayerModel == playerModel) {
            startAnim(playStatus)
        }
    }

    override fun onError(throwable: Throwable, playerModel: PlayerModel?) {
        super.onError(throwable, playerModel)
        anim?.pause()
    }

    override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
        super<MusicEventListener>.onPause(playStatus, playerModel)
        if (lastPlayerModel == playerModel) {
            startAnim(playStatus)
        }
    }

    override fun onStart() {
        super.onStart()
        anim?.resume()
    }

    override fun onStop() {
        super<NoParamsLifecycleObserver>.onStop()
        anim?.pause()
    }

    private fun startAnim(isPlay: Boolean) {
        if (isPlay) {
            if (anim == null) {
                anim =
                    ObjectAnimator.ofFloat(this, View.ROTATION, rotation, rotation + 360f).apply {
                        interpolator = LinearInterpolator()
                        repeatCount = -1
                        duration = 7000
                    }
                anim?.start()
            }
        } else {
            anim?.cancel()
            anim = null
        }
    }

}