package com.yizisu.music.and.video.bean.messapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchNodeJsMiguBean {

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
         * total : 222
         */

        @SerializedName("total")
        private int total;
        @SerializedName("list")
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * name : 晴天
             * id : 3790007
             * cid : 60054701923
             * mvId :
             * url : http://tyst.migu.cn/public%2Fproduct5th%2Fproduct34%2F2019%2F07%2F1822%2F2009%E5%B9%B406%E6%9C%8826%E6%97%A5%E5%8D%9A%E5%B0%94%E6%99%AE%E6%96%AF%2F%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC%2FMp3_64_22_16%2F60054701923.mp3
             * album : {"picUrl":"http://mcontent.10086.cn/newlv2/new/album/20191031/8592/s_cNhRDwG7QFLdfm6e.jpg","name":"叶惠美","id":"8592"}
             * artists : [{"id":"112","name":"周杰伦"}]
             */

            @SerializedName("name")
            private String name;
            @SerializedName("id")
            private String id;
            @SerializedName("cid")
            private String cid;
            @SerializedName("mvId")
            private String mvId;
            @SerializedName("url")
            private String url;
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

            public String getMvId() {
                return mvId;
            }

            public void setMvId(String mvId) {
                this.mvId = mvId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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
                 * picUrl : http://mcontent.10086.cn/newlv2/new/album/20191031/8592/s_cNhRDwG7QFLdfm6e.jpg
                 * name : 叶惠美
                 * id : 8592
                 */

                @SerializedName("picUrl")
                private String picUrl;
                @SerializedName("name")
                private String name;
                @SerializedName("id")
                private String id;

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

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
}
