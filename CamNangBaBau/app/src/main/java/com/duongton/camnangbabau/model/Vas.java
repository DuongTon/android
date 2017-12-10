package com.duongton.camnangbabau.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.duongton.camnangbabau.R;

/**
 * Created by Theson on 11/30/2017.
 */

public class Vas {
    public static final String SHARED_PREFERENCES_DATE = "THESON";
    public static final String SHARED_PREFERENCES_BOOLEAN = "DIALOG";
    public static final String SELECT_DATE = "DATE";
    public static final String BOOLEAN = "BOOLEAN";
    public interface ACTION {
        String MAIN_ACTION = "com.marothiatechs.customnotification.action.main";
        String INIT_ACTION = "com.marothiatechs.customnotification.action.init";
        String PREV_ACTION = "com.marothiatechs.customnotification.action.prev";
        String PLAY_ACTION = "com.marothiatechs.customnotification.action.play";
        String NEXT_ACTION = "com.marothiatechs.customnotification.action.next";
        String STARTFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.startforeground";
        String STOPFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.stopforeground";
    }
    public interface NOTIFICATION_ID{
        int FOREGROUND_SERVICE = 101;
    }
    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.icon, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }
    public static String TEXT = "Nhắc nhở lúc: ";
    public static String TYPE_TIEMCHUNG = "Tiêm chủng";
    public static String TYPE_KHAMTHAI = "Khám thai";
    public static String TYPE_KHAC = "Khác";
}
