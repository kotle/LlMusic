package com.yizisu.music.and.video.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestVideoBean {

    @SerializedName("trailers")
    private List<TrailersBean> trailers;

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public static class TrailersBean {
        /**
         * id : 75289
         * movieName : 《速度与激情:特别行动》曝全新中文预告
         * coverImg : http://img5.mtime.cn/mg/2019/06/29/002009.16684021_120X90X4.jpg
         * movieId : 254114
         * url : http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4
         * videoTitle : 速度与激情：特别行动 中文预告
         * videoLength : 146
         * rating : -1
         * type : ["动作","冒险"]
         * summary :
         */

        @SerializedName("id")
        private int id;
        @SerializedName("movieName")
        private String movieName;
        @SerializedName("coverImg")
        private String coverImg;
        @SerializedName("movieId")
        private int movieId;
        @SerializedName("url")
        private String url;
        @SerializedName("hightUrl")
        private String hightUrl;
        @SerializedName("videoTitle")
        private String videoTitle;
        @SerializedName("videoLength")
        private int videoLength;
        @SerializedName("rating")
        private double rating;
        @SerializedName("summary")
        private String summary;
        @SerializedName("type")
        private List<String> type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
