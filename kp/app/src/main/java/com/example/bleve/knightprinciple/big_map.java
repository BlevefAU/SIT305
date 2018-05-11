package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class big_map extends AppCompatActivity {
    DatabaseConnect connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_map);

        final Button btn_m1 = (Button) findViewById(R.id.p1);
        final Button btn_m2 = (Button) findViewById(R.id.p2);
        final Button btn_m3 = (Button) findViewById(R.id.p3);

        final Button btn_m5 = (Button) findViewById(R.id.p5);
        final Button btn_m6 = (Button) findViewById(R.id.p6);
        final Button btn_m7 = (Button) findViewById(R.id.p7);
        final Button btn_m8 = (Button) findViewById(R.id.p8);
        final Button btn_m9 = (Button) findViewById(R.id.p9);

        final TextView tx1 = (TextView)findViewById(R.id.pt1);
        final TextView tx2 = (TextView)findViewById(R.id.pt2);
        final TextView tx3 = (TextView)findViewById(R.id.pt3);
        final TextView tx5 = (TextView)findViewById(R.id.pt5);
        final TextView tx6 = (TextView)findViewById(R.id.pt6);
        final TextView tx7 = (TextView)findViewById(R.id.pt7);
        final TextView tx8 = (TextView)findViewById(R.id.pt8);
        final TextView tx9 = (TextView)findViewById(R.id.pt9);

        connectionClass = new DatabaseConnect(this,"",null,1);

        String res2 = connectionClass.load_item();

        Cursor res = connectionClass.load_process();
        res.moveToFirst();
        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 1) {

            btn_m3.setVisibility(View.VISIBLE);
            tx3.setVisibility(View.VISIBLE);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 6) {

            btn_m5.setVisibility(View.VISIBLE);
            tx5.setVisibility(View.VISIBLE);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 10) {

            btn_m6.setVisibility(View.VISIBLE);
            tx6.setVisibility(View.VISIBLE);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 19) {

            btn_m9.setVisibility(View.VISIBLE);
            tx9.setVisibility(View.VISIBLE);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 21) {

            btn_m8.setVisibility(View.VISIBLE);
            tx8.setVisibility(View.VISIBLE);
        }

        if(res2.contains("3")== true){
            btn_m2.setVisibility(View.VISIBLE);
            tx2.setVisibility(View.VISIBLE);
        }
        if(res2.contains("7")== true){
            btn_m7.setVisibility(View.VISIBLE);
            tx7.setVisibility(View.VISIBLE);
        }
        btn_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, first_map.class));
            }
        });
        btn_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, mystery.class));
            }
        });
        btn_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, civi.class));
            }
        });
        btn_m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, wood.class));
            }
        });

        btn_m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, Dive.class));
            }
        });
        btn_m9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, livier.class));
            }
        });
        btn_m7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, elem.class));
            }
        });
        btn_m8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(big_map.this, atlas.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
