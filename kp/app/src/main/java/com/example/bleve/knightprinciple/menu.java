package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class menu extends AppCompatActivity {
    DatabaseConnect connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //database
        connectionClass = new DatabaseConnect(this,"",null,1);

        String process = "You wake up in a unknow place. You do not have any memorize, not even your name. You decided to go out and look what happened.";

        final TextView quest = (TextView) findViewById(R.id.quest);
        final ImageView i1 = (ImageView)findViewById(R.id.i1);
        final ImageView i2 = (ImageView)findViewById(R.id.i2);
        final ImageView i3 = (ImageView)findViewById(R.id.i3);
        final ImageView i4 = (ImageView)findViewById(R.id.i4);
        final ImageView i5 = (ImageView)findViewById(R.id.i5);
        final ImageView i6 = (ImageView)findViewById(R.id.i6);
        final ImageView i7 = (ImageView)findViewById(R.id.i7);
        final ImageView i8 = (ImageView)findViewById(R.id.i8);

        Cursor res = connectionClass.load_process();
        res.moveToFirst();

        String res2 = connectionClass.load_item();

        if (Integer.parseInt(String.valueOf(res.getString(0))) == 0 || Integer.parseInt(String.valueOf(res.getString(0))) == 1 || Integer.parseInt(String.valueOf(res.getString(0))) == 2) {
            quest.setText(process);
        }

        if(res2.contains("1")== true){
            i1.setImageResource(R.drawable.map_item);
            Log.d("test",res2);
        }
        if(res2.contains("2")== true){
            i2.setImageResource(R.drawable.map_item2);
            Log.d("test",res2);
        }
        if(res2.contains("3")== true){
            i3.setImageResource(R.drawable.map_item3);
            Log.d("test",res2);
        }
        if(res2.contains("4")== true){
            i3.setImageResource(R.drawable.map_item4);
            Log.d("test",res2);
        }

        Button btn_house = (Button) findViewById(R.id.home);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, home.class));
            }
        });

        Button btn_map = (Button) findViewById(R.id.map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, big_map.class));
            }
        });

        Button btn_exit = (Button) findViewById(R.id.exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, MainActivity.class));
            }
        });
    }
}
