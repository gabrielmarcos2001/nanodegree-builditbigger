package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by gabrielmarcos on 1/18/16.
 */
public class JokeApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // Necessary for multi dex support
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
