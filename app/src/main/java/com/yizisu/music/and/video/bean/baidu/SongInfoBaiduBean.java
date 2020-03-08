package com.yizisu.music.and.video.bean.baidu;

import com.google.gson.annotations.SerializedName;

public class SongInfoBaiduBean {

    /**
     * songinfo : {"special_type":0,"pic_huge":"http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_1000,h_1000","ting_uid":"209982150","pic_premium":"http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_500,h_500","havehigh":2,"si_proxycompany":"独立音乐人","author":"蒋明","toneid":"0","has_mv":0,"song_id":"546887632","title":"从前慢","artist_id":"14436981","lrclink":"http://qukufile2.qianqian.com/data2/lrc/e395d2de3c7c1d4a7001b5fa5d348675/659899566/659899566.lrc","relate_status":"0","learn":0,"pic_big":"http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_150,h_150","play_type":0,"album_id":"546887608","pic_radio":"http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_300,h_300","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","all_artist_id":"14436981","all_artist_ting_uid":"209982150","piao_id":"0","charge":0,"copy_type":"0","all_rate":"96,128,224,320,flac","korean_bb_song":"0","is_first_publish":0,"has_mv_mobile":0,"album_title":"故纸堆","pic_small":"http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_90,h_90","album_no":"2","resource_type_ext":"0","resource_type":"0"}
     * error_code : 22000
     * bitrate : {"show_link":"http://audio04.dmhmusic.com/71_53_T10046722712_128_4_1_0_sdk-cpm/cn/0208/M00/6F/02/ChR461uC3bWAXT8LAFBOio2zSeU812.mp3?xcode=a2b50769a3dbf18e5fcae6f0da95baf0fc5e2f5","free":1,"song_file_id":0,"file_size":5262986,"file_extension":"mp3","file_duration":328,"file_bitrate":128,"file_link":"http://audio04.dmhmusic.com/71_53_T10046722712_128_4_1_0_sdk-cpm/cn/0208/M00/6F/02/ChR461uC3bWAXT8LAFBOio2zSeU812.mp3?xcode=a2b50769a3dbf18e5fcae6f0da95baf0fc5e2f5","hash":"bc1993dd70ed2746a7c37f7f1102e2e30a45cd88"}
     */

    @SerializedName("songinfo")
    private SonginfoBean songinfo;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("bitrate")
    private Object bitrate;

    public SonginfoBean getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(SonginfoBean songinfo) {
        this.songinfo = songinfo;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public BitrateBean getBitrate() {
        if (bitrate instanceof BitrateBean) {
            return (BitrateBean) bitrate;
        }
        return null;
    }

    public void setBitrate(BitrateBean bitrate) {
        this.bitrate = bitrate;
    }

    public static class SonginfoBean {
        /**
         * special_type : 0
         * pic_huge : http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_1000,h_1000
         * ting_uid : 209982150
         * pic_premium : http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_500,h_500
         * havehigh : 2
         * si_proxycompany : 独立音乐人
         * author : 蒋明
         * toneid : 0
         * has_mv : 0
         * song_id : 546887632
         * title : 从前慢
         * artist_id : 14436981
         * lrclink : http://qukufile2.qianqian.com/data2/lrc/e395d2de3c7c1d4a7001b5fa5d348675/659899566/659899566.lrc
         * relate_status : 0
         * learn : 0
         * pic_big : http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_150,h_150
         * play_type : 0
         * album_id : 546887608
         * pic_radio : http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_300,h_300
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * song_source : web
         * all_artist_id : 14436981
         * all_artist_ting_uid : 209982150
         * piao_id : 0
         * charge : 0
         * copy_type : 0
         * all_rate : 96,128,224,320,flac
         * korean_bb_song : 0
         * is_first_publish : 0
         * has_mv_mobile : 0
         * album_title : 故纸堆
         * pic_small : http://qukufile2.qianqian.com/data2/pic/a75093c9b6929a8319c94c2a07659d29/659899434/659899434.jpg@s_2,w_90,h_90
         * album_no : 2
         * resource_type_ext : 0
         * resource_type : 0
         */

        @SerializedName("special_type")
        private int specialType;
        @SerializedName("pic_huge")
        private String picHuge;
        @SerializedName("ting_uid")
        private String tingUid;
        @SerializedName("pic_premium")
        private String picPremium;
        @SerializedName("havehigh")
        private int havehigh;
        @SerializedName("si_proxycompany")
        private String siProxycompany;
        @SerializedName("author")
        private String author;
        @SerializedName("toneid")
        private String toneid;
        @SerializedName("has_mv")
        private int hasMv;
        @SerializedName("song_id")
        private String songId;
        @SerializedName("title")
        private String title;
        @SerializedName("artist_id")
        private String artistId;
        @SerializedName("lrclink")
        private String lrclink;
        @SerializedName("relate_status")
        private String relateStatus;
        @SerializedName("learn")
        private int learn;
        @SerializedName("pic_big")
        private String picBig;
        @SerializedName("play_type")
        private int playType;
        @SerializedName("album_id")
        private String albumId;
        @SerializedName("pic_radio")
        private String picRadio;
        @SerializedName("bitrate_fee")
        private String bitrateFee;
        @SerializedName("song_source")
        private String songSource;
        @SerializedName("all_artist_id")
        private String allArtistId;
        @SerializedName("all_artist_ting_uid")
        private String allArtistTingUid;
        @SerializedName("piao_id")
        private String piaoId;
        @SerializedName("charge")
        private int charge;
        @SerializedName("copy_type")
        private String copyType;
        @SerializedName("all_rate")
        private String allRate;
        @SerializedName("korean_bb_song")
        private String koreanBbSong;
        @SerializedName("is_first_publish")
        private int isFirstPublish;
        @SerializedName("has_mv_mobile")
        private int hasMvMobile;
        @SerializedName("album_title")
        private String albumTitle;
        @SerializedName("pic_small")
        private String picSmall;
        @SerializedName("album_no")
        private String albumNo;
        @SerializedName("resource_type_ext")
        private String resourceTypeExt;
        @SerializedName("resource_type")
        private String resourceType;

        public int getSpecialType() {
            return specialType;
        }

        public void setSpecialType(int specialType) {
            this.specialType = specialType;
        }

        public String getPicHuge() {
            return picHuge;
        }

        public void setPicHuge(String picHuge) {
            this.picHuge = picHuge;
        }

        public String getTingUid() {
            return tingUid;
        }

        public void setTingUid(String tingUid) {
            this.tingUid = tingUid;
        }

        public String getPicPremium() {
            return picPremium;
        }

        public void setPicPremium(String picPremium) {
            this.picPremium = picPremium;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public String getSiProxycompany() {
            return siProxycompany;
        }

        public void setSiProxycompany(String siProxycompany) {
            this.siProxycompany = siProxycompany;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getToneid() {
            return toneid;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public int getHasMv() {
            return hasMv;
        }

        public void setHasMv(int hasMv) {
            this.hasMv = hasMv;
        }

        public String getSongId() {
            return songId;
        }

        public void setSongId(String songId) {
            this.songId = songId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtistId() {
            return artistId;
        }

        public void setArtistId(String artistId) {
            this.artistId = artistId;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public String getRelateStatus() {
            return relateStatus;
        }

        public void setRelateStatus(String relateStatus) {
            this.relateStatus = relateStatus;
        }

        public int getLearn() {
            return learn;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public String getPicBig() {
            return picBig;
        }

        public void setPicBig(String picBig) {
            this.picBig = picBig;
        }

        public int getPlayType() {
            return playType;
        }

        public void setPlayType(int playType) {
            this.playType = playType;
        }

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getPicRadio() {
            return picRadio;
        }

        public void setPicRadio(String picRadio) {
            this.picRadio = picRadio;
        }

        public String getBitrateFee() {
            return bitrateFee;
        }

        public void setBitrateFee(String bitrateFee) {
            this.bitrateFee = bitrateFee;
        }

        public String getSongSource() {
            return songSource;
        }

        public void setSongSource(String songSource) {
            this.songSource = songSource;
        }

        public String getAllArtistId() {
            return allArtistId;
        }

        public void setAllArtistId(String allArtistId) {
            this.allArtistId = allArtistId;
        }

        public String getAllArtistTingUid() {
            return allArtistTingUid;
        }

        public void setAllArtistTingUid(String allArtistTingUid) {
            this.allArtistTingUid = allArtistTingUid;
        }

        public String getPiaoId() {
            return piaoId;
        }

        public void setPiaoId(String piaoId) {
            this.piaoId = piaoId;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public String getCopyType() {
            return copyType;
        }

        public void setCopyType(String copyType) {
            this.copyType = copyType;
        }

        public String getAllRate() {
            return allRate;
        }

        public void setAllRate(String allRate) {
            this.allRate = allRate;
        }

        public String getKoreanBbSong() {
            return koreanBbSong;
        }

        public void setKoreanBbSong(String koreanBbSong) {
            this.koreanBbSong = koreanBbSong;
        }

        public int getIsFirstPublish() {
            return isFirstPublish;
        }

        public void setIsFirstPublish(int isFirstPublish) {
            this.isFirstPublish = isFirstPublish;
        }

        public int getHasMvMobile() {
            return hasMvMobile;
        }

        public void setHasMvMobile(int hasMvMobile) {
            this.hasMvMobile = hasMvMobile;
        }

        public String getAlbumTitle() {
            return albumTitle;
        }

        public void setAlbumTitle(String albumTitle) {
            this.albumTitle = albumTitle;
        }

        public String getPicSmall() {
            return picSmall;
        }

        public void setPicSmall(String picSmall) {
            this.picSmall = picSmall;
        }

        public String getAlbumNo() {
            return albumNo;
        }

        public void setAlbumNo(String albumNo) {
            this.albumNo = albumNo;
        }

        public String getResourceTypeExt() {
            return resourceTypeExt;
        }

        public void setResourceTypeExt(String resourceTypeExt) {
            this.resourceTypeExt = resourceTypeExt;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }
    }

    public static class BitrateBean {
        /**
         * show_link : http://audio04.dmhmusic.com/71_53_T10046722712_128_4_1_0_sdk-cpm/cn/0208/M00/6F/02/ChR461uC3bWAXT8LAFBOio2zSeU812.mp3?xcode=a2b50769a3dbf18e5fcae6f0da95baf0fc5e2f5
         * free : 1
         * song_file_id : 0
         * file_size : 5262986
         * file_extension : mp3
         * file_duration : 328
         * file_bitrate : 128
         * file_link : http://audio04.dmhmusic.com/71_53_T10046722712_128_4_1_0_sdk-cpm/cn/0208/M00/6F/02/ChR461uC3bWAXT8LAFBOio2zSeU812.mp3?xcode=a2b50769a3dbf18e5fcae6f0da95baf0fc5e2f5
         * hash : bc1993dd70ed2746a7c37f7f1102e2e30a45cd88
         */

        @SerializedName("show_link")
        private String showLink;
        @SerializedName("free")
        private int free;
        @SerializedName("song_file_id")
        private int songFileId;
        @SerializedName("file_size")
        private int fileSize;
        @SerializedName("file_extension")
        private String fileExtension;
        @SerializedName("file_duration")
        private int fileDuration;
        @SerializedName("file_bitrate")
        private int fileBitrate;
        @SerializedName("file_link")
        private String fileLink;
        @SerializedName("hash")
        private String hash;

        public String getShowLink() {
            return showLink;
        }

        public void setShowLink(String showLink) {
            this.showLink = showLink;
        }

        public int getFree() {
            return free;
        }

        public void setFree(int free) {
            this.free = free;
        }

        public int getSongFileId() {
            return songFileId;
        }

        public void setSongFileId(int songFileId) {
            this.songFileId = songFileId;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public String getFileExtension() {
            return fileExtension;
        }

        public void setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        public int getFileDuration() {
            return fileDuration;
        }

        public void setFileDuration(int fileDuration) {
            this.fileDuration = fileDuration;
        }

        public int getFileBitrate() {
            return fileBitrate;
        }

        public void setFileBitrate(int fileBitrate) {
            this.fileBitrate = fileBitrate;
        }

        public String getFileLink() {
            return fileLink;
        }

        public void setFileLink(String fileLink) {
            this.fileLink = fileLink;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
