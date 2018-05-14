package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    DatabaseConnect connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        connectionClass = new DatabaseConnect(this,"",null,1);

        final TextView info = (TextView) findViewById(R.id.info);

        // play bgm
        startService(new Intent(this, bgm.class));

        //Intialization Button
        Button startbtn = (Button) findViewById(R.id.start);
        // when start a new game jump to show up background story
        startbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                connectionClass.setup();
                finish();
                startActivity(new Intent(MainActivity.this, bg_story.class));
            }
        });


        Button loadbtn = (Button) findViewById(R.id.load);
        loadbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                int num;
                num = connectionClass.load();
                if (num == 0) {
                    finish();
                    startActivity(new Intent(MainActivity.this, first_map.class));
                } else {
                    info.setVisibility(View.VISIBLE);
                }
            }
        });

        Button btn_exit = (Button) findViewById(R.id.exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
        stopService(new Intent(getApplicationContext(), bgm.class));

                System.exit(0);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
