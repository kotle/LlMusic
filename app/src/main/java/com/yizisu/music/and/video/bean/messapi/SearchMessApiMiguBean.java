package com.yizisu.music.and.video.bean.messapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMessApiMiguBean {

    /**
     * code : 200
     * msg : OK
     * timestamp : 0
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("timestamp")
    private int timestamp;
    @SerializedName("data")
    private DataBean data;

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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * correct : []
         * isFromCache : 0
         * tipStatus : 0
         * totalCount : 196
         * resultType : 2
         */

        @SerializedName("isFromCache")
        private String isFromCache;
        @SerializedName("tipStatus")
        private String tipStatus;
        @SerializedName("totalCount")
        private String totalCount;
        @SerializedName("resultType")
        private String resultType;
        @SerializedName("correct")
        private List<?> correct;
        @SerializedName("resultList")
        private List<List<ResultListBean>> resultList;

        public String getIsFromCache() {
            return isFromCache;
        }

        public void setIsFromCache(String isFromCache) {
            this.isFromCache = isFromCache;
        }

        public String getTipStatus() {
            return tipStatus;
        }

        public void setTipStatus(String tipStatus) {
            this.tipStatus = tipStatus;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public List<?> getCorrect() {
            return correct;
        }

        public void setCorrect(List<?> correct) {
            this.correct = correct;
        }

        public List<List<ResultListBean>> getResultList() {
            return resultList;
        }

        public void setResultList(List<List<ResultListBean>> resultList) {
            this.resultList = resultList;
        }

        public static class ResultListBean {
            /**
             * rateFormats : [{"size":"1922278","price":"200","format":"000019","formatType":"LQ","url":"ftp://218.200.160.122:21/public/product8th/product38/2020/01/0314/2009年06月26日博尔普斯/全曲试听/Mp3_64_22_16/60054701938.mp3","fileType":"mp3","resourceType":"3"},{"size":"3848193","price":"200","format":"020007","formatType":"PQ","url":"ftp://218.200.160.122:21/public/product5th/product35/2019/11/0822/2009年06月26日博尔普斯/标清高清/MP3_128_16_Stero/60054701938.mp3","fileType":"mp3","resourceType":"2"},{"size":"9614149","price":"200","format":"020010","formatType":"HQ","url":"ftp://218.200.160.122:21/public/product5th/product35/2019/11/0822/2009年06月26日博尔普斯/标清高清/MP3_320_16_Stero/60054701938.mp3","fileType":"mp3","resourceType":"2"},{"iosFormat":"011003","iosAccuracyLevel":"16bit","androidFormat":"011002","format":"011002","androidAccuracyLevel":"16bit","androidSize":"26215426","size":"26215426","price":"200","androidUrl":"ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/flac/搁浅-周杰伦.flac","iosSize":"26738823","formatType":"SQ","iosUrl":"ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/alac/搁浅-周杰伦.m4a","resourceType":"E"}]
             * albums : [{"name":"七里香","id":"7949","type":"1"}]
             * copyright : 1
             * contentId : 600902000006889306
             * isInSalesPeriod : 0
             * newRateFormats : [{"size":"1922278","price":"200","format":"000019","formatType":"LQ","url":"ftp://218.200.160.122:21/public/product8th/product38/2020/01/0314/2009年06月26日博尔普斯/全曲试听/Mp3_64_22_16/60054701938.mp3","fileType":"mp3","resourceType":"3"},{"size":"3848193","price":"200","format":"020007","formatType":"PQ","url":"ftp://218.200.160.122:21/public/product5th/product35/2019/11/0822/2009年06月26日博尔普斯/标清高清/MP3_128_16_Stero/60054701938.mp3","fileType":"mp3","resourceType":"2"},{"size":"9614149","price":"200","format":"020010","formatType":"HQ","url":"ftp://218.200.160.122:21/public/product5th/product35/2019/11/0822/2009年06月26日博尔普斯/标清高清/MP3_320_16_Stero/60054701938.mp3","fileType":"mp3","resourceType":"2"},{"iosFormat":"011003","iosAccuracyLevel":"16bit","androidFormat":"011002","format":"011002","androidAccuracyLevel":"16bit","androidSize":"26215426","size":"26215426","price":"200","androidUrl":"ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/flac/搁浅-周杰伦.flac","iosSize":"26738823","formatType":"SQ","iosUrl":"ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/alac/搁浅-周杰伦.m4a","resourceType":"E"}]
             * digitalColumnId :
             * songDescs :
             * scopeOfcopyright : 01
             * mgVideoParam4Adr :
             * tones : [{"copyrightId":"60054701938","price":"200","expireDate":"2022-11-30","id":"600902000006889304"}]
             * id : 2156
             * relatedSongs : [{"productId":"600902000006889305","copyrightId":"60054701938","resourceTypeName":"振铃","resourceType":"1"},{"productId":"600902000006889307","copyrightId":"60054701938","resourceTypeName":"随身听","resourceType":"3"},{"productId":"600902000006889306","copyrightId":"60054701938","resourceTypeName":"无损","resourceType":"E"},{"productId":"600902000006889304","copyrightId":"60054701938","resourceTypeName":"彩铃","resourceType":"0"}]
             * imgItems : [{"img":"https://d.musicapp.migu.cn/prod/file-service/file-down/b1899d500dda5db2da11df3efc89cba6/6f2dddebc74d0b27ae68c1fc526ff209/ed1cf6dd55b9cfa97195fd47db957023","imgSizeType":"03"},{"img":"https://d.musicapp.migu.cn/prod/file-service/file-down/b1899d500dda5db2da11df3efc89cba6/6f2dddebc74d0b27ae68c1fc526ff209/92798b8a85ee502dba10de53a1d435dd","imgSizeType":"02"},{"img":"https://d.musicapp.migu.cn/prod/file-service/file-down/b1899d500dda5db2da11df3efc89cba6/6f2dddebc74d0b27ae68c1fc526ff209/fbb8f4c7df696ab1d5b0f9dd5832c896","imgSizeType":"01"}]
             * chargeAuditions : 0
             * lyricUrl : https://d.musicapp.migu.cn/prod/file-service/file-down/4eedd78464c21ce789dea6928415b323/3b6661af21a09ad8482d2aa773080b60/39e63b70527266b47b4ce2b391b54b2d
             * dalbumId :
             * singers : [{"name":"周杰伦","id":"112"}]
             * mrcurl : https://d.musicapp.migu.cn/prod/file-service/file-down/4eedd78464c21ce789dea6928415b323/4d6807e8dbae6996865f131ba0b5711a/97d755451e8a462cf9da28e25dbe44d7
             * songAliasName :
             * invalidateDate : 2022-11-30
             * translateName :
             * vipType :
             * isInDAlbum : 0
             * trcUrl :
             * toneControl : 1111
             * songType :
             * isInSideDalbum : 0
             * highlightStr : ["周杰伦"]
             * tags : ["流行","爱情","寂寞","思念","国语","伤感","KTV金曲","黄昏","00年代","抖音","抒情流行","原创单曲"]
             * actionUrlParams :
             * copyrightId : 60054701938
             * mgVideoParam4IOS :
             * name : 搁浅
             * resourceType : 2
             */

            @SerializedName("copyright")
            private String copyright;
            @SerializedName("contentId")
            private String contentId;
            @SerializedName("isInSalesPeriod")
            private String isInSalesPeriod;
            @SerializedName("digitalColumnId")
            private String digitalColumnId;
            @SerializedName("songDescs")
            private String songDescs;
            @SerializedName("scopeOfcopyright")
            private String scopeOfcopyright;
            @SerializedName("mgVideoParam4Adr")
            private String mgVideoParam4Adr;
            @SerializedName("id")
            private String id;
            @SerializedName("chargeAuditions")
            private String chargeAuditions;
            @SerializedName("lyricUrl")
            private String lyricUrl;
            @SerializedName("dalbumId")
            private String dalbumId;
            @SerializedName("mrcurl")
            private String mrcurl;
            @SerializedName("songAliasName")
            private String songAliasName;
            @SerializedName("invalidateDate")
            private String invalidateDate;
            @SerializedName("translateName")
            private String translateName;
            @SerializedName("vipType")
            private String vipType;
            @SerializedName("isInDAlbum")
            private String isInDAlbum;
            @SerializedName("trcUrl")
            private String trcUrl;
            @SerializedName("toneControl")
            private String toneControl;
            @SerializedName("songType")
            private String songType;
            @SerializedName("isInSideDalbum")
            private String isInSideDalbum;
            @SerializedName("actionUrlParams")
            private String actionUrlParams;
            @SerializedName("copyrightId")
            private String copyrightId;
            @SerializedName("mgVideoParam4IOS")
            private String mgVideoParam4IOS;
            @SerializedName("name")
            private String name;
            @SerializedName("resourceType")
            private String resourceType;
            @SerializedName("rateFormats")
            private List<RateFormatsBean> rateFormats;
            @SerializedName("albums")
            private List<AlbumsBean> albums;
            @SerializedName("newRateFormats")
            private List<NewRateFormatsBean> newRateFormats;
            @SerializedName("tones")
            private List<TonesBean> tones;
            @SerializedName("relatedSongs")
            private List<RelatedSongsBean> relatedSongs;
            @SerializedName("imgItems")
            private List<ImgItemsBean> imgItems;
            @SerializedName("singers")
            private List<SingersBean> singers;
            @SerializedName("highlightStr")
            private List<String> highlightStr;
            @SerializedName("tags")
            private List<String> tags;

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getContentId() {
                return contentId;
            }

            public void setContentId(String contentId) {
                this.contentId = contentId;
            }

            public String getIsInSalesPeriod() {
                return isInSalesPeriod;
            }

            public void setIsInSalesPeriod(String isInSalesPeriod) {
                this.isInSalesPeriod = isInSalesPeriod;
            }

            public String getDigitalColumnId() {
                return digitalColumnId;
            }

            public void setDigitalColumnId(String digitalColumnId) {
                this.digitalColumnId = digitalColumnId;
            }

            public String getSongDescs() {
                return songDescs;
            }

            public void setSongDescs(String songDescs) {
                this.songDescs = songDescs;
            }

            public String getScopeOfcopyright() {
                return scopeOfcopyright;
            }

            public void setScopeOfcopyright(String scopeOfcopyright) {
                this.scopeOfcopyright = scopeOfcopyright;
            }

            public String getMgVideoParam4Adr() {
                return mgVideoParam4Adr;
            }

            public void setMgVideoParam4Adr(String mgVideoParam4Adr) {
                this.mgVideoParam4Adr = mgVideoParam4Adr;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getChargeAuditions() {
                return chargeAuditions;
            }

            public void setChargeAuditions(String chargeAuditions) {
                this.chargeAuditions = chargeAuditions;
            }

            public String getLyricUrl() {
                return lyricUrl;
            }

            public void setLyricUrl(String lyricUrl) {
                this.lyricUrl = lyricUrl;
            }

            public String getDalbumId() {
                return dalbumId;
            }

            public void setDalbumId(String dalbumId) {
                this.dalbumId = dalbumId;
            }

            public String getMrcurl() {
                return mrcurl;
            }

            public void setMrcurl(String mrcurl) {
                this.mrcurl = mrcurl;
            }

            public String getSongAliasName() {
                return songAliasName;
            }

            public void setSongAliasName(String songAliasName) {
                this.songAliasName = songAliasName;
            }

            public String getInvalidateDate() {
                return invalidateDate;
            }

            public void setInvalidateDate(String invalidateDate) {
                this.invalidateDate = invalidateDate;
            }

            public String getTranslateName() {
                return translateName;
            }

            public void setTranslateName(String translateName) {
                this.translateName = translateName;
            }

            public String getVipType() {
                return vipType;
            }

            public void setVipType(String vipType) {
                this.vipType = vipType;
            }

            public String getIsInDAlbum() {
                return isInDAlbum;
            }

            public void setIsInDAlbum(String isInDAlbum) {
                this.isInDAlbum = isInDAlbum;
            }

            public String getTrcUrl() {
                return trcUrl;
            }

            public void setTrcUrl(String trcUrl) {
                this.trcUrl = trcUrl;
            }

            public String getToneControl() {
                return toneControl;
            }

            public void setToneControl(String toneControl) {
                this.toneControl = toneControl;
            }

            public String getSongType() {
                return songType;
            }

            public void setSongType(String songType) {
                this.songType = songType;
            }

            public String getIsInSideDalbum() {
                return isInSideDalbum;
            }

            public void setIsInSideDalbum(String isInSideDalbum) {
                this.isInSideDalbum = isInSideDalbum;
            }

            public String getActionUrlParams() {
                return actionUrlParams;
            }

            public void setActionUrlParams(String actionUrlParams) {
                this.actionUrlParams = actionUrlParams;
            }

            public String getCopyrightId() {
                return copyrightId;
            }

            public void setCopyrightId(String copyrightId) {
                this.copyrightId = copyrightId;
            }

            public String getMgVideoParam4IOS() {
                return mgVideoParam4IOS;
            }

            public void setMgVideoParam4IOS(String mgVideoParam4IOS) {
                this.mgVideoParam4IOS = mgVideoParam4IOS;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getResourceType() {
                return resourceType;
            }

            public void setResourceType(String resourceType) {
                this.resourceType = resourceType;
            }

            public List<RateFormatsBean> getRateFormats() {
                return rateFormats;
            }

            public void setRateFormats(List<RateFormatsBean> rateFormats) {
                this.rateFormats = rateFormats;
            }

            public List<AlbumsBean> getAlbums() {
                return albums;
            }

            public void setAlbums(List<AlbumsBean> albums) {
                this.albums = albums;
            }

            public List<NewRateFormatsBean> getNewRateFormats() {
                return newRateFormats;
            }

            public void setNewRateFormats(List<NewRateFormatsBean> newRateFormats) {
                this.newRateFormats = newRateFormats;
            }

            public List<TonesBean> getTones() {
                return tones;
            }

            public void setTones(List<TonesBean> tones) {
                this.tones = tones;
            }

            public List<RelatedSongsBean> getRelatedSongs() {
                return relatedSongs;
            }

            public void setRelatedSongs(List<RelatedSongsBean> relatedSongs) {
                this.relatedSongs = relatedSongs;
            }

            public List<ImgItemsBean> getImgItems() {
                return imgItems;
            }

            public void setImgItems(List<ImgItemsBean> imgItems) {
                this.imgItems = imgItems;
            }

            public List<SingersBean> getSingers() {
                return singers;
            }

            public void setSingers(List<SingersBean> singers) {
                this.singers = singers;
            }

            public List<String> getHighlightStr() {
                return highlightStr;
            }

            public void setHighlightStr(List<String> highlightStr) {
                this.highlightStr = highlightStr;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public static class RateFormatsBean {
                /**
                 * size : 1922278
                 * price : 200
                 * format : 000019
                 * formatType : LQ
                 * url : ftp://218.200.160.122:21/public/product8th/product38/2020/01/0314/2009年06月26日博尔普斯/全曲试听/Mp3_64_22_16/60054701938.mp3
                 * fileType : mp3
                 * resourceType : 3
                 * iosFormat : 011003
                 * iosAccuracyLevel : 16bit
                 * androidFormat : 011002
                 * androidAccuracyLevel : 16bit
                 * androidSize : 26215426
                 * androidUrl : ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/flac/搁浅-周杰伦.flac
                 * iosSize : 26738823
                 * iosUrl : ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/alac/搁浅-周杰伦.m4a
                 */

                @SerializedName("size")
                private String size;
                @SerializedName("price")
                private String price;
                @SerializedName("format")
                private String format;
                @SerializedName("formatType")
                private String formatType;
                @SerializedName("url")
                private String url;
                @SerializedName("fileType")
                private String fileType;
                @SerializedName("resourceType")
                private String resourceType;
                @SerializedName("iosFormat")
                private String iosFormat;
                @SerializedName("iosAccuracyLevel")
                private String iosAccuracyLevel;
                @SerializedName("androidFormat")
                private String androidFormat;
                @SerializedName("androidAccuracyLevel")
                private String androidAccuracyLevel;
                @SerializedName("androidSize")
                private String androidSize;
                @SerializedName("androidUrl")
                private String androidUrl;
                @SerializedName("iosSize")
                private String iosSize;
                @SerializedName("iosUrl")
                private String iosUrl;

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFormat() {
                    return format;
                }

                public void setFormat(String format) {
                    this.format = format;
                }

                public String getFormatType() {
                    return formatType;
                }

                public void setFormatType(String formatType) {
                    this.formatType = formatType;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getFileType() {
                    return fileType;
                }

                public void setFileType(String fileType) {
                    this.fileType = fileType;
                }

                public String getResourceType() {
                    return resourceType;
                }

                public void setResourceType(String resourceType) {
                    this.resourceType = resourceType;
                }

                public String getIosFormat() {
                    return iosFormat;
                }

                public void setIosFormat(String iosFormat) {
                    this.iosFormat = iosFormat;
                }

                public String getIosAccuracyLevel() {
                    return iosAccuracyLevel;
                }

                public void setIosAccuracyLevel(String iosAccuracyLevel) {
                    this.iosAccuracyLevel = iosAccuracyLevel;
                }

                public String getAndroidFormat() {
                    return androidFormat;
                }

                public void setAndroidFormat(String androidFormat) {
                    this.androidFormat = androidFormat;
                }

                public String getAndroidAccuracyLevel() {
                    return androidAccuracyLevel;
                }

                public void setAndroidAccuracyLevel(String androidAccuracyLevel) {
                    this.androidAccuracyLevel = androidAccuracyLevel;
                }

                public String getAndroidSize() {
                    return androidSize;
                }

                public void setAndroidSize(String androidSize) {
                    this.androidSize = androidSize;
                }

                public String getAndroidUrl() {
                    return androidUrl;
                }

                public void setAndroidUrl(String androidUrl) {
                    this.androidUrl = androidUrl;
                }

                public String getIosSize() {
                    return iosSize;
                }

                public void setIosSize(String iosSize) {
                    this.iosSize = iosSize;
                }

                public String getIosUrl() {
                    return iosUrl;
                }

                public void setIosUrl(String iosUrl) {
                    this.iosUrl = iosUrl;
                }
            }

            public static class AlbumsBean {
                /**
                 * name : 七里香
                 * id : 7949
                 * type : 1
                 */

                @SerializedName("name")
                private String name;
                @SerializedName("id")
                private String id;
                @SerializedName("type")
                private String type;

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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public static class NewRateFormatsBean {
                /**
                 * size : 1922278
                 * price : 200
                 * format : 000019
                 * formatType : LQ
                 * url : ftp://218.200.160.122:21/public/product8th/product38/2020/01/0314/2009年06月26日博尔普斯/全曲试听/Mp3_64_22_16/60054701938.mp3
                 * fileType : mp3
                 * resourceType : 3
                 * iosFormat : 011003
                 * iosAccuracyLevel : 16bit
                 * androidFormat : 011002
                 * androidAccuracyLevel : 16bit
                 * androidSize : 26215426
                 * androidUrl : ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/flac/搁浅-周杰伦.flac
                 * iosSize : 26738823
                 * iosUrl : ftp://218.200.160.122:21/public/ringmaker01/n17/2017/07/无损/2009年06月26日博尔普斯/alac/搁浅-周杰伦.m4a
                 */

                @SerializedName("size")
                private String size;
                @SerializedName("price")
                private String price;
                @SerializedName("format")
                private String format;
                @SerializedName("formatType")
                private String formatType;
                @SerializedName("url")
                private String url;
                @SerializedName("fileType")
                private String fileType;
                @SerializedName("resourceType")
                private String resourceType;
                @SerializedName("iosFormat")
                private String iosFormat;
                @SerializedName("iosAccuracyLevel")
                private String iosAccuracyLevel;
                @SerializedName("androidFormat")
                private String androidFormat;
                @SerializedName("androidAccuracyLevel")
                private String androidAccuracyLevel;
                @SerializedName("androidSize")
                private String androidSize;
                @SerializedName("androidUrl")
                private String androidUrl;
                @SerializedName("iosSize")
                private String iosSize;
                @SerializedName("iosUrl")
                private String iosUrl;

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFormat() {
                    return format;
                }

                public void setFormat(String format) {
                    this.format = format;
                }

                public String getFormatType() {
                    return formatType;
                }

                public void setFormatType(String formatType) {
                    this.formatType = formatType;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getFileType() {
                    return fileType;
                }

                public void setFileType(String fileType) {
                    this.fileType = fileType;
                }

                public String getResourceType() {
                    return resourceType;
                }

                public void setResourceType(String resourceType) {
                    this.resourceType = resourceType;
                }

                public String getIosFormat() {
                    return iosFormat;
                }

                public void setIosFormat(String iosFormat) {
                    this.iosFormat = iosFormat;
                }

                public String getIosAccuracyLevel() {
                    return iosAccuracyLevel;
                }

                public void setIosAccuracyLevel(String iosAccuracyLevel) {
                    this.iosAccuracyLevel = iosAccuracyLevel;
                }

                public String getAndroidFormat() {
                    return androidFormat;
                }

                public void setAndroidFormat(String androidFormat) {
                    this.androidFormat = androidFormat;
                }

                public String getAndroidAccuracyLevel() {
                    return androidAccuracyLevel;
                }

                public void setAndroidAccuracyLevel(String androidAccuracyLevel) {
                    this.androidAccuracyLevel = androidAccuracyLevel;
                }

                public String getAndroidSize() {
                    return androidSize;
                }

                public void setAndroidSize(String androidSize) {
                    this.androidSize = androidSize;
                }

                public String getAndroidUrl() {
                    return androidUrl;
                }

                public void setAndroidUrl(String androidUrl) {
                    this.androidUrl = androidUrl;
                }

                public String getIosSize() {
                    return iosSize;
                }

                public void setIosSize(String iosSize) {
                    this.iosSize = iosSize;
                }

                public String getIosUrl() {
                    return iosUrl;
                }

                public void setIosUrl(String iosUrl) {
                    this.iosUrl = iosUrl;
                }
            }

            public static class TonesBean {
                /**
                 * copyrightId : 60054701938
                 * price : 200
                 * expireDate : 2022-11-30
                 * id : 600902000006889304
                 */

                @SerializedName("copyrightId")
                private String copyrightId;
                @SerializedName("price")
                private String price;
                @SerializedName("expireDate")
                private String expireDate;
                @SerializedName("id")
                private String id;

                public String getCopyrightId() {
                    return copyrightId;
                }

                public void setCopyrightId(String copyrightId) {
                    this.copyrightId = copyrightId;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getExpireDate() {
                    return expireDate;
                }

                public void setExpireDate(String expireDate) {
                    this.expireDate = expireDate;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }

            public static class RelatedSongsBean {
                /**
                 * productId : 600902000006889305
                 * copyrightId : 60054701938
                 * resourceTypeName : 振铃
                 * resourceType : 1
                 */

                @SerializedName("productId")
                private String productId;
                @SerializedName("copyrightId")
                private String copyrightId;
                @SerializedName("resourceTypeName")
                private String resourceTypeName;
                @SerializedName("resourceType")
                private String resourceType;

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getCopyrightId() {
                    return copyrightId;
                }

                public void setCopyrightId(String copyrightId) {
                    this.copyrightId = copyrightId;
                }

                public String getResourceTypeName() {
                    return resourceTypeName;
                }

                public void setResourceTypeName(String resourceTypeName) {
                    this.resourceTypeName = resourceTypeName;
                }

                public String getResourceType() {
                    return resourceType;
                }

                public void setResourceType(String resourceType) {
                    this.resourceType = resourceType;
                }
            }

            public static class ImgItemsBean {
                /**
                 * img : https://d.musicapp.migu.cn/prod/file-service/file-down/b1899d500dda5db2da11df3efc89cba6/6f2dddebc74d0b27ae68c1fc526ff209/ed1cf6dd55b9cfa97195fd47db957023
                 * imgSizeType : 03
                 */

                @SerializedName("img")
                private String img;
                @SerializedName("imgSizeType")
                private String imgSizeType;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getImgSizeType() {
                    return imgSizeType;
                }

                public void setImgSizeType(String imgSizeType) {
                    this.imgSizeType = imgSizeType;
                }
            }

            public static class SingersBean {
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
