package com.mattpanaro.software.android.velocimeter;

import android.location.Location;
import android.text.format.Time;

public class LocationData
{
    private Location lastLocation;

    private Location currentLocation;

    private long startTime = System.currentTimeMillis();

    private long distance = 0;

    private static final String TWO_PLACES = "%.2f";
    private static final String FOUR_PLACES = "%.4f";

    private static final double M_TO_MI = 6.214e-4;
    private static final double MS_TO_MPH = 2.236936292;

    private static final String TIME_FORMAT = "%H:%M:%S";

    private static final String[] HEADINGS =
    {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    private static final float COMPASS_OFFSET = 360 / HEADINGS.length;

    public String[] values = new String[TileProperties.TILE_COUNT];

    public LocationData(Location location)
    {
        currentLocation = location;
    }

    public void update(Location location)
    {
        lastLocation = currentLocation;
        currentLocation = location;
        distance += lastLocation.distanceTo(currentLocation);

        setElapsedTime();
        setDistanceTravelled();

        setInstantaneousSpeed();
        setAverageSpeed();

        setNumericHeading();
        setStringHeading();

        setLatitude();
        setLongitude();
    }

    private void setElapsedTime()
    {
        Time time = new Time(Time.TIMEZONE_UTC);
        time.set(currentLocation.getTime() - startTime);

        values[0] = time.format(TIME_FORMAT);
    }

    private void setDistanceTravelled()
    {
        values[1] = String.format(TWO_PLACES, M_TO_MI * distance);
    }

    private void setInstantaneousSpeed()
    {
        values[2] = String.format(TWO_PLACES, MS_TO_MPH * currentLocation.getSpeed());
    }

    private void setAverageSpeed()
    {
        long duration = (currentLocation.getTime() - startTime) / 1000; //ms -> s

        values[3] = String.format(TWO_PLACES, MS_TO_MPH * distance / duration);
    }

    private void setNumericHeading()
    {
        values[4] = (new Integer(Math.round(currentLocation.getBearing()))).toString();
    }

    private void setStringHeading()
    {
        float heading = currentLocation.getBearing();
        int index = (int)((heading / COMPASS_OFFSET) + 0.5);
        index %= 8;//mod to handle heading >= (360 - COMPASS_OFFSET/2)

        values[5] = HEADINGS[index];
    }

    private void setLatitude()
    {
        values[6] = String.format(FOUR_PLACES, currentLocation.getLatitude());
    }

    private void setLongitude()
    {
        values[7] = String.format(FOUR_PLACES, currentLocation.getLongitude());
    }
}
