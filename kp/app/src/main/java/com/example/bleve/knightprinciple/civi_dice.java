package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class civi_dice extends AppCompatActivity {

    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int dice_num =0;
    int check_dice;
    String [] text_data = {
            "Poli:\n Wow, you win the game.",
            "Yeah, luckly.",
            "Poli:\n Lady luck smile on you.",
            "Poli:\n Young man, take this.",
            "(You get the pocket watch)",
            "Thanks."
    };
    String [] text_data2 = {
            "Poli:\n Bad luck.",
            "Em..",
            "Poli:\n Until next time"
    };
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civi_dice);

        final Random rand = new Random();
        connectionClass = new DatabaseConnect(this,"",null,1);
        final TextView text_show = (TextView)findViewById(R.id.text);
        final TextView tv = (TextView)findViewById(R.id.tv);

        final Button btn_dice = (Button) findViewById(R.id.dice);
        btn_dice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dice_num = rand.nextInt(5) + 1;
                text_show.setText("You can now select one you guess is right");
                btn_dice.setVisibility(View.INVISIBLE);
                tv.setVisibility(View.INVISIBLE);
            };

        });



        final Button btn_one = (Button) findViewById(R.id.one);
        btn_one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                check_dice = 1;
                if (check_dice == dice_num){
                    connectionClass.add_item("2");
                    connectionClass.update_process("8");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            };
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
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


        final Button btn_two = (Button) findViewById(R.id.two);
        btn_two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                check_dice = 2;
                if (check_dice == dice_num){
                    connectionClass.add_item("2");
                    connectionClass.update_process("8");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            };
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
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


        final Button btn_three = (Button) findViewById(R.id.three);
        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_dice = 3;
                if (check_dice == dice_num){
                    connectionClass.add_item("2");
                    connectionClass.update_process("8");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            };
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
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


        final Button btn_four = (Button) findViewById(R.id.four);
        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_dice = 4;
                if (check_dice == dice_num){
                    connectionClass.add_item("2");
                    connectionClass.update_process("8");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            };
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
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

        final Button btn_five = (Button) findViewById(R.id.five);
        btn_five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                check_dice = 5;
                if (check_dice == dice_num){
                    connectionClass.add_item("2");
                    connectionClass.update_process("8");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            };
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
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

        final Button btn_six = (Button) findViewById(R.id.six);
        btn_six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                check_dice = 6;
                if (check_dice == dice_num){
                    connectionClass.add_item("2");
                    connectionClass.update_process("8");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text2(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                }
            };
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                }
            };
            // Runnable method
            final Runnable myRunnable2 = new Runnable() {
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


        final Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi_dice.this, civi_right.class));
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
