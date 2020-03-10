package com.yizisu.music.and.video.module.fragment.search


import android.os.Bundle
import android.view.LayoutInflater
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable

import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment() {
    companion object {
        fun create(type: String): SearchFragment {
            return SearchFragment().apply {
                sourceType = type
            }
        }
    }

    private var sourceType: String? = null
    private val searchAdapter = SearchAdapter()
    private val searchViewModel by lazy { getActivityViewModel<SearchViewModel>() }
    override fun getContentResOrView(inflater: LayoutInflater): Any? = R.layout.fragment_search


    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        searchRcv.adapter = searchAdapter
    }

    override fun initViewModel() {
        super.initViewModel()
        when (sourceType) {
            //搜索网易云
            LocalMusicBean.SOURCE_TYPE_NETEASE -> {
                searchViewModel?.neteaseSearchData?.register {
                    loadSuccess(it) {
                        refreshAdapter(searchViewModel?.neteaseToSearchBean(it.data))
                    }
                }
            }
            //搜索百度
            LocalMusicBean.SOURCE_TYPE_BAIDU -> {
                searchViewModel?.baiduSearchData?.register {
                    loadSuccess(it) {
                        refreshAdapter(searchViewModel?.baiduToSearchBean(it.data))
                    }
                }
            }
        }
    }

    /**
     * 处理加载成功
     */
    private fun <T> loadSuccess(data: LiveBean<T>, onSuccess: (LiveBean<T>) -> Unit) {
        when (data.status) {
            LiveBeanStatus.START -> {
                showLoadingView()
                searchAdapter.clearDatas()
            }
            LiveBeanStatus.SUCCESS -> {
                showContentView()
                onSuccess.invoke(data)
            }
            LiveBeanStatus.FAIL -> {
                showOtherView(data.errorMsg)
            }
        }
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    private fun refreshAdapter(bean: SearchBean?) {
//        searchAdapter.loadMoreList(bean?.data)
        searchAdapter.refreshList(bean?.songInfoTables)
    }

    override fun getTitle(): CharSequence? {
        return sourceType
    }

}
