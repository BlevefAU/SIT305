package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
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
    }
}
