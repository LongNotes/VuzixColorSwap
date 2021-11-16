package com.bachelorproject.vuzixcolorswap;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.vuzix.hud.actionmenu.ActionMenuActivity;

public class MainActivity extends ActionMenuActivity {
    ConstraintLayout cl;
    int counter;
    boolean isFirstBoot;
    TextView firstBootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.colorswitcher);
        counter = 0;
        cl = findViewById(R.id.csl);
        cl.setBackgroundColor(Color.RED);

        firstBootView = findViewById(R.id.colortext);

        isFirstBoot = true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN)
            switchColor();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_UP) {
            return false;
        }

        return onColorKeyDown(event);

    }

    public boolean onColorKeyDown(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER) {
switchColor();
        }
        return true;
    }


    public void switchColor() {
        if(isFirstBoot) {
            firstBootView.setVisibility(View.INVISIBLE);
            isFirstBoot = false;
        }
        switch (this.counter % 3) {
            case 0:
                counter = 0;
                cl.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                cl.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                cl.setBackgroundColor(Color.RED);
                break;
        }
        counter++;
    }
}
