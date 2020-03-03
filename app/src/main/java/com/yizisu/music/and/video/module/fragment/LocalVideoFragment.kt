package com.yizisu.music.and.video.module.fragment


import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.view.simpleTextRcvAdater
import com.yizisu.music.and.video.AppData


import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseFragment
import com.yizisu.music.and.video.bean.LocalMusicBean
import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.music.and.video.module.fragment.adapter.LocalMusicAdapter
import com.yizisu.music.and.video.module.local_music.adapter.LocalPagerAdapter
import com.yizisu.music.and.video.service.music.MusicService
import com.yizisu.music.and.video.viewmodel.LocalMusicViewModel
import com.yizisu.playerlibrary.helper.PlayerModel
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
        AppData.localVideoData.register(::onQueryLocalMusic)
    }

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        adapter.setOnItemClickListener { itemView, position, itemData ->
            MusicService.startPlay(adapter.datas.map {
                SongModel(it)
            }.toMutableList(), position, true, this@LocalVideoFragment::class.java.simpleName)
        }
        localMusicRcv.adapter = adapter
    }

    override fun initData() {
        super.initData()
        getPermission(
            mutableListOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            if (it) {
                getActivityViewModel<LocalMusicViewModel>()?.queryLocalVideo()
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
                if (!musics.isNullOrEmpty()) {
                    adapter.refreshList(musics)
                }
            }
            LiveBeanStatus.FAIL -> {

            }
        }
    }
}
