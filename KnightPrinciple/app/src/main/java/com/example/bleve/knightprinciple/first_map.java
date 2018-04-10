package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            "Hey lad, you finally wake up. I picked up you just near the river, what happen to you? (Red color means NPC's words, White color means your words)",
            "I...I can't remember anything about myself, not even my name...I think I lose my memorize.",
            "Well, I am sorry to hear that. Maybe you should go to the Civi(A city name) to see if you can find anything that can help you get your memorize. Here, take it and show to a friend of mine called Cirl in Civi, she can help you.",
            "Thank you, I won't forget that. (You received the Ruby Necklace)"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_map);

        // claim the component
        final Button btn_npc = (Button) findViewById(R.id.npc_one);
        final TextView text_show = (TextView)findViewById(R.id.text);



        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Timer
                TimerTask myTask = new TimerTask() {
                    public void run() {
                        update_text(); // text update method
                    }
                };
                myTimer.schedule(myTask,0,5000); // TimerTask, delay, period
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
