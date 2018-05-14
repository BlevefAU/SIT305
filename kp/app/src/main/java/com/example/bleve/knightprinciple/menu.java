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
        String process2 = "You meet Tedy and she is in civi, she said she will help you.";
        String process3 = "You are in the wood and go hunt to get access to mystery place.";
        String process4 = "Looking for the king who just in Dive to seek your identify.";
        String process5 = "Facing the curse of your live in elem.";
        String process6 = "Go to Atlas";


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

        if (Integer.parseInt(String.valueOf(res.getString(0))) <= 2) {
            quest.setText(process);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) > 2 && Integer.parseInt(String.valueOf(res.getString(0))) < 6 ) {
            quest.setText(process2);
        }
        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 6 && Integer.parseInt(String.valueOf(res.getString(0))) < 10 ) {
            quest.setText(process3);
        }
        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 10 && Integer.parseInt(String.valueOf(res.getString(0))) < 19 ) {
            quest.setText(process4);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 19 && Integer.parseInt(String.valueOf(res.getString(0))) < 20 ) {
            quest.setText(process5);
        }

        if (Integer.parseInt(String.valueOf(res.getString(0))) >= 20 && Integer.parseInt(String.valueOf(res.getString(0))) <= 21 ) {
            quest.setText(process6);
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
            i4.setImageResource(R.drawable.map_item4);
            Log.d("test",res2);
        }
        if(res2.contains("5")== true){
            i5.setImageResource(R.drawable.map_item6);
            Log.d("test",res2);
        }
        if(res2.contains("6")== true){
            i6.setImageResource(R.drawable.map_item8);
            Log.d("test",res2);
        }
        if(res2.contains("7")== true){
            i7.setImageResource(R.drawable.map_item5);
            Log.d("test",res2);
        }
        if(res2.contains("8")== true){
            i7.setImageResource(R.drawable.map_item7);
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
                stopService(new Intent(getApplicationContext(), bgm.class));

                startActivity(new Intent(menu.this, MainActivity.class));
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
