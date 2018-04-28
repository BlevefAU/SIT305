package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class first_map extends AppCompatActivity {
    // define value
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int i=0;
    String [] text_data = {
            "Old man:\nHey young lad, you finally wake up. I picked up you just near the river, what happen to you?",
            "I...I can't remember anything about myself, not even my name...I think I lose my memorize.",
            "Old man:\nWell, I am sorry to hear that. Maybe you should go to the Civi to see if you can find anything that can help",
            "Old man:\n Here, take it and show to a friend of mine called Tedy in Civi, she can help you.",
            "Thank you, I won't forget that. (You received the Ruby Necklace)"
    };
    DatabaseConnect connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_map);

        // claim the component
        final Button btn_npc = (Button) findViewById(R.id.npc_one);
        final Button btn_item = (Button) findViewById(R.id.map_item);
        final TextView text_show = (TextView)findViewById(R.id.text);

        //database
        connectionClass = new DatabaseConnect(this,"",null,1);

        Button btn_menu = (Button) findViewById(R.id.menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(first_map.this, menu.class));
            }
        });


        Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(first_map.this, big_map.class));
            }
        });

        Button btn_house = (Button) findViewById(R.id.home);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(first_map.this, home.class));
            }
        });

        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 0) {
                    connectionClass.update_process("1");

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

        btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 1) {
                    connectionClass.update_process("2");
                    connectionClass.add_item("1");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            text_show.setText("The old man said I was find in this river. But why am I here? ...Wait, there is something in the river! (You received the mystery ring)");
                        }
                    }, 500);
                }
            }
        });
    }
}
