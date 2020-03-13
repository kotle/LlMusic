package com.yizisu.music.and.video.utils;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

import com.yizisu.basemvvm.utils.ViewExtFunKt;
import com.yizisu.basemvvm.utils.permission.PermissionUtil;
import com.yizisu.music.and.roomdblibrary.DbCons;
import com.yizisu.music.and.roomdblibrary.bean.SongInfoTable;
import com.yizisu.music.and.video.R;
import com.yizisu.music.and.video.bean.LocalMusicBean;
import com.yizisu.music.and.video.bean.LocalVideoBean;

import java.util.ArrayList;
import java.util.List;

import static com.yizisu.basemvvm.MvvmLibKt.app;

public class LocalMusicUtil {
    public static List<SongInfoTable> getSongInfos(Context context) {
        if (!PermissionUtil.INSTANCE.check(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return new ArrayList<>();
        }
        List<LocalMusicBean> musicInfo = getMusicInfo(context);
        ArrayList<SongInfoTable> songs = new ArrayList<>();
        if (musicInfo == null || musicInfo.size() == 0) {
            return songs;
        }
        for (LocalMusicBean bean : musicInfo) {
            SongInfoTable table = new SongInfoTable();
            table.setName(bean.title);
            table.setDes(bean.singer);
            table.setId(bean.id);
            table.setType(DbCons.TYPE_FREE);
            table.setSource(DbCons.SOURCE_LOCAL);
            table.setPlayFilePath(bean.path);
            table.setDuration((long) bean.duration);
            songs.add(table);
        }
        return songs;
    }

    public static List<LocalMusicBean> getMusicInfo(Context context) {
        List<LocalMusicBean> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                , null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    LocalMusicBean song = new LocalMusicBean();
                    song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    if (song.title == null) {
                        song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    }
                    song.id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    song.album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
//                    song.height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.HEIGHT));
//                    song.width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.WIDTH));
//                把歌曲名字和歌手切割开
                    if (song.size > 1000 * 800) {
//                    if (song.title.contains("-")) {
//                        String[] str = song.title.split("-");
//                        song.singer = str[0];
//                        song.title = str[1];
//                    }
                        list.add(song);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        List<LocalMusicBean> newList = new ArrayList<>();
        for (int index = list.size() - 1; index >= 0; index--) {
            newList.add(list.get(index));
        }
        return newList;
    }

    public static List<LocalMusicBean> getVideoInfo(Context context) {
        List<LocalMusicBean> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                , null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    LocalVideoBean song = new LocalVideoBean();
                    song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    if (song.title == null) {
                        song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    }
                    song.id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                    song.album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                    song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                    song.height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT));
                    song.width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH));
                    song.sourceType = LocalMusicBean.SOURCE_TYPE_LOCAL;
//                把歌曲名字和歌手切割开
                    if (song.size > 1000 * 800) {
                        list.add(song);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        List<LocalMusicBean> newList = new ArrayList<>();
        for (int index = list.size() - 1; index >= 0; index--) {
            newList.add(list.get(index));
        }
        return newList;
    }

    /**
     * 获取图片
     *
     * @param mediaUri
     * @return
     */

    public static Bitmap loadingMusicCover(String mediaUri) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(mediaUri);
            byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();
            if (picture == null) {
                return defaultBitmap();
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
            mediaMetadataRetriever.release();
            if (bitmap == null) {
                return defaultBitmap();
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultBitmap();
        }
    }

    /**
     * 获取视频封面
     *
     * @param mediaUri
     * @return
     */
    public static Bitmap loadingVideoCover(String mediaUri) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(mediaUri);
            Bitmap picture = mediaMetadataRetriever.getFrameAtTime();
            if (picture == null) {
                return defaultBitmap();
            }
            return picture;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultBitmap();
        }
    }

    private static Bitmap defaultBitmap() {
        BitmapDrawable drawable = ((BitmapDrawable) ViewExtFunKt.getResDrawable(app, R.drawable.default_cover_icon));
        if (drawable != null) {
            return drawable.getBitmap();
        }
        return null;
    }
}
