package com.example.harshpatel.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    Login_Registration_Backend login_registration;

    EditText Fname,Lname,Username,Password,Cpassword;
    String firstname,lastname,username,password,confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login_registration = new Login_Registration_Backend(registration.this);
        login_registration = login_registration.open();

        Fname = (EditText)findViewById(R.id.Fname);
        Lname = (EditText)findViewById(R.id.Lname);
        Username = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        Cpassword = (EditText)findViewById(R.id.Cpassword);

    }

    public void register(View view) {

        firstname = Fname.getText().toString();
        lastname = Lname.getText().toString();
        username = Username.getText().toString();
        password = Password.getText().toString();
        confirmpassword = Cpassword.getText().toString();

        login_registration.Insert(firstname,lastname,username,password);


        Toast.makeText(this, "New User has been register", Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {

        Fname.setText("");
        Lname.setText("");
        Username.setText("");
        Password.setText("");
        Cpassword.setText("");

    }
}
