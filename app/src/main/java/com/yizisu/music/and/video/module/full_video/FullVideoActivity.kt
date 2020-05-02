package com.yizisu.music.and.video.module.full_video

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.yizisu.basemvvm.logI
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.playerlibrary.IYzsPlayer
import com.yizisu.playerlibrary.PlayerFactory
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener
import kotlinx.android.synthetic.main.activity_full_video.*
import java.io.Serializable

open class FullVideoActivity : BaseUiActivity() {
    companion object {
        private const val KEY_VIDEO_DATA = "KEY_VIDEO_DATA"
        fun start(appCompatActivity: AppCompatActivity?, bean: FullVideoData) {
            appCompatActivity?.navigateTo(FullVideoActivity::class.java) {
                it.putExtra(KEY_VIDEO_DATA, bean)
            }
        }
    }

    data class FullVideoData(
        val path: String,
        val title: String?,
        val width: Int = 0,
        val height: Int = 0,
        val duration: Long = 0
    ) : Serializable

    private var player: IYzsPlayer<PlayerModel>? = null

    private var videoData: FullVideoData? = null
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_full_video
    }


    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        videoData = intent.getSerializableExtra(KEY_VIDEO_DATA) as? FullVideoData
        if (videoData == null) {
            when (intent.action) {
                Intent.ACTION_VIEW -> {
                    val data = intent.dataString
                    if (data.isNullOrBlank()) {
                        finish()
                        "视频无法播放".toast()
                        return
                    } else {
                        videoData = FullVideoData(
                            data,
                            intent.getStringExtra(Intent.EXTRA_TITLE)
                                ?: intent.extras?.getString(Intent.EXTRA_TITLE)
                        )
                    }
                }
                else -> {
                    finish()
                    "没有找到视频".toast()
                    return
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        showLoadingState()
        player = PlayerFactory.createLifecyclePlayer(this, PlayerFactory.PLAYER_IMPL_EXO)
        player?.attachView(playerView.textureView)
        player?.setAudioForceEnable(true)
        player?.addPlayerListener(listener)
        playerView.setVideoInfo(videoData?.title)
        //双击监听
        playerView.setOnDoubleClickListener {
            updatePlayerViewUi()
        }
        //速率改变监听
        var oldSpeed = 1f
        playerView.setOnSpeedChangeListener {
            if (it != oldSpeed) {
                oldSpeed = it
                player?.setVideoSpeed(it)
            }
        }
        //拖动进度条监听
        playerView.setOnSeekBarListener { isFinish, slidingRatio ->
            logI("$isFinish,$slidingRatio")
            if (isFinish) {
                //向右滑动slidingRatio小于0，所以需要取相反的
                seekVideo(player?.getCurrentModel(), -slidingRatio)
                player?.play()
            }
        }
        startPlayVideo()
        //设置默认进度
        videoData?.let {
            setVideoProgress(0, 0, it.duration)
        }
    }

    /**
     * seek视频
     */
    private fun seekVideo(model: PlayerModel?, slidingRatio: Float) {
        model?.apply {
            player?.seekTo((totalDuration * slidingRatio).toLong() + currentDuration)
        }
    }

    private fun updatePlayerViewUi() {
        if (!isHadGetPlayerSize) {
            startPlayVideo()
            return
        }
        player?.apply {
            val isPlay = !isPlaying()
            if (isPlay) {
                play()
            } else {
                pause()
            }
        }
    }

    override fun isFullScreen(): Boolean {
        return true
    }


    private var isHadGetPlayerSize = false
    private val listener = object : SimplePlayerListener<PlayerModel> {
        override fun onTick(playerModel: PlayerModel) {
            playerModel.apply {
                setVideoProgress(currentDuration, currentBufferDuration, totalDuration)
            }
        }

        override fun onBufferStateChange(
            isBuffering: Boolean,
            playStatus: Boolean,
            playerModel: PlayerModel?
        ) {
            super.onBufferStateChange(isBuffering, playStatus, playerModel)
            if (playStatus) {
                if (isBuffering) {
                    showLoadingState()
                } else {
                    showPlayingState()
                }
            } else {
                showPauseState()
            }
        }

        override fun onError(throwable: Throwable, playerModel: PlayerModel?) {
            super.onError(throwable, playerModel)
            AlertDialog.Builder(this@FullVideoActivity)
                .setMessage(throwable.message)
                .setCancelable(false)
                .setNegativeButton("结束播放") { dialog, which ->
                    finish()
                }
                .setPositiveButton("重试") { dialog, which ->
                    startPlayVideo()
                }
                .show()
        }

        override fun onVideoSizeChange(
            width: Int,
            height: Int,
            unappliedRotationDegrees: Int,
            pixelWidthHeightRatio: Float,
            playerModel: PlayerModel?
        ) {
            setScreenByVideoSize(width, height)
        }

        override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
            super.onPlay(playStatus, playerModel)
            showPlayingState()
        }

        override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
            super.onPause(playStatus, playerModel)
            if (!playStatus) {
                showPauseState()
            }
        }

        override fun onStop(playStatus: Boolean, playerModel: PlayerModel?) {
            super.onStop(playStatus, playerModel)
            isHadGetPlayerSize = false
        }
    }

    /**
     * 根据宽高设置屏幕方向
     */
    private fun setScreenByVideoSize(width: Int, height: Int) {
        if (!isHadGetPlayerSize) {
            isHadGetPlayerSize = true
            if (width < height) {
                playerView.setVideoSize(width, height, true)
                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                }

            } else {
                playerView.setVideoSize(width, height, false)
                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                }
            }
        }
    }

    /**
     * 开始播放视频
     */
    private fun startPlayVideo() {
        player?.apply {
            prepareAndPlay(
                mutableListOf(
                    VideoMode(videoData!!)
                )
            )
            playerView.setPlay(isPlaying())
        }
    }

    /**
     * 设置进度条
     */
    private fun setVideoProgress(
        currentDuration: Long?,
        currentBufferDuration: Long?,
        totalDuration: Long
    ) {
        playerView.setProgress(
            currentDuration,
            currentBufferDuration,
            totalDuration
        )
    }


    override fun isNeedToolbar(): Boolean {
        return false
    }

    /**
     * 正在播放状态
     */
    private fun showPlayingState() {
        showContentView()
        playerView.setPlay(true)
    }

    /**
     * 缓冲状态
     */
    private fun showLoadingState() {
        showLoadingView(isEnableContentView = true)
    }

    /**
     * 暂停播放状态
     */
    private fun showPauseState() {
        playerView.setPlay(false)
    }


    override fun onDestroy() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        player?.removePlayerListener(listener)
        super.onDestroy()
    }

    private class VideoMode(private val data: FullVideoData) : PlayerModel() {
        override fun callMediaUri(uriCall: (Uri?, Throwable?, Boolean) -> Unit) {
            uriCall.invoke(Uri.parse(data.path), null, false)
        }
    }
}
