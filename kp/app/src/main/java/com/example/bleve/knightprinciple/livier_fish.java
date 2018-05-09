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

public class livier_fish extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int i = 0;
    int fish_num=0;
    int fish_time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livier_fish);
        connectionClass = new DatabaseConnect(this,"",null,1);
        final TextView text_show = (TextView)findViewById(R.id.show);
        final Button btn_fish =  (Button) findViewById(R.id.fish_start);
        final Button btn_up =  (Button) findViewById(R.id.up);
        final Button btn_ruby =  (Button) findViewById(R.id.ruby);
        final Button btn_right =  (Button) findViewById(R.id.right);

        final Random rand = new Random();
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(livier_fish.this, livier.class));
            }

          });

        btn_ruby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionClass.add_item("3");
            }

        });


        btn_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_fish.setVisibility(View.INVISIBLE);
                fish_time =  rand.nextInt(10) + 1;
                TimerTask myTask = new TimerTask() {
                    public void run() {
                        visible_show(); // text update method
                    }
                };
                myTimer.schedule(myTask,0,fish_time * 1000); // TimerTask, delay, period
            }
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    if (i == 2){
                        btn_up.setVisibility(View.VISIBLE);
                    }
                }
            };


            // update_text method related to a Runnable
            private void visible_show() {

                if(i <= 2) {
                    i++;
                    // text_data.setText(String.valueOf(i)); = avoid the RunTime error
                    myHandler.post(myRunnable); // relate this to a Runnable
                } else {
                    myTimer.cancel(); // stop the timer
                    return;
                }
            }

        });

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_up.setVisibility(View.INVISIBLE);
                fish_num = rand.nextInt(30);
                switch (fish_num){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        text_show.setText("You get a small pawn");
                        break;

                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        text_show.setText("A tree branch");
                        break;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        text_show.setText("You get a small fish");
                        break;
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        text_show.setText("You get a big fish");
                        break;
                    case 26:
                    case 27:
                    case 28:
                    case 30:
                        text_show.setText("You get a rubish");
                        break;
                    case 29:
                        text_show.setText("You get a mystery egg.");
                        connectionClass.add_item("6");
                        break;
                    default:
                        text_show.setText("You get nothing");
                        break;

                }
            }
        });
    }
}
