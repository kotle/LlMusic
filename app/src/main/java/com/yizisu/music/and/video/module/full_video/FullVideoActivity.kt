package com.yizisu.music.and.video.module.full_video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.yizisu.basemvvm.utils.navigateTo
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
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
        val title: String? = null
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
        showLoadingState()
        player = createLifecycleSimplePlayer(this)
        player?.attachView(playerView)
        player?.setAudioForceEnable(true)
        player?.addPlayerListener(listener)
        player?.prepareAndPlay(
            mutableListOf(
                VideoMode(videoData!!)
            )
        )
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    private var isChangePlayerSize = false
    private val listener = object : SimplePlayerListener {
        override fun onTick(playerModel: PlayerModel) {

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

        override fun onVideoSizeChange(
            width: Int,
            height: Int,
            unappliedRotationDegrees: Int,
            pixelWidthHeightRatio: Float,
            playerModel: PlayerModel?
        ) {
            if (!isChangePlayerSize) {
                isChangePlayerSize = true
                playerView.layoutParams = changePlayerSize(
                    playerView.width,
                    playerView.height,
                    width, height
                )

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


    override fun isNeedToolbar(): Boolean {
        return false
    }

    /**
     * 正在播放状态
     */
    private fun showPlayingState() {
        showContentView()
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

    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onDestroy() {
        player?.removePlayerListener(listener)
        super.onDestroy()
    }

    /**
     * 设置视频尺寸
     */
    private fun changePlayerSize(
        viewWidth: Int, viewHeight: Int,
        videoWidth: Int, videoHeight: Int
    ): FrameLayout.LayoutParams {
        val viewR = viewWidth.toFloat() / viewHeight
        val videoR = videoWidth.toFloat() / videoHeight
        val lp = if (viewR <= videoR) {
            //view的宽度不变设置高度
            FrameLayout.LayoutParams(
                viewWidth, viewWidth * videoHeight / videoWidth
            )
        } else {
            //view高度不变,动态设置宽度
            FrameLayout.LayoutParams(
                viewHeight * videoWidth / videoHeight, viewHeight
            )
        }
        lp.gravity = Gravity.CENTER
        return lp
    }

    private class VideoMode(private val data: FullVideoData) : PlayerModel() {
        override fun callMediaUri(uriCall: (Uri) -> Unit) {
            uriCall.invoke(Uri.parse(data.path))
        }
    }
}
