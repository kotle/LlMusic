package com.yizisu.music.and.video.bean.netease;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayListNeteaseBean {

    /**
     * result : {"subscribers":[],"subscribed":false,"creator":{"defaultAvatar":false,"province":1000000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/805YQrfgtyJzjDNCUY8pFw==/109951164742050082.jpg","accountStatus":0,"gender":1,"city":1010000,"birthday":-2209017600000,"userId":116887224,"userType":0,"nickname":"高冷的小书生","signature":"我辞江湖去，谢却当年花。","description":"","detailDescription":"","avatarImgId":109951164742050082,"backgroundImgId":109951164742043774,"backgroundUrl":"http://p1.music.126.net/UQ0danFMNw9iEDXtYFAZrA==/109951164742043774.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"109951164742043774","avatarImgIdStr":"109951164742050082","avatarImgId_str":"109951164742050082"},"artists":null,"tracks":null,"updateFrequency":null,"backgroundCoverId":0,"backgroundCoverUrl":null,"titleImage":0,"titleImageUrl":null,"englishTitle":null,"opRecommend":false,"recommendInfo":null,"ordered":false,"status":0,"highQuality":false,"createTime":1474603414382,"totalDuration":0,"coverImgUrl":"http://p2.music.126.net/OM6prM7Fg07e9oLHY2HsTQ==/18823639069522222.jpg","trackCount":157,"playCount":691,"trackNumberUpdateTime":1583162729153,"trackUpdateTime":1584147746858,"updateTime":1583162729153,"newImported":false,"anonimous":false,"coverImgId":18823639069522222,"specialType":0,"commentThreadId":"A_PL_0_470171780","privacy":0,"userId":116887224,"tags":["古风"],"description":"一曲古风，一个故事，一生江湖。","subscribedCount":1,"cloudTrackCount":0,"adType":0,"name":"若红楼梦空、宠古风不终","id":470171780,"shareCount":0,"coverImgId_str":"18823639069522222","commentCount":0}
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
         * subscribers : []
         * subscribed : false
         * creator : {"defaultAvatar":false,"province":1000000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/805YQrfgtyJzjDNCUY8pFw==/109951164742050082.jpg","accountStatus":0,"gender":1,"city":1010000,"birthday":-2209017600000,"userId":116887224,"userType":0,"nickname":"高冷的小书生","signature":"我辞江湖去，谢却当年花。","description":"","detailDescription":"","avatarImgId":109951164742050082,"backgroundImgId":109951164742043774,"backgroundUrl":"http://p1.music.126.net/UQ0danFMNw9iEDXtYFAZrA==/109951164742043774.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"109951164742043774","avatarImgIdStr":"109951164742050082","avatarImgId_str":"109951164742050082"}
         * artists : null
         * tracks : null
         * updateFrequency : null
         * backgroundCoverId : 0
         * backgroundCoverUrl : null
         * titleImage : 0
         * titleImageUrl : null
         * englishTitle : null
         * opRecommend : false
         * recommendInfo : null
         * ordered : false
         * status : 0
         * highQuality : false
         * createTime : 1474603414382
         * totalDuration : 0
         * coverImgUrl : http://p2.music.126.net/OM6prM7Fg07e9oLHY2HsTQ==/18823639069522222.jpg
         * trackCount : 157
         * playCount : 691
         * trackNumberUpdateTime : 1583162729153
         * trackUpdateTime : 1584147746858
         * updateTime : 1583162729153
         * newImported : false
         * anonimous : false
         * coverImgId : 18823639069522222
         * specialType : 0
         * commentThreadId : A_PL_0_470171780
         * privacy : 0
         * userId : 116887224
         * tags : ["古风"]
         * description : 一曲古风，一个故事，一生江湖。
         * subscribedCount : 1
         * cloudTrackCount : 0
         * adType : 0
         * name : 若红楼梦空、宠古风不终
         * id : 470171780
         * shareCount : 0
         * coverImgId_str : 18823639069522222
         * commentCount : 0
         */

        @SerializedName("subscribed")
        private boolean subscribed;
        @SerializedName("creator")
        private CreatorBean creator;
        @SerializedName("artists")
        private Object artists;
        @SerializedName("tracks")
        private List<SearchNeteaseBean.ResultBean.SongsBean> tracks;
        @SerializedName("updateFrequency")
        private Object updateFrequency;
        @SerializedName("backgroundCoverId")
        private int backgroundCoverId;
        @SerializedName("backgroundCoverUrl")
        private Object backgroundCoverUrl;
        @SerializedName("titleImage")
        private int titleImage;
        @SerializedName("titleImageUrl")
        private Object titleImageUrl;
        @SerializedName("englishTitle")
        private Object englishTitle;
        @SerializedName("opRecommend")
        private boolean opRecommend;
        @SerializedName("recommendInfo")
        private Object recommendInfo;
        @SerializedName("ordered")
        private boolean ordered;
        @SerializedName("status")
        private int status;
        @SerializedName("highQuality")
        private boolean highQuality;
        @SerializedName("createTime")
        private long createTime;
        @SerializedName("totalDuration")
        private int totalDuration;
        @SerializedName("coverImgUrl")
        private String coverImgUrl;
        @SerializedName("trackCount")
        private int trackCount;
        @SerializedName("playCount")
        private int playCount;
        @SerializedName("trackNumberUpdateTime")
        private long trackNumberUpdateTime;
        @SerializedName("trackUpdateTime")
        private long trackUpdateTime;
        @SerializedName("updateTime")
        private long updateTime;
        @SerializedName("newImported")
        private boolean newImported;
        @SerializedName("anonimous")
        private boolean anonimous;
        @SerializedName("coverImgId")
        private long coverImgId;
        @SerializedName("specialType")
        private int specialType;
        @SerializedName("commentThreadId")
        private String commentThreadId;
        @SerializedName("privacy")
        private int privacy;
        @SerializedName("userId")
        private long userId;
        @SerializedName("description")
        private String description;
        @SerializedName("subscribedCount")
        private int subscribedCount;
        @SerializedName("cloudTrackCount")
        private int cloudTrackCount;
        @SerializedName("adType")
        private int adType;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private long id;
        @SerializedName("shareCount")
        private int shareCount;
        @SerializedName("coverImgId_str")
        private String coverImgIdStr;
        @SerializedName("commentCount")
        private int commentCount;
        @SerializedName("subscribers")
        private List<?> subscribers;
        @SerializedName("tags")
        private List<String> tags;

        public boolean isSubscribed() {
            return subscribed;
        }

        public void setSubscribed(boolean subscribed) {
            this.subscribed = subscribed;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public Object getArtists() {
            return artists;
        }

        public void setArtists(Object artists) {
            this.artists = artists;
        }

        public List<SearchNeteaseBean.ResultBean.SongsBean> getTracks() {
            return tracks;
        }

        public void setTracks(List<SearchNeteaseBean.ResultBean.SongsBean> tracks) {
            this.tracks = tracks;
        }

        public Object getUpdateFrequency() {
            return updateFrequency;
        }

        public void setUpdateFrequency(Object updateFrequency) {
            this.updateFrequency = updateFrequency;
        }

        public int getBackgroundCoverId() {
            return backgroundCoverId;
        }

        public void setBackgroundCoverId(int backgroundCoverId) {
            this.backgroundCoverId = backgroundCoverId;
        }

        public Object getBackgroundCoverUrl() {
            return backgroundCoverUrl;
        }

        public void setBackgroundCoverUrl(Object backgroundCoverUrl) {
            this.backgroundCoverUrl = backgroundCoverUrl;
        }

        public int getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(int titleImage) {
            this.titleImage = titleImage;
        }

        public Object getTitleImageUrl() {
            return titleImageUrl;
        }

        public void setTitleImageUrl(Object titleImageUrl) {
            this.titleImageUrl = titleImageUrl;
        }

        public Object getEnglishTitle() {
            return englishTitle;
        }

        public void setEnglishTitle(Object englishTitle) {
            this.englishTitle = englishTitle;
        }

        public boolean isOpRecommend() {
            return opRecommend;
        }

        public void setOpRecommend(boolean opRecommend) {
            this.opRecommend = opRecommend;
        }

        public Object getRecommendInfo() {
            return recommendInfo;
        }

        public void setRecommendInfo(Object recommendInfo) {
            this.recommendInfo = recommendInfo;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isHighQuality() {
            return highQuality;
        }

        public void setHighQuality(boolean highQuality) {
            this.highQuality = highQuality;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(int totalDuration) {
            this.totalDuration = totalDuration;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public long getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public long getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(long trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isNewImported() {
            return newImported;
        }

        public void setNewImported(boolean newImported) {
            this.newImported = newImported;
        }

        public boolean isAnonimous() {
            return anonimous;
        }

        public void setAnonimous(boolean anonimous) {
            this.anonimous = anonimous;
        }

        public long getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(long coverImgId) {
            this.coverImgId = coverImgId;
        }

        public int getSpecialType() {
            return specialType;
        }

        public void setSpecialType(int specialType) {
            this.specialType = specialType;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public int getPrivacy() {
            return privacy;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(int subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public int getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(int cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

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

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public String getCoverImgIdStr() {
            return coverImgIdStr;
        }

        public void setCoverImgIdStr(String coverImgIdStr) {
            this.coverImgIdStr = coverImgIdStr;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public List<?> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<?> subscribers) {
            this.subscribers = subscribers;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public static class CreatorBean {
            /**
             * defaultAvatar : false
             * province : 1000000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/805YQrfgtyJzjDNCUY8pFw==/109951164742050082.jpg
             * accountStatus : 0
             * gender : 1
             * city : 1010000
             * birthday : -2209017600000
             * userId : 116887224
             * userType : 0
             * nickname : 高冷的小书生
             * signature : 我辞江湖去，谢却当年花。
             * description :
             * detailDescription :
             * avatarImgId : 109951164742050082
             * backgroundImgId : 109951164742043774
             * backgroundUrl : http://p1.music.126.net/UQ0danFMNw9iEDXtYFAZrA==/109951164742043774.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 0
             * vipType : 0
             * remarkName : null
             * backgroundImgIdStr : 109951164742043774
             * avatarImgIdStr : 109951164742050082
             * avatarImgId_str : 109951164742050082
             */

            @SerializedName("defaultAvatar")
            private boolean defaultAvatar;
            @SerializedName("province")
            private int province;
            @SerializedName("authStatus")
            private int authStatus;
            @SerializedName("followed")
            private boolean followed;
            @SerializedName("avatarUrl")
            private String avatarUrl;
            @SerializedName("accountStatus")
            private int accountStatus;
            @SerializedName("gender")
            private int gender;
            @SerializedName("city")
            private int city;
            @SerializedName("birthday")
            private long birthday;
            @SerializedName("userId")
            private int userId;
            @SerializedName("userType")
            private int userType;
            @SerializedName("nickname")
            private String nickname;
            @SerializedName("signature")
            private String signature;
            @SerializedName("description")
            private String description;
            @SerializedName("detailDescription")
            private String detailDescription;
            @SerializedName("avatarImgId")
            private long avatarImgId;
            @SerializedName("backgroundImgId")
            private long backgroundImgId;
            @SerializedName("backgroundUrl")
            private String backgroundUrl;
            @SerializedName("authority")
            private int authority;
            @SerializedName("mutual")
            private boolean mutual;
            @SerializedName("expertTags")
            private Object expertTags;
            @SerializedName("experts")
            private Object experts;
            @SerializedName("djStatus")
            private int djStatus;
            @SerializedName("vipType")
            private int vipType;
            @SerializedName("remarkName")
            private Object remarkName;
            @SerializedName("backgroundImgIdStr")
            private String backgroundImgIdStr;
            @SerializedName("avatarImgIdStr")
            private String avatarImgIdStr;
            @SerializedName("avatarImgId_str")
            private String avatarImgId_Str;

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(long avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public long getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(long backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public Object getExperts() {
                return experts;
            }

            public void setExperts(Object experts) {
                this.experts = experts;
            }

            public int getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(int djStatus) {
                this.djStatus = djStatus;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getAvatarImgId_Str() {
                return avatarImgId_Str;
            }

            public void setAvatarImgId_Str(String avatarImgIdStr) {
                this.avatarImgId_Str = avatarImgIdStr;
            }
        }
    }
}
