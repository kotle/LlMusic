package com.yizisu.music.and.video.baselib.base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.ViewUtils
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.mvvm.mvvm_helper.SingleClickListener
import com.yizisu.basemvvm.view.BaseRcvAdapter
import com.yizisu.basemvvm.widget.BaseLinearLayout
import com.yizisu.basemvvm.widget.BaseRecyclerView

open class ListDialog<T> : BaseDialog() {

    override fun onRootViewLayoutParams(lp: FrameLayout.LayoutParams) {
        super.onRootViewLayoutParams(lp)
        onLayoutParams?.invoke(recyclerView, lp)
    }

    private var itemLayoutRes: Int? = null

    private var onItemClickListener: ((dialog: ListDialog<T>, temView: View, position: Int, data: T) -> Unit)? =
            null
    private var onBindData: ((itemView: View, position: Int, itemData: T) -> Unit)? = null
    private var onLayoutParams: Function2<RecyclerView, FrameLayout.LayoutParams, Unit>? = null

    /**
     * 设置布局的res
     */
    fun setItemLayoutRes(@LayoutRes layoutRes: Int): ListDialog<T> {
        itemLayoutRes = layoutRes
        return this
    }

    /**
     * 设置点击监听
     */
    fun setItemClickListener(listener: (dialog: ListDialog<T>, itemView: View, position: Int, data: T) -> Unit): ListDialog<T> {
        onItemClickListener = listener
        return this
    }

    /**
     * 设置数据绑定
     */
    fun setOnBindDataListener(onBindData: (itemView: View, position: Int, itemData: T) -> Unit): ListDialog<T> {
        this.onBindData = onBindData
        return this
    }

    /**
     * 设置布局监听
     */
    fun setOnLayoutParamsListener(onLayoutParams: Function2<RecyclerView, FrameLayout.LayoutParams, Unit>): ListDialog<T> {
        this.onLayoutParams = onLayoutParams
        return this
    }

    /**
     * 设置数据
     */
    fun setDatas(datas: MutableList<T>): ListDialog<T> {
        if (itemLayoutRes == null) {
            safeDismiss()
        }
        adapter.refreshList(datas)
        return this
    }

    override fun isCanceledOnTouchOutside(): Boolean {
        return true
    }

    private val adapter = Adapter()

    val recyclerView by lazy {
        BaseRecyclerView(context!!).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ListDialog.adapter
        }
    }

    override fun getContentResOrView(): Any? = BaseLinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addView(recyclerView)
    }

    private inner class Adapter : BaseRcvAdapter<T, Holder>() {
        override fun getItemLayoutRes(): Int = itemLayoutRes!!

        override fun onCreateViewHolder(itemView: View): Holder = Holder(itemView)

        override fun onBindViewHolder(holder: Holder, position: Int, itemData: T) {
            onBindData?.invoke(holder.itemView, position, itemData)
            holder.itemView.setOnClickListener(SingleClickListener {
                onItemClickListener?.invoke(this@ListDialog, it, position, itemData)
            })
        }
    }

    private inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

}