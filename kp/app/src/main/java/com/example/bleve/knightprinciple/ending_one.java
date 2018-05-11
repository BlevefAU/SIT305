package com.example.bleve.knightprinciple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ending_one extends AppCompatActivity {
    DatabaseConnect connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending_one);
        final Button btn_back =  (Button) findViewById(R.id.back);
        connectionClass = new DatabaseConnect(this,"",null,1);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionClass.setup();
                finish();
                startActivity(new Intent(ending_one.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
