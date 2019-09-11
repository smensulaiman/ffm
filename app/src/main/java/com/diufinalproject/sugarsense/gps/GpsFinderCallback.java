package com.diufinalproject.sugarsense.gps;

import android.location.Location;

public interface GpsFinderCallback {
    void didGetGps(Location location, String gpsInfo);
    void didFailedGps(String error);
}
