package com.news.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rohit on 15/9/16.
 */
public class Prefs {
    private static final String PREF_USERID = "userId";

    static SharedPreferences getPrefs(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setUserId(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_USERID, s).commit();
    }

    public static String getUserId(Context ctx) {
        return getPrefs(ctx).getString(PREF_USERID, "");
    }


}
