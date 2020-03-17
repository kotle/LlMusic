package com.yizisu.music.and.video.dialog

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBeanStatus
import com.yizisu.basemvvm.mvvm.mvvm_helper.fail
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.basemvvm.utils.switchToUi
import com.yizisu.basemvvm.utils.toast
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.base.BaseDialog
import com.yizisu.music.and.video.bean.netease.PlayListNeteaseBean
import com.yizisu.music.and.video.utils.refreshAllAlbum
import com.yizisu.music.and.video.viewmodel.PlayListViewModel
import com.yizisu.music.and.video.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.dialog_import_play_list.*

class ImportPlayListDialog : BaseDialog() {
    companion object {
        fun show(appCompatActivity: AppCompatActivity?) {
            appCompatActivity ?: return
            ImportPlayListDialog().show(appCompatActivity)
        }
    }

    private val viewModel by lazy { getViewModel<PlayListViewModel>() }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.neteasePlayListData?.register {
            when (it.status) {
                LiveBeanStatus.START -> {
                    showLoadingView()
                }
                LiveBeanStatus.SUCCESS -> {
                    val result = it.data?.result
                    if (result == null) {
                        showContentView()
                        hintTv.text = "获取歌单失败"
                    } else {
                        startImport(result)
                    }
                }

                LiveBeanStatus.FAIL -> {
                    showContentView()
                    hintTv.text = it.errorMsg
                }
            }
        }
    }


    override fun isNeedSwitchView(): Boolean {
        return true
    }

    override fun getContentResOrView(): Any? = R.layout.dialog_import_play_list

    override fun getClickView(): List<View?>? {
        return listOf(dialogCancelTv, dialogOkTv)
    }

    override fun onSingleClick(view: View) {
        super.onSingleClick(view)
        when (view) {
            dialogCancelTv -> {
                dismiss()
            }
            dialogOkTv -> {
                val url = playListUrlEt.text.toString()
                if (url.isEmpty()) {
                    viewModel.neteasePlayListData.fail("请输入地址")
                } else {
                    viewModel.netneasePlayListDetail(url)
                }
            }
        }
    }

    /**
     * 歌单导入数据库
     */
    private fun playListWriteDb(result: PlayListNeteaseBean.ResultBean) {
//        val id = result.id
//        val album = DbHelper.queryAlbum(id, DbCons.SOURCE_NETEASE)
//        if (!album.isNullOrEmpty()) {
//            showContentView()
//            PlayListConflictDialog.show(appCompatActivity, "查询到已存在歌单：${album.map { it.title }}") {
//                dealSelectPlayList(it, album, result)
//            }
//            return
//        } else {
//            launchThread {
//                startImport(result)
//            }
//        }
    }

    /**
     * 检测到冲突
     */
    private fun dealSelectPlayList(
        it: Int,
        oldAlbumInfoTable: AlbumInfoTable,
        result: PlayListNeteaseBean.ResultBean
    ) {
//        showLoadingView()
//        launchThread {
//            when (it) {
//                PlayListConflictDialog.SELECT_REPLACE -> {
//                    //删除之前的
//                    DbHelper.deleteAlbum(oldAlbumInfoTable.dbId)
//                    startImport(result)
//                }
//                PlayListConflictDialog.SELECT_CREATE_NEW -> {
//                    startImport(result)
//                }
//            }
//        }
    }


    private fun startImport(result: PlayListNeteaseBean.ResultBean) {
        launchThread {
            val songs = result.tracks
            val name = result.name
            val des = result.description
            val id = result.id
            val source = DbCons.SOURCE_NETEASE
            val album = DbHelper.queryAlbum(id, source)
            var urlPath: String? = null
            val songInfoTables = SearchViewModel.neteaseSongsFormat(songs)?.asReversed()
            if (!songInfoTables.isNullOrEmpty()) {
                urlPath = songInfoTables.last().coverUrlPath
            }
            if (!album.isNullOrEmpty()) {
                album.forEach {
                    DbHelper.deleteAlbum(it.dbId)
                }
            }
            DbHelper.addSongToAlbum(
                songInfoTables,
                AlbumInfoTable(
                    null,
                    id.toString(),
                    source,
                    DbCons.TYPE_FREE,
                    null,
                    urlPath,
                    name,
                    des,
                    System.currentTimeMillis(),
                    System.currentTimeMillis()
                )
            )
            switchToUi {
                safeDismiss()
                refreshAllAlbum()
                "歌单导入成功\n本次共导入歌曲${songInfoTables?.count()}首".toast()
            }
        }
    }
}