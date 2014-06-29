package com.mattpanaro.software.android.velocimeter;

import java.util.HashSet;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocationService extends android.app.Service
{
    private final IBinder binder = new LocationBinder();

    private LocationListener locationListener;

    private HashSet<LocationClient> locationClients = new HashSet<LocationClient>();

    public void onCreate()
    {
        locationListener = new LocationListener(this);
    }
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        locationListener.onStart();
        return START_STICKY;
    }
    public boolean StopService(Intent name)
    {
        locationListener.onStop();
        return true;
    }

    public void updateLocationData(LocationData locationData)
    {
        for(LocationClient client : locationClients) client.processLocationData(locationData);
    }

    public void reset()
    {
        locationListener.getLocationData().reset();
    }

    public LocationService addLocationClient(LocationClient locationClient)
    {
        locationClients.add(locationClient);
        return this;
    }
    public boolean removeLocationClient(LocationClient locationClient)
    {
        return locationClients.remove(locationClient);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocationBinder extends Binder
    {
        LocationService getService()
        {
            return LocationService.this;
        }
    }
}
