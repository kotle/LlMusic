package com.yizisu.music.and.roomdblibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class SongWithAlbum {
    @Id
    private Long id;
    @Index
    private Long albumId;
    private Long songId;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;

    @Generated(hash = 1395467662)
    public SongWithAlbum(Long id, Long albumId, Long songId, Long createTime,
                         Long updateTime) {
        this.id = id;
        this.albumId = albumId;
        this.songId = songId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 403150121)
    public SongWithAlbum() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getSongId() {
        return this.songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
