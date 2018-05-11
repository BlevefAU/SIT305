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
    String [] text_data2 = {
            "Kelly:\n Looks like you are out from palace.",
            "Yes, thanks for your help.",
            "Kelly:\n So, where is the ring?",
            "Here you go.",
            "Kelly:\n How dare you really stolen the king's ring! ",
            "But.. it is what you ask..",
            "Kelly:\n I am just kidding you, lad.",
            "...",
            "Kelly:\n alright alright, keep that with you I don't want to show in wanting.",
            "Sure..",
            "Tedy:\n King said we should go to livier to seek for a Naji.",
            "Kelly:\n I knew her, she is famous at divine",
            "Wow, that is cool.",
            "Kelly:\n I am also need to head to livier.",
            "Tedy:\n Guess we can go together.",
            "Sure."
    };
    String [] text_data3 = {
            "Kelly:\n Looks like you are out from palace.",
            "Yes, thanks for your help.",
            "Kelly:\n So, where is the ring?",
            "I haven't bring it out.",
            "Kelly:\n Guess so, coward.",
            "...",
            "Kelly:\n I am just kidding you, lad.",
            "...you..",
            "Kelly:\n alright alright just a joke.",
            "Sure..",
            "Tedy:\n King said we should go to livier to seek for a Naji.",
            "Kelly:\n I knew her, she is famous at divine",
            "Wow, that is cool.",
            "Kelly:\n I am also need to head to livier.",
            "Tedy:\n Guess we can go together.",
            "Sure."
    };
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
        final Button btn_right = (Button) findViewById(R.id.right);

        final Button btn_map = (Button) findViewById(R.id.map);
        final Button btn_menu = (Button) findViewById(R.id.menu);
        final Button btn_home = (Button) findViewById(R.id.home);


        btn_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Dive.this, dive_left.class));
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Dive.this, dive_right_two.class));
            }
        });


        btn_npc_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                String res2 = connectionClass.load_item();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 10) {
                    connectionClass.update_process("11");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else if (Integer.parseInt(String.valueOf(res.getString(0))) == 18 && res2.contains("5")== true) {
                    connectionClass.update_process("19");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }else if (Integer.parseInt(String.valueOf(res.getString(0))) == 18 && res2.contains("5")== false) {
                    connectionClass.update_process("19");
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
            // Runnable method
            final Runnable myRunnable3 = new Runnable() {
                public void run() {
                    text_show.setText(text_data3[i -1]); // update text
                }
            };


            // update_text method related to a Runnable
            private void update_text3() {

                if(i < text_data3.length) {
                    i++;
                    // text_data.setText(String.valueOf(i)); = avoid the RunTime error
                    myHandler.post(myRunnable3); // relate this to a Runnable
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
