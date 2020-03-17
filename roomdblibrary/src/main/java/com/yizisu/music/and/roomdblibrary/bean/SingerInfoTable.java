package com.yizisu.music.and.roomdblibrary.bean;

import com.greendao.gen.DaoSession;
import com.greendao.gen.SingerInfoTableDao;
import com.greendao.gen.SongInfoTableDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;


/**
 * 歌手表
 * 保存歌手的一些基本信息
 */
@Entity(nameInDb = "singer_info")
public class SingerInfoTable {
    //自增的数据库id
    @Id(autoincrement = true)
    private Long dbId;
    //id
    @Index
    private String id;
    //歌曲来源，本地0，百度1，网易云2
    private Integer source;

    //歌单类型，我喜欢，自建等等
    //歌手类型，收费，免费等等
    //歌曲类型，收费，免费等等
    private Integer type;
    private String name;
    private String des;
    private Long createTime;
    private Long updateTime;
    //一个歌手多个歌曲
    //一个歌曲有多个歌手
    //多对多
    @ToMany
    @JoinEntity(
            entity = SongWithSinger.class,
            sourceProperty = "singerId",
            targetProperty = "songId"
    )
    private List<SongInfoTable> songInfoTables;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 590133284)
    private transient SingerInfoTableDao myDao;
    @Generated(hash = 739759107)
    public SingerInfoTable(Long dbId, String id, Integer source, Integer type,
            String name, String des, Long createTime, Long updateTime) {
        this.dbId = dbId;
        this.id = id;
        this.source = source;
        this.type = type;
        this.name = name;
        this.des = des;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    @Generated(hash = 213847969)
    public SingerInfoTable() {
    }
    public Long getDbId() {
        return this.dbId;
    }
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getSource() {
        return this.source;
    }
    public void setSource(Integer source) {
        this.source = source;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
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
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1889111026)
    public List<SongInfoTable> getSongInfoTables() {
        if (songInfoTables == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SongInfoTableDao targetDao = daoSession.getSongInfoTableDao();
            List<SongInfoTable> songInfoTablesNew = targetDao
                    ._querySingerInfoTable_SongInfoTables(dbId);
            synchronized (this) {
                if (songInfoTables == null) {
                    songInfoTables = songInfoTablesNew;
                }
            }
        }
        return songInfoTables;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 638000004)
    public synchronized void resetSongInfoTables() {
        songInfoTables = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1406877959)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSingerInfoTableDao() : null;
    }

}
