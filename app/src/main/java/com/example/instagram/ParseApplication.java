package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("aIyfLVnn0Exlyfrg5B6wZWojrSA4fwWrQ4ngl9bd")
                .clientKey("iH9cYWJT9spNFSn0ZLkxcHXY8N00ahJAT7Bpr4R2")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
