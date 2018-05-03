package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class wood extends AppCompatActivity {
    DatabaseConnect connectionClass;
    int i=0;
    final Timer myTimer = new Timer();
    final Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood);


        final TextView text_show = (TextView)findViewById(R.id.text);
        Button btn_npc = (Button) findViewById(R.id.npc);
        btn_npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }
}
