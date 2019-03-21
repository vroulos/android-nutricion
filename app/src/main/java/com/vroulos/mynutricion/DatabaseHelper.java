package com.vroulos.mynutricion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "nutri.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //insert data to table user
    public boolean insert(String name , String password){
            SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", name);
        contentValues.put("password" , password);
        long ins = db.insert("user", null,contentValues);

        if (ins== -1) {
            return false;
        }
        else return true;
    }

    //check the username and password to login
    public boolean checkUserLogin(String name, String password){
        Log.d("a tag", "checkUserLogin: this is running right now");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from user where username=? and password=?", new String[]{name, password});
        Cursor cursor1 = (Cursor) db.rawQuery("select * from user", null);
//        if (cursor1.getCount() >0) {
//            Log.d("usertag", "checkUserLogin: all users are here");
//            return  true;
//        }
        if(cursor.getCount()>0){
            Log.d("mytag", "checkUserLogin: the user exists");

            return true;

        }
        return false;
    }
    //checking name if exists
    public Boolean chkname(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?",new String[]{name});
        if (cursor.getCount()>0){
            return false;
        }else return true;
    }
}
