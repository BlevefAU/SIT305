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

public class wood extends AppCompatActivity {
    DatabaseConnect connectionClass;
    int i=0;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    String [] text_data = {
            "Tedy:\nCome on man, btw, I haven't ask your name.",
            "Sorry, I do not know my name, I lose my memorise.",
            "Tedy:\nOh, ok, any nick name for you now?",
            "Em.. maybe you just call me Kata.",
            "Tedy:\nAlright Kata, I guess you want your memorise back.",
            "Sure.",
            "Tedy:\nMaybe you should go to the mystery place.",
            "Where is it?",
            "Tedy:\nA mystery place in the wood.It is said it can help get your memorise back.",
            "How can I enter?",
            "Tedy:\nGo hunting so you can proof your brave to the god of that place.",
            "Alright, I will try."
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood);


        final TextView text_show = (TextView)findViewById(R.id.text);
        connectionClass = new DatabaseConnect(this,"",null,1);
        Button btn_npc = (Button) findViewById(R.id.npc);

        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 6 || Integer.parseInt(String.valueOf(res.getString(0))) == 8) {
                    connectionClass.update_process("9");
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

        Button btn_hunt = (Button) findViewById(R.id.hunt);

        btn_hunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(wood.this, hunt.class));
            }
        });

        final Button btn_home = (Button) findViewById(R.id.home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(wood.this, home.class));
            }
        });

        final Button btn_menu = (Button) findViewById(R.id.menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(wood.this, menu.class));
            }
        });


        final Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(wood.this, big_map.class));
            }
        });
    }
}
