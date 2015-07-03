package com.samuel.destructo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Samuel on 6/30/2015.
 */
public class DestructoApplication extends Application {

    @Override
    public void onCreate(){

        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "XNHlmxnyTqPoEHAVyrZusQFDmQMuBkWzjhpoil2s", "7aKni4WIUQSPYKjVafp5Lax88nptCekY2XUmzwAe");

    }
}
