package com.yeniyoo.Core;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Created by YJLaptop on 2016-07-16.
 */


public class LocalStore {

    public static final String SP_NAME = "LocalData";
    private SharedPreferences sharedPref;

    public LocalStore(Context context){
        sharedPref = context.getSharedPreferences(SP_NAME,0);
    }

    ObjectMapper _objectMapper;
    private final ObjectMapper getObjectMapper() {
        if (_objectMapper == null) {
            _objectMapper = new ObjectMapper();
            _objectMapper.registerModule(new JodaModule());
        }
        return _objectMapper;
    }

    /*
     * Generic LocalStore methods
     */

    public int getIntValue(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    public void setIntValue(String key, int value) {
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.putInt(key, value);
        spEditor.commit();
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return sharedPref.getBoolean(key, defaultValue);
    }

    public void setBooleanValue(String key, boolean value) {
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.putBoolean(key, value);
        spEditor.commit();
    }

    public String getStringValue(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }

    public void setStringValue(String key, String value) {
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.putString(key, value);
        spEditor.commit();
    }

    /*
     *
     */
    public void setLoginToken(String loginToken){
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.putString("login_token", loginToken);
        spEditor.commit();
    }

    public void setBaseUrl(String baseUrl){
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.putString("base_url", baseUrl);
        spEditor.commit();
    }

    public String getLoginToken(){
        return sharedPref.getString("login_token", null);
    }

    public String getBaseUrl(){
        return sharedPref.getString("base_url","https://qanda.co.kr");
    }

    public void clearLoginData(){
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.remove("login_token");
        spEditor.remove("user");
        spEditor.commit();
    }


}
