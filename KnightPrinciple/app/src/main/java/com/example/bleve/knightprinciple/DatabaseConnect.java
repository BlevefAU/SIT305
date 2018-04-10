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
        sqLiteDatabase.execSQL("CREATE TABLE player(LEVEL INTEGER, EXPERIENCES INTEGER, HEALTH INTEGER, MAGIC INTEGER, DEXTERITY INTEGER, LUCK INTEGER, ATTACK INTEGER, CIRT_HIT INTEGER, MAGIC_DAMAGE INTEGER, MERCY INTEGER, LOVEONE INTEGER, LOVETWO INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST player;");
        onCreate(sqLiteDatabase);
    }

    public void start_game() {
        ContentValues cv = new ContentValues();
        cv.put("LEVEL", 1);
        cv.put("EXPERIENCES", 0);
        cv.put("HEALTH", 0);
        cv.put("MAGIC", 0);
        cv.put("DEXTERITY", 0);
        cv.put("LUCK", 0);
        cv.put("ATTACK", 0);
        cv.put("CIRT_HIT", 0);
        cv.put("MAGIC_DAMAGE", 0);
        cv.put("MERCY", 0);
        cv.put("LOVEONE", 0);
        cv.put("LOVETWO", 0);

        //
        this.getWritableDatabase().insertOrThrow("player", "", cv);
    }

}
