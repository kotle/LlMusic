package com.yizisu.music.and.video.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LrcBean {

    /**
     * count : 16
     * code : 0
     * result : [{"aid":1563419,"artist_id":9208,"sid":1668536,"lrc":"http://s.gecimi.com/lrc/166/16685/1668536.lrc","song":"海阔天空"},{"aid":1567586,"artist_id":9208,"sid":1673997,"lrc":"http://s.gecimi.com/lrc/167/16739/1673997.lrc","song":"海阔天空"},{"aid":1567586,"artist_id":9208,"sid":1673998,"lrc":"http://s.gecimi.com/lrc/167/16739/1673998.lrc","song":"海阔天空"},{"aid":1571906,"artist_id":9208,"sid":1679605,"lrc":"http://s.gecimi.com/lrc/167/16796/1679605.lrc","song":"海阔天空"},{"aid":1573814,"artist_id":9208,"sid":1681961,"lrc":"http://s.gecimi.com/lrc/168/16819/1681961.lrc","song":"海阔天空"},{"aid":1656038,"artist_id":9208,"sid":1790768,"lrc":"http://s.gecimi.com/lrc/179/17907/1790768.lrc","song":"海阔天空"},{"aid":1718741,"artist_id":9208,"sid":1875769,"lrc":"http://s.gecimi.com/lrc/187/18757/1875769.lrc","song":"海阔天空"},{"aid":2003267,"artist_id":9208,"sid":2264296,"lrc":"http://s.gecimi.com/lrc/226/22642/2264296.lrc","song":"海阔天空"},{"aid":2020610,"artist_id":9208,"sid":2288967,"lrc":"http://s.gecimi.com/lrc/228/22889/2288967.lrc","song":"海阔天空"},{"aid":2051678,"artist_id":9208,"sid":2332322,"lrc":"http://s.gecimi.com/lrc/233/23323/2332322.lrc","song":"海阔天空"},{"aid":2412704,"artist_id":9208,"sid":2837689,"lrc":"http://s.gecimi.com/lrc/283/28376/2837689.lrc","song":"海阔天空"},{"aid":2607041,"artist_id":9208,"sid":3111659,"lrc":"http://s.gecimi.com/lrc/311/31116/3111659.lrc","song":"海阔天空"},{"aid":2647055,"artist_id":9208,"sid":3166350,"lrc":"http://s.gecimi.com/lrc/316/31663/3166350.lrc","song":"海阔天空"},{"aid":2657468,"artist_id":9208,"sid":3180339,"lrc":"http://s.gecimi.com/lrc/318/31803/3180339.lrc","song":"海阔天空"},{"aid":3093833,"artist_id":9208,"sid":3774083,"lrc":"http://s.gecimi.com/lrc/377/37740/3774083.lrc","song":"海阔天空"},{"aid":3161846,"artist_id":9208,"sid":3861244,"lrc":"http://s.gecimi.com/lrc/386/38612/3861244.lrc","song":"海阔天空"}]
     */

    @SerializedName("count")
    private int count;
    @SerializedName("code")
    private int code;
    @SerializedName("result")
    private List<ResultBean> result;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * aid : 1563419
         * artist_id : 9208
         * sid : 1668536
         * lrc : http://s.gecimi.com/lrc/166/16685/1668536.lrc
         * song : 海阔天空
         */

        @SerializedName("aid")
        private int aid;
        @SerializedName("artist_id")
        private int artistId;
        @SerializedName("sid")
        private int sid;
        @SerializedName("lrc")
        private String lrc;
        @SerializedName("song")
        private String song;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getLrc() {
            return lrc;
        }

        public void setLrc(String lrc) {
            this.lrc = lrc;
        }

        public String getSong() {
            return song;
        }

        public void setSong(String song) {
            this.song = song;
        }
    }
}
