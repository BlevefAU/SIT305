package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class hunt extends AppCompatActivity {
    DatabaseConnect connectionClass;
    int i=0;
    int score_data = 0;
    int number = 0;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunt);
        connectionClass = new DatabaseConnect(this,"",null,1);

        final TextView text_show = (TextView)findViewById(R.id.show);


        final Random rand = new Random();

        final Button btn_tiger = (Button) findViewById(R.id.tiger);
        btn_tiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_tiger.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button btn_tiger2 = (Button) findViewById(R.id.tiger2);
        btn_tiger2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_tiger2.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button btn_tiger3 = (Button) findViewById(R.id.tiger3);
        btn_tiger3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_tiger3.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button ben_eagle = (Button) findViewById(R.id.eagle);
        ben_eagle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ben_eagle.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button ben_eagle2 = (Button) findViewById(R.id.eagle2);
        ben_eagle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ben_eagle2.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });


        final Button ben_eagle3 = (Button) findViewById(R.id.eagle3);
        ben_eagle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ben_eagle3.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button btn_snake = (Button) findViewById(R.id.snake);
        btn_snake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_snake.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });
        final Button btn_snake2 = (Button) findViewById(R.id.snake2);
        btn_snake2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_snake2.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button btn_snake3 = (Button) findViewById(R.id.snake3);
        btn_snake3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_snake3.setVisibility(View.INVISIBLE);
                score_data += 10;
            }
        });

        final Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hunt.this, wood.class));
            }
        });

        final Button btn_start = (Button) findViewById(R.id.start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Cursor res = connectionClass.load_process();
//                res.moveToFirst();
//                if (Integer.parseInt(String.valueOf(res.getString(0))) == 6 || Integer.parseInt(String.valueOf(res.getString(0))) == 8 || Integer.parseInt(String.valueOf(res.getString(0))) == 7) {
//                    connectionClass.update_process("9");
//                    // Timer
//
//                }
                TimerTask myTask = new TimerTask() {
                    public void run() {
                        update_text(); // text update method
                    }
                };
                myTimer.schedule(myTask, 0, 1000); // TimerTask, delay, period
            }

            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    btn_tiger.setVisibility(View.INVISIBLE);

                    btn_tiger2.setVisibility(View.INVISIBLE);

                    btn_tiger3.setVisibility(View.INVISIBLE);

                    ben_eagle.setVisibility(View.INVISIBLE);
                    ben_eagle2.setVisibility(View.INVISIBLE);
                    ben_eagle3.setVisibility(View.INVISIBLE);


                    btn_snake.setVisibility(View.INVISIBLE);

                    btn_snake2.setVisibility(View.INVISIBLE);

                    btn_snake3.setVisibility(View.INVISIBLE);
//                   text_show.setText(text_data[i -1]); // update text

                    number = rand.nextInt(8);
                    Log.d("log", String.valueOf(number));

                    if (number == 0){
                        btn_tiger.setVisibility(View.VISIBLE);
                    } else if (number == 1){
                        btn_tiger2.setVisibility(View.VISIBLE);
                    }else if (number == 2){
                        btn_tiger3.setVisibility(View.VISIBLE);
                    }else if (number == 3){
                        ben_eagle.setVisibility(View.VISIBLE);
                    }else if (number == 4){
                        ben_eagle2.setVisibility(View.VISIBLE);
                    }else if (number == 5){
                        ben_eagle3.setVisibility(View.VISIBLE);
                    }else if (number == 6){
                        btn_snake.setVisibility(View.VISIBLE);
                    }else if (number == 7){
                        btn_snake2.setVisibility(View.VISIBLE);
                    }else if (number == 8){
                        btn_snake3.setVisibility(View.VISIBLE);
                    }
                    if (score_data > 240){
                        text_show.setVisibility(View.VISIBLE);
                        connectionClass.add_item("3");
                        int number;
                        number = connectionClass.load_loveone();
                        number ++;
                        connectionClass.add_loveone(number);
                    };
                }
            };

            // update_text method related to a Runnable
            private void update_text() {


                if (i <= 30) {
                    i++;
                    // text_data.setText(String.valueOf(i)); = avoid the RunTime error
                    myHandler.post(myRunnable); // relate this to a Runnable
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
