package com.vroulos.mynutricion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

//    public DBManager(Context c) {
//        this.context = c;
//    }




    public DBManager(Context ctx){
        database = SQLiteDatabase.openOrCreateDatabase("nutri.db",null,null);

    }

    public void getUserWeight(){
        database = SQLiteDatabase.openOrCreateDatabase("nutri.db",null,null);
    }

//    public boolean setPerimeter(String perimeter){
//        SQLiteDatabase db = dbH.getWritableDatabase();
//
//        ContentValues valuesPerimeter = new ContentValues();
//        valuesPerimeter.put("perimeter",perimeter);
//        long val = db.insert("user_perimeter",null,valuesPerimeter);
//
//        if(val == -1){
//            Toast.makeText(context, "perimeter is not inserted", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

}
