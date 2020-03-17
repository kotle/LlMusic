package com.yizisu.music.and.video.bean.kugou;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongInfoKugouBean {

    /**
     * status : 1
     * err_code : 0
     */

    @SerializedName("status")
    private int status;
    @SerializedName("err_code")
    private int errCode;
    @SerializedName("data")
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hash : CB7EE97F4CC11C4EA7A1FA4B516A5D97
         * timelength : 199000
         * filesize : 3198974
         * audio_name : 李玉刚 - 刚好遇见你
         * have_album : 1
         * album_name : 刚好遇见你 (单曲)
         * album_id : 1820033
         * img : http://imge.kugou.com/stdmusic/20161109/20161109171040932108.jpg
         * have_mv : 1
         * video_id : 596892
         * author_name : 李玉刚
         * song_name : 刚好遇见你
         * lyrics : ﻿[id:$00000000]
         [ar:李玉刚]
         [ti:刚好遇见你]
         [by:]
         [hash:cb7ee97f4cc11c4ea7a1fa4b516a5d97]
         [al:]
         [sign:]
         [qq:]
         [total:0]
         [offset:0]
         [00:00.02]李玉刚 - 刚好遇见你
         [00:00.75]词：高进
         [00:00.85]曲：高进
         [00:00.94]编曲：关天天
         [00:13.13]我们哭了
         [00:15.79]我们笑着
         [00:18.83]我们抬头望天空
         [00:21.86]星星还亮着几颗
         [00:24.98]我们唱着
         [00:27.96]时间的歌
         [00:31.09]才懂得相互拥抱
         [00:33.98]到底是为了什么
         [00:37.30]因为我刚好遇见你
         [00:40.77]留下足迹才美丽
         [00:43.79]风吹花落泪如雨
         [00:46.80]因为不想分离
         [00:49.95]因为刚好遇见你
         [00:53.10]留下十年的期许
         [00:55.99]如果再相遇
         [00:59.21]我想我会记得你
         [01:14.32]我们哭了
         [01:17.23]我们笑着
         [01:20.34]我们抬头望天空
         [01:23.33]星星还亮着几颗
         [01:26.51]我们唱着
         [01:29.53]时间的歌
         [01:32.59]才懂得相互拥抱
         [01:35.59]到底是为了什么
         [01:38.73]因为我刚好遇见你
         [01:42.23]留下足迹才美丽
         [01:45.30]风吹花落泪如雨
         [01:48.39]因为不想分离
         [01:51.55]因为刚好遇见你
         [01:54.54]留下十年的期许
         [01:57.61]如果再相遇
         [02:00.81]我想我会记得你
         [02:03.99]因为刚好遇见你
         [02:06.86]留下足迹才美丽
         [02:09.94]风吹花落泪如雨
         [02:13.03]因为不想分离
         [02:16.06]因为刚好遇见你
         [02:19.16]留下十年的期许
         [02:22.21]如果再相遇
         [02:25.34]我想我会记得你
         [02:31.40]因为我刚好遇见你
         [02:34.51]留下足迹才美丽
         [02:37.59]风吹花落泪如雨
         [02:40.67]因为不想分离
         [02:43.77]因为刚好遇见你
         [02:46.84]留下十年的期许
         [02:49.94]如果再相遇
         [02:53.11]我想我会记得你
         * author_id : 2018
         * privilege : 8
         * privilege2 : 1000
         * play_url : https://webfs.yun.kugou.com/202003171033/a3c06588f20eb59a9b735ec290778213/G078/M08/18/17/jg0DAFgi6G-AKqsqADDP_nSW5F4051.mp3
         * authors : [{"author_id":"2018","is_publish":"1","sizable_avatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20181224/20181224183453372.jpg","author_name":"李玉刚","avatar":"http://singerimg.kugou.com/uploadpic/softhead/400/20181224/20181224183453372.jpg"}]
         * is_free_part : 0
         * bitrate : 128
         * audio_id : 23609415
         * play_backup_url : https://webfs.cloud.kugou.com/202003171033/2f1ba86484b13bcd6b3c78c0adad6e7f/G078/M08/18/17/jg0DAFgi6G-AKqsqADDP_nSW5F4051.mp3
         */

        @SerializedName("hash")
        private String hash;
        @SerializedName("timelength")
        private int timelength;
        @SerializedName("filesize")
        private int filesize;
        @SerializedName("audio_name")
        private String audioName;
        @SerializedName("have_album")
        private int haveAlbum;
        @SerializedName("album_name")
        private String albumName;
        @SerializedName("album_id")
        private String albumId;
        @SerializedName("img")
        private String img;
        @SerializedName("have_mv")
        private int haveMv;
        @SerializedName("video_id")
        private String videoId;
        @SerializedName("author_name")
        private String authorName;
        @SerializedName("song_name")
        private String songName;
        @SerializedName("lyrics")
        private String lyrics;
        @SerializedName("author_id")
        private String authorId;
        @SerializedName("privilege")
        private int privilege;
        @SerializedName("privilege2")
        private String privilege2;
        @SerializedName("play_url")
        private String playUrl;
        @SerializedName("is_free_part")
        private int isFreePart;
        @SerializedName("bitrate")
        private int bitrate;
        @SerializedName("audio_id")
        private String audioId;
        @SerializedName("play_backup_url")
        private String playBackupUrl;
        @SerializedName("authors")
        private List<AuthorsBean> authors;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getTimelength() {
            return timelength;
        }

        public void setTimelength(int timelength) {
            this.timelength = timelength;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public String getAudioName() {
            return audioName;
        }

        public void setAudioName(String audioName) {
            this.audioName = audioName;
        }

        public int getHaveAlbum() {
            return haveAlbum;
        }

        public void setHaveAlbum(int haveAlbum) {
            this.haveAlbum = haveAlbum;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getHaveMv() {
            return haveMv;
        }

        public void setHaveMv(int haveMv) {
            this.haveMv = haveMv;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public String getLyrics() {
            return lyrics;
        }

        public void setLyrics(String lyrics) {
            this.lyrics = lyrics;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public int getPrivilege() {
            return privilege;
        }

        public void setPrivilege(int privilege) {
            this.privilege = privilege;
        }

        public String getPrivilege2() {
            return privilege2;
        }

        public void setPrivilege2(String privilege2) {
            this.privilege2 = privilege2;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public int getIsFreePart() {
            return isFreePart;
        }

        public void setIsFreePart(int isFreePart) {
            this.isFreePart = isFreePart;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public String getAudioId() {
            return audioId;
        }

        public void setAudioId(String audioId) {
            this.audioId = audioId;
        }

        public String getPlayBackupUrl() {
            return playBackupUrl;
        }

        public void setPlayBackupUrl(String playBackupUrl) {
            this.playBackupUrl = playBackupUrl;
        }

        public List<AuthorsBean> getAuthors() {
            return authors;
        }

        public void setAuthors(List<AuthorsBean> authors) {
            this.authors = authors;
        }

        public static class AuthorsBean {
            /**
             * author_id : 2018
             * is_publish : 1
             * sizable_avatar : http://singerimg.kugou.com/uploadpic/softhead/{size}/20181224/20181224183453372.jpg
             * author_name : 李玉刚
             * avatar : http://singerimg.kugou.com/uploadpic/softhead/400/20181224/20181224183453372.jpg
             */

            @SerializedName("author_id")
            private String authorId;
            @SerializedName("is_publish")
            private String isPublish;
            @SerializedName("sizable_avatar")
            private String sizableAvatar;
            @SerializedName("author_name")
            private String authorName;
            @SerializedName("avatar")
            private String avatar;

            public String getAuthorId() {
                return authorId;
            }

            public void setAuthorId(String authorId) {
                this.authorId = authorId;
            }

            public String getIsPublish() {
                return isPublish;
            }

            public void setIsPublish(String isPublish) {
                this.isPublish = isPublish;
            }

            public String getSizableAvatar() {
                return sizableAvatar;
            }

            public void setSizableAvatar(String sizableAvatar) {
                this.sizableAvatar = sizableAvatar;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
