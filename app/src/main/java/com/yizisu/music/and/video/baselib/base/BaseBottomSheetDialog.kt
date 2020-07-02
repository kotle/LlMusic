package com.yizisu.music.and.video.baselib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.yizisu.basemvvm.mvvm.MvvmBottomSheetDialog
import com.yizisu.basemvvm.mvvm.MvvmDialog
import com.yizisu.basemvvm.utils.dip

/**
 * 若红楼梦空，亦初心不变
 * 作者：thinker
 * 包名：vc.thinker.powerbank.dialog
 * 时间：2019/4/8 14:46
 * 描述：
 */
abstract class BaseBottomSheetDialog : MvvmBottomSheetDialog(), IBaseHelper, IBaseHelperView by BaseHelperImpl() {

    abstract fun getContentResOrView(): Any?

    final override fun getLayoutResOrView(inflater: LayoutInflater): Any? {
        val activity = appCompatActivity
        return if (activity == null) {
            null
        } else {
            getContentView(activity,
                    inflater,
                    getContentResOrView(),
                    true,
                    isNeedToolbar(),
                    isNeedBackIcon(),
                    isNeedSwitchView())
        }
    }


    override fun initUi(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }


    override fun getClickView(): List<View?>? = null


    override fun onSingleClick(view: View) {

    }

    override fun onRootViewLayoutParams(lp: FrameLayout.LayoutParams) {
        super.onRootViewLayoutParams(lp)
        lp.marginStart = dip(24)
        lp.marginEnd = dip(24)
    }
}