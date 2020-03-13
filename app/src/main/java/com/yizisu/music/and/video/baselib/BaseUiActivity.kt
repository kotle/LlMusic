package com.yizisu.music.and.video.baselib

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.yizisu.basemvvm.activityList
import com.yizisu.basemvvm.utils.isStatusBarBlackTextColor
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.module.main.MainActivity

/**
 * 可以处理一些主题切换等操作
 */
abstract class BaseUiActivity : BaseActivity() {
    override fun isNeedToolbar(): Boolean {
        return this !is MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        isStatusBarBlackTextColor(false)
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawableResource(R.color.colorBackground)
        getToolbar()?.apply {
            setTitleTextAppearance(this@BaseUiActivity, R.style.ToolBarTitle)
            setBackgroundResource(R.color.colorAccent)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
    }
}