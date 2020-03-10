package com.yizisu.music.and.roomdblibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.DaoException;

import com.greendao.gen.DaoSession;
import com.greendao.gen.SongInfoTableDao;
import com.greendao.gen.AlbumInfoTableDao;

/**
 * 专辑表
 * <p>
 * * 歌单和专辑应该是一样的数据库结构
 * * 不同的是专辑是官方的，歌单是自己的
 */

@Entity(nameInDb = "album_info")
public class AlbumInfoTable {
    //自增的数据库id
    @Id(autoincrement = true)
    private Long dbId;
    //id
    @Index
    public Long id;

    //歌曲来源，本地0，百度1，网易云2
    private Integer source;

    //歌单类型，我喜欢，自建等等
    //歌手类型，收费，免费等等
    //歌曲类型，收费，免费等等
    private Integer type;
    //专辑封面图,本地路径。
    private String filePath;
    //专辑封面图,网络路径。
    private String urlPath;
    //专辑名称
    private String title;
    //专辑描述
    private String des;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
    //一个专辑多个歌曲
    //多对多
    //一个歌曲可以在多个专辑
    //一个专辑可以多个歌曲
    @ToMany
    @JoinEntity(
            entity = SongWithAlbum.class,
            sourceProperty = "albumId",
            targetProperty = "songId"
    )
    private List<SongInfoTable> songInfoTables;

    public void setSongInfoTables(List<SongInfoTable> songInfoTables) {
        this.songInfoTables = songInfoTables;
    }

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 644635760)
    private transient AlbumInfoTableDao myDao;
    @Generated(hash = 1988958292)
    public AlbumInfoTable(Long dbId, Long id, Integer source, Integer type,
            String filePath, String urlPath, String title, String des,
            Long createTime, Long updateTime) {
        this.dbId = dbId;
        this.id = id;
        this.source = source;
        this.type = type;
        this.filePath = filePath;
        this.urlPath = urlPath;
        this.title = title;
        this.des = des;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    @Generated(hash = 56033727)
    public AlbumInfoTable() {
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
    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getUrlPath() {
        return this.urlPath;
    }
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    @Generated(hash = 1131950937)
    public List<SongInfoTable> getSongInfoTables() {
        if (songInfoTables == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SongInfoTableDao targetDao = daoSession.getSongInfoTableDao();
            List<SongInfoTable> songInfoTablesNew = targetDao
                    ._queryAlbumInfoTable_SongInfoTables(dbId);
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
    @Generated(hash = 1814313285)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAlbumInfoTableDao() : null;
    }

}
