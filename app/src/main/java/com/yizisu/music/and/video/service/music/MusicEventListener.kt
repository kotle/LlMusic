package com.yizisu.music.and.video.service.music

import com.yizisu.music.and.video.bean.SongModel
import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener

interface MusicEventListener : SimplePlayerListener<SongModel> {
    override fun onTick(playerModel: SongModel) {

    }
}