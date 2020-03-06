package com.yizisu.music.and.video.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.yizisu.basemvvm.mvvm.mvvm_helper.NoParamsLifecycleObserver
import com.yizisu.basemvvm.utils.safeGet
import com.yizisu.basemvvm.widget.BaseImageView
import com.yizisu.music.and.video.service.music.MusicEventListener
import com.yizisu.music.and.video.service.music.MusicService
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
        context.safeGet<LifecycleOwner>()?.lifecycle?.addObserver(this)
    }

    private var anim: ObjectAnimator? = null
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        MusicService.addMusicEventListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        MusicService.removeMusicEventListener(this)
    }

    override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
        super.onPlay(playStatus, playerModel)
        startAnim(playStatus)
    }

    override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
        super<MusicEventListener>.onPause(playStatus, playerModel)
        startAnim(playStatus)
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