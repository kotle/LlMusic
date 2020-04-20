package com.yizisu.music.and.video.module.fragment.down_task

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.widget.BaseRingView
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.utils.DownSongWithNotification

class DownloadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val LAYOUT_RES = R.layout.rcv_item_downloading_music
    }

    private val songName = itemView.findViewById<TextView>(R.id.songNameTv)
    private val songDes = itemView.findViewById<TextView>(R.id.songDesTv)
    private val songSource = itemView.findViewById<TextView>(R.id.songSourceTv)
    private val songCover = itemView.findViewById<ImageView>(R.id.songCoverIv)
    private val checkBox = itemView.findViewById<BaseRingView>(R.id.musicJumpFl)
    private val listener = object : DownloadHelper.DownListener() {
        override fun onProgressUpdate(downloadSize: Long, totalSize: Long, progress: Long) {
            super.onProgressUpdate(downloadSize, totalSize, progress)
            checkBox.ringProgress = progress * 3.6f
        }

        override fun onComplete(result: DownloadHelper.DownloadBean?) {
            super.onComplete(result)
            checkBox.textFrom("完成")
            checkBox.ringBackgroundColor = null
            checkBox.ringProgress = 0f
        }

        override fun onCancelled() {
            super.onCancelled()
            checkBox.textFrom("已取消")
            checkBox.ringBackgroundColor = null
            checkBox.ringProgress = 0f
        }
    }

    init {
        checkBox.ringColor = getResColor(R.color.colorAccent)
        checkBox.ringBackgroundColor = getResColor(R.color.colorAccentTranslucent)
        checkBox.ringWidth = dip(1.6f)
    }


    fun setData(bean: DownSongWithNotification.DownSongBean) {
        bean.listener = listener
        bean.song.apply {
            songName.textFrom(name)
            songDes.textFrom(des)
            songCover.setImageGlide(coverFilePath ?: coverUrlPath, R.drawable.default_cover_icon, radius = 4)
        }
        checkBox.setOnClickListener {
            bean.downloadHelper.cancel()
        }
    }
}