package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    int i;
    ImageView transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.levelList);
        transition= findViewById(R.id.transitionDraw);

        int delay = 1000;
        int period = 1000;

        Timer timer = new Timer();
        final Handler handler = new Handler() {
            @SuppressLint("HandlerLeak")
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    if (i < 4) {
                        i++;
                        imageView.setImageLevel(i);
                    } else {
                        i = 0;
                        imageView.setImageLevel(i);
                    }
                }
            }
        };

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, delay, period);
    }

    public void press(View view){
        Drawable drawable = transition.getDrawable();
        if(drawable instanceof TransitionDrawable){
            ((TransitionDrawable) drawable).startTransition(500);
        }

    }

    public void clipDraw(View view){
        ImageView clip = findViewById(R.id.clipDraw);
        Drawable drawable = clip.getDrawable();
        if(drawable instanceof ClipDrawable){
            ((ClipDrawable) drawable).setLevel(drawable.getLevel()+1000);
        }
    }

}
