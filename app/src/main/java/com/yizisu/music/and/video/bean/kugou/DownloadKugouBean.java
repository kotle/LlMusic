package com.yizisu.music.and.video.bean.kugou;

import com.google.gson.annotations.SerializedName;

public class DownloadKugouBean {
    /**
     * trans_param : {"display":32,"display_rate":0}
     * fileHead : 100
     * q : 0
     * fileSize : 5120879
     * fileName : 1C8B9FCBA5EAA10CF131C771FF4C0468 - 139910732293888.fla
     * status : 1
     * url : http://fs.pc.kugou.com/202003171249/700d663fa2b60ff7a4c69cc5eac54ec0/G137/M06/08/17/yQ0DAFuGdOCABiECAE4jb57rgmY473.mp3
     * extName : mp3
     * bitRate : 128000
     * timeLength : 320
     */

    @SerializedName("trans_param")
    private TransParamBean transParam;
    @SerializedName("fileHead")
    private int fileHead;
    @SerializedName("q")
    private int q;
    @SerializedName("fileSize")
    private int fileSize;
    @SerializedName("fileName")
    private String fileName;
    @SerializedName("status")
    private int status;
    @SerializedName("url")
    private String url;
    @SerializedName("extName")
    private String extName;
    @SerializedName("bitRate")
    private int bitRate;
    @SerializedName("timeLength")
    private int timeLength;

    public TransParamBean getTransParam() {
        return transParam;
    }

    public void setTransParam(TransParamBean transParam) {
        this.transParam = transParam;
    }

    public int getFileHead() {
        return fileHead;
    }

    public void setFileHead(int fileHead) {
        this.fileHead = fileHead;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public int getBitRate() {
        return bitRate;
    }

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public static class TransParamBean {
        /**
         * display : 32
         * display_rate : 0
         */

        @SerializedName("display")
        private int display;
        @SerializedName("display_rate")
        private int displayRate;

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public int getDisplayRate() {
            return displayRate;
        }

        public void setDisplayRate(int displayRate) {
            this.displayRate = displayRate;
        }
    }
}
