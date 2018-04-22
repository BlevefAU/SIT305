package com.example.bleve.knightprinciple;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Bleve on 2017/9/1.
 */
//
//public class DatabaseConnect extends SQLiteOpenHelper {
//    public static final String Userdetail = "Userdetail.db";
//    public static final String Tablename = "user_table";
//    public static final String COL_1 = "USERNAME";
//
//    public DatabaseConnect(Context context) {
//        super(context, Userdetail, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table" + Tablename + "(USERNAME INTEGER PRIMARY KEY AUTOINCREMENT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXITS" + Tablename);
//        onCreate(db);
//    }
//}
public class DatabaseConnect extends SQLiteOpenHelper{
    public DatabaseConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "playerdata.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PLAYER( ID INTEGER PRIMARY KEY AUTOINCREMENT, MERCY TEXT, LOVEONE TEXT, LOVETWO TEXT, PROCESS TEXT, ITEM TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST PLAYER;");
        onCreate(sqLiteDatabase);
    }

    public void setup(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PLAYER WHERE (ID = 0)", null);
        ContentValues cv = new ContentValues();
        cv.put("ID", 0);
        cv.put("MERCY", "0");
        cv.put("LOVEONE", "0");
        cv.put("LOVETWO", "0");
        cv.put("PROCESS", "0");
        cv.put("ITEM", "0");
        ContentValues cv2 = new ContentValues();
        cv2.put("MERCY", "0");
        cv2.put("LOVEONE", "0");
        cv2.put("LOVETWO", "0");
        cv2.put("PROCESS", "0");
        cv2.put("ITEM", "0");
        if (!(cursor.moveToFirst())) {
            this.getWritableDatabase().insertOrThrow("PLAYER", "", cv);
            Log.d("create","0");
        } else {
            this.getWritableDatabase().update("PLAYER", cv2, "ID =" + 0 , null);
            Log.d("Update","1");
        }
    }

    public int load() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PLAYER WHERE (ID = 0)", null);
        if (!(cursor.moveToFirst())) {
            return 0;
        } else {
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

}