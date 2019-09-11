package com.diufinalproject.sugarsense.gps;

import android.app.Activity;
import android.location.Location;

import org.jetbrains.annotations.NotNull;

import mumayank.com.airlocationlibrary.AirLocation;

public class GpsFinder {

    private Activity activity;

    public GpsFinder(Activity activity){
        this.activity = activity;
    }

    public void getGps(final GpsFinderCallback callback) {

        Location lastLocation = GpsDataManager.getKeyLastGpsData(activity.getApplicationContext());
        if (lastLocation != null) {
            callback.didGetGps(lastLocation,"Get Last GPS within 30 Seconds");
        }else {
            new AirLocation(this.activity, true, true, new AirLocation.Callbacks() {
                @Override
                public void onSuccess(@NotNull Location location) {
                    callback.didGetGps(location,null);
                    GpsDataManager.setKeyLastGpsData(activity.getApplicationContext(), location);
                }

                @Override
                public void onFailed(@NotNull AirLocation.LocationFailedEnum locationFailedEnum) {
                    callback.didFailedGps(locationFailedEnum.toString());
                }
            });
        }
    }
}
