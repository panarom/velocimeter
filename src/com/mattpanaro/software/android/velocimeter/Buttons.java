package com.mattpanaro.software.android.velocimeter;

import android.view.View;
import android.widget.Button;

import com.mattpanaro.software.android.velocimeter.buttonclicklistener.*;

public class Buttons {
    private static int BUTTON_COUNT = 3;
    private static String[] BUTTON_NAMES = {"quit_button", "reset_button", "record_button"};

    private static ButtonClickListener[] CLICK_LISTENERS =
    {new Quit(), new Reset(), new Record()};

    public void setup(MainActivity mainActivity)
    {
        int[] ids = mainActivity.getViewIds(BUTTON_NAMES);

        ButtonClickListener listener;
        for(int i = 0; i < BUTTON_COUNT; i++)
        {
            listener = CLICK_LISTENERS[i];
            listener.setMainActivity(mainActivity);

            ((Button)mainActivity.findViewById(ids[i])).setOnClickListener(listener);
        }
    }
}
