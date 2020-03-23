package com.yizisu.music.and.video.module.down_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.yizisu.music.and.video.AppData
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.baselib.BaseUiActivity
import com.yizisu.music.and.video.module.play_list_detail.PlayListDetailActivity

class DownLoadTaskActivity : BaseUiActivity() {

    override fun getContentResOrView(inflater: LayoutInflater): Any? =
        R.layout.activity_down_load_task

    override fun initUi(savedInstanceState: Bundle?) {
        super.initUi(savedInstanceState)
        title = "下载管理"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menu?.add("已下载")?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == "已下载") {
            PlayListDetailActivity.start(this, AppData.dbDownloadAlbumData.data)
        }
        return super.onOptionsItemSelected(item)
    }

}
