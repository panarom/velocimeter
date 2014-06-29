package com.mattpanaro.software.android.velocimeter.buttonclicklistener;

import android.content.Intent;
import android.view.View;

import com.mattpanaro.software.android.velocimeter.ButtonClickListener;
import com.mattpanaro.software.android.velocimeter.LocationService;
import com.mattpanaro.software.android.velocimeter.MainActivity;


public class Quit extends ButtonClickListener
{
    public void onClick(View v)
    {
        MainActivity mainActivity = this.getMainActivity();

        mainActivity.stopService(new Intent(mainActivity, LocationService.class));

        mainActivity.finish();
    }
}
