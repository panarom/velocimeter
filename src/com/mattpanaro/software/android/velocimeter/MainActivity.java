package com.mattpanaro.software.android.velocimeter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private LocationListener locationListener;
    private TextView[] tiles = new TextView[TileProperties.TILE_COUNT];

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        setTiles();

        locationListener = new LocationListener();
        locationListener.onCreate(this, savedInstanceState);

        this.getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void updateViews(String[] values)
    {
        for(int i = 0; i < TileProperties.TILE_COUNT; i++) tiles[i].setText(values[i]);
    }

    protected void onStart()
    {
        super.onStart();
        locationListener.onStart();
    }

    protected void onStop()
    {
        locationListener.onStop();
        super.onStop();
    }

    private void setTiles()
    {
        android.content.res.Resources res = getResources();

        int id;
        for(int i = 0; i < TileProperties.TILE_COUNT; i++)
        {
            id = res.getIdentifier(TileProperties.TILE_NAMES[i], "id", getPackageName());

            tiles[i] = (TextView) findViewById(id);
        }
    }
}
