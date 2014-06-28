package com.mattpanaro.software.android.velocimeter;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Buttons {
    public void setup(MainActivity mainActivity)
    {
        ((Button)mainActivity.findViewById(R.id.quit_button))
            .setOnClickListener(new Quit(mainActivity));
    }

    public class Quit implements View.OnClickListener
    {
        private MainActivity mainActivity;

        public Quit(MainActivity mainActivity)
        {
            this.mainActivity = mainActivity;
        }

        public void onClick(View v)
        {
            mainActivity.stopService(new Intent(mainActivity, LocationService.class));

            Toast.makeText(mainActivity, "Service Stopped", Toast.LENGTH_SHORT).show();
        }
    }
}
