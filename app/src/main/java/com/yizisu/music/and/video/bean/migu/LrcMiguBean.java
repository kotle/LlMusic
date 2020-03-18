package com.yizisu.music.and.video.bean.migu;

import com.google.gson.annotations.SerializedName;

public class LrcMiguBean {

    /**
     * result : 100
     * data : [00:01.00]歌曲名 说好不哭
     [00:02.00]歌手名 周杰伦
     [00:03.00]作词：方文山
     [00:04.00]作曲：周杰伦
     [00:26.82]没有了联络
     [00:28.31]后来的生活我都是听别人说
     [00:33.04]说你怎么了
     [00:34.54]说你怎么过放不下的人是我
     [00:39.38]人多的时候
     [00:40.82]就待在角落就怕别人问起我
     [00:45.74]你们怎么了
     [00:47.14]你低着头护着我连抱怨都没有
     [00:51.83]电话开始躲
     [00:53.37]从不对我说不习惯一个人生活
     [00:58.31]离开我以后
     [00:59.71]要我好好过怕打扰想自由的我
     [01:04.59]都这个时候
     [01:05.69]你还在意着
     [01:07.63]别人是怎么怎么看我的
     [01:10.93]拼命解释着
     [01:12.12]不是我的错是你要走
     [01:15.76]眼看着你难过挽留的话却没有说
     [01:28.47]你会微笑放手说好不哭让我走
     [01:55.14]电话开始躲
     [01:56.53]从不对我说不习惯一个人生活
     [02:01.42]离开我以后
     [02:02.82]要我好好过怕打扰想自由的我
     [02:07.84]都这个时候
     [02:09.03]你还在意着别人是怎么怎么看我的
     [02:13.82]拼命解释着不是我的错是你要走
     [02:18.76]眼看着你难过挽留的话却没有说
     [02:31.48]你会微笑放手说好不哭让我走
     [02:53.60]你什么都没有却还为我的梦加油
     [03:06.02]心疼过了多久还在找理由等我

     */

    @SerializedName("result")
    private int result;
    @SerializedName("data")
    private String data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
