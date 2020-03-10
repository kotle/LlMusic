package com.yizisu.music.and.roomdblibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SongWithSinger {
    @Id
    private Long id;
    private Long singerId;
    private Long songId;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
    @Generated(hash = 1596129706)
    public SongWithSinger(Long id, Long singerId, Long songId, Long createTime,
            Long updateTime) {
        this.id = id;
        this.singerId = singerId;
        this.songId = songId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    @Generated(hash = 801115879)
    public SongWithSinger() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSingerId() {
        return this.singerId;
    }
    public void setSingerId(Long singerId) {
        this.singerId = singerId;
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
