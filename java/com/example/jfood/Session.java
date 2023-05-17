package com.example.jfood;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setuser(String uname) {
        prefs.edit().putString("user", uname).commit();
        //  prefsCommit();
    }

    public String getuser() {
        String uname = prefs.getString("user","");
        return uname;
    }

    public void setid(String id) {
        prefs.edit().putString("id", id).commit();
        //  prefsCommit();
    }

    public String getid() {
        String id = prefs.getString("id","");
        return id;
    }
    public void setcid(String cid) {
        prefs.edit().putString("cid", cid).commit();
        //  prefsCommit();
    }

    public String getcid() {
        String cid = prefs.getString("cid","");
        return cid;
    }

    public void setcat(String cat) {
        prefs.edit().putString("cat", cat).commit();
        //  prefsCommit();
    }

    public String getcat() {
        String cat = prefs.getString("cat","");
        return cat;
    }

}
