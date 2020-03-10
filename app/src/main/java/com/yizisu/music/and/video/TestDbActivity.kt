package com.yizisu.music.and.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yizisu.music.and.roomdblibrary.DbCons
import com.yizisu.music.and.roomdblibrary.DbHelper
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SingerInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable
import com.yizisu.music.and.roomdblibrary.bean.SongWithSinger
import kotlinx.android.synthetic.main.activity_test_db.*

class TestDbActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_db)
        DbHelper.init(this, "testDb")
        insetAlbum.setOnClickListener {
        }

    }

}
