package com.example.bleve.knightprinciple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by Bleve on 1/04/2018.
 */

class DatabaseConnect extends SQLiteOpenHelper {
    public DatabaseConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "userdata.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table USER
        sqLiteDatabase.execSQL("CREATE TABLE USER( USERNAME INTEGER PRIMARY KEY AUTOINCREMENT, PASSWORD TEXT, CONTEXT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST USER;");
        onCreate(sqLiteDatabase);
    }


}
