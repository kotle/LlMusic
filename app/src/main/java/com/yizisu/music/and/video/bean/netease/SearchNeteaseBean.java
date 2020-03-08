package com.yizisu.music.and.video.bean.netease;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchNeteaseBean {

    /**
     * code : 200
     */

    @SerializedName("result")
    private ResultBean result;
    @SerializedName("code")
    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * songCount : 600
         */

        @SerializedName("songCount")
        private int songCount;
        @SerializedName("songs")
        private List<SongsBean> songs;

        public int getSongCount() {
            return songCount;
        }

        public void setSongCount(int songCount) {
            this.songCount = songCount;
        }

        public List<SongsBean> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsBean> songs) {
            this.songs = songs;
        }

        public static class SongsBean {
            /**
             * name : 下山
             * id : 1404885266
             * position : 0
             * alias : []
             * status : 0
             * fee : 8
             * copyrightId : 1372818
             * disc : 01
             * no : 1
             * starred : false
             * popularity : 100
             * score : 100
             * starredNum : 0
             * duration : 173414
             * playedNum : 0
             * dayPlays : 0
             * hearTime : 0
             * ringtone :
             * crbt : null
             * audition : null
             * copyFrom :
             * commentThreadId : R_SO_4_1404885266
             * rtUrl : null
             * ftype : 0
             * rtUrls : []
             * copyright : 1
             * mvid : 0
             * rtype : 0
             * rurl : null
             * bMusic : {"name":null,"id":4171211307,"size":2775597,"extension":"mp3","sr":48000,"dfsId":0,"bitrate":128000,"playTime":173414,"volumeDelta":-42617}
             * mp3Url : http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3
             * hMusic : {"name":null,"id":4171211305,"size":6938925,"extension":"mp3","sr":48000,"dfsId":0,"bitrate":320000,"playTime":173414,"volumeDelta":-46932}
             * mMusic : {"name":null,"id":4171211306,"size":4163373,"extension":"mp3","sr":48000,"dfsId":0,"bitrate":192000,"playTime":173414,"volumeDelta":-44327}
             * lMusic : {"name":null,"id":4171211307,"size":2775597,"extension":"mp3","sr":48000,"dfsId":0,"bitrate":128000,"playTime":173414,"volumeDelta":-42617}
             */

            @SerializedName("name")
            private String name;
            @SerializedName("id")
            private long id;
            @SerializedName("position")
            private long position;
            @SerializedName("status")
            private long status;
            @SerializedName("fee")
            private long fee;
            @SerializedName("copyrightId")
            private long copyrightId;
            @SerializedName("disc")
            private String disc;
            @SerializedName("no")
            private long no;
            @SerializedName("album")
            private AlbumBean album;
            @SerializedName("starred")
            private boolean starred;
            @SerializedName("popularity")
            private long popularity;
            @SerializedName("score")
            private long score;
            @SerializedName("starredNum")
            private long starredNum;
            @SerializedName("duration")
            private long duration;
            @SerializedName("playedNum")
            private long playedNum;
            @SerializedName("dayPlays")
            private long dayPlays;
            @SerializedName("hearTime")
            private long hearTime;
            @SerializedName("ringtone")
            private String ringtone;
            @SerializedName("crbt")
            private Object crbt;
            @SerializedName("audition")
            private Object audition;
            @SerializedName("copyFrom")
            private String copyFrom;
            @SerializedName("commentThreadId")
            private String commentThreadId;
            @SerializedName("rtUrl")
            private Object rtUrl;
            @SerializedName("ftype")
            private long ftype;
            @SerializedName("copyright")
            private long copyright;
            @SerializedName("mvid")
            private long mvid;
            @SerializedName("rtype")
            private long rtype;
            @SerializedName("rurl")
            private Object rurl;
            @SerializedName("bMusic")
            private BMusicBean bMusic;
            @SerializedName("mp3Url")
            private String mp3Url;
            @SerializedName("hMusic")
            private BMusicBean hMusic;
            @SerializedName("mMusic")
            private BMusicBean mMusic;
            @SerializedName("lMusic")
            private BMusicBean lMusic;
            @SerializedName("alias")
            private List<?> alias;
            @SerializedName("artists")
            private List<ArtistsBeanX> artists;
            @SerializedName("rtUrls")
            private List<?> rtUrls;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getPosition() {
                return position;
            }

            public void setPosition(long position) {
                this.position = position;
            }

            public long getStatus() {
                return status;
            }

            public void setStatus(long status) {
                this.status = status;
            }

            public long getFee() {
                return fee;
            }

            public void setFee(long fee) {
                this.fee = fee;
            }

            public long getCopyrightId() {
                return copyrightId;
            }

            public void setCopyrightId(long copyrightId) {
                this.copyrightId = copyrightId;
            }

            public String getDisc() {
                return disc;
            }

            public void setDisc(String disc) {
                this.disc = disc;
            }

            public long getNo() {
                return no;
            }

            public void setNo(long no) {
                this.no = no;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }

            public boolean isStarred() {
                return starred;
            }

            public void setStarred(boolean starred) {
                this.starred = starred;
            }

            public long getPopularity() {
                return popularity;
            }

            public void setPopularity(long popularity) {
                this.popularity = popularity;
            }

            public long getScore() {
                return score;
            }

            public void setScore(long score) {
                this.score = score;
            }

            public long getStarredNum() {
                return starredNum;
            }

            public void setStarredNum(long starredNum) {
                this.starredNum = starredNum;
            }

            public long getDuration() {
                return duration;
            }

            public void setDuration(long duration) {
                this.duration = duration;
            }

            public long getPlayedNum() {
                return playedNum;
            }

            public void setPlayedNum(long playedNum) {
                this.playedNum = playedNum;
            }

            public long getDayPlays() {
                return dayPlays;
            }

            public void setDayPlays(long dayPlays) {
                this.dayPlays = dayPlays;
            }

            public long getHearTime() {
                return hearTime;
            }

            public void setHearTime(long hearTime) {
                this.hearTime = hearTime;
            }

            public String getRingtone() {
                return ringtone;
            }

            public void setRingtone(String ringtone) {
                this.ringtone = ringtone;
            }

            public Object getCrbt() {
                return crbt;
            }

            public void setCrbt(Object crbt) {
                this.crbt = crbt;
            }

            public Object getAudition() {
                return audition;
            }

            public void setAudition(Object audition) {
                this.audition = audition;
            }

            public String getCopyFrom() {
                return copyFrom;
            }

            public void setCopyFrom(String copyFrom) {
                this.copyFrom = copyFrom;
            }

            public String getCommentThreadId() {
                return commentThreadId;
            }

            public void setCommentThreadId(String commentThreadId) {
                this.commentThreadId = commentThreadId;
            }

            public Object getRtUrl() {
                return rtUrl;
            }

            public void setRtUrl(Object rtUrl) {
                this.rtUrl = rtUrl;
            }

            public long getFtype() {
                return ftype;
            }

            public void setFtype(long ftype) {
                this.ftype = ftype;
            }

            public long getCopyright() {
                return copyright;
            }

            public void setCopyright(long copyright) {
                this.copyright = copyright;
            }

            public long getMvid() {
                return mvid;
            }

            public void setMvid(long mvid) {
                this.mvid = mvid;
            }

            public long getRtype() {
                return rtype;
            }

            public void setRtype(long rtype) {
                this.rtype = rtype;
            }

            public Object getRurl() {
                return rurl;
            }

            public void setRurl(Object rurl) {
                this.rurl = rurl;
            }

            public BMusicBean getBMusic() {
                return bMusic;
            }

            public void setBMusic(BMusicBean bMusic) {
                this.bMusic = bMusic;
            }

            public String getMp3Url() {
                return mp3Url;
            }

            public void setMp3Url(String mp3Url) {
                this.mp3Url = mp3Url;
            }

            public BMusicBean getHMusic() {
                return hMusic;
            }

            public void setHMusic(BMusicBean hMusic) {
                this.hMusic = hMusic;
            }

            public BMusicBean getMMusic() {
                return mMusic;
            }

            public void setMMusic(BMusicBean mMusic) {
                this.mMusic = mMusic;
            }

            public BMusicBean getLMusic() {
                return lMusic;
            }

            public void setLMusic(BMusicBean lMusic) {
                this.lMusic = lMusic;
            }

            public List<?> getAlias() {
                return alias;
            }

            public void setAlias(List<?> alias) {
                this.alias = alias;
            }

            public List<ArtistsBeanX> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBeanX> artists) {
                this.artists = artists;
            }

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
            }

            public static class AlbumBean {
                /**
                 * name : 下山
                 * id : 83535299
                 * type : EP/Single
                 * size : 2
                 * picId : 109951164499744148
                 * blurPicUrl : http://p1.music.126.net/Aj4X1kpV-C2LLi-e_Xhgvg==/109951164499744148.jpg
                 * companyId : 0
                 * pic : 109951164499744148
                 * picUrl : http://p1.music.126.net/Aj4X1kpV-C2LLi-e_Xhgvg==/109951164499744148.jpg
                 * publishTime : 1574352000000
                 * description :
                 * tags :
                 * company : 嗨库文化
                 * briefDesc :
                 * songs : []
                 * alias : []
                 * status : 1
                 * copyrightId : 1372818
                 * commentThreadId : R_AL_3_83535299
                 * picId_str : 109951164499744148
                 */

                @SerializedName("name")
                private String name;
                @SerializedName("id")
                private long id;
                @SerializedName("type")
                private String type;
                @SerializedName("size")
                private long size;
                @SerializedName("picId")
                private long picId;
                @SerializedName("blurPicUrl")
                private String blurPicUrl;
                @SerializedName("companyId")
                private long companyId;
                @SerializedName("pic")
                private long pic;
                @SerializedName("picUrl")
                private String picUrl;
                @SerializedName("publishTime")
                private long publishTime;
                @SerializedName("description")
                private String description;
                @SerializedName("tags")
                private String tags;
                @SerializedName("company")
                private String company;
                @SerializedName("briefDesc")
                private String briefDesc;
                @SerializedName("artist")
                private ArtistBean artist;
                @SerializedName("status")
                private long status;
                @SerializedName("copyrightId")
                private long copyrightId;
                @SerializedName("commentThreadId")
                private String commentThreadId;
                @SerializedName("picId_str")
                private String picIdStr;
                @SerializedName("songs")
                private List<?> songs;
                @SerializedName("alias")
                private List<?> alias;
                @SerializedName("artists")
                private List<ArtistsBean> artists;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public long getSize() {
                    return size;
                }

                public void setSize(long size) {
                    this.size = size;
                }

                public long getPicId() {
                    return picId;
                }

                public void setPicId(long picId) {
                    this.picId = picId;
                }

                public String getBlurPicUrl() {
                    return blurPicUrl;
                }

                public void setBlurPicUrl(String blurPicUrl) {
                    this.blurPicUrl = blurPicUrl;
                }

                public long getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(long companyId) {
                    this.companyId = companyId;
                }

                public long getPic() {
                    return pic;
                }

                public void setPic(long pic) {
                    this.pic = pic;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public long getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getTags() {
                    return tags;
                }

                public void setTags(String tags) {
                    this.tags = tags;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getBriefDesc() {
                    return briefDesc;
                }

                public void setBriefDesc(String briefDesc) {
                    this.briefDesc = briefDesc;
                }

                public ArtistBean getArtist() {
                    return artist;
                }

                public void setArtist(ArtistBean artist) {
                    this.artist = artist;
                }

                public long getStatus() {
                    return status;
                }

                public void setStatus(long status) {
                    this.status = status;
                }

                public long getCopyrightId() {
                    return copyrightId;
                }

                public void setCopyrightId(long copyrightId) {
                    this.copyrightId = copyrightId;
                }

                public String getCommentThreadId() {
                    return commentThreadId;
                }

                public void setCommentThreadId(String commentThreadId) {
                    this.commentThreadId = commentThreadId;
                }

                public String getPicIdStr() {
                    return picIdStr;
                }

                public void setPicIdStr(String picIdStr) {
                    this.picIdStr = picIdStr;
                }

                public List<?> getSongs() {
                    return songs;
                }

                public void setSongs(List<?> songs) {
                    this.songs = songs;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }

                public List<ArtistsBean> getArtists() {
                    return artists;
                }

                public void setArtists(List<ArtistsBean> artists) {
                    this.artists = artists;
                }

                public static class ArtistBean {
                    /**
                     * name :
                     * id : 0
                     * picId : 0
                     * img1v1Id : 0
                     * briefDesc :
                     * picUrl : http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * img1v1Url : http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * albumSize : 0
                     * alias : []
                     * trans :
                     * musicSize : 0
                     */

                    @SerializedName("name")
                    private String name;
                    @SerializedName("id")
                    private long id;
                    @SerializedName("picId")
                    private long picId;
                    @SerializedName("img1v1Id")
                    private long img1v1Id;
                    @SerializedName("briefDesc")
                    private String briefDesc;
                    @SerializedName("picUrl")
                    private String picUrl;
                    @SerializedName("img1v1Url")
                    private String img1v1Url;
                    @SerializedName("albumSize")
                    private long albumSize;
                    @SerializedName("trans")
                    private String trans;
                    @SerializedName("musicSize")
                    private long musicSize;
                    @SerializedName("alias")
                    private List<?> alias;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public long getPicId() {
                        return picId;
                    }

                    public void setPicId(long picId) {
                        this.picId = picId;
                    }

                    public long getImg1v1Id() {
                        return img1v1Id;
                    }

                    public void setImg1v1Id(long img1v1Id) {
                        this.img1v1Id = img1v1Id;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public long getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(long albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getTrans() {
                        return trans;
                    }

                    public void setTrans(String trans) {
                        this.trans = trans;
                    }

                    public long getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(long musicSize) {
                        this.musicSize = musicSize;
                    }

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }

                public static class ArtistsBean {
                    /**
                     * name : 要不要买菜
                     * id : 33435854
                     * picId : 0
                     * img1v1Id : 0
                     * briefDesc :
                     * picUrl : http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * img1v1Url : http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * albumSize : 0
                     * alias : []
                     * trans :
                     * musicSize : 0
                     */

                    @SerializedName("name")
                    private String name;
                    @SerializedName("id")
                    private long id;
                    @SerializedName("picId")
                    private long picId;
                    @SerializedName("img1v1Id")
                    private long img1v1Id;
                    @SerializedName("briefDesc")
                    private String briefDesc;
                    @SerializedName("picUrl")
                    private String picUrl;
                    @SerializedName("img1v1Url")
                    private String img1v1Url;
                    @SerializedName("albumSize")
                    private long albumSize;
                    @SerializedName("trans")
                    private String trans;
                    @SerializedName("musicSize")
                    private long musicSize;
                    @SerializedName("alias")
                    private List<?> alias;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public long getPicId() {
                        return picId;
                    }

                    public void setPicId(long picId) {
                        this.picId = picId;
                    }

                    public long getImg1v1Id() {
                        return img1v1Id;
                    }

                    public void setImg1v1Id(long img1v1Id) {
                        this.img1v1Id = img1v1Id;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public long getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(long albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getTrans() {
                        return trans;
                    }

                    public void setTrans(String trans) {
                        this.trans = trans;
                    }

                    public long getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(long musicSize) {
                        this.musicSize = musicSize;
                    }

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }
            }

            public static class BMusicBean {
                /**
                 * name : null
                 * id : 4171211307
                 * size : 2775597
                 * extension : mp3
                 * sr : 48000
                 * dfsId : 0
                 * bitrate : 128000
                 * playTime : 173414
                 * volumeDelta : -42617
                 */

                @SerializedName("name")
                private Object name;
                @SerializedName("id")
                private long id;
                @SerializedName("size")
                private long size;
                @SerializedName("extension")
                private String extension;
                @SerializedName("sr")
                private long sr;
                @SerializedName("dfsId")
                private long dfsId;
                @SerializedName("bitrate")
                private long bitrate;
                @SerializedName("playTime")
                private long playTime;
                @SerializedName("volumeDelta")
                private double volumeDelta;

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public long getSize() {
                    return size;
                }

                public void setSize(long size) {
                    this.size = size;
                }

                public String getExtension() {
                    return extension;
                }

                public void setExtension(String extension) {
                    this.extension = extension;
                }

                public long getSr() {
                    return sr;
                }

                public void setSr(long sr) {
                    this.sr = sr;
                }

                public long getDfsId() {
                    return dfsId;
                }

                public void setDfsId(long dfsId) {
                    this.dfsId = dfsId;
                }

                public long getBitrate() {
                    return bitrate;
                }

                public void setBitrate(long bitrate) {
                    this.bitrate = bitrate;
                }

                public long getPlayTime() {
                    return playTime;
                }

                public void setPlayTime(long playTime) {
                    this.playTime = playTime;
                }

                public double getVolumeDelta() {
                    return volumeDelta;
                }

                public void setVolumeDelta(double volumeDelta) {
                    this.volumeDelta = volumeDelta;
                }
            }

            public static class ArtistsBeanX {
                /**
                 * name : 要不要买菜
                 * id : 33435854
                 * picId : 0
                 * img1v1Id : 0
                 * briefDesc :
                 * picUrl : http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * img1v1Url : http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * albumSize : 0
                 * alias : []
                 * trans :
                 * musicSize : 0
                 */

                @SerializedName("name")
                private String name;
                @SerializedName("id")
                private long id;
                @SerializedName("picId")
                private long picId;
                @SerializedName("img1v1Id")
                private long img1v1Id;
                @SerializedName("briefDesc")
                private String briefDesc;
                @SerializedName("picUrl")
                private String picUrl;
                @SerializedName("img1v1Url")
                private String img1v1Url;
                @SerializedName("albumSize")
                private long albumSize;
                @SerializedName("trans")
                private String trans;
                @SerializedName("musicSize")
                private long musicSize;
                @SerializedName("alias")
                private List<?> alias;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public long getPicId() {
                    return picId;
                }

                public void setPicId(long picId) {
                    this.picId = picId;
                }

                public long getImg1v1Id() {
                    return img1v1Id;
                }

                public void setImg1v1Id(long img1v1Id) {
                    this.img1v1Id = img1v1Id;
                }

                public String getBriefDesc() {
                    return briefDesc;
                }

                public void setBriefDesc(String briefDesc) {
                    this.briefDesc = briefDesc;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getImg1v1Url() {
                    return img1v1Url;
                }

                public void setImg1v1Url(String img1v1Url) {
                    this.img1v1Url = img1v1Url;
                }

                public long getAlbumSize() {
                    return albumSize;
                }

                public void setAlbumSize(long albumSize) {
                    this.albumSize = albumSize;
                }

                public String getTrans() {
                    return trans;
                }

                public void setTrans(String trans) {
                    this.trans = trans;
                }

                public long getMusicSize() {
                    return musicSize;
                }

                public void setMusicSize(long musicSize) {
                    this.musicSize = musicSize;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }
    }
}
