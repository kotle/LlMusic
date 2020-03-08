package com.yizisu.music.and.video.utils

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yizisu.basemvvm.utils.*
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.playerlibrary.helper.PlayerModel

/**
 * 更新封面
 */
fun ImageView.updateCover(playerModel: PlayerModel, isCircle: Boolean = false) {
    if (playerModel is SongModel) {
        when (playerModel.song.sourceType) {
            LocalMusicBean.SOURCE_TYPE_BAIDU, LocalMusicBean.SOURCE_TYPE_NETEASE -> {
                if (isCircle) {
                    setImageGlide(
                        playerModel.song.coverUrl,
                        R.drawable.default_cover_icon,
                        radius = GLIDE_LOAD_RADIUS_CIRCLE
                    )
                } else {
                    setImageGlide(
                        playerModel.song.coverUrl,
                        R.drawable.default_cover_icon
                    )
                }
            }
            else -> {
                if (isCircle) {
                    setImageGlide(
                        R.drawable.default_cover_icon,
                        radius = GLIDE_LOAD_RADIUS_CIRCLE
                    )
                } else {
                    setImageGlide(
                        R.drawable.default_cover_icon
                    )
                }
            }
        }
    }
}