package com.example.bleve.knightprinciple;

import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Cursor res = connectionClass.load_process();
        res.moveToFirst();

        if (Integer.parseInt(String.valueOf(res.getString(0))) == 0 || Integer.parseInt(String.valueOf(res.getString(0))) == 1 || Integer.parseInt(String.valueOf(res.getString(0))) == 2) {
            quest.setText(process);
        }
    }
}
