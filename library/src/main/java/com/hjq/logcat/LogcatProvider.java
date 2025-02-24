package com.hjq.logcat;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/Logcat
 *    time   : 2020/01/24
 *    desc   : Logcat 初始化
 */
public final class LogcatProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context != null) {
            // 初始化 Logcat 配置项
            LogcatConfig.init(context.getApplicationContext());

            Boolean notifyEntrance = LogcatUtils.getMetaBooleanData(
                    context, LogcatContract.META_DATA_LOGCAT_NOTIFY_ENTRANCE);
            Boolean windowEntrance = LogcatUtils.getMetaBooleanData(
                    context, LogcatContract.META_DATA_LOGCAT_WINDOW_ENTRANCE);
            if (notifyEntrance == null && windowEntrance == null) {
                if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                    notifyEntrance = true;
                } else {
                    windowEntrance = true;
                }
            }

            if (notifyEntrance != null && notifyEntrance) {
                if (context instanceof Application) {
                    ForegroundServiceStartTask.with((Application) context);
                }
            }

            if (windowEntrance != null && windowEntrance) {
                if (context instanceof Application) {
                    LogcatDispatcher.with((Application) context);
                } else {
                    Toast.makeText(context, R.string.logcat_launch_error, Toast.LENGTH_LONG).show();
                }
            }
        }
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}