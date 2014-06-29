package com.mattpanaro.software.android.velocimeter;

import android.view.View;

public abstract class ButtonClickListener implements View.OnClickListener
{
    private MainActivity mainActivity;
    public ButtonClickListener setMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
        return this;
    }
    public MainActivity getMainActivity()
    {
        return this.mainActivity;
    }
}
