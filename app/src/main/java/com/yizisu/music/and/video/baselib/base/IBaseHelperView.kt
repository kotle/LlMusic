package com.yizisu.music.and.video.baselib.base

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

interface IBaseHelperView {
    data class BaseHelperBean(var rootView: LinearLayout?,
                              var switcherView: SwitchView?,
                              var toolbar: Toolbar?)

    /**
     * 如果需要，返回view
     * 否则返回null
     */
//    fun getSwitchView(context: Context,layoutInflater: LayoutInflater, viewOrLayoutRes: Any?, isNeedSwitchView: Boolean): SwitchView?

    /**
     * 如果需要，返回view
     * 否则返回null
     */
//    fun getToolbar(context: Context, isNeedToolbar: Boolean): Toolbar?


    /**
     * 获取根view
     * 如果需要toolbar，则返回LinerLayout
     * 如果需要toolbar，switchView，则返回LinerLayout
     * 如果需要switchView，则返回switchView
     * 如果什么都不需要，则返回传入的view
     */
    fun getContentView(context: AppCompatActivity,
                       layoutInflater: LayoutInflater,
                       viewOrLayoutRes: Any?,
                       isNotActivity: Boolean,
                       isNeedToolbar: Boolean,
                       isNeedBackIcon: Boolean,
                       isNeedSwitchView: Boolean): Any?

    /**
     * 获取view
     */
    fun getBaseHelperBean(): BaseHelperBean

    /**
     * 获取toolbar
     */
    fun getToolbar(): Toolbar?

    /**
     * 获取switchView
     */
    fun getSwitchView(): SwitchView?

    /**
     * 获取根view
     * 当有toolbar的时候才有
     */
    fun getRootView(): LinearLayout?

    /**
     * 显示loading
     */
    fun showLoadingView(message: String? = null, isHideContentView: Boolean = false) {
        getBaseHelperBean().switcherView?.showLoadingView(message, isHideContentView)
    }

    /**
     * 显示other
     */
    fun showOtherView(message: String? = null, @DrawableRes drawable: Int? = null) {
        getBaseHelperBean().switcherView?.showOtherView(message, drawable)
    }

    /**
     * 显示other
     */
    fun showContentView() {
        getBaseHelperBean().switcherView?.showContentView()
    }
}