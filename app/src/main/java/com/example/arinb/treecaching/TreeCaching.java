package com.example.arinb.treecaching;

import android.app.Application;
import android.content.Context;

/**
 * Created by arinb on 2016-11-08.
 */

public class TreeCaching extends Application {

    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }

}