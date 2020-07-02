package com.yizisu.music.and.video.baselib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.yizisu.basemvvm.mvvm.MvvmActivity
import com.yizisu.basemvvm.mvvm.mvvm_helper.MessageBus

abstract class BaseActivity : MvvmActivity(), IBaseHelper, MessageBus.MessageBusInterface,
    IBaseHelperView by BaseHelperImpl() {
    abstract fun getContentResOrView(inflater: LayoutInflater): Any?

    final override fun getLayoutResOrView(inflater: LayoutInflater): Any? = getContentView(
        this,
        inflater,
        getContentResOrView(inflater),
        false,
        isNeedToolbar(),
        isNeedBackIcon(),
        isNeedSwitchView()
    )

    override fun isNeedSwitchView(): Boolean {
        return super.isNeedSwitchView()
    }

    override fun isNeedBackIcon(): Boolean {
        return super.isNeedBackIcon()
    }

    override fun isNeedToolbar(): Boolean {
        return super.isNeedToolbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessageBus.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        MessageBus.unRegister(this)
    }

    /**
     * 菜单创建的时候回调
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
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
     * 是否支持屏幕适配，默认支持
     */
    override fun isNeedScreenAdaptation(): Boolean {
        return super.isNeedScreenAdaptation()
    }

    /**
     * activty是否是全屏，默认不是
     *  这里全屏是支持内容绘制在刘海区的
     */
    override fun isFullScreen(): Boolean {
        return super.isFullScreen()
    }

    /**
     * MessageBus发送的消息会在这里回调
     */
    override fun onMessageBus(event: Any?, code: Int) {
        super.onMessageBus(event, code)
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
