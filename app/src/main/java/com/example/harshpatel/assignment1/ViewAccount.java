package com.example.harshpatel.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.harshpatel.assignment1.MainActivity.MyPREFERENCES;

public class ViewAccount extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView Fname,Lname,Userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);


        Fname = (TextView) findViewById(R.id.fname);
        Lname = (TextView) findViewById(R.id.lname);
        Userid = (TextView) findViewById(R.id.userid);


        Login_Registration_Backend login_registration;

        login_registration =new Login_Registration_Backend(this);
        login_registration=login_registration.open();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String username = sharedPreferences.getString("EMAIL",null);

        Cursor cursor= login_registration.infoAll(username);
        String firstname = cursor.getString(cursor.getColumnIndexOrThrow("FNAME"));
        String lastname = cursor.getString(cursor.getColumnIndexOrThrow("LNAME"));
        String userid = cursor.getString(cursor.getColumnIndexOrThrow("USERID"));

        Fname.setText(firstname);
        Lname.setText(lastname);
        Userid.setText(userid);

    }
    public void update(View view) {

        Intent updateAccount =  new Intent(ViewAccount.this,UpdateAccount.class);
        startActivity(updateAccount);
    }


    public void logout(View view) {

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", null);
        editor.apply();
        editor.commit();
        Intent intent = new Intent(ViewAccount.this, MainActivity.class);
        startActivity(intent);
        ViewAccount.this.finish();
    }
}


