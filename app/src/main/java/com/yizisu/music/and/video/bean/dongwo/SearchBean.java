package com.yizisu.music.and.video.bean.dongwo;

import com.google.gson.annotations.SerializedName;
import com.yizisu.music.and.roomdblibrary.bean.AlbumInfoTable;
import com.yizisu.music.and.roomdblibrary.bean.SingerInfoTable;
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable;

import java.util.List;

public class SearchBean  {
    private List<SongInfoTable> songInfoTables;

    public List<SongInfoTable> getSongInfoTables() {
        return songInfoTables;
    }

    public void setSongInfoTables(List<SongInfoTable> songInfoTables) {
        this.songInfoTables = songInfoTables;
    }

    private List<SingerInfoTable> singerInfoTables;

    public List<SingerInfoTable> getSingerInfoTables() {
        return singerInfoTables;
    }

    public void setSingerInfoTables(List<SingerInfoTable> singerInfoTables) {
        this.singerInfoTables = singerInfoTables;
    }

}
