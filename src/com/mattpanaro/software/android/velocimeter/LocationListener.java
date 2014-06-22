package com.mattpanaro.software.android.velocimeter;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;

public class LocationListener
implements
    com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks,
    com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener,
    com.google.android.gms.location.LocationListener
{
    private static final int INTERVAL = 1000; //GPS poll frequency of 1 second

    private LocationRequest locationRequest;
    private LocationClient locationClient;

    private LocationData locationData;
    private LocationService locationService;

    public LocationListener(LocationService locationService)
    {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(INTERVAL);
        locationRequest.setFastestInterval(INTERVAL);

        locationClient = new LocationClient(locationService, this, this);

        this.locationService = locationService;
    }

    public void onStart()
    {
        locationClient.connect();
    }

    public void onStop()
    {
        if (locationClient.isConnected()) locationClient.removeLocationUpdates(this);

        locationClient.disconnect();

    }

    //ConnectionCallbacks
    public void onConnected(Bundle connectionHint)
    {
        locationClient.requestLocationUpdates(locationRequest, this);
    }
    public void onDisconnected(){}

    //OnConnectionFailedListener
    public void onConnectionFailed(com.google.android.gms.common.ConnectionResult result){}

    //LocationListener
    public void onLocationChanged(Location location)
    {
        if (locationData == null) locationData = new LocationData(location);

        locationData.update(location);
        locationService.updateLocationData(locationData);
    }

    public void onProviderDisabled(String provider) {}

    public void onProviderEnabled(String provider) {}

    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
