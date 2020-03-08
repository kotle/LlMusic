package com.yizisu.music.and.video.bean;

public class LocalMusicBean {
    public static String SOURCE_TYPE_LOCAL = "本地";
    public static String SOURCE_TYPE_BAIDU = "百度";
    public static String SOURCE_TYPE_NETEASE = "网易云";
    public long id;//歌曲id
    public String title;//歌曲名
    public String singer;//歌手
    public String album = "";//专辑
    public long size;//歌曲所占空间大小
    public int duration;//歌曲时间长度
    public int height;//歌曲时间长度
    public int width;//歌曲时间长度
    public String path;//歌曲地址
    public String sourceType;//歌曲来源
    public String lrcUrl;//歌曲来源
    public String coverUrl;//歌曲来源

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
