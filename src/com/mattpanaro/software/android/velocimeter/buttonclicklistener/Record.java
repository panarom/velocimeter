package com.mattpanaro.software.android.velocimeter.buttonclicklistener;

import android.view.View;

import com.mattpanaro.software.android.velocimeter.ButtonClickListener;
import com.mattpanaro.software.android.velocimeter.LocationRecorder;
import com.mattpanaro.software.android.velocimeter.MainActivity;

public class Record extends ButtonClickListener
{
    public void onClick(View v)
    {
        MainActivity mainActivity = this.getMainActivity();

        LocationRecorder recorder = new LocationRecorder(mainActivity);

        mainActivity.getLocationService().addLocationClient(recorder);
    }
}
