package com.mattpanaro.software.android.velocimeter.buttonclicklistener;

import android.view.View;

import com.mattpanaro.software.android.velocimeter.ButtonClickListener;

public class Reset extends ButtonClickListener
{
    public void onClick(View v)
    {
        this.getMainActivity().getLocationService().reset();
    }
}
