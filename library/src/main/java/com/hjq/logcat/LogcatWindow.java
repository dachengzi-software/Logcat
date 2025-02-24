package com.hjq.logcat;

import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import com.hjq.window.EasyWindow;
import com.hjq.window.draggable.SpringBackDraggable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/Logcat
 *    time   : 2020/01/24
 *    desc   : 悬浮窗口
 */
final class LogcatWindow extends EasyWindow<LogcatWindow> implements EasyWindow.OnClickListener<View> {

    LogcatWindow(Activity activity) {
        super(activity);

        ImageView imageView = new ImageView(activity.getApplicationContext());
        imageView.setId(android.R.id.icon);
        imageView.setImageResource(R.drawable.logcat_selector_floating);
        setContentView(imageView);

        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, activity.getResources().getDisplayMetrics());
        setWidth(size);
        setHeight(size);

        setAnimStyle(android.R.style.Animation_Toast);
        setDraggable(new SpringBackDraggable());
        setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        setOnClickListener(android.R.id.icon, this);
    }

    @Override
    public void onClick(EasyWindow window, View view) {
        startActivity(new Intent(getContext(), LogcatActivity.class));
    }
}