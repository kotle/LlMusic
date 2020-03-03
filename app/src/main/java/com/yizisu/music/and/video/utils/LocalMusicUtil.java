package com.yizisu.music.and.video.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

import androidx.appcompat.widget.ViewUtils;

import com.yizisu.basemvvm.utils.ActivityUtilsKt;
import com.yizisu.basemvvm.utils.ViewExtFunKt;
import com.yizisu.music.and.video.R;
import com.yizisu.music.and.video.bean.LocalMusicBean;

import java.util.ArrayList;
import java.util.List;

import static com.yizisu.basemvvm.MvvmLibKt.app;

public class LocalMusicUtil {

    public static List<LocalMusicBean> getMusicInfo(Context context) {
        List<LocalMusicBean> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                , null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                LocalMusicBean song = new LocalMusicBean();
                song.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                song.album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
//                把歌曲名字和歌手切割开
                if (song.size > 1000 * 800) {
                    if (song.song.contains("-")) {
                        String[] str = song.song.split("-");
                        song.singer = str[0];
                        song.song = str[1];
                    }
                    list.add(song);
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

    public static Bitmap loadingCover(String mediaUri) {
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

    private static Bitmap defaultBitmap() {
        BitmapDrawable drawable = ((BitmapDrawable) ViewExtFunKt.getResDrawable(app, R.mipmap.logo));
        if (drawable != null) {
            return drawable.getBitmap();
        }
        return null;
    }

    //    转换歌曲时间的格式
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            String tt = time / 1000 / 60 + ":0" + time / 1000 % 60;
            return tt;
        } else {
            String tt = time / 1000 / 60 + ":" + time / 1000 % 60;
            return tt;
        }
    }
}
