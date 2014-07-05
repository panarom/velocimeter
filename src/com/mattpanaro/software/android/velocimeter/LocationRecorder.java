package com.mattpanaro.software.android.velocimeter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.text.format.Time;

public class LocationRecorder implements LocationClient
{
    private MainActivity mainActivity;

    BufferedWriter out;

    public LocationRecorder(MainActivity mainActivity) throws IOException
    {
        this.mainActivity = mainActivity;

        Time time = new Time(Time.TIMEZONE_UTC);
        time.set(System.currentTimeMillis());

        out = new BufferedWriter(
            new FileWriter(
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                         time.format("%y%m%d-%H%M%S") + ".geo"))));

    }

    public void processLocationData(LocationData locationData)
    {
        String recordString = android.text.TextUtils.join('|', locationData.values);

        try
        {
            out.write(recordString);
            out.newline();
            out.flush();
        }
        catch (IOException ioe) {}
    }
}
