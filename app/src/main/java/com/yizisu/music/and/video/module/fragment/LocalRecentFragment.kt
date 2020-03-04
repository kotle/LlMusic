package com.yizisu.music.and.video.module.fragment


import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.utils.getCountTimeByLong
import com.yizisu.basemvvm.view.simpleTextRcvAdater
import com.yizisu.music.and.video.AppData


import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.module.fragment.adapter.LocalMusicAdapter
import com.yizisu.music.and.video.module.full_video.FullVideoActivity
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.LocalMusicViewModel
import com.yizisu.playerlibrary.helper.PlayerModel
import kotlinx.android.synthetic.main.fragment_local_music.*

class LocalRecentFragment : BaseFragment() {
    companion object {
        fun create(): LocalRecentFragment {
            return LocalRecentFragment()
        }
    }

    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_local_music
    }

    private val adapter = LocalMusicAdapter()

    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun initViewModel() {
        super.initViewModel()
        getActivityViewModel<LocalMusicViewModel>()?.testVideoData?.register {
            when (it.status) {
                LiveBeanStatus.START -> {
                    showLoadingView()
                }
                LiveBeanStatus.SUCCESS -> {
                    showContentView()
                    it.data?.trailers?.let {
                        adapter.refreshList(it.map {
                            LocalMusicBean().apply {
                                path = it.hightUrl ?: it.url
                                title = it.movieName
                                singer = "[${it.type}] [${getCountTimeByLong(
                                    it.videoLength.toLong() * 1000
                                )}] ${it.videoTitle}"
                            }
                        }.toMutableList())
                    }
                }
                LiveBeanStatus.FAIL -> {
                    showOtherView(it.errorMsg)
                }
            }
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            FullVideoActivity.start(
                appCompatActivity, FullVideoActivity.FullVideoData(
                    itemData.path,
                    itemData.title
                )
            )
        }
        localMusicRcv.adapter = adapter
    }

    override fun onFirstVisible() {
        super.onFirstVisible()
        getActivityViewModel<LocalMusicViewModel>()?.queryTestVideo()
    }
}
