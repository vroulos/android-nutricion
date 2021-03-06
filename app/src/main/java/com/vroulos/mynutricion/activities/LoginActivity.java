package com.vroulos.mynutricion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.vroulos.mynutricion.DatabaseHelper;
import com.vroulos.mynutricion.R;
import com.vroulos.mynutricion.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity {

    Button registerButton;
    Button loginButton;
    EditText nameLogin;
    EditText passLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Stetho.initializeWithDefaults(this);

        final DatabaseHelper db = new DatabaseHelper(this);

        registerButton = (Button) findViewById(R.id.not_registered);
        loginButton = (Button) findViewById(R.id.login);
        nameLogin = (EditText) findViewById(R.id.username);
        passLogin = (EditText) findViewById(R.id.password);





        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name = nameLogin.getText().toString();
                String password = passLogin.getText().toString();

                //if the the users has put the right name and password ,the function returns true
                //the db is the current database object
                if (name.length() >0 && password.length()>0){
                    Boolean checkuser = db.checkUserLogin(name,password);
                    if (checkuser){
                        SaveSharedPreference.setUserName(LoginActivity.this, name);
                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity1.class);
                        startActivity(homeIntent);
                        Toast.makeText(getApplicationContext(),"Successful login", Toast.LENGTH_SHORT).show();


                    }
                }else {
                    Toast.makeText(LoginActivity.this, "small username", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    //if the user wants to register from login activity / goes to RegisterActivity
    public void notRegistered(View view) {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
