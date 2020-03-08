package com.yizisu.music.and.video.bean.baidu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchBaiduBean {

    /**
     * song : [{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"1099","songname":"海阔天空","resource_type":"0","songid":"613921282","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"张穆庭","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"1099","songname":"海阔天空","resource_type":"0","songid":"565884031","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"三师兄","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"180821BAB472085D021528"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"199","songname":"vol.135 迈一步，海阔天空","resource_type":"0","songid":"600443282","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"日谈公园","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"610823CA098E085BE94D97"}]
     * album : [{"albumname":"海阔天空-电吉他版","weight":"0","artistname":"MC雪殇","resource_type_ext":"0","artistpic":"http://qukufile2.qianqian.com/data2/pic/default_album.jpg@s_2,w_40,h_40","albumid":"611657010"},{"albumname":"14.海阔天空","weight":"1","artistname":"左右乐队","resource_type_ext":"0","artistpic":"http://qukufile2.qianqian.com/data2/pic/default_album.jpg@s_2,w_40,h_40","albumid":"611650237"}]
     * order : artist,song,album
     * error_code : 22000
     * artist : [{"yyr_artist":"0","artistname":"海阔天空","artistid":"2345733","artistpic":"http://qukufile2.qianqian.com/data2/pic/5F1741B07058A32998B93B4DE698450B/252837196/252837196.jpg@s_2,w_48,h_48","weight":"0"}]
     */

    @SerializedName("order")
    private String order;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("song")
    private List<SongBean> song;
    @SerializedName("album")
    private List<AlbumBean> album;
    @SerializedName("artist")
    private List<ArtistBean> artist;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<SongBean> getSong() {
        return song;
    }

    public void setSong(List<SongBean> song) {
        this.song = song;
    }

    public List<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumBean> album) {
        this.album = album;
    }

    public List<ArtistBean> getArtist() {
        return artist;
    }

    public void setArtist(List<ArtistBean> artist) {
        this.artist = artist;
    }

    public static class SongBean {
        /**
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * weight : 1099
         * songname : 海阔天空
         * resource_type : 0
         * songid : 613921282
         * has_mv : 0
         * yyr_artist : 0
         * resource_type_ext : 0
         * artistname : 张穆庭
         * info :
         * resource_provider : 1
         * control : 0000000000
         * encrypted_songid :
         */

        @SerializedName("bitrate_fee")
        private String bitrateFee;
        @SerializedName("weight")
        private String weight;
        @SerializedName("songname")
        private String songname;
        @SerializedName("resource_type")
        private String resourceType;
        @SerializedName("songid")
        private String songid;
        @SerializedName("has_mv")
        private String hasMv;
        @SerializedName("yyr_artist")
        private String yyrArtist;
        @SerializedName("resource_type_ext")
        private String resourceTypeExt;
        @SerializedName("artistname")
        private String artistname;
        @SerializedName("info")
        private String info;
        @SerializedName("resource_provider")
        private String resourceProvider;
        @SerializedName("control")
        private String control;
        @SerializedName("encrypted_songid")
        private String encryptedSongid;

        public String getBitrateFee() {
            return bitrateFee;
        }

        public void setBitrateFee(String bitrateFee) {
            this.bitrateFee = bitrateFee;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getHasMv() {
            return hasMv;
        }

        public void setHasMv(String hasMv) {
            this.hasMv = hasMv;
        }

        public String getYyrArtist() {
            return yyrArtist;
        }

        public void setYyrArtist(String yyrArtist) {
            this.yyrArtist = yyrArtist;
        }

        public String getResourceTypeExt() {
            return resourceTypeExt;
        }

        public void setResourceTypeExt(String resourceTypeExt) {
            this.resourceTypeExt = resourceTypeExt;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getResourceProvider() {
            return resourceProvider;
        }

        public void setResourceProvider(String resourceProvider) {
            this.resourceProvider = resourceProvider;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getEncryptedSongid() {
            return encryptedSongid;
        }

        public void setEncryptedSongid(String encryptedSongid) {
            this.encryptedSongid = encryptedSongid;
        }
    }

    public static class AlbumBean {
        /**
         * albumname : 海阔天空-电吉他版
         * weight : 0
         * artistname : MC雪殇
         * resource_type_ext : 0
         * artistpic : http://qukufile2.qianqian.com/data2/pic/default_album.jpg@s_2,w_40,h_40
         * albumid : 611657010
         */

        @SerializedName("albumname")
        private String albumname;
        @SerializedName("weight")
        private String weight;
        @SerializedName("artistname")
        private String artistname;
        @SerializedName("resource_type_ext")
        private String resourceTypeExt;
        @SerializedName("artistpic")
        private String artistpic;
        @SerializedName("albumid")
        private String albumid;

        public String getAlbumname() {
            return albumname;
        }

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getResourceTypeExt() {
            return resourceTypeExt;
        }

        public void setResourceTypeExt(String resourceTypeExt) {
            this.resourceTypeExt = resourceTypeExt;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }
    }

    public static class ArtistBean {
        /**
         * yyr_artist : 0
         * artistname : 海阔天空
         * artistid : 2345733
         * artistpic : http://qukufile2.qianqian.com/data2/pic/5F1741B07058A32998B93B4DE698450B/252837196/252837196.jpg@s_2,w_48,h_48
         * weight : 0
         */

        @SerializedName("yyr_artist")
        private String yyrArtist;
        @SerializedName("artistname")
        private String artistname;
        @SerializedName("artistid")
        private String artistid;
        @SerializedName("artistpic")
        private String artistpic;
        @SerializedName("weight")
        private String weight;

        public String getYyrArtist() {
            return yyrArtist;
        }

        public void setYyrArtist(String yyrArtist) {
            this.yyrArtist = yyrArtist;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getArtistid() {
            return artistid;
        }

        public void setArtistid(String artistid) {
            this.artistid = artistid;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
