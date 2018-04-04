package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class home extends AppCompatActivity {

    DatabaseConnect connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        character player = new character();

        // define animation
        final Animation topdownan = AnimationUtils.loadAnimation(this,R.animator.topdown);
        final Animation downtopan = AnimationUtils.loadAnimation(this,R.animator.downtop);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // define value
        final ImageView c1 = (ImageView) findViewById(R.id.c1);
        final TextView text = (TextView) findViewById(R.id.text);

        Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, first_map.class));
            }
        });


        // animation of character
        c1.startAnimation(topdownan);
        topdownan.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                c1.startAnimation(downtopan);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        downtopan.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                c1.startAnimation(topdownan);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        text.setText("Em... Where am I?");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                text.setText("My head really hurt...I am in a house? I should go out and see if there is any people.(Leave the house by click the map icon in the top right)");
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {

    }

    // turn the visibility of textview to invisible
    public boolean onTouchEvent(MotionEvent e) {
        final TextView text = (TextView) findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);
        return false;
    }

}
