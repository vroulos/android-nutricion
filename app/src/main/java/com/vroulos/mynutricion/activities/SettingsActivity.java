package com.vroulos.mynutricion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vroulos.mynutricion.R;
import com.vroulos.mynutricion.SaveSharedPreference;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //press the button to delete logout the user
        Button logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.clearUserName(SettingsActivity.this);
                Intent goToLogin = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(goToLogin);
            }
        });

    }
}
