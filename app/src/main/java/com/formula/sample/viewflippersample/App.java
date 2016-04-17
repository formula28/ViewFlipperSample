package com.formula.sample.viewflippersample;

import android.app.Application;
import android.util.Log;

/**
 * Created by @formula on 2016/04/17.
 */
public class App extends Application {
    private static App mInstance;
    public static final String LOGTAG = "VIEW_FLIPPER";

    public static App getInstance()
    {
        Log.d(App.LOGTAG, mInstance.toString());
        return mInstance;
    }
    public App() {
        mInstance = this;
    }
}
