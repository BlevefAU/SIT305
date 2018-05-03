package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class bg_story extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // define animation
        final Animation fadeup = AnimationUtils.loadAnimation(this,R.animator.fadeup);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg_story);

        // define value
        final TextView bg_s = (TextView) findViewById(R.id.bg_story);

        // run aniamtion
        bg_s.startAnimation(fadeup);

        // 5 second wait and go to home page
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(bg_story.this, home.class));
            }
        }, 500);
    }

    // disable back arrow function
    @Override
    public void onBackPressed() {

    }
}
