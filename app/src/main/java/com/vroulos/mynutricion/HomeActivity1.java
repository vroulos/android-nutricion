package com.vroulos.mynutricion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check if the user has logged in
        if(SaveSharedPreference.getUserName(HomeActivity1.this).length() == 0)
        {
            // call Login Activity
            Intent loginIntent = new Intent(HomeActivity1.this , LoginActivity.class);
            startActivity(loginIntent);
        }
        else
        {
            // Stay at the current activity.
            setContentView(R.layout.activity_home1);
        }

    }

    public void onClickToProgram(View view) {
        Intent programIntent = new Intent(HomeActivity1.this, NutricionProgramActivity.class);
        startActivity(programIntent);
    }

    //go to MyDataActivity with Intent object
    public void viewMyNotes(View view) {
        Intent intentToNotes = new Intent(HomeActivity1.this, MyDataActivity.class);
        startActivity(intentToNotes);
    }

    public void goToSetting(View view) {
        Intent settingsIntent = new Intent(HomeActivity1.this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void goToPhotos(View view) {
        Intent goToPhotos = new Intent(HomeActivity1.this, PhotosActivity1.class);
        startActivity(goToPhotos);
    }

    public void goToNotesActivity(View view){
        Intent notesIntent = new Intent(HomeActivity1.this, NotesActivity.class);
        startActivity(notesIntent);
    }
}
