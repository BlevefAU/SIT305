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

public class elem extends AppCompatActivity {
    DatabaseConnect connectionClass;

    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();
    int i =0;
    String [] text_data = {
            "Tedy:\n It is a diary..",
            "I feel it is familiar",
            "Tedy:\n What? How can you know it.",
            "I guess.. it belong to me.",
            "Diary: I am dying... It is really hurt(Cant read some)",
            "Diary: I need to sleep until next time I weak up...(Cant read some)",
            "Diary: When you get this kill yourself so you are not been used.",
            "I am the king of darkness...Really.",
            "Tedy:\n Do not believe it Kata.",
            "I am not Kata, I am king Atlas. I need to head to my palace.",
            "Tedy:\n No way...",
            "I will end this now.(You get dairy)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elem);

        connectionClass = new DatabaseConnect(this,"",null,1);
        final TextView text_show = (TextView)findViewById(R.id.text);
        final Button btn_book =  (Button) findViewById(R.id.book);

        final Button btn_menu = (Button) findViewById(R.id.menu);
        final Button btn_map = (Button) findViewById(R.id.map);
        final Button btn_home = (Button) findViewById(R.id.home);


        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(elem.this, menu.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(elem.this, home.class));
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(elem.this, big_map.class));
            }
        });




        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = connectionClass.load_process();
                res.moveToFirst();
                if (Integer.parseInt(String.valueOf(res.getString(0))) == 20) {
                    connectionClass.update_process("21");
                    connectionClass.add_item("8");
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
