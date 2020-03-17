package com.yizisu.music.and.video.module.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.MediatorLiveBean
import com.yizisu.basemvvm.utils.*
import com.yizisu.basemvvm.view.simpleFragmentPagerAdapter
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseActivity
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.module.fragment.search.SearchFragment
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search_music.*

class SearchMusicActivity : BaseActivity() {

    companion object {
        fun start(appCompatActivity: AppCompatActivity?) {
            appCompatActivity?.navigateTo(SearchMusicActivity::class.java)
        }
    }

    private val searchViewModel by lazy { getViewModel<SearchViewModel>() }
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.activity_search_music
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        window.statusBarColor = getResColor(R.color.colorAccent)
        searchEt.post {
            showKeyboard(searchEt)
        }
        searchToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        searchEt.onSearch {
            startSearch()
        }

        searchEt.afterTextChanged {
            if (it.isNullOrBlank()) {
                searchVp.invisible()
                searchMusicTab.invisible()
            }
        }
        searchVp.adapter = simpleFragmentPagerAdapter(
            mutableListOf(
                SearchFragment.create(DbCons.SOURCE_LOCAL),
                SearchFragment.create(DbCons.SOURCE_NETEASE),
                SearchFragment.create(DbCons.SOURCE_KUGOU),
                SearchFragment.create(DbCons.SOURCE_BAIDU)
            )
        )
        searchVp.invisible()
        searchMusicTab.invisible()
        searchMusicTab.setupWithViewPager(searchVp)
    }

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun getClickView(): List<View?>? {
        return listOf(searchBt)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            searchBt -> {
                startSearch()
            }
        }
    }

    private fun startSearch() {
        val keyword = searchEt.text?.toString()
        if (keyword.isNullOrBlank()) {
            R.string.search_hint.toast()
            return
        }
        searchViewModel.search(keyword)
        searchVp.visible()
        searchMusicTab.visible()
        hiddenKeyboard()
    }
}
