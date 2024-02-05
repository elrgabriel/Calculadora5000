package com.example.avaliacao;

import android.app.Application;
import android.content.Context;
import android.util.Log;


public class App extends Application {
    public static Context ctx;
    public static final String TAG = "Xpto";

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
        Log.i(TAG,"App");
    }
}
