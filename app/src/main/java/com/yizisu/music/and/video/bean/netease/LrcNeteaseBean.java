package com.yizisu.music.and.video.bean.netease;

import com.google.gson.annotations.SerializedName;

public class LrcNeteaseBean {

    /**
     * songStatus : -1
     * lyricVersion : 17
     * lyric : [by:Chatoyantsuga]
     * [00:00.28]멈춘 시간 속
     * [00:02.36]잠든 너를 찾아가
     * [00:05.17]아무리 막아도
     * [00:06.93]결국 너의 곁인 걸
     * [00:09.60]길고 긴 여행을 끝내
     * [00:12.34]이젠 돌아가
     * [00:14.80]너라는 집으로
     * [00:16.50]지금 다시
     * [00:17.97]way back home
     * [00:39.33]아무리 힘껏 닫아도
     * [00:41.75]다시 열린 서랍 같아
     * [00:44.36]하늘로 높이 날린 넌
     * [00:46.12]자꾸 내게 되돌아와
     * [00:49.02]힘들게 삼킨 이별도
     * [00:51.40]다 그대로인 걸
     * [00:53.48]oh oh oh
     * [00:57.46]수없이 떠난 길 위에서
     * [00:59.94]난 너를 발견하고
     * [01:02.39]비우려 했던 맘은 또
     * [01:04.62]이렇게 너로 차올라
     * [01:07.06]발걸음의 끝에
     * [01:09.14]늘 니가 부딪혀
     * [01:11.58]그만
     * [01:14.16]그만
     * [01:17.05]멈춘 시간 속
     * [01:18.82]잠든 너를 찾아가
     * [01:21.24]아무리 막아도
     * [01:23.64]결국 너의 곁인 걸
     * [01:25.70]길고 긴 여행을 끝내
     * [01:29.15]이젠 돌아가
     * [01:31.09]너라는 집으로
     * [01:33.29]지금 다시
     * [01:34.86]way back home
     * [01:55.40]조용히 잠든 방을 열어
     * [01:57.73]기억을 꺼내 들어
     * [02:00.07]부서진 시간 위에서
     * [02:02.18]선명히 너는 떠올라
     * [02:04.33]길 잃은 맘 속에
     * [02:07.20]널 가둔 채 살아
     * [02:09.24]그만
     * [02:11.67]그만
     * [02:14.72]멈춘 시간 속
     * [02:16.41]잠든 너를 찾아가
     * [02:19.08]아무리 막아도
     * [02:21.12]결국 너의 곁인 걸
     * [02:23.67]길고 긴 여행을 끝내
     * [02:26.44]이젠 돌아가
     * [02:28.37]너라는 집으로
     * [02:31.16]지금 다시
     * [02:32.36]way back home
     * [02:34.72]세상을 뒤집어
     * [02:36.86]찾으려 해
     * [02:39.05]오직 너로 완결된
     * [02:41.19]이야기를
     * [02:44.09]모든 걸 잃어도
     * [02:49.04]난 너 하나면 돼
     * [03:03.58]빛이 다 꺼진 여기
     * [03:05.26]나를 안아줘
     * [03:12.14]눈을 감으면
     * [03:14.27]소리 없이 밀려와
     * [03:16.50]이 마음 그 위로
     * [03:18.99]넌 또 한 겹 쌓여가
     * [03:21.51]내겐 그 누구도 아닌
     * [03:24.49]니가 필요해
     * [03:26.61]돌아와 내 곁에
     * [03:28.74]그날까지
     * [03:30.15]I’m not done
     * [03:31.57]
     * code : 200
     */

    @SerializedName("songStatus")
    private int songStatus;
    @SerializedName("lyricVersion")
    private String lyricVersion;
    @SerializedName("lyric")
    private String lyric;
    @SerializedName("code")
    private int code;

    public int getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(int songStatus) {
        this.songStatus = songStatus;
    }

    public String getLyricVersion() {
        return lyricVersion;
    }

    public void setLyricVersion(String lyricVersion) {
        this.lyricVersion = lyricVersion;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
