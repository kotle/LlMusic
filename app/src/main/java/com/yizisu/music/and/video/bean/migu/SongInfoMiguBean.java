package com.yizisu.music.and.video.bean.migu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongInfoMiguBean {

    /**
     * result : 100
     * data : {"name":"稻香","id":"697066","cid":"60054702010","artists":[{"id":"112","name":"周杰伦 "}],"album":{"name":"魔杰座","id":"25578"},"pic":"//cdnmusic.migu.cn/picture/2019/1031/0116/AS3f169c1d25854897bed1453a6aead54c.jpg","bgPic":"//cdnmusic.migu.cn/picture/2019/1031/0116/AL3f169c1d25854897bed1453a6aead54c.jpg","128k":"http://tyst.migu.cn/public/product5th/product35/2019/10/1420/2009年06月26日博尔普斯/标清高清/MP3_128_16_Stero/60054702010.mp3","320k":"http://tyst.migu.cn/public/product5th/product35/2019/10/1420/2009年06月26日博尔普斯/标清高清/MP3_320_16_Stero/60054702010.mp3","flac":"http://tyst.migu.cn/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/flac/稻香-周杰伦.flac"}
     */

    @SerializedName("result")
    private int result;
    @SerializedName("data")
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 稻香
         * id : 697066
         * cid : 60054702010
         * artists : [{"id":"112","name":"周杰伦 "}]
         * album : {"name":"魔杰座","id":"25578"}
         * pic : //cdnmusic.migu.cn/picture/2019/1031/0116/AS3f169c1d25854897bed1453a6aead54c.jpg
         * bgPic : //cdnmusic.migu.cn/picture/2019/1031/0116/AL3f169c1d25854897bed1453a6aead54c.jpg
         * 128k : http://tyst.migu.cn/public/product5th/product35/2019/10/1420/2009年06月26日博尔普斯/标清高清/MP3_128_16_Stero/60054702010.mp3
         * 320k : http://tyst.migu.cn/public/product5th/product35/2019/10/1420/2009年06月26日博尔普斯/标清高清/MP3_320_16_Stero/60054702010.mp3
         * flac : http://tyst.migu.cn/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/flac/稻香-周杰伦.flac
         */

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("cid")
        private String cid;
        @SerializedName("album")
        private AlbumBean album;
        @SerializedName("pic")
        private String pic;
        @SerializedName("bgPic")
        private String bgPic;
        @SerializedName("128k")
        private String $128k;
        @SerializedName("320k")
        private String $320k;
        @SerializedName("flac")
        private String flac;
        @SerializedName("artists")
        private List<ArtistsBean> artists;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getBgPic() {
            return bgPic;
        }

        public void setBgPic(String bgPic) {
            this.bgPic = bgPic;
        }

        public String get$128k() {
            return $128k;
        }

        public void set$128k(String $128k) {
            this.$128k = $128k;
        }

        public String get$320k() {
            return $320k;
        }

        public void set$320k(String $320k) {
            this.$320k = $320k;
        }

        public String getFlac() {
            return flac;
        }

        public void setFlac(String flac) {
            this.flac = flac;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public static class AlbumBean {
            /**
             * name : 魔杰座
             * id : 25578
             */

            @SerializedName("name")
            private String name;
            @SerializedName("id")
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class ArtistsBean {
            /**
             * id : 112
             * name : 周杰伦
             */

            @SerializedName("id")
            private String id;
            @SerializedName("name")
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
