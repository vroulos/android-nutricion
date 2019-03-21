package com.vroulos.mynutricion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button registerButton;
    Button loginButton;
    EditText nameLogin;
    EditText passLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DatabaseHelper db = new DatabaseHelper(this);

        registerButton = (Button) findViewById(R.id.not_registered);
        loginButton = (Button) findViewById(R.id.login);
        nameLogin = (EditText) findViewById(R.id.username);
        passLogin = (EditText) findViewById(R.id.password);





        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name = nameLogin.getText().toString();
                String password = passLogin.getText().toString();

                Boolean checkuser = db.checkUserLogin(name,password);
                if (checkuser){
                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    Toast.makeText(getApplicationContext(),"Successful login", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    public void notRegistered(View view) {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
