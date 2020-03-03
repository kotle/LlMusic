package com.yizisu.music.and.video.baselib.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.lang.IllegalArgumentException

class BaseHelperImpl : IBaseHelperView {
    private val baseHelperBean = IBaseHelperView.BaseHelperBean(null, null, null)

    private fun getToolbar(context: Context, isNeedToolbar: Boolean): Toolbar? {
        return if (isNeedToolbar) {
            Toolbar(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        } else {
            return null
        }
    }

    override fun getContentView(context: AppCompatActivity,
                                layoutInflater: LayoutInflater,
                                viewOrLayoutRes: Any?,
                                isNotActivity: Boolean,
                                isNeedToolbar: Boolean,
                                isNeedBackIcon: Boolean, isNeedSwitchView: Boolean): Any? {
        val toolbar = getToolbar(context, isNeedToolbar)
        val switcherView = getSwitchView(context, layoutInflater, viewOrLayoutRes, isNeedSwitchView)
        baseHelperBean.toolbar = toolbar
        baseHelperBean.switcherView = switcherView
        return if (toolbar == null) {
            switcherView ?: viewOrLayoutRes
        } else {
            //toolbar不是null，
            //判断是在activity还是fragment添加
            addToolbarView(context, layoutInflater, toolbar, switcherView, viewOrLayoutRes, isNotActivity,
                    isNeedBackIcon)
        }
    }

    private fun addToolbarView(context: AppCompatActivity, layoutInflater: LayoutInflater, toolbar: Toolbar, switcherView: SwitchView?,
                               viewOrLayoutRes: Any?, isNotActivity: Boolean, isNeedBackIcon: Boolean): Any? {
        return if (isNotActivity) {
            //不是activity的view
            addToolbarViewInNotActivity(context, layoutInflater, toolbar, switcherView, viewOrLayoutRes)
        } else {
            //在activity中
            addToolbarViewInActivity(context, layoutInflater, toolbar, switcherView, viewOrLayoutRes, isNeedBackIcon)
        }
    }

    /**
     * 在activity中添加toolbar
     */
    private fun addToolbarViewInActivity(context: AppCompatActivity,
                                         layoutInflater: LayoutInflater,
                                         toolbar: Toolbar,
                                         switcherView: SwitchView?, viewOrLayoutRes: Any?,
                                         isNeedBackIcon: Boolean): Any? {
        return if (context.supportActionBar != null) {
            //已经有了actionbar
            switcherView ?: viewOrLayoutRes
        } else {
            //没有actionbar
            context.setSupportActionBar(toolbar)
            if (isNeedBackIcon) {
                context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            addToolbarViewInNotActivity(context, layoutInflater, toolbar, switcherView, viewOrLayoutRes)
        }
    }

    /**
     * 在fragment中添加toolbar
     */
    private fun addToolbarViewInNotActivity(context: AppCompatActivity,
                                            layoutInflater: LayoutInflater,
                                            toolbar: Toolbar,
                                            switcherView: SwitchView?,
                                            viewOrLayoutRes: Any?): Any? {
        val rootView = LinearLayout(context)
        rootView.id = View.generateViewId()
        baseHelperBean.rootView = rootView
        rootView.orientation = LinearLayout.VERTICAL
        rootView.addView(toolbar)
        if (switcherView != null) {
            rootView.addView(switcherView)
        } else {
            val view = when (viewOrLayoutRes) {
                is Int -> layoutInflater.inflate(viewOrLayoutRes, rootView, false)
                is View -> viewOrLayoutRes
                else -> null
            }
            if (view != null) {
                rootView.addView(view)
            }
        }
        return rootView
    }

    private fun getSwitchView(context: Context, layoutInflater: LayoutInflater, viewOrLayoutRes: Any?, isNeedSwitchView: Boolean): SwitchView? {
        viewOrLayoutRes ?: return null
        return if (isNeedSwitchView) {
            SwitchView(context) {
                when (viewOrLayoutRes) {
                    is Int -> layoutInflater.inflate(viewOrLayoutRes, it, false)
                    is View -> viewOrLayoutRes
                    else -> throw IllegalArgumentException("switchView无法创建view")
                }
            }.apply {
                layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
            }
        } else {
            null
        }
    }

    override fun getBaseHelperBean(): IBaseHelperView.BaseHelperBean {
        return baseHelperBean
    }

    override fun getToolbar(): Toolbar? = baseHelperBean.toolbar

    override fun getSwitchView(): SwitchView? = baseHelperBean.switcherView

    override fun getRootView(): LinearLayout? = baseHelperBean.rootView
}