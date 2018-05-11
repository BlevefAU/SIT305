package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
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

        // define animation
        final Animation topdownan = AnimationUtils.loadAnimation(this,R.animator.topdown);
        final Animation downtopan = AnimationUtils.loadAnimation(this,R.animator.downtop);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // define value
        final ImageView c1 = (ImageView) findViewById(R.id.c1);
        final TextView text = (TextView) findViewById(R.id.text);

        //database
        connectionClass = new DatabaseConnect(this,"",null,1);

        Button btn_house = (Button) findViewById(R.id.home);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(home.this, first_map.class));
            }
        });


        Button btn_menu = (Button) findViewById(R.id.menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(home.this, menu.class));
            }
        });

        Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(home.this, big_map.class));
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

        Cursor res;
        res = connectionClass.load_process();
        res.moveToFirst();

        if (Integer.parseInt(String.valueOf(res.getString(0))) == 0) {
            text.setText("Em... Where am I?");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    text.setText("My head really hurt...I am in a house? I should go out and see if there is any people.(Leave by click the house icon in the top)");
                }
            }, 500);
        } else if(Integer.parseInt(String.valueOf(res.getString(0))) == 1){

        }

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


    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

}
