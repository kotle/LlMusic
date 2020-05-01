package com.yizisu.music.and.video.module.fragment.test


import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.getActivityViewModel
import com.yizisu.basemvvm.mvvm.mvvm_helper.registerLiveBean
import com.yizisu.basemvvm.utils.permission.PermissionUtil
import com.yizisu.music.and.video.AppData


import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.module.fragment.test.adapter.LocalMusicAdapter
import com.yizisu.music.and.video.module.full_video.FullVideoActivity
import com.yizisu.music.and.video.viewmodel.LocalMusicViewModel
import kotlinx.android.synthetic.main.fragment_local_music.*

class LocalVideoFragment : BaseFragment() {
    companion object {
        fun create(): LocalVideoFragment {
            return LocalVideoFragment()
        }
    }

    private val adapter = LocalMusicAdapter()
    override fun getContentResOrView(inflater: LayoutInflater): Any? {
        return R.layout.fragment_local_music
    }

    override fun initViewModel() {
        super.initViewModel()
        registerLiveBean(
            getActivityViewModel<LocalMusicViewModel>().localVideoData,
            ::onQueryLocalMusic
        )
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)

        adapter.setOnItemClickListener { itemView, position, itemData ->
//            FullVideoActivity.start(
//                appCompatActivity, FullVideoActivity.FullVideoData(
//                    itemData.path,
//                    itemData.title
//                )
//            )
        }
        localMusicRcv.adapter = adapter
    }

    override fun initData() {
        super.initData()
        PermissionUtil.request(
            mutableListOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            if (it.isEmpty()) {
                getActivityViewModel<LocalMusicViewModel>().queryLocalVideo()
            }
        }
    }

    /**
     * 查询本地音乐
     */
    private fun onQueryLocalMusic(bean: LiveBean<MutableList<LocalMusicBean>>) {
        when (bean.status) {
            LiveBeanStatus.START -> {

            }
            LiveBeanStatus.SUCCESS -> {
                val musics = bean.data
//                adapter.refreshList(musics)
            }
            LiveBeanStatus.FAIL -> {

            }
        }
    }
}
