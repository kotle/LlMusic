package com.yizisu.music.and.video.service.music

import com.yizisu.playerlibrary.helper.PlayerModel
import com.yizisu.playerlibrary.helper.SimplePlayerListener

interface MusicEventListener : SimplePlayerListener {
    override fun onTick(playerModel: PlayerModel) {

    }
}