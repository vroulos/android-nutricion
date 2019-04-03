package com.vroulos.mynutricion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
    }

    public void onClickToProgram(View view) {
        Intent programIntent = new Intent(HomeActivity1.this, NutricionProgramActivity.class);
        startActivity(programIntent);
    }
}
