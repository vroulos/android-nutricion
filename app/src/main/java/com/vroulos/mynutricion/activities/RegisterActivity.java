package com.vroulos.mynutricion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vroulos.mynutricion.DatabaseHelper;
import com.vroulos.mynutricion.R;

public class RegisterActivity extends AppCompatActivity {

    Button notLoggedinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final DatabaseHelper db = new DatabaseHelper(this);

        notLoggedinButton = (Button) findViewById(R.id.notLogin);
        final EditText username =(EditText) findViewById(R.id.rgsUsername);
        final EditText password =(EditText) findViewById(R.id.rgsPassord);
        final EditText verificationPass = (EditText) findViewById(R.id.passVerification);
        final EditText email = (EditText) findViewById(R.id.emailEditext);

        Button registerButton = (Button) findViewById(R.id.Register);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String s1 = username.getText().toString();
                String s2 = password.getText().toString();
                String s4 = email.getText().toString();
                String s3 = verificationPass.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                    Log.d("tse tse ","to to");
                } else{
                    if (s2.equals(s3)) {
                        //Log.d("onoma", "s2 iso me s1");
                        Boolean chkname1 = db.chkname(s1);
                        if (chkname1){
                        Log.d("mytag", "h chkname einai true!");
                        Boolean insert = db.insert(s1,s2,s4);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"you are registered",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                            Toast.makeText(getApplicationContext(),"Το όνομα υπάρχει ήδη", Toast.LENGTH_SHORT).show();
                        }
                        }
                    }
                }

            }
        );

    }
    //if the user wants to login
    public void notLoggedin(View view) {
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
