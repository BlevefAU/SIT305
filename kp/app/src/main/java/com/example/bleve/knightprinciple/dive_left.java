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

public class dive_left extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data = {
            "Hi, do you need any help?",
            "???:\n oh, you want to help me?",
            "Yeah.",
            "???:\n Actually, my book was stuck in the tree I can not get it.",
            "How did book stuck in there..",
            "???:\n Many reason. Anyway, can you help me?",
            "No problem."
    };
    String [] text_data2 = {
            "Sorry, I broke the book.",
            "???:\n .. That is fine, just give me the book.",
            "Here you are.",
            "???:\n Thanks. You are not helping me for free right?",
            "I need to enter the palace to meet the king.",
            "Hve:\n Alright, take this to the soldier. Tell him, it is Hve.",
            "Thanks."
    };

    String [] text_data3 = {
            "Here you are..",
            "Tedy:\n We get it by pole. Smart isn't it?",
            "???:\n Thanks. You are not helping me for free right?",
            "I need to enter the palace to meet the king.",
            "Hve:\n Alright, take this to the soldier. Tell him, it is Hve.",
            "Thanks."
    };
    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive_left);

        final Button btn_right = (Button) findViewById(R.id.right);
        final Button btn_npc =  (Button) findViewById(R.id.npc);
        final Button btn_tree = (Button) findViewById(R.id.tree);
        final TextView text_show = (TextView)findViewById(R.id.text);
        connectionClass = new DatabaseConnect(this,"",null,1);


        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dive_left.this, Dive.class));
            }
        });

        btn_tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dive_left.this, dive_tree.class));
            }
        });


        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 11) {
                    connectionClass.update_process("12");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else if (Integer.parseInt(String.valueOf(res.getString(0))) == 13){
                    connectionClass.update_process("16");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else if (Integer.parseInt(String.valueOf(res.getString(0))) == 15){
                    connectionClass.update_process("16");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text3(); // text update method
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
                    text_show.setText(text_data[i -1]); // update text
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


            // Runnable method
            final Runnable myRunnable3 = new Runnable() {
                public void run() {
                    text_show.setText(text_data3[i -1]); // update text
                }
            };


            // update_text method related to a Runnable
            private void update_text3() {

                if(i < text_data.length) {
                    i++;
                    // text_data.setText(String.valueOf(i)); = avoid the RunTime error
                    myHandler.post(myRunnable3); // relate this to a Runnable
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
