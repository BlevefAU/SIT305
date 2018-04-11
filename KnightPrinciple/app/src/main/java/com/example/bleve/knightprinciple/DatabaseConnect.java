package com.example.bleve.knightprinciple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Bleve on 1/04/2018.
 */

class DatabaseConnect extends SQLiteOpenHelper {
    public DatabaseConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "playerdata.db", factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table USER
        sqLiteDatabase.execSQL("CREATE TABLE player(MERCY INTEGER, LOVEONE INTEGER, LOVETWO INTEGER, PROCESS INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST player;");
        onCreate(sqLiteDatabase);
    }

    public void start_game() {
        ContentValues cv = new ContentValues();
        cv.put("MERCY", 0);
        cv.put("LOVEONE", 0);
        cv.put("LOVETWO", 0);
        cv.put("PROCESS", 0);

        //
        this.getWritableDatabase().insertOrThrow("player", "", cv);
    }

    public int load_game(TextView textview ) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM player", null);
        if (cursor ==null) {
            textview.setVisibility(View.VISIBLE);
            return 1;
        }
        return 0;
    }

}
