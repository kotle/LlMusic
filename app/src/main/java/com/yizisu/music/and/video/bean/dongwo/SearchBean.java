package com.yizisu.music.and.video.bean.dongwo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchBean {

    /**
     * code : 1
     * data : [{"type":"qq","link":"http://y.qq.com/n/yqq/song/003TLQVN4XZuPR.html","songid":"003TLQVN4XZuPR","title":"再见吧我最爱的你","author":"东方晴儿","lrc":"","url":"http://dl.stream.qqmusic.qq.com/M500003TLQVN4XZuPR.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=17D20C4813093E7DDD03505663E185B5364C6B940EA821BE2573ADC4D3CC418EA89E8310EF416229E3F747BFFE3E5325CB38FFAA075FEE5B&fromtag=46","pic":"http://y.gtimg.cn/music/photo_new/T002R300x300M000003RZEXe1fG18Y.jpg"},"...."]
     * msg :
     * url :
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("url")
    private String url;
    @SerializedName("data")
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : qq
         * link : http://y.qq.com/n/yqq/song/003TLQVN4XZuPR.html
         * songid : 003TLQVN4XZuPR
         * title : 再见吧我最爱的你
         * author : 东方晴儿
         * lrc :
         * url : http://dl.stream.qqmusic.qq.com/M500003TLQVN4XZuPR.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=17D20C4813093E7DDD03505663E185B5364C6B940EA821BE2573ADC4D3CC418EA89E8310EF416229E3F747BFFE3E5325CB38FFAA075FEE5B&fromtag=46
         * pic : http://y.gtimg.cn/music/photo_new/T002R300x300M000003RZEXe1fG18Y.jpg
         */

        @SerializedName("type")
        private String type;
        @SerializedName("link")
        private String link;
        @SerializedName("songid")
        private String songid;
        @SerializedName("title")
        private String title;
        @SerializedName("author")
        private String author;
        @SerializedName("lrc")
        private String lrc;
        @SerializedName("url")
        private String url;
        @SerializedName("pic")
        private String pic;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getLrc() {
            return lrc;
        }

        public void setLrc(String lrc) {
            this.lrc = lrc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
