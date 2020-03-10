package com.yizisu.music.and.roomdblibrary.bean;

import com.greendao.gen.SingerInfoTableDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.DaoException;

import com.greendao.gen.DaoSession;
import com.greendao.gen.AlbumInfoTableDao;
import com.greendao.gen.SongInfoTableDao;

/**
 * 歌曲信息表
 */
@Entity(nameInDb = "song_info")
public class SongInfoTable {
    //自增的数据库id
    @Id(autoincrement = true)
    private Long dbId;
    //id
    @Index
    private Long id;
    //歌曲来源，本地0，百度1，网易云2
    private Integer source;
    //歌单类型，我喜欢，自建等等
    private Integer type;
    private String des;
    private String name;
    private Long createTime;
    private Long updateTime;
    //一首歌曲包含歌手和专辑
    //歌曲长度
    private Long duration;
    //歌曲内存大小
    private Long size;
    private String coverFilePath;
    private String coverUrlPath;
    private String playFilePath;
    private String playUrlPath;
    private String lrcFilePath;
    private String lrcUrlPath;
    private String lrcString;
    //多对多
    //一个歌曲可以在多个专辑
    //一个专辑可以多个歌曲
    @ToMany
    @JoinEntity(
            entity = SongWithAlbum.class,
            sourceProperty = "songId",
            targetProperty = "albumId"
    )
    private List<AlbumInfoTable> albumInfoTables;
    //多对多
    //一首歌有多个歌手
    //一个歌手多首歌
    @ToMany
    @JoinEntity(
            entity = SongWithSinger.class,
            sourceProperty = "songId",
            targetProperty = "singerId"
    )
    private List<SingerInfoTable> singerInfoTables;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 314583867)
    private transient SongInfoTableDao myDao;

    @Generated(hash = 1041589305)
    public SongInfoTable(Long dbId, Long id, Integer source, Integer type, String des, String name, Long createTime, Long updateTime, Long duration, Long size, String coverFilePath, String coverUrlPath, String playFilePath, String playUrlPath, String lrcFilePath, String lrcUrlPath, String lrcString) {
        this.dbId = dbId;
        this.id = id;
        this.source = source;
        this.type = type;
        this.des = des;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.duration = duration;
        this.size = size;
        this.coverFilePath = coverFilePath;
        this.coverUrlPath = coverUrlPath;
        this.playFilePath = playFilePath;
        this.playUrlPath = playUrlPath;
        this.lrcFilePath = lrcFilePath;
        this.lrcUrlPath = lrcUrlPath;
        this.lrcString = lrcString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SongInfoTable(Long dbId, Long id, Integer source, Integer type, String des, String name, Long createTime, Long updateTime, Long duration, Long size, String coverFilePath, String coverUrlPath, String playFilePath, String playUrlPath, String lrcFilePath, String lrcUrlPath, String lrcString, List<AlbumInfoTable> albumInfoTables, List<SingerInfoTable> singerInfoTables, DaoSession daoSession, SongInfoTableDao myDao) {
        this.dbId = dbId;
        this.id = id;
        this.source = source;
        this.type = type;
        this.des = des;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.duration = duration;
        this.size = size;
        this.coverFilePath = coverFilePath;
        this.coverUrlPath = coverUrlPath;
        this.playFilePath = playFilePath;
        this.playUrlPath = playUrlPath;
        this.lrcFilePath = lrcFilePath;
        this.lrcUrlPath = lrcUrlPath;
        this.lrcString = lrcString;
        this.albumInfoTables = albumInfoTables;
        this.singerInfoTables = singerInfoTables;
        this.daoSession = daoSession;
        this.myDao = myDao;
    }

    @Generated(hash = 1640910853)
    public SongInfoTable() {
    }

    public Long getDbId() {
        return this.dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 650447324)
    public List<AlbumInfoTable> getAlbumInfoTables() {
        if (albumInfoTables == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AlbumInfoTableDao targetDao = daoSession.getAlbumInfoTableDao();
            List<AlbumInfoTable> albumInfoTablesNew = targetDao
                    ._querySongInfoTable_AlbumInfoTables(dbId);
            synchronized (this) {
                if (albumInfoTables == null) {
                    albumInfoTables = albumInfoTablesNew;
                }
            }
        }
        return albumInfoTables;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 38280237)
    public synchronized void resetAlbumInfoTables() {
        albumInfoTables = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2144768173)
    public List<SingerInfoTable> getSingerInfoTables() {
        if (singerInfoTables == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SingerInfoTableDao targetDao = daoSession.getSingerInfoTableDao();
            List<SingerInfoTable> singerInfoTablesNew = targetDao
                    ._querySongInfoTable_SingerInfoTables(dbId);
            synchronized (this) {
                if (singerInfoTables == null) {
                    singerInfoTables = singerInfoTablesNew;
                }
            }
        }
        return singerInfoTables;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1692628096)
    public synchronized void resetSingerInfoTables() {
        singerInfoTables = null;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 892623342)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSongInfoTableDao() : null;
    }

    public String getCoverFilePath() {
        return this.coverFilePath;
    }

    public void setCoverFilePath(String coverFilePath) {
        this.coverFilePath = coverFilePath;
    }

    public String getCoverUrlPath() {
        return this.coverUrlPath;
    }

    public void setCoverUrlPath(String coverUrlPath) {
        this.coverUrlPath = coverUrlPath;
    }

    public String getLrcFilePath() {
        return this.lrcFilePath;
    }

    public void setLrcFilePath(String lrcFilePath) {
        this.lrcFilePath = lrcFilePath;
    }

    public String getLrcUrlPath() {
        return this.lrcUrlPath;
    }

    public void setLrcUrlPath(String lrcUrlPath) {
        this.lrcUrlPath = lrcUrlPath;
    }

    public String getLrcString() {
        return this.lrcString;
    }

    public void setLrcString(String lrcString) {
        this.lrcString = lrcString;
    }

    public String getPlayFilePath() {
        return this.playFilePath;
    }

    public void setPlayFilePath(String playFilePath) {
        this.playFilePath = playFilePath;
    }

    public String getPlayUrlPath() {
        return this.playUrlPath;
    }

    public void setPlayUrlPath(String playUrlPath) {
        this.playUrlPath = playUrlPath;
    }

}
