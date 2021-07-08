package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("aIyfLVnn0Exlyfrg5B6wZWojrSA4fwWrQ4ngl9bd")
                .clientKey("iH9cYWJT9spNFSn0ZLkxcHXY8N00ahJAT7Bpr4R2")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
