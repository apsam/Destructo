package com.samuel.destructo;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Samuel on 6/30/2015.
 */
public class DestructoApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ParseObject.registerSubclass(Message.class);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, DestructoKeys.AUTH_KEY, DestructoKeys.AUTH_SECRET);
    }
}
