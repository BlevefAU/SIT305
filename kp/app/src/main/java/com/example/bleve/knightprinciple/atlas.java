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

public class atlas extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int i =0;
    boolean click = false;
    String [] text_data = {
            "Siliver:\n You finally come in my lord.",
            "I guess I have not choice but come to here.",
            "Siliver:\n Sure, I am the one called you out from sleeping.",
            "Siliver:\n All of us are waiting for you to lead the darkness.",
            "Siliver:\n To destroy the human's world.",
            "Tedy:\n You demon will never success!",
            "Siliver:\n Who the hell you are talk with, little girl!",
            "(You saw the magic power is group up in his hand)",
            "Stop moving right there!Siliver!",
            "Siliver:\n Guess she is your girl am I right?",
            "You are wrong.",
            "Tedy:\n ...",
            "Siliver:\n I should know you are still not understand the situation.",
            "Siliver:\n You will be king, not matter how you think.",
            "Nope, I still have choice."
    };
    String [] text_data2 = {
            "Siliver:\n You finally come in my lord.",
            "I guess I have not choice but come to here.",
            "Siliver:\n Sure, I am the one called you out from sleeping.",
            "Siliver:\n All of us are waiting for you to lead the darkness.",
            "Siliver:\n To destroy the human's world.",
            "Tedy:\n You demon will never success!",
            "Siliver:\n Who the hell you are talk with, little girl!",
            "(You saw the magic power is group up in his hand)",
            "Stop moving right there!Siliver!",
            "Siliver:\n Guess she is your girl am I right?",
            "You are right. So I won't let your hurt her.",
            "Tedy:\n ...Kata... You really..",
            "Siliver:\n Hahaha.The loving story ? Looks it is too good to happen on you.",
            "Siliver:\n I should know you are still not understand the situation.",
            "Siliver:\n You will be king, not matter how you think.",
            "Nope, I still have choice."
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atlas);
        connectionClass = new DatabaseConnect(this,"",null,1);

        final TextView text_show = (TextView)findViewById(R.id.text);
        final Button btn_npc =  (Button) findViewById(R.id.npc);
        final Button btn_c1 =  (Button) findViewById(R.id.c1);
        final Button btn_c2 =  (Button) findViewById(R.id.c2);
        final Button btn_c3 =  (Button) findViewById(R.id.c3);
        final Button btn_c4 =  (Button) findViewById(R.id.c4);



        btn_c1.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                finish();
                startActivity(new Intent(atlas.this, ending_one.class));
            }
          });
        btn_c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(atlas.this, ending_one.class));
            }
        });
        btn_c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(atlas.this, ending_two.class));
            }
        });
        btn_c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(atlas.this, ending_three.class));
            }
        });
        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == false ){
                    int res = connectionClass.load_loveone();
                    // if love level not height enough will turn to another conversation
                    if (res >= 3) {
                        // Timer
                        TimerTask myTask = new TimerTask() {
                            public void run() {
                                update_text(); // text update method
                            }
                        };
                        myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                    } else {
                        TimerTask myTask = new TimerTask() {
                            public void run() {
                                update_text2(); // text update method
                            }
                        };
                        myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                    }
                    click = true;
                }

            }
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                    if(i == text_data.length - 1){
                        // if the user do not have all item with him. he can only go through two ending
                        String res = connectionClass.load_item();
                        if(res.contains("1") && res.contains("2") && res.contains("3") && res.contains("4") && res.contains("5") && res.contains("6") && res.contains("7") && res.contains("8")){
                            btn_c1.setVisibility(View.VISIBLE);
                            btn_c2.setVisibility(View.VISIBLE);
                            btn_c3.setVisibility(View.VISIBLE);
                            btn_c4.setVisibility(View.VISIBLE);
                        } else {
                            btn_c2.setVisibility(View.VISIBLE);
                            btn_c3.setVisibility(View.VISIBLE);
                        }

                    }
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
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
                public void run() {
                    text_show.setText(text_data2[i -1]); // update text
                }
            };
            // update_text method related to a Runnable
            private void update_text2() {

                if(i < text_data2.length) {
                    i++;
                    // text_data.setText(String.valueOf(i)); = avoid the RunTime error
                    myHandler.post(myRunnable2); // relate this to a Runnable
                } else {
                    myTimer.cancel(); // stop the timer
                    return;
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
