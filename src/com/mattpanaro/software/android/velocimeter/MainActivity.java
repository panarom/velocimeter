package com.mattpanaro.software.android.velocimeter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationClient
{
    private LocationService locationService;

    private TextView[] tiles = new TextView[TileProperties.TILE_COUNT];

    public LocationService getLocationService()
    {
        return this.locationService;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, LocationService.class));

        setContentView(R.layout.main);
        setTiles();

        (new Buttons()).setup(this);

        this.getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void onStart()
    {
        super.onStart();

        bindService(new Intent(this, LocationService.class),
                    serviceConnection,
                    android.content.Context.BIND_AUTO_CREATE);
    }
    public void onStop()
    {
        super.onStop();

        unbindService(serviceConnection);
    }

    //LocationClient interface
    public void processLocationData(LocationData locationData)
    {
        for(int i = 0; i < TileProperties.TILE_COUNT; i++) tiles[i].setText(locationData.values[i]);
    }

    public int[] getViewIds(String[] names)
    {
        int length = names.length;

        int[] ids = new int[length];

        android.content.res.Resources res = getResources();

        for(int i = 0; i < length; i++) ids[i] = res.getIdentifier(names[i], "id", getPackageName());

        return ids;
    }

    private void setTiles()
    {
        int[] ids = getViewIds(TileProperties.TILE_NAMES);

        for(int i = 0; i < TileProperties.TILE_COUNT; i++) tiles[i] = (TextView) findViewById(ids[i]);
    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, android.os.IBinder service)
        {
            LocationService.LocationBinder locationBinder = (LocationService.LocationBinder) service;
            locationService = locationBinder.getService();
            locationService.addLocationClient(MainActivity.this);
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            locationService.removeLocationClient(MainActivity.this);
        }
    };
}
