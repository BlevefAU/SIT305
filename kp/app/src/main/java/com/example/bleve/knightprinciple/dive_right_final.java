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

public class dive_right_final extends AppCompatActivity {
    DatabaseConnect connectionClass;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int i =0;
    String [] text_data = {
            "King:\n Who are you?",
            "My king,I am Kata, I was been asked to talk with you.",
            "King:\n Em...Who asked you to do so?",
            "I do not know the name. But he is appear in the mystery.",
            "King:\n Old man with a gray cloak?",
            "Yes my king.",
            "King:\n I guess I know who he is.",
            "King:\n So,what brings you here?",
            "I lose my memorise...",
            "King:\n I can help you to search if there is any people missing like you.",
            "Thank you, my king.",
            "King:\n But I will takes more time, I can not promise result.",
            "Yeah...I knew.",
            "King:\n One thing you can do now is go to Livier and meet a woman called Naji",
            "Who is she?",
            "King:\n She has ability to see your deep memorise.",
            "Really!Thanks so much. I will go now looking for her.",
            "King:\n Good.",
            "(Ah, almost forget I need the ring from king)"

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive_right_final);

        final Button btn_left = (Button) findViewById(R.id.left);
        final Button btn_npc= (Button) findViewById(R.id.npc);
        final Button btn_one= (Button) findViewById(R.id.select_one);
        final Button btn_two= (Button) findViewById(R.id.select_two);

        final TextView text_show = (TextView)findViewById(R.id.text);

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dive_right_final.this, dive_right_two.class));
            }
        });

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_show.setText("(You steal the ring)");
                connectionClass.add_item("5");
            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_show.setText("(You lose chance to steal the ring)");
            }
        });
        connectionClass = new DatabaseConnect(this,"",null,1);
        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 17) {
                    connectionClass.update_process("18");
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
    }
}
