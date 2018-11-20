package com.news.app.utils;

import android.view.View;

public class GlobalUtils {

    public interface ItemClikListener {
        void onItemClick(View view, int itemType, int position);
    }

    public static final int TYPE_BOOKMARK = 1;
    public static final int TYPE_FAVOURITE = 2;

    public interface ButtonClickListener {
        void onButtonClick(View view, int position);
    }
}
