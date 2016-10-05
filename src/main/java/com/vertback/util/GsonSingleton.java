package com.vertback.util;

import com.google.gson.Gson;

/**
 * Created by HW on 10/4/16.
 */
public class GsonSingleton {

    private static GsonSingleton gsonSingleton= null;

    private GsonSingleton(){

    }

    private Gson gson = new Gson();

    public static GsonSingleton getInstance(){
        if(gsonSingleton == null){
            gsonSingleton = new GsonSingleton();
        }
        return gsonSingleton;
    }

    public Gson  getGson(){
        return gson;
    }
}
