package com.yizisu.music.and.video.module.full_video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.service.music.MusicService.Companion.startPlay
import com.yizisu.playerlibrary.SimplePlayer
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener
import com.yizisu.playerlibrary.helper.createLifecycleSimplePlayer
import kotlinx.android.synthetic.main.activity_full_video.*
import java.io.Serializable

class FullVideoActivity : BaseUiActivity() {
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
        val title: String?
    ) : Serializable

    private var player: SimplePlayer? = null

    private val videoData by lazy { intent.getSerializableExtra(KEY_VIDEO_DATA) as? FullVideoData }
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_full_video
    }


    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        if (videoData == null) {
            finish()
            "没有找到视频".toast()
            return
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        showLoadingState()
        player = createLifecycleSimplePlayer(this)
        player?.attachView(playerView.textureView)
        player?.setAudioForceEnable(true)
        player?.addPlayerListener(listener)
        playerView.setVideoInfo(videoData?.title)
        playerView.setOnDoubleClickListener(View.OnClickListener {
            updatePlayerViewUi()
        })
        startPlayVideo()
    }

    private fun updatePlayerViewUi() {
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

    private var isChangePlayerSize = false
    private val listener = object : SimplePlayerListener {
        override fun onTick(playerModel: PlayerModel) {
            playerModel.apply {
                playerView.setProgress(
                    currentDurationText,
                    totalDurationText,
                    (currentDuration * 100 / totalDuration).toInt(),
                    (currentBufferDuration * 100 / totalDuration).toInt()
                )
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
            startPlayVideo()
            AlertDialog.Builder(this@FullVideoActivity)
                .setMessage(throwable.message)
//                 .setCancelable(false)
                .show()
        }

        override fun onVideoSizeChange(
            width: Int,
            height: Int,
            unappliedRotationDegrees: Int,
            pixelWidthHeightRatio: Float,
            playerModel: PlayerModel?
        ) {
            if (!isChangePlayerSize) {
                isChangePlayerSize = true
                playerView.setVideoSize(width, height)

            }
        }

        override fun onPlay(playStatus: Boolean, playerModel: PlayerModel?) {
            super.onPlay(playStatus, playerModel)
            showPlayingState()
        }

        override fun onPause(playStatus: Boolean, playerModel: PlayerModel?) {
            super.onPause(playStatus, playerModel)
            showPauseState()
        }
    }

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
        showLoadingView()
    }

    /**
     * 暂停播放状态
     */
    private fun showPauseState() {
        playerView.setPlay(false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        player?.removePlayerListener(listener)
        super.onDestroy()
    }

    private class VideoMode(private val data: FullVideoData) : PlayerModel() {
        override fun callMediaUri(uriCall: (Uri) -> Unit) {
            uriCall.invoke(Uri.parse(data.path))
        }
    }
}
