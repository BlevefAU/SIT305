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

public class mystery extends AppCompatActivity {
    DatabaseConnect connectionClass;
    int i=0;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data= {
           "???:\nEm..",
            "Who are you?",
            "???:\nYou are ...",
            "You know who I am?",
            "???:\nYou won't want to know.",
            "But...wait!",
            "???:\n Go to Dive meet the king.We will meet after.",
            "Strange man... (He just disappear from water.)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);

        final TextView text_show = (TextView)findViewById(R.id.text);
        connectionClass = new DatabaseConnect(this,"",null,1);

        final Button btn_npc = (Button) findViewById(R.id.npc);
        final Button btn_map = (Button) findViewById(R.id.map);
        final Button btn_menu = (Button) findViewById(R.id.menu);
        final Button btn_home = (Button) findViewById(R.id.home);


        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 9) {
                    connectionClass.update_process("10");
                    // Timer
                    TimerTask myTask = new TimerTask() {
                        public void run() {
                            update_text(); // text update method
                        }
                    };
                    myTimer.schedule(myTask,0,3000); // TimerTask, delay, period
                } else {
                    btn_npc.setVisibility(View.INVISIBLE);

                }
            }
            // Runnable method
            final Runnable myRunnable = new Runnable() {
                public void run() {
                    text_show.setText(text_data[i -1]); // update text
                    if(i == text_data.length - 1){
                        btn_npc.setVisibility(View.INVISIBLE);

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

        Button btn_map_item = (Button) findViewById(R.id.map_item);

        btn_map_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connectionClass.add_item("4");
                text_show.setText("Ah...I find something.");
            }
        });


        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mystery.this, big_map.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mystery.this, home.class));
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mystery.this, menu.class));
            }
        });
    }
}
