package com.yizisu.music.and.video.module.fragment.search


import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.yizisu.basemvvm.mvvm.MvvmDialog
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.utils.getResString
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.runOnUi
import com.yizisu.basemvvm.utils.textFrom
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.baselib.base.ListDialog
import com.yizisu.music.and.video.bean.dongwo.SearchBean
import com.yizisu.music.and.video.module.search.adapter.SearchAdapter
import com.yizisu.music.and.video.utils.dbViewModel
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment() {
    companion object {
        fun create(type: Int): SearchFragment {
            return SearchFragment().apply {
                sourceType = type
            }
        }
    }

    private var sourceType: Int? = null
    private val searchAdapter = SearchAdapter()
    private val searchViewModel by lazy { getActivityViewModel<SearchViewModel>() }
    override fun getContentResOrView(inflater: LayoutInflater): Any? = R.layout.fragment_search


    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        searchRcv.adapter = searchAdapter
        searchAdapter.setOnItemLongClickListener { itemView, position, itemData ->
            val song = itemData
            ListDialog<AlbumInfoTable>().apply {
                setItemLayoutRes(R.layout.rcv_item_local_music)
                setOnBindDataListener { itemView, position, itemData ->
                    itemView.findViewById<TextView>(R.id.songNameTv)?.textFrom(itemData.title)
                    itemView.findViewById<TextView>(R.id.songDesTv)?.textFrom(itemData.des)
                }
                setItemClickListener { dialog, itemView, position, data ->
                    addNewSongToAlbum(song, data)
                    dialog.dismiss()
                }
                setDatas(AppData.allAlbumData.data?.filter {
                    it.id == DbCons.ALBUM_ID_HEART || it.id == DbCons.ALBUM_ID_NORMAL
                }?.toMutableList())
            }.show(appCompatActivity)
        }
    }

    private fun addNewSongToAlbum(song: SongInfoTable, album: AlbumInfoTable) {
        launchThread {
            DbHelper.addSongToAlbum(song, album)
            when (album.dbId) {
                DbCons.ALBUM_ID_HEART -> {
                    dbViewModel.queryHeartList()
                }
                else -> {
                }
            }
        }
    }

    private val titles by lazy {
        mutableMapOf(
            DbCons.SOURCE_LOCAL to getResString(R.string.local_music),
            DbCons.SOURCE_NETEASE to getResString(R.string.netease_music),
            DbCons.SOURCE_BAIDU to getResString(R.string.baidu_music)
        )
    }

    override fun initViewModel() {
        super.initViewModel()
        when (sourceType) {
            //搜索网易云
            DbCons.SOURCE_NETEASE -> {
                searchViewModel?.neteaseSearchData?.register {
                    loadSuccess(it) {
                        refreshAdapter(searchViewModel?.neteaseToSearchBean(it.data))
                    }
                }
            }
            //搜索百度
            DbCons.SOURCE_BAIDU -> {
                searchViewModel?.baiduSearchData?.register {
                    loadSuccess(it) {
                        refreshAdapter(searchViewModel?.baiduToSearchBean(it.data))
                    }
                }
            }
            DbCons.SOURCE_LOCAL -> {
                searchViewModel?.localSearchData?.register {
                    loadSuccess(it) {
                        refreshAdapter(it.data)
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
        if (bean?.songInfoTables.isNullOrEmpty()) {
            showOtherView("什么都没搜到呢")
        } else {
            searchAdapter.refreshList(bean?.songInfoTables)
        }
    }

    override fun getTitle(): CharSequence? {
        return titles[sourceType]
    }

}
