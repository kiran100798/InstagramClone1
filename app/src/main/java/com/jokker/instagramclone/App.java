package com.jokker.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("iG6ZkoWEgVgLWhetuMWRehubip0vFKudI8fyK1B6")
                // if defined
                .clientKey("PvY2BqZEnBv4mfer6wwJuRnqIu3PjA4VSMTIgUtW")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
