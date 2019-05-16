package com.vroulos.mynutricion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.util.Arrays;
import java.util.Date;


public class MyDataActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase ;
    final DatabaseHelper dbHelper = new DatabaseHelper(this);
    private static String TAG = "this id my TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DatabaseHelper db = new DatabaseHelper(this);
        //final DBManager dbManager = new DBManager(this);

        setContentView(R.layout.activity_my_data);


        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle("Προσωπικά Δεδομένα");
        }


        Button deleteButton = (Button) findViewById(R.id.button_deleteDB);
        //when the user presses the button the database is deleted
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteDb()){
                    Toast.makeText(MyDataActivity.this, "database deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        //GraphView graphToDisplayPerimeter = (GraphView) findViewById(R.id.graphwaste);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getData1());
        graph.addSeries(series);



        final EditText userWeight = (EditText) findViewById(R.id.insertionWeight);
        Button addWeightToDb = (Button) findViewById(R.id.addWeightId);
        //Button deleteTableWeight = (Button) findViewById(R.id.delete_weight);
        Button addWasteMeasureToDb = (Button) findViewById(R.id.addWasteId);
        final EditText insertPerimeterEditext = (EditText) findViewById(R.id.insertionWaste);

        //the system executes the code you write in onClick(View) after the user presses the button
        addWeightToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = userWeight.getText().toString();
                Date date = new Date();
                long currentdate  = date.getTime();
                db.insert_weight(weight, currentdate);
                userWeight.setText("");

                GraphView graph = (GraphView) findViewById(R.id.graph);

                //create LineGraphSeries object and get the getData1
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getData1());

                //Add a new series to the graph
                graph.addSeries(series);

                //Redraw GraphView after scale change
                graph.onDataChanged(false, false);
            }
        });

        //put the waste to db and display the history in chart
        addWasteMeasureToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set the text to perimeter
                String perimeter = insertPerimeterEditext.getText().toString();

                //check if perimeter inserted to database
                boolean isOk = db.setPerimeter(perimeter);

                //if the editext has characters then clear it
                if(insertPerimeterEditext.length() > 0){
                    insertPerimeterEditext.getText().clear();
                }
                if(isOk){
                    Toast.makeText(MyDataActivity.this, "the perimeter inserted", Toast.LENGTH_SHORT).show();
                }

                GraphView graphViewPerimeter = (GraphView) findViewById(R.id.graphwaste);

                LineGraphSeries<DataPoint> perimeterSeries = new LineGraphSeries(getPerimeterData());

                graphViewPerimeter.addSeries(perimeterSeries);

                //Redraw GraphView after scale change
                graphViewPerimeter.onDataChanged(false,false);

            }
        });



    }


    //fetch the data from table
    private DataPoint[] getData(){


            Toast.makeText(this, "getData() starts", Toast.LENGTH_SHORT).show();

            String[] columns = {"date_record", "weight"};
            sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.query("user_weight", columns, null, null, null, null, null);
            Toast.makeText(this, "We have the user weight", Toast.LENGTH_SHORT).show();
            DataPoint[] dp = new DataPoint[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                dp[i] = new DataPoint(cursor.getInt(0), cursor.getInt(1));

            }
            cursor.close();
            return dp;

    }

    private DataPoint[] getData1(){


        Toast.makeText(this, "getData() starts", Toast.LENGTH_SHORT).show();

        String[] columns = {"rowid", "weight"};
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("user_weight", columns, null, null, null, null, null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0), cursor.getInt(1));

        }
        return dp;

    }

    private DataPoint[] getPerimeterData(){


        Toast.makeText(this, "getPerimeterData() starts", Toast.LENGTH_SHORT).show();

        String[] columns = {"rowid", "perimeter"};
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("user_perimeter", columns, null, null, null, null, null);
        DataPoint[] dp = new DataPoint[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0), cursor.getInt(1));

        }
        return dp;

    }

    //delete the database
    public boolean deleteDb(){
        boolean isDeleted = deleteDatabase("nutri.db");
        if(!doesDatabaseExist(this,"nutri.db")){
            Log.d(TAG, "deleteDb: the database not exists");
        }
        return isDeleted;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void addWeight(View view) {

    }

    public void delete_weight(View view) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        int deleteVal = sqLiteDatabase.delete("user_weight","1", null);


        if(deleteVal == -1){
            Toast.makeText(this, "why this is happening?", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "the table data is deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void inspectDB(View view) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("user_weight",null,null,null,null,null,null);
        String[] weightValues = cursor.getColumnNames();


            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(cursor.getColumnIndex("weight")));
            }

        Integer weightcount = cursor.getCount();
        System.out.println("arr: " + Arrays.toString(weightValues));
        System.out.println("rows in table user_weight: " + weightcount);

        cursor.close(); // that's important too, otherwise you're gonna leak cursors
    }

    public void createDatabase(View view){
//        final SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("nutri.db", Context.MODE_PRIVATE, null);
//        return sqLiteDatabase;

        //where i am wanting to create the database and tables
        final DatabaseHelper db = new DatabaseHelper(this);

        // open to read and write
        db.getWritableDatabase();



        if (doesDatabaseExist(this,"nutri.db")){
            Log.d(TAG, "createDatabase: The database exists");

            Log.d(TAG, "the path of nutri.db  " + getPathOfMyDB(this,"nutri.db"));
        }
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    private String getPathOfMyDB(Context context, String dbPath){
        String path = context.getDatabasePath(dbPath).toString();
        return path;
    }


}
