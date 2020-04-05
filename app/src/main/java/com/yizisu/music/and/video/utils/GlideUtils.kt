package com.yizisu.music.and.video.utils

import android.app.Activity
import android.graphics.Bitmap
import android.media.Image
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.playerlibrary.helper.PlayerModel
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

//fun ImageView.loadBlur(bitMap: Bitmap?, default: Int) {
//    Glide.with(this)
//        .load(bitMap ?: default)
//        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
//        .into(this)
//}

/**
 * 设置高斯模糊
 */
fun Activity.setBlurView(blurView: BlurView) {
    val radius = 18f
    val decorView = window.decorView
    val rootView = decorView.findViewById(android.R.id.content) as ViewGroup
    val windowBackground = decorView.background
    blurView.setupWith(rootView)
        .setFrameClearDrawable(windowBackground)
        .setBlurAlgorithm(RenderScriptBlur(this))
        .setBlurRadius(radius)
        .setHasFixedTransformationMatrix(true)
}