package com.yizisu.music.and.video.baselib.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.yizisu.basemvvm.view.SimpleSwitchView
import com.yizisu.music.and.video.R

class SwitchView : SimpleSwitchView {
    private val loadingTextView by lazy { loadingView?.findViewById<TextView>(R.id.loadingMessageTv) }
    private val otherTextView by lazy { otherView?.findViewById<TextView>(R.id.emptyHintTv) }
    private val loadingTextIv by lazy { otherView?.findViewById<ImageView>(R.id.emptyHintIv) }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    constructor(context: Context, getContentView: (ViewGroup) -> View) : super(context, getContentView)

    override fun getDefaultLoadingView(context: Context): View {
        return super.getDefaultLoadingView(context)
    }

    override fun getDefaultOtherView(context: Context): View {
        return super.getDefaultOtherView(context)
    }

    fun showLoadingView(message: String?, isHideContentView: Boolean = false) {
        loadingTextView?.text = message
        showLoadingView(isHideContentView)
    }

    fun showOtherView(message: String?, @DrawableRes drawable: Int?) {
        otherTextView?.text = message
        drawable?.let {
            loadingTextIv?.setImageResource(it)
        }
        showOtherView()
    }
}