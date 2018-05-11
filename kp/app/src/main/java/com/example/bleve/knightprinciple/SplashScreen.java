package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread mythread = new Thread(){
            @Override
            public void run (){
                try{
                    sleep(5000);
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                    finish();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
