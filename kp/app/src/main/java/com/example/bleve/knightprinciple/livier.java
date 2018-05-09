package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class livier extends AppCompatActivity {
    DatabaseConnect connectionClass;

    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data = {
          "Naji:\n ..",
            "Are you Naji?",
            "Naji:\n Yes I am.",
            "Good, I am Kata, I was heard you are able to read memorise.",
            "Naji:\n Actually I can divine something.",
            "Can you please help me get my memorise back?",
            "Naji:\n I knew you before.",
            "You knew me ?!",
            "Naji:\n You are...",
            "Naji:\n King of darkness.",
            "How can...",
            "Tedy:\n It is impossible how can he is the..",
            "Naji:\n Believe it or not.",
            "How can I be king of darkness..",
            "Tedy:\n He must be joking.",
            "Naji:\n It is because you awake, so there are so many creatures come out.",
            "Naji:\n If you do not want they to hurt more people, go and end it.",
            "I can not be ...",
            "Tedy:\n ...",
            "Naji:\n Atlas.",
            "Atlas..",
            "Naji:\n Go there and find what you really are.",
            "Where can I enter there?",
            "Naji:\n Deep inside the lake.",
            "Naji:\n There is an ruby there, get that and it will show you the way.",
            "I will do.",
            "Tedy:\n Wait me Kata..."
    };
    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livier);
        connectionClass = new DatabaseConnect(this,"",null,1);
        final Button btn_left =  (Button) findViewById(R.id.left);
        final Button btn_npc =  (Button) findViewById(R.id.npc);
        final TextView text_show = (TextView)findViewById(R.id.text);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(livier.this, livier_fish.class));
            }
        });

        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 19) {
                    connectionClass.update_process("20");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            }
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // update_text method related to a Runnable
            private void update_text() {

                if(i < text_data.length) {
                    i++;
                    // text_data.setText(String.valueOf(i)); = avoid the RunTime error
                    myHandler.post(myRunnable); // relate this to a Runnable
                } else {
                    myTimer.cancel(); // stop the timer
                    return;
                }
            }
        });
        Button btn_house = (Button) findViewById(R.id.home);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(livier.this, home.class));
            }
        });

        Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(livier.this, big_map.class));
            }
        });

        Button btn_menu = (Button) findViewById(R.id.menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(livier.this, MainActivity.class));
            }
        });

    }
}
