package com.yizisu.music.and.video.bean.migu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMiguBean {

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
         * totalPage : 8
         */

        @SerializedName("totalPage")
        private int totalPage;
        @SerializedName("list")
        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
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
             * artists : [{"name":"周杰伦","id":"112"}]
             * album : {"name":"叶惠美","id":"8592"}
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
                 * name : 叶惠美
                 * id : 8592
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
                 * name : 周杰伦
                 * id : 112
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
        }
    }
}
