package com.example.bleve.knightprinciple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnect extends SQLiteOpenHelper {
    public DatabaseConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "playerdata.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PLAYER( ID INTEGER PRIMARY KEY AUTOINCREMENT, LOVEONE INTEGER, LOVETWO INTEGER, PROCESS TEXT, ITEM TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST PLAYER;");
        onCreate(sqLiteDatabase);
    }

    // for start game and cover old data, if there exist any old data
    public void setup(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PLAYER WHERE (ID = 0)", null);
        ContentValues cv = new ContentValues();
        cv.put("ID", 0);
        cv.put("LOVEONE", 0);
        cv.put("LOVETWO", 0);
        cv.put("PROCESS", "0");
        cv.put("ITEM", "0");
        ContentValues cv2 = new ContentValues();
        cv2.put("LOVEONE", 0);
        cv2.put("LOVETWO", 0);
        cv2.put("PROCESS", "0");
        cv2.put("ITEM", "0");
        if (!(cursor.moveToFirst())) {
            this.getWritableDatabase().insertOrThrow("PLAYER", "", cv);
        } else {
            this.getWritableDatabase().update("PLAYER", cv2, "ID =" + 0 , null);
        }
    }

    // for loading game data
    public int load() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PLAYER WHERE (ID = 0)", null);
        if (cursor.moveToFirst()) {
            // if there is data return 0
            return 0;
        } else {
            // if there is not any data return 1

            return 1;
        }
    }

    // every page will need to check process to give different function
    public Cursor load_process(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT PROCESS FROM PLAYER WHERE (ID = 0)", null);
        if (cursor.moveToFirst()) {
            return cursor;
        }
        return null;
    }

    // when something happen process change
    public void update_process(String str){
        ContentValues cv = new ContentValues();
        cv.put("PROCESS", str);
        this.getWritableDatabase().update("PLAYER", cv, "ID = 0" , null);
    }

    // when the time require some item
    public String load_item(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT ITEM FROM PLAYER WHERE (ID = 0)", null);
        if (cursor.moveToFirst()) {

            return String.valueOf(cursor.getString(0));
        }
        return null;
    }

    // when get some item
    public void add_item (String str) {
        String old_str;
        old_str = load_item();
        Log.d("test", old_str);
        ContentValues cv = new ContentValues();
        cv.put("ITEM",  str + "," + old_str);
        this.getWritableDatabase().update("PLAYER", cv, "ID = 0" , null);
    }


    // when the time require some item
    public int load_loveone(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT LOVEONE FROM PLAYER WHERE (ID = 0)", null);
        if (cursor.moveToFirst()) {
            Log.d("loveone", String.valueOf(cursor.getString(0)));
            return Integer.parseInt(String.valueOf(cursor.getString(0)));
        }
        return 0;
    }

    // when get some item
    public void add_loveone (int num) {
        ContentValues cv = new ContentValues();
        cv.put("LOVEONE", num);
        this.getWritableDatabase().update("PLAYER", cv, "ID = 0" , null);
    }


}