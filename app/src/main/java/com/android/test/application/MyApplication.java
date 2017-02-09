package com.android.test.application;

import android.app.Application;

import com.android.test.database.CategoryHelper;
import com.android.test.network.RestClient;
import com.facebook.stetho.Stetho;

/**
 * Created by Manish on 9/2/17.
 */

public class MyApplication extends Application {

    private static RestClient restClient;


    public static RestClient getRestClient() {
        return restClient;
    }

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created. Implementations should be as quick as
     * possible (for example using lazy initialization of state) since the time spent in this
     * function directly impacts the performance of starting the first activity, service, or
     * receiver in a process. If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();
        restClient=new RestClient();
        Stetho.initializeWithDefaults(this);
        CategoryHelper categoryHelper=new CategoryHelper(this);
    }
}
