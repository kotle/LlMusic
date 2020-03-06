package com.yizisu.music.and.video.utils

import android.widget.ImageView
import com.yizisu.basemvvm.utils.setCircleImageFromBitmap
import com.yizisu.basemvvm.utils.setCircleImageFromRes
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.playerlibrary.helper.PlayerModel

/**
 * 更新封面
 */
fun ImageView.updateCover(playerModel: PlayerModel, isCircle: Boolean = false) {
    if (playerModel is SongModel) {
        val cover = LocalMusicUtil.loadingMusicCover(playerModel.song.path)
        if (cover == null) {
            if (isCircle) {
                setCircleImageFromRes(R.mipmap.logo)
            } else {
                setImageResource(R.mipmap.logo)
            }
        } else {
            if (isCircle) {
                setCircleImageFromBitmap(cover)
            } else {
                setImageBitmap(cover)
            }
        }
    }
}