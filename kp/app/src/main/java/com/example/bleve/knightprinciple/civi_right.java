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

public class civi_right extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data = {
            "Poli:\nHey there, do you want to play a dice game?",
            "Dice game, that sound interesting.",
            "Poli:\nIf you can win me.",
            "I can get reward?",
            "Poli:\nYeah, you are clever. The reward is this pocket watches.",
            "That looks good...",
            "Poli:\nSo, you want to play?"
    };
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civi_right);

        connectionClass = new DatabaseConnect(this,"",null,1);

        final TextView text_show = (TextView)findViewById(R.id.text);
        final Button btn_play =  (Button) findViewById(R.id.play);
        final Button btn_noplay =  (Button) findViewById(R.id.notplay);
        final Button btn_left = (Button) findViewById(R.id.right);

        final Button btn_npc = (Button) findViewById(R.id.npc);
        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 6 ) {
                    connectionClass.update_process("7");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period

                } else if (Integer.parseInt(String.valueOf(res.getString(0))) == 7 ) {
                    text_show.setText("Poli:\nIf you do not want to play then just go away.");
                }

            }
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                    if (i == text_data.length) {
                        btn_play.setVisibility(View.VISIBLE);
                        btn_noplay.setVisibility(View.VISIBLE);
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
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi_right.this, civi_dice.class));
            }
        });

        btn_noplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_play.setVisibility(View.INVISIBLE);
                btn_noplay.setVisibility(View.INVISIBLE);
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(civi_right.this, civi.class));
            }
        });
    }
}
