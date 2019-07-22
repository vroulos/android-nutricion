package com.vroulos.mynutricion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vroulos.mynutricion.models.Message;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "super name";

    public static final String DATABASE_NAME = "nutri.db";
    private static final int DATABASE_VERSION = 1;
    public static final String INPUT_TABLE_NAME = "input";
    public static final String INPUT_COLUMN_ID = "_id";
    public static final String INPUT_COLUMN_Title = "title";
    public static final String INPUT_COLUMN_Text = "text";

    //the database created when the app installed
    public DatabaseHelper(Context context) {
        super(context, "nutri.db", null, 2);

    }




    @Override
    //create the tables user and user_username
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username text primary key, email text, password text)");
        db.execSQL("create table user_weight( weight int, username text , date_record long)");
        db.execSQL("create table user_perimeter(perimeter int, username text)");
        db.execSQL("create table user_messeges(customer text, messege text , inSync int)");


        db.execSQL("CREATE TABLE " + INPUT_TABLE_NAME + "(" +
                INPUT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                INPUT_COLUMN_Title + " TEXT, " +
                INPUT_COLUMN_Text + " TEXT )"
        );
    }

//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists user_weight");
        db.execSQL("drop table if exists user_perimeter");
        db.execSQL("drop table if exists user_messeges");

        db.execSQL("DROP TABLE IF EXISTS " + INPUT_TABLE_NAME);
        onCreate(db);
    }

    //insert data to table user
    public boolean insert(String name, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long ins = db.insert("user", null, contentValues);

        if (ins == -1) {
            return false;
        } else return true;
    }


    public boolean insert_weight(String weight, long date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValuesWeight = new ContentValues();
        contentValuesWeight.put("weight", weight);
        contentValuesWeight.put("date_record", date);
        long insVal = db.insert("user_weight", null, contentValuesWeight);

        if (insVal == -1) {
            Log.d("trela tag", "insert_weight: noooo");
            return false;

        } else {
            Log.d("lela tag", "insert_weight: yea");
            return true;
        }
    }

    public int insertMessagesFromMysql(List<Message> messages){
        int z = 0;

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValuesMessage = new ContentValues();
        // elegxei ean o pinakas messages den einai adeios
        if (messages != null) {
            for (int i = 0; i < messages.size(); i++) {
                String customer = messages.get(i).getCustomer();
                String message = messages.get(i).getMessage();
                Integer isSync = messages.get(i).getIn_sync();
                contentValuesMessage.put("customer", customer);
                contentValuesMessage.put("messege", message);

                // check if the data is synchronized
                if (isSync == 0) {
                    Log.d(TAG, "insertMessagesFromMysql: the issync is zero");
                    contentValuesMessage.put("inSync", 1);
                    db.insert("user_messeges", null, contentValuesMessage);
                    z++;
                }


            }
        }else {
            
        }
//        String customer = messages.get(0).getCustomer();
//        String message = messages.get(0).getMessage();





//            if (isOk == -1) {
//                Log.d("trela tag", "insert_weight: noooo");
//                return false;
//
//            } else {
//                Log.d("lela tag", "insert_weight: yea");
//                return true;
//            }
        db.close();

        return z;



    }

    public boolean setPerimeter(String perimeter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesPerimeter = new ContentValues();
        valuesPerimeter.put("perimeter", perimeter);
        long val = db.insert("user_perimeter", null, valuesPerimeter);

        if (val == -1) {
            return false;
        }
        return true;
    }

    public int deleteAllMessages(){
        String TABLE_NAME = "user_messeges";
        SQLiteDatabase db = this.getWritableDatabase();

        int deleted_rows = db.delete(TABLE_NAME, "1", null);
        return deleted_rows;
    }

    //check the username and password to login
    public boolean checkUserLogin(String name, String password) {
        Log.d("a tag", "checkUserLogin: this is running right now");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from user where username=? and password=?", new String[]{name, password});
        Cursor cursor1 = (Cursor) db.rawQuery("select * from user", null);
//        if (cursor1.getCount() >0) {
//            Log.d("usertag", "checkUserLogin: all users are here");
//            return  true;
//        }
        if (cursor.getCount() > 0) {
            Log.d("mytag", "checkUserLogin: the user exists");

            return true;

        }
        return false;
    }

    //fetch all messeges from current user and return them as arraylist to dislplay them
    public ArrayList<String> fetchUserMessages(String name){
        Integer d = 0;
        Log.d(TAG, "fetchUserMessages: first here");
        ArrayList<String> userMessages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery("Select messege from user_messeges where customer = ?", new String[]{name});
        Cursor cursor = db.rawQuery("SELECT * FROM user_messeges", null);
        if (cursor.moveToFirst()){
            do{
                String data = cursor.getString(cursor.getColumnIndex("messege"));
                userMessages.add(data);
                d++;
            }while(cursor.moveToNext());
            Log.d(TAG, "fetchUserMessages: tro tro troou"+d);
        }
        //cursor.close();
        return userMessages;

    }


    //checking name if exists
    public Boolean chkname(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{name});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public Cursor fetch() {

        ArrayList<Long> values = new ArrayList<Long>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT date_record FROM user_weight", null);

        if (cursor.moveToFirst()) {
            do {
                // values.add(cursor.getString(cursor.getColumnIndex("date_record")));
                values.add(cursor.getLong(cursor.getColumnIndex("date_record")));
            } while (cursor.moveToNext());
        }
//
        Log.d(TAG, "fetch: the cursor is working");
//        if(cursor != null && !cursor.isClosed()){
//            cursor.close();
//        }

        //db.close();
        return cursor;
    }


    //insert the note to database
    public boolean insertPerson(String title, String text) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INPUT_COLUMN_Title, title);
        contentValues.put(INPUT_COLUMN_Text, text);
        db.insert(INPUT_TABLE_NAME, null, contentValues);
        return true;
    }


    //get all notes
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + INPUT_TABLE_NAME, null);
        return res;
    }

    //get single note
    public Cursor getPerson(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + INPUT_TABLE_NAME + " WHERE " +
                INPUT_COLUMN_ID + "=?", new String[]{id});
        return res;
    }


    //delete a note
    public void deleteSingleContact(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(INPUT_TABLE_NAME, INPUT_COLUMN_ID + "=?", new String[]{id});
//KEY_NAME is a column name
    }






}
