package com.yizisu.music.and.video.bean.kugou;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchKugouBean {

    /**
     * status : 1
     * error :
     * errcode : 0
     */

    @SerializedName("status")
    private int status;
    @SerializedName("error")
    private String error;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("errcode")
    private int errcode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public static class DataBean {
        /**
         * tab :
         * correctiontype : 0
         * timestamp : 1584416656
         * allowerr : 0
         * total : 345
         * istag : 0
         * istagresult : 0
         * forcecorrection : 0
         * correctiontip :
         */

        @SerializedName("tab")
        private String tab;
        @SerializedName("correctiontype")
        private int correctiontype;
        @SerializedName("timestamp")
        private int timestamp;
        @SerializedName("allowerr")
        private int allowerr;
        @SerializedName("total")
        private int total;
        @SerializedName("istag")
        private int istag;
        @SerializedName("istagresult")
        private int istagresult;
        @SerializedName("forcecorrection")
        private int forcecorrection;
        @SerializedName("correctiontip")
        private String correctiontip;
        @SerializedName("aggregation")
        private List<AggregationBean> aggregation;
        @SerializedName("info")
        private List<InfoBean> info;

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public int getCorrectiontype() {
            return correctiontype;
        }

        public void setCorrectiontype(int correctiontype) {
            this.correctiontype = correctiontype;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getAllowerr() {
            return allowerr;
        }

        public void setAllowerr(int allowerr) {
            this.allowerr = allowerr;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getIstag() {
            return istag;
        }

        public void setIstag(int istag) {
            this.istag = istag;
        }

        public int getIstagresult() {
            return istagresult;
        }

        public void setIstagresult(int istagresult) {
            this.istagresult = istagresult;
        }

        public int getForcecorrection() {
            return forcecorrection;
        }

        public void setForcecorrection(int forcecorrection) {
            this.forcecorrection = forcecorrection;
        }

        public String getCorrectiontip() {
            return correctiontip;
        }

        public void setCorrectiontip(String correctiontip) {
            this.correctiontip = correctiontip;
        }

        public List<AggregationBean> getAggregation() {
            return aggregation;
        }

        public void setAggregation(List<AggregationBean> aggregation) {
            this.aggregation = aggregation;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class AggregationBean {
            /**
             * key : DJ
             * count : 0
             */

            @SerializedName("key")
            private String key;
            @SerializedName("count")
            private int count;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class InfoBean {
            /**
             * othername_original :
             * pay_type_320 : 3
             * m4afilesize : 1313663
             * price_sq : 200
             * isoriginal : 0
             * filesize : 5120879
             * source :
             * bitrate : 128
             * topic :
             * price : 200
             * Accompany : 1
             * old_cpy : 0
             * songname_original : 海阔天空
             * singername : BEYOND
             * pay_type : 3
             * sourceid : 0
             * topic_url :
             * fail_process_320 : 4
             * pkg_price : 1
             * feetype : 0
             * filename : BEYOND - 海阔天空
             * price_320 : 200
             * songname : 海阔天空
             * hash : 8e496d133287434a933dcb63a5de8982
             * mvhash : 3e33ca79e6966281c5b893c25064d94d
             * rp_type : audio
             * privilege : 8
             * album_audio_id : 32155307
             * rp_publish : 1
             * album_id : 973001
             * ownercount : 15901
             * fold_type : 0
             * audio_id : 3261014
             * pkg_price_sq : 1
             * 320filesize : 12802133
             * isnew : 0
             * duration : 320
             * pkg_price_320 : 1
             * srctype : 1
             * fail_process_sq : 4
             * sqfilesize : 30565161
             * fail_process : 4
             * 320hash : 486b2da49e6fa8415c70ffa2de8b89ce
             * extname : mp3
             * sqhash : 996ae36c7b77d63ab42722d943072789
             * pay_type_sq : 3
             * 320privilege : 10
             * sqprivilege : 10
             * album_name : 乐与怒
             * othername :
             */

            @SerializedName("othername_original")
            private String othernameOriginal;
            @SerializedName("pay_type_320")
            private int payType320;
            @SerializedName("m4afilesize")
            private int m4afilesize;
            @SerializedName("price_sq")
            private int priceSq;
            @SerializedName("isoriginal")
            private int isoriginal;
            @SerializedName("filesize")
            private int filesize;
            @SerializedName("source")
            private String source;
            @SerializedName("bitrate")
            private int bitrate;
            @SerializedName("topic")
            private String topic;
            @SerializedName("trans_param")
            private TransParamBean transParam;
            @SerializedName("price")
            private int price;
            @SerializedName("Accompany")
            private int Accompany;
            @SerializedName("old_cpy")
            private int oldCpy;
            @SerializedName("songname_original")
            private String songnameOriginal;
            @SerializedName("singername")
            private String singername;
            @SerializedName("pay_type")
            private int payType;
            @SerializedName("sourceid")
            private int sourceid;
            @SerializedName("topic_url")
            private String topicUrl;
            @SerializedName("fail_process_320")
            private int failProcess320;
            @SerializedName("pkg_price")
            private int pkgPrice;
            @SerializedName("feetype")
            private int feetype;
            @SerializedName("filename")
            private String filename;
            @SerializedName("price_320")
            private int price320;
            @SerializedName("songname")
            private String songname;
            @SerializedName("hash")
            private String hash;
            @SerializedName("mvhash")
            private String mvhash;
            @SerializedName("rp_type")
            private String rpType;
            @SerializedName("privilege")
            private int privilege;
            @SerializedName("album_audio_id")
            private int albumAudioId;
            @SerializedName("rp_publish")
            private int rpPublish;
            @SerializedName("album_id")
            private String albumId;
            @SerializedName("ownercount")
            private int ownercount;
            @SerializedName("fold_type")
            private int foldType;
            @SerializedName("audio_id")
            private int audioId;
            @SerializedName("pkg_price_sq")
            private int pkgPriceSq;
            @SerializedName("320filesize")
            private int $320filesize;
            @SerializedName("isnew")
            private int isnew;
            @SerializedName("duration")
            private int duration;
            @SerializedName("pkg_price_320")
            private int pkgPrice320;
            @SerializedName("srctype")
            private int srctype;
            @SerializedName("fail_process_sq")
            private int failProcessSq;
            @SerializedName("sqfilesize")
            private int sqfilesize;
            @SerializedName("fail_process")
            private int failProcess;
            @SerializedName("320hash")
            private String $320hash;
            @SerializedName("extname")
            private String extname;
            @SerializedName("sqhash")
            private String sqhash;
            @SerializedName("pay_type_sq")
            private int payTypeSq;
            @SerializedName("320privilege")
            private int $320privilege;
            @SerializedName("sqprivilege")
            private int sqprivilege;
            @SerializedName("album_name")
            private String albumName;
            @SerializedName("othername")
            private String othername;
            @SerializedName("group")
            private List<GroupBean> group;

            public String getOthernameOriginal() {
                return othernameOriginal;
            }

            public void setOthernameOriginal(String othernameOriginal) {
                this.othernameOriginal = othernameOriginal;
            }

            public int getPayType320() {
                return payType320;
            }

            public void setPayType320(int payType320) {
                this.payType320 = payType320;
            }

            public int getM4afilesize() {
                return m4afilesize;
            }

            public void setM4afilesize(int m4afilesize) {
                this.m4afilesize = m4afilesize;
            }

            public int getPriceSq() {
                return priceSq;
            }

            public void setPriceSq(int priceSq) {
                this.priceSq = priceSq;
            }

            public int getIsoriginal() {
                return isoriginal;
            }

            public void setIsoriginal(int isoriginal) {
                this.isoriginal = isoriginal;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public TransParamBean getTransParam() {
                return transParam;
            }

            public void setTransParam(TransParamBean transParam) {
                this.transParam = transParam;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getAccompany() {
                return Accompany;
            }

            public void setAccompany(int Accompany) {
                this.Accompany = Accompany;
            }

            public int getOldCpy() {
                return oldCpy;
            }

            public void setOldCpy(int oldCpy) {
                this.oldCpy = oldCpy;
            }

            public String getSongnameOriginal() {
                return songnameOriginal;
            }

            public void setSongnameOriginal(String songnameOriginal) {
                this.songnameOriginal = songnameOriginal;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public int getSourceid() {
                return sourceid;
            }

            public void setSourceid(int sourceid) {
                this.sourceid = sourceid;
            }

            public String getTopicUrl() {
                return topicUrl;
            }

            public void setTopicUrl(String topicUrl) {
                this.topicUrl = topicUrl;
            }

            public int getFailProcess320() {
                return failProcess320;
            }

            public void setFailProcess320(int failProcess320) {
                this.failProcess320 = failProcess320;
            }

            public int getPkgPrice() {
                return pkgPrice;
            }

            public void setPkgPrice(int pkgPrice) {
                this.pkgPrice = pkgPrice;
            }

            public int getFeetype() {
                return feetype;
            }

            public void setFeetype(int feetype) {
                this.feetype = feetype;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public int getPrice320() {
                return price320;
            }

            public void setPrice320(int price320) {
                this.price320 = price320;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getMvhash() {
                return mvhash;
            }

            public void setMvhash(String mvhash) {
                this.mvhash = mvhash;
            }

            public String getRpType() {
                return rpType;
            }

            public void setRpType(String rpType) {
                this.rpType = rpType;
            }

            public int getPrivilege() {
                return privilege;
            }

            public void setPrivilege(int privilege) {
                this.privilege = privilege;
            }

            public int getAlbumAudioId() {
                return albumAudioId;
            }

            public void setAlbumAudioId(int albumAudioId) {
                this.albumAudioId = albumAudioId;
            }

            public int getRpPublish() {
                return rpPublish;
            }

            public void setRpPublish(int rpPublish) {
                this.rpPublish = rpPublish;
            }

            public String getAlbumId() {
                return albumId;
            }

            public void setAlbumId(String albumId) {
                this.albumId = albumId;
            }

            public int getOwnercount() {
                return ownercount;
            }

            public void setOwnercount(int ownercount) {
                this.ownercount = ownercount;
            }

            public int getFoldType() {
                return foldType;
            }

            public void setFoldType(int foldType) {
                this.foldType = foldType;
            }

            public int getAudioId() {
                return audioId;
            }

            public void setAudioId(int audioId) {
                this.audioId = audioId;
            }

            public int getPkgPriceSq() {
                return pkgPriceSq;
            }

            public void setPkgPriceSq(int pkgPriceSq) {
                this.pkgPriceSq = pkgPriceSq;
            }

            public int get$320filesize() {
                return $320filesize;
            }

            public void set$320filesize(int $320filesize) {
                this.$320filesize = $320filesize;
            }

            public int getIsnew() {
                return isnew;
            }

            public void setIsnew(int isnew) {
                this.isnew = isnew;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getPkgPrice320() {
                return pkgPrice320;
            }

            public void setPkgPrice320(int pkgPrice320) {
                this.pkgPrice320 = pkgPrice320;
            }

            public int getSrctype() {
                return srctype;
            }

            public void setSrctype(int srctype) {
                this.srctype = srctype;
            }

            public int getFailProcessSq() {
                return failProcessSq;
            }

            public void setFailProcessSq(int failProcessSq) {
                this.failProcessSq = failProcessSq;
            }

            public int getSqfilesize() {
                return sqfilesize;
            }

            public void setSqfilesize(int sqfilesize) {
                this.sqfilesize = sqfilesize;
            }

            public int getFailProcess() {
                return failProcess;
            }

            public void setFailProcess(int failProcess) {
                this.failProcess = failProcess;
            }

            public String get$320hash() {
                return $320hash;
            }

            public void set$320hash(String $320hash) {
                this.$320hash = $320hash;
            }

            public String getExtname() {
                return extname;
            }

            public void setExtname(String extname) {
                this.extname = extname;
            }

            public String getSqhash() {
                return sqhash;
            }

            public void setSqhash(String sqhash) {
                this.sqhash = sqhash;
            }

            public int getPayTypeSq() {
                return payTypeSq;
            }

            public void setPayTypeSq(int payTypeSq) {
                this.payTypeSq = payTypeSq;
            }

            public int get$320privilege() {
                return $320privilege;
            }

            public void set$320privilege(int $320privilege) {
                this.$320privilege = $320privilege;
            }

            public int getSqprivilege() {
                return sqprivilege;
            }

            public void setSqprivilege(int sqprivilege) {
                this.sqprivilege = sqprivilege;
            }

            public String getAlbumName() {
                return albumName;
            }

            public void setAlbumName(String albumName) {
                this.albumName = albumName;
            }

            public String getOthername() {
                return othername;
            }

            public void setOthername(String othername) {
                this.othername = othername;
            }

            public List<GroupBean> getGroup() {
                return group;
            }

            public void setGroup(List<GroupBean> group) {
                this.group = group;
            }

            public static class TransParamBean {
                /**
                 * cid : 4996418
                 * pay_block_tpl : 1
                 * musicpack_advance : 0
                 * display_rate : 0
                 * display : 32
                 * exclusive : 1
                 */

                @SerializedName("cid")
                private int cid;
                @SerializedName("pay_block_tpl")
                private int payBlockTpl;
                @SerializedName("musicpack_advance")
                private int musicpackAdvance;
                @SerializedName("display_rate")
                private int displayRate;
                @SerializedName("display")
                private int display;
                @SerializedName("exclusive")
                private int exclusive;

                public int getCid() {
                    return cid;
                }

                public void setCid(int cid) {
                    this.cid = cid;
                }

                public int getPayBlockTpl() {
                    return payBlockTpl;
                }

                public void setPayBlockTpl(int payBlockTpl) {
                    this.payBlockTpl = payBlockTpl;
                }

                public int getMusicpackAdvance() {
                    return musicpackAdvance;
                }

                public void setMusicpackAdvance(int musicpackAdvance) {
                    this.musicpackAdvance = musicpackAdvance;
                }

                public int getDisplayRate() {
                    return displayRate;
                }

                public void setDisplayRate(int displayRate) {
                    this.displayRate = displayRate;
                }

                public int getDisplay() {
                    return display;
                }

                public void setDisplay(int display) {
                    this.display = display;
                }

                public int getExclusive() {
                    return exclusive;
                }

                public void setExclusive(int exclusive) {
                    this.exclusive = exclusive;
                }
            }

            public static class GroupBean {
                /**
                 * othername_original :
                 * pay_type_320 : 3
                 * m4afilesize : 1313663
                 * price_sq : 200
                 * isoriginal : 0
                 * filesize : 5120879
                 * source :
                 * bitrate : 128
                 * topic :
                 * trans_param : {"cid":31860538,"pay_block_tpl":1,"musicpack_advance":0,"display_rate":0,"display":32,"exclusive":1}
                 * price : 200
                 * Accompany : 1
                 * old_cpy : 1
                 * songname_original : 海阔天空
                 * singername : BEYOND
                 * pay_type : 3
                 * sourceid : 0
                 * topic_url :
                 * fail_process_320 : 4
                 * pkg_price : 1
                 * feetype : 0
                 * filename : BEYOND - 海阔天空
                 * price_320 : 200
                 * songname : 海阔天空
                 * hash : 8e496d133287434a933dcb63a5de8982
                 * mvhash : 3e33ca79e6966281c5b893c25064d94d
                 * rp_type : audio
                 * privilege : 8
                 * album_audio_id : 40288246
                 * rp_publish : 1
                 * album_id : 493161
                 * ownercount : 10
                 * fold_type : 0
                 * audio_id : 3261014
                 * pkg_price_sq : 1
                 * 320filesize : 12802133
                 * isnew : 0
                 * duration : 320
                 * pkg_price_320 : 1
                 * srctype : 1
                 * fail_process_sq : 4
                 * sqfilesize : 30565161
                 * fail_process : 4
                 * 320hash : 486b2da49e6fa8415c70ffa2de8b89ce
                 * extname : mp3
                 * sqhash : 996ae36c7b77d63ab42722d943072789
                 * pay_type_sq : 3
                 * 320privilege : 10
                 * sqprivilege : 10
                 * album_name : 遥望
                 * othername :
                 */

                @SerializedName("othername_original")
                private String othernameOriginal;
                @SerializedName("pay_type_320")
                private int payType320;
                @SerializedName("m4afilesize")
                private int m4afilesize;
                @SerializedName("price_sq")
                private int priceSq;
                @SerializedName("isoriginal")
                private int isoriginal;
                @SerializedName("filesize")
                private int filesize;
                @SerializedName("source")
                private String source;
                @SerializedName("bitrate")
                private int bitrate;
                @SerializedName("topic")
                private String topic;
                @SerializedName("trans_param")
                private TransParamBeanX transParam;
                @SerializedName("price")
                private int price;
                @SerializedName("Accompany")
                private int Accompany;
                @SerializedName("old_cpy")
                private int oldCpy;
                @SerializedName("songname_original")
                private String songnameOriginal;
                @SerializedName("singername")
                private String singername;
                @SerializedName("pay_type")
                private int payType;
                @SerializedName("sourceid")
                private int sourceid;
                @SerializedName("topic_url")
                private String topicUrl;
                @SerializedName("fail_process_320")
                private int failProcess320;
                @SerializedName("pkg_price")
                private int pkgPrice;
                @SerializedName("feetype")
                private int feetype;
                @SerializedName("filename")
                private String filename;
                @SerializedName("price_320")
                private int price320;
                @SerializedName("songname")
                private String songname;
                @SerializedName("hash")
                private String hash;
                @SerializedName("mvhash")
                private String mvhash;
                @SerializedName("rp_type")
                private String rpType;
                @SerializedName("privilege")
                private int privilege;
                @SerializedName("album_audio_id")
                private int albumAudioId;
                @SerializedName("rp_publish")
                private int rpPublish;
                @SerializedName("album_id")
                private String albumId;
                @SerializedName("ownercount")
                private int ownercount;
                @SerializedName("fold_type")
                private int foldType;
                @SerializedName("audio_id")
                private int audioId;
                @SerializedName("pkg_price_sq")
                private int pkgPriceSq;
                @SerializedName("320filesize")
                private int $320filesize;
                @SerializedName("isnew")
                private int isnew;
                @SerializedName("duration")
                private int duration;
                @SerializedName("pkg_price_320")
                private int pkgPrice320;
                @SerializedName("srctype")
                private int srctype;
                @SerializedName("fail_process_sq")
                private int failProcessSq;
                @SerializedName("sqfilesize")
                private int sqfilesize;
                @SerializedName("fail_process")
                private int failProcess;
                @SerializedName("320hash")
                private String $320hash;
                @SerializedName("extname")
                private String extname;
                @SerializedName("sqhash")
                private String sqhash;
                @SerializedName("pay_type_sq")
                private int payTypeSq;
                @SerializedName("320privilege")
                private int $320privilege;
                @SerializedName("sqprivilege")
                private int sqprivilege;
                @SerializedName("album_name")
                private String albumName;
                @SerializedName("othername")
                private String othername;

                public String getOthernameOriginal() {
                    return othernameOriginal;
                }

                public void setOthernameOriginal(String othernameOriginal) {
                    this.othernameOriginal = othernameOriginal;
                }

                public int getPayType320() {
                    return payType320;
                }

                public void setPayType320(int payType320) {
                    this.payType320 = payType320;
                }

                public int getM4afilesize() {
                    return m4afilesize;
                }

                public void setM4afilesize(int m4afilesize) {
                    this.m4afilesize = m4afilesize;
                }

                public int getPriceSq() {
                    return priceSq;
                }

                public void setPriceSq(int priceSq) {
                    this.priceSq = priceSq;
                }

                public int getIsoriginal() {
                    return isoriginal;
                }

                public void setIsoriginal(int isoriginal) {
                    this.isoriginal = isoriginal;
                }

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getBitrate() {
                    return bitrate;
                }

                public void setBitrate(int bitrate) {
                    this.bitrate = bitrate;
                }

                public String getTopic() {
                    return topic;
                }

                public void setTopic(String topic) {
                    this.topic = topic;
                }

                public TransParamBeanX getTransParam() {
                    return transParam;
                }

                public void setTransParam(TransParamBeanX transParam) {
                    this.transParam = transParam;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getAccompany() {
                    return Accompany;
                }

                public void setAccompany(int Accompany) {
                    this.Accompany = Accompany;
                }

                public int getOldCpy() {
                    return oldCpy;
                }

                public void setOldCpy(int oldCpy) {
                    this.oldCpy = oldCpy;
                }

                public String getSongnameOriginal() {
                    return songnameOriginal;
                }

                public void setSongnameOriginal(String songnameOriginal) {
                    this.songnameOriginal = songnameOriginal;
                }

                public String getSingername() {
                    return singername;
                }

                public void setSingername(String singername) {
                    this.singername = singername;
                }

                public int getPayType() {
                    return payType;
                }

                public void setPayType(int payType) {
                    this.payType = payType;
                }

                public int getSourceid() {
                    return sourceid;
                }

                public void setSourceid(int sourceid) {
                    this.sourceid = sourceid;
                }

                public String getTopicUrl() {
                    return topicUrl;
                }

                public void setTopicUrl(String topicUrl) {
                    this.topicUrl = topicUrl;
                }

                public int getFailProcess320() {
                    return failProcess320;
                }

                public void setFailProcess320(int failProcess320) {
                    this.failProcess320 = failProcess320;
                }

                public int getPkgPrice() {
                    return pkgPrice;
                }

                public void setPkgPrice(int pkgPrice) {
                    this.pkgPrice = pkgPrice;
                }

                public int getFeetype() {
                    return feetype;
                }

                public void setFeetype(int feetype) {
                    this.feetype = feetype;
                }

                public String getFilename() {
                    return filename;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }

                public int getPrice320() {
                    return price320;
                }

                public void setPrice320(int price320) {
                    this.price320 = price320;
                }

                public String getSongname() {
                    return songname;
                }

                public void setSongname(String songname) {
                    this.songname = songname;
                }

                public String getHash() {
                    return hash;
                }

                public void setHash(String hash) {
                    this.hash = hash;
                }

                public String getMvhash() {
                    return mvhash;
                }

                public void setMvhash(String mvhash) {
                    this.mvhash = mvhash;
                }

                public String getRpType() {
                    return rpType;
                }

                public void setRpType(String rpType) {
                    this.rpType = rpType;
                }

                public int getPrivilege() {
                    return privilege;
                }

                public void setPrivilege(int privilege) {
                    this.privilege = privilege;
                }

                public int getAlbumAudioId() {
                    return albumAudioId;
                }

                public void setAlbumAudioId(int albumAudioId) {
                    this.albumAudioId = albumAudioId;
                }

                public int getRpPublish() {
                    return rpPublish;
                }

                public void setRpPublish(int rpPublish) {
                    this.rpPublish = rpPublish;
                }

                public String getAlbumId() {
                    return albumId;
                }

                public void setAlbumId(String albumId) {
                    this.albumId = albumId;
                }

                public int getOwnercount() {
                    return ownercount;
                }

                public void setOwnercount(int ownercount) {
                    this.ownercount = ownercount;
                }

                public int getFoldType() {
                    return foldType;
                }

                public void setFoldType(int foldType) {
                    this.foldType = foldType;
                }

                public int getAudioId() {
                    return audioId;
                }

                public void setAudioId(int audioId) {
                    this.audioId = audioId;
                }

                public int getPkgPriceSq() {
                    return pkgPriceSq;
                }

                public void setPkgPriceSq(int pkgPriceSq) {
                    this.pkgPriceSq = pkgPriceSq;
                }

                public int get$320filesize() {
                    return $320filesize;
                }

                public void set$320filesize(int $320filesize) {
                    this.$320filesize = $320filesize;
                }

                public int getIsnew() {
                    return isnew;
                }

                public void setIsnew(int isnew) {
                    this.isnew = isnew;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public int getPkgPrice320() {
                    return pkgPrice320;
                }

                public void setPkgPrice320(int pkgPrice320) {
                    this.pkgPrice320 = pkgPrice320;
                }

                public int getSrctype() {
                    return srctype;
                }

                public void setSrctype(int srctype) {
                    this.srctype = srctype;
                }

                public int getFailProcessSq() {
                    return failProcessSq;
                }

                public void setFailProcessSq(int failProcessSq) {
                    this.failProcessSq = failProcessSq;
                }

                public int getSqfilesize() {
                    return sqfilesize;
                }

                public void setSqfilesize(int sqfilesize) {
                    this.sqfilesize = sqfilesize;
                }

                public int getFailProcess() {
                    return failProcess;
                }

                public void setFailProcess(int failProcess) {
                    this.failProcess = failProcess;
                }

                public String get$320hash() {
                    return $320hash;
                }

                public void set$320hash(String $320hash) {
                    this.$320hash = $320hash;
                }

                public String getExtname() {
                    return extname;
                }

                public void setExtname(String extname) {
                    this.extname = extname;
                }

                public String getSqhash() {
                    return sqhash;
                }

                public void setSqhash(String sqhash) {
                    this.sqhash = sqhash;
                }

                public int getPayTypeSq() {
                    return payTypeSq;
                }

                public void setPayTypeSq(int payTypeSq) {
                    this.payTypeSq = payTypeSq;
                }

                public int get$320privilege() {
                    return $320privilege;
                }

                public void set$320privilege(int $320privilege) {
                    this.$320privilege = $320privilege;
                }

                public int getSqprivilege() {
                    return sqprivilege;
                }

                public void setSqprivilege(int sqprivilege) {
                    this.sqprivilege = sqprivilege;
                }

                public String getAlbumName() {
                    return albumName;
                }

                public void setAlbumName(String albumName) {
                    this.albumName = albumName;
                }

                public String getOthername() {
                    return othername;
                }

                public void setOthername(String othername) {
                    this.othername = othername;
                }

                public static class TransParamBeanX {
                    /**
                     * cid : 31860538
                     * pay_block_tpl : 1
                     * musicpack_advance : 0
                     * display_rate : 0
                     * display : 32
                     * exclusive : 1
                     */

                    @SerializedName("cid")
                    private int cid;
                    @SerializedName("pay_block_tpl")
                    private int payBlockTpl;
                    @SerializedName("musicpack_advance")
                    private int musicpackAdvance;
                    @SerializedName("display_rate")
                    private int displayRate;
                    @SerializedName("display")
                    private int display;
                    @SerializedName("exclusive")
                    private int exclusive;

                    public int getCid() {
                        return cid;
                    }

                    public void setCid(int cid) {
                        this.cid = cid;
                    }

                    public int getPayBlockTpl() {
                        return payBlockTpl;
                    }

                    public void setPayBlockTpl(int payBlockTpl) {
                        this.payBlockTpl = payBlockTpl;
                    }

                    public int getMusicpackAdvance() {
                        return musicpackAdvance;
                    }

                    public void setMusicpackAdvance(int musicpackAdvance) {
                        this.musicpackAdvance = musicpackAdvance;
                    }

                    public int getDisplayRate() {
                        return displayRate;
                    }

                    public void setDisplayRate(int displayRate) {
                        this.displayRate = displayRate;
                    }

                    public int getDisplay() {
                        return display;
                    }

                    public void setDisplay(int display) {
                        this.display = display;
                    }

                    public int getExclusive() {
                        return exclusive;
                    }

                    public void setExclusive(int exclusive) {
                        this.exclusive = exclusive;
                    }
                }
            }
        }
    }
}
