package com.diufinalproject.sugarsense.activities.map;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class GeocodeManager {

    private static final String PREF_NAME = "geocode-data";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isExist(Context context,String latitude,String longitude) {

        String key = latitude + "-" + longitude;
        Map<String, ?> allEntries = getPrefs(context).getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public static String getGeoCode(Context context,String latitude,String longitude) {
        String key = latitude + "-" + longitude;
        return getPrefs(context).getString(key,null);
    }

    public static void setGeoCode(Context context, String latitude,String longitude, String address) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        String key = latitude + "-" + longitude;
        editor.putString(key, address);
        editor.commit();
    }
}
