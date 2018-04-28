package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class civi extends AppCompatActivity {
    DatabaseConnect connectionClass;
    int i=0;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data = {
            "Tedy:\nHey, you looks like you are new here.",
            "Yes, I... I lose my memorize.",
            "Tedy:\nI see, so what happen before you lose your memorize?",
            "I do not know. When I open my eye, I was picked up by a old man from the village. He said he find me in the river.",
            "Tedy:\nOld man in the village?",
            "Em, he give me this necklace and said let me find his friend in this city.",
            "Tedy:\nI guess I am the one you are looking for. The old man you called is Faily.",
            "So, you are Tedy?",
            "Tedy:\nYes I am. But I am currently working now and do not have time to go with you.",
            "Anything I can help?",
            "Tedy:\nEm... Look, there is a bucket. Can you help me get the water from the pump?",
            "Sure."
    };

    String [] text_data2= {
            "Tedy:\n Ah, you back, how is the water bucket?",
            "Sorry, I do not find out it is too old to get too much water.",
            "Tedy:\n ...em, alright, that is fine, I am plan to change it also. But anyway, I am done of the job.",
            "So, now you are free?",
            "Tedy:\n Yes. I will take you go through this city now.",
            "Thank you Tedy.",
            "(You follow with Tedy and have a look with the city)",
            "...",
            "Tedy:\n Just outside the fruit shop, there is a lady, she like playing dice.",
            "Tedy:\n Maybe you should have one game with here if you get time.",
            "Tedy:\n Oh, have you heared the king of darkness?",
            "I guess, no...(you feel something just flash in mind)em..",
            "Tedy:\n Are you ok?",
            "...Yeah, yeah, I am fine.",
            "Tedy:\nYou should have a rest. Come to my house in the wood.",
            "Ok, thanks."
    };


    String [] text_data3= {
            "Tedy:\n Ah, you back, how is the water bucket?",
            "Yes, I get the water, here..",
            "Tedy:\n Great, you are stronger than I thought.",
            "So, now you are free?",
            "Tedy:\n Yes. I will take you go through this city now.",
            "Thank you Tedy.",
            "(You follow with Tedy and have a look with the city)",
            "...",
            "Tedy:\n Just outside the fruit shop, there is a lady, she like playing dice. ",
            "Tedy:\n Maybe you should have one game with here if you get time.",
            "Tedy:\n Oh, have you heared the king of darkness?",
            "I guess, no...(you feel something just flash in mind)em..",
            "Tedy:\n Are you ok?",
            "...Yeah, yeah, I am fine.",
            "Tedy:\nYou should have a rest. Come to my house in the wood.",
            "Ok, thanks."
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civi);

        final TextView text_show = (TextView)findViewById(R.id.text);

        connectionClass = new DatabaseConnect(this,"",null,1);
        Button btn_menu = (Button) findViewById(R.id.menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi.this, menu.class));
            }
        });
        Button btn_house = (Button) findViewById(R.id.home);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi.this, home.class));
            }
        });

        Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi.this, big_map.class));
            }
        });

        final Button btn_right = (Button) findViewById(R.id.turn_right);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi.this, big_map.class));
            }
        });



        Button btn_npc = (Button) findViewById(R.id.npc_two);

        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 1 || Integer.parseInt(String.valueOf(res.getString(0))) == 2) {
                    connectionClass.update_process("3");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
                else if (Integer.parseInt(String.valueOf(res.getString(0))) == 4) {
                    connectionClass.update_process("6");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else if (Integer.parseInt(String.valueOf(res.getString(0))) == 5) {
                    int number;
                    number = connectionClass.load_loveone();
                    number ++;
                    connectionClass.add_loveone(number);
                    connectionClass.update_process("6");
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

            // Runnable method second one
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

            // Runnable method second one
            final Runnable myRunnable3 = new Runnable() {
                public void run() {
                    text_show.setText(text_data3[i -1]); // update text
                }
            };

            // update_text method related to a Runnable
            private void update_text3() {
                if(i < text_data3.length) {
                    i++;
                    myHandler.post(myRunnable3); // relate this to a Runnable
                } else {
                    myTimer.cancel(); // stop the timer
                    return;
                }
            }
        });

        Button btn_water = (Button) findViewById(R.id.use_item);
        btn_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 3) {
                    startActivity(new Intent(civi.this, civi_water.class));
                }
            }
        });

    }
}
