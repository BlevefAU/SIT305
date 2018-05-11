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

public class dive_right_two extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int i =0;
    String [] text_data = {
            "Solider:\n Hey, you there.",
            "Yes?",
            "Solider:\n You are not allow to enter the palace.",
            "Em..Hve tell me to give you this.",
            "Solider:\n Hve.. You know him?",
            "Just meet outside and I help him, he give me that.",
            "Said I should bring it to you.",
            "Solider:\n alright...ok. You are in, but do not make any mess here.",
            "Thanks"
    };
    String [] text_data2 = {
            "Solider:\n You are not allow to enter.",
            "But I really have something to talk with the king.",
            "Solider:\nGet out.",
            "(I can not just enter like that)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive_right_two);

        final Button btn_left = (Button) findViewById(R.id.left);
        final Button btn_right = (Button) findViewById(R.id.right);
        final TextView text_show = (TextView)findViewById(R.id.text);

        final Button btn_npc = (Button) findViewById(R.id.npc);

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dive_right_two.this, Dive.class));
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dive_right_two.this, dive_right_final.class));
            }
        });
        connectionClass = new DatabaseConnect(this,"",null,1);

        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 16 || Integer.parseInt(String.valueOf(res.getString(0))) == 17) {
                    connectionClass.update_process("17");
                    btn_right.setVisibility(View.VISIBLE);
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else if (Integer.parseInt(String.valueOf(res.getString(0))) < 16){
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
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
