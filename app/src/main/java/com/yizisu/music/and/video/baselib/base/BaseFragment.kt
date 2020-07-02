package com.yizisu.music.and.video.baselib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.yizisu.basemvvm.mvvm.MvvmFragment

abstract class BaseFragment : MvvmFragment(), IBaseHelper, IBaseHelperView by BaseHelperImpl() {
    abstract fun getContentResOrView(inflater: LayoutInflater): Any?

    final override fun getLayoutResOrView(inflater: LayoutInflater): Any? {
        val activity = appCompatActivity
        return if (activity == null) {
            null
        } else {
            getContentView(activity,
                    inflater,
                    getContentResOrView(inflater),
                    true,
                    isNeedToolbar(),
                    isNeedBackIcon(),
                    isNeedSwitchView())
        }
    }


    /**
     * 在onCreate()中被回调
     * 此时已经执行完setContent()方法
     * 做一些与Ui相关的事情
     */
    override fun initUi(savedInstanceState: Bundle?) {
    }

    /**
     * 在onCreate()中被回调
     * 可以处理一些数据
     */
    override fun initData() {
    }

    /**
     * fragment不需要这个
     */
    final override fun isNeedBackIcon(): Boolean {
        return super.isNeedBackIcon()
    }

    /**
     * 注册需要点击的view
     * 在onSingleClick(view: View)会被回调
     */
    override fun getClickView(): List<View?>? = null

    /**
     * 在getClickView(): List<View?>?注册的view
     * 点击会在这这个方法回调
     */
    override fun onSingleClick(view: View) {
    }

    /**
     * 获取两次view的是点击时间间隔
     * 少于这个时间多次点击只相应一次
     * 默认300毫秒
     */
    override fun getDoubleClickSpace(): Long {
        return super.getDoubleClickSpace()
    }
}