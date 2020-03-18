package com.yizisu.music.and.video.bean.migu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumMiguBean {

    /**
     * result : 100
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
         * name : 周杰伦的床边故事
         * id : 1003767159
         * songList : [{"name":"床边故事","id":"1003767183","cid":"6005479Z00C","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"说走就走","id":"1004202159","cid":"60054704029","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"一点点","id":"1004202171","cid":"60054704031","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"前世情人","id":"1003767184","cid":"6005479Z00D","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"英雄","id":"1004202154","cid":"60054704032","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"不该(电视剧《幻城》主题曲)","id":"1004202181","cid":"60054704025","artists":[{"id":"112","name":"周杰伦"},{"id":"1002221569","name":"张惠妹"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"土耳其冰淇淋","id":"1004202158","cid":"60054704030","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"告白气球","id":"1004202180","cid":"60054704037","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"Now You See Me(电影《惊天魔盗团2》主题曲)","id":"1004202173","cid":"60054704026","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}},{"name":"爱情废柴","id":"1004202172","cid":"60054704035","artists":[{"id":"112","name":"周杰伦"}],"album":{"name":"周杰伦的床边故事","id":"1003767159"}}]
         * artists : [{"id":"112","name":"周杰伦"}]
         * company : 杰威尔音乐有限公司
         * publishTime : 2016-06-24
         * picUrl : //cdnmusic.migu.cn/picture/2019/1031/0254/AMd6c2d9697d2a4f5f96508c8a7ec8b1a8.jpg
         * desc :
         */

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("company")
        private String company;
        @SerializedName("publishTime")
        private String publishTime;
        @SerializedName("picUrl")
        private String picUrl;
        @SerializedName("desc")
        private String desc;
        @SerializedName("songList")
        private List<SongListBean> songList;
        @SerializedName("artists")
        private List<ArtistsBeanX> artists;

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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<SongListBean> getSongList() {
            return songList;
        }

        public void setSongList(List<SongListBean> songList) {
            this.songList = songList;
        }

        public List<ArtistsBeanX> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBeanX> artists) {
            this.artists = artists;
        }

        public static class SongListBean {
            /**
             * name : 床边故事
             * id : 1003767183
             * cid : 6005479Z00C
             * artists : [{"id":"112","name":"周杰伦"}]
             * album : {"name":"周杰伦的床边故事","id":"1003767159"}
             */

            @SerializedName("name")
            private String name;
            @SerializedName("id")
            private String id;
            @SerializedName("cid")
            private String cid;
            @SerializedName("album")
            private AlbumBean album;
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

            public List<ArtistsBean> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBean> artists) {
                this.artists = artists;
            }

            public static class AlbumBean {
                /**
                 * name : 周杰伦的床边故事
                 * id : 1003767159
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

        public static class ArtistsBeanX {
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
