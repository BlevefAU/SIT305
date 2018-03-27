package com.example.bleve.knightprinciple;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Animation topdownan = AnimationUtils.loadAnimation(this,R.animator.topdown);
        final Animation downtopan = AnimationUtils.loadAnimation(this,R.animator.downtop);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ImageView c1 = (ImageView) findViewById(R.id.c1);

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



    }
}
