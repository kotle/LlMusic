package com.yizisu.music.and.video.baselib.base

import android.content.Context
import androidx.appcompat.widget.Toolbar

interface IBaseHelper {
    /**
     * 是否需要可以切换不同状态的view
     */
    fun isNeedSwitchView(): Boolean = false

    /**
     * 是否需要标题栏
     */
    fun isNeedToolbar(): Boolean = false

    /**
     * 标题栏是否需要返回图标
     * isNeedToolbar()才会被调用
     */
    fun isNeedBackIcon() = true
}