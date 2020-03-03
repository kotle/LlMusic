package com.yizisu.music.and.video.bean

import android.net.Uri
import com.yizisu.basemvvm.utils.launchThread
import com.yizisu.playerlibrary.helper.PlayerModel

class SongModel(val song: LocalMusicBean) : PlayerModel() {
    override fun callMediaUri(uriCall: (Uri) -> Unit) {
        launchThread {
            uriCall.invoke(Uri.parse(song.path))
        }
    }
}