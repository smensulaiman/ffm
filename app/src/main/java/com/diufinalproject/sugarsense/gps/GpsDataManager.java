package com.diufinalproject.sugarsense.gps;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import java.util.Arrays;
import java.util.List;

public class GpsDataManager {

    private static final String PREF_NAME = "data";

    private static final String KEY_LAST_GPS_DATA  = "KEY_LAST_GPS_DATA";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static Location getKeyLastGpsData(Context context) {
        String data = getPrefs(context).getString(KEY_LAST_GPS_DATA,null);
        if (data == null){
            return null;
        }
        List<String> arr = Arrays.asList(data.split(","));
        long currentSeconds = System.currentTimeMillis() / 1000;
        long lastSeconds = Long.parseLong(arr.get(0));

        long diff = currentSeconds - lastSeconds;
        if (diff <= 30){
            String latitude  = arr.get(1);
            String longitude = arr.get(2);
            Location location = new Location("");
            location.setLatitude(Double.parseDouble(latitude));
            location.setLongitude(Double.parseDouble(longitude));
            return location;
        }
        return null;
    }

    public static void setKeyLastGpsData(Context context, Location location) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        String latitude  = "" + location.getLatitude();
        String longitude = "" + location.getLongitude();
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(KEY_LAST_GPS_DATA, currentSeconds + "," + latitude + "," + longitude);
        editor.commit();
    }
}
