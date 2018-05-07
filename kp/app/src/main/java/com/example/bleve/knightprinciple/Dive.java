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

public class Dive extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data = {
            "Tedy:\n Hi, you come here so quick.",
            "Yeah. By the way, do you have anything about how to enter the palace.",
            "Tedy:\n I do not know.. Sorry I can not help that.",
            "Em...",
            "???:\n hey,you guys stuck here? Need any help?",
            "Ah, yeah, we are looking for a way to enter palace.",
            "???:\n I do know some way to enter.",
            "Could you please let us know?",
            "Tedy:\n Please.",
            "???:\n Alright, but I can not help you with out any reward.",
            "...Sure, what do you want?",
            "???:\n Deal. I just need you to steal king's ring for me.",
            "WHAT?!",
            "???:\n Looks like you are not brave enough.",
            "Are you kidding me?",
            "???:\n Nope.",
            "Tedy:\n Why are you want king's ring?",
            "???:\n For private reason. Anyway, you can or not?",
            "...",
            "Tedy:\n If you promise it is not for bad use.",
            "???:\n I promise.",
            "Alright.",
            "Kelly:\n Good. I am Kelly. What are your guys name?",
            "I am... Kata.",
            "Tedy:\n Tedy.",
            "So now you can tell us what to do?",
            "Kelly:\nYeah. You just go left, there is a man, he lose something.",
            "Kelly:\nhelp him, he will bring your guys in.",
            "Ok."
    };
    String [] text_data2 = {};
    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive);

        connectionClass = new DatabaseConnect(this,"",null,1);

        final TextView text_show = (TextView)findViewById(R.id.text);
        final Button btn_npc_one =  (Button) findViewById(R.id.npc_one);
        final Button btn_npc_two =  (Button) findViewById(R.id.npc_two);
        final Button btn_left = (Button) findViewById(R.id.left);
        final Button btn_right = (Button) findViewById(R.id.left);
        final Button btn_map = (Button) findViewById(R.id.map);
        final Button btn_menu = (Button) findViewById(R.id.menu);
        final Button btn_home = (Button) findViewById(R.id.home);


        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dive.this, dive_left.class));
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dive.this, dive_right_two.class));
            }
        });


        btn_npc_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 10) {
                    connectionClass.update_process("11");
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


        btn_npc_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 14) {
                    connectionClass.update_process("15");
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
                    text_show.setText(text_data2[i -1]); // update text
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


        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dive.this, big_map.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dive.this, home.class));
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dive.this, menu.class));
            }
        });
    }
}
