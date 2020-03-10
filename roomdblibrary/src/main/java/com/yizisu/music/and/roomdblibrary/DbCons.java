package com.yizisu.music.and.roomdblibrary;

public class DbCons {
    /********************固定歌单id**********************/
    public static final long ALBUM_ID_HEART = 1;
    public static final long ALBUM_ID_CURRENT = 2;
    public static final long ALBUM_ID_LOCAL = 3;
    public static final long ALBUM_ID_RECENT = 4;
    public static final long ALBUM_ID_NORMAL = 5;
    /********************source********************/
    //手动创建的歌单
    public static final int SOURCE_DB = 1;
    public static final int SOURCE_BAIDU = 2;
    public static final int SOURCE_NETEASE = 3;
    public static final int SOURCE_LOCAL = 4;

    /*********************type*******************/
    public static final int TYPE_FREE = 0;
    public static final int TYPE_VIP = 1;
}
