package com.example.harshpatel.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,clear;
    Login_Registration_Backend login_registration;

    public static final String MyPREFERENCES = "MyPrefs" ;

    SharedPreferences sharedPreferences;

    String UsernameString,PasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_registration = new Login_Registration_Backend(MainActivity.this);
        login_registration = login_registration.open();



        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        clear = (Button)findViewById(R.id.clear);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsernameString = username.getText().toString();
                PasswordString = password.getText().toString();

                String storedpassword = login_registration.Login(UsernameString);
                if (PasswordString.equals(storedpassword)) {


                    Intent home=new Intent(MainActivity.this,Home.class);
                    startActivity(home);

                    sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("EMAIL",UsernameString);
                    editor.apply();
                    editor.commit();
                    MainActivity.this.finish();

                } else {

                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }

            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
            }
        });



    }

    public void registration(View view) {

        Intent registration =  new Intent(MainActivity.this,registration.class);
        startActivity(registration);
    }
}
