package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class first_map extends AppCompatActivity {
    boolean bool_first = false;
    boolean bool_two = false;
    boolean bool_three = false;
    boolean bool_four = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_map);

        final Button btn_npc = (Button) findViewById(R.id.npc_one);
        final TextView text = (TextView)findViewById(R.id.text);
        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bool_first != true) {
                    text.setText("Hey lad, you finally wake up. I picked up you just near the river, what happen to you? (Red color means NPC's words, White color means your words)");
                    bool_first = true;
                }

            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bool_two != true && bool_first == true) {
                    text.setTextColor(Color.parseColor("#bf0019"));
                    text.setText("I...I can't remember anything about myself, not even my name...I think I lose my memorize.");
                    bool_two = true;
                }
                if (bool_three != true && bool_two == true && bool_first == true) {
                    text.setTextColor(Color.parseColor("#ffffff"));
                    text.setText("Well, I am sorry to hear that. Maybe you should go to the Atlas(A city name) to see if you can find anything that can help you get your memorize. Here, take it and show to a friend of mine called Cirl in Atlas, she can help you.");
                    bool_three = true;
                }
                if (bool_three == true && bool_two == true && bool_first == true) {
                    text.setTextColor(Color.parseColor("#bf0019"));
                    text.setText("Thank you, I won't forget that. (You received the Ruby Necklace)");
                    bool_four = true;
                }
            }
        });
    }
}
