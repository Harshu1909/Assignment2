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

    TextView Fname,Lname,Userid,Add1,Add2,City,State,Country;
    String id,firstname,lastname,userid,add1,add2,state,city,country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);


        Fname = (TextView) findViewById(R.id.fname);
        Lname = (TextView) findViewById(R.id.lname);
        Userid = (TextView) findViewById(R.id.userid);
        Add1 = (TextView) findViewById(R.id.unitnumber);
        Add2 = (TextView) findViewById(R.id.streetname);
        City = (TextView) findViewById(R.id.city);
        State = (TextView) findViewById(R.id.state);
        Country = (TextView) findViewById(R.id.country);



        Login_Registration_Backend login_registration;

        login_registration =new Login_Registration_Backend(this);
        login_registration=login_registration.open();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String username = sharedPreferences.getString("EMAIL",null);

        Cursor cursor= login_registration.infoAll(username);
        id = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
        firstname = cursor.getString(cursor.getColumnIndexOrThrow("FNAME"));
        lastname = cursor.getString(cursor.getColumnIndexOrThrow("LNAME"));
        userid = cursor.getString(cursor.getColumnIndexOrThrow("USERID"));

        add1 = cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS1"));
        add2 = cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS2"));
        city = cursor.getString(cursor.getColumnIndexOrThrow("CITY"));
        state = cursor.getString(cursor.getColumnIndexOrThrow("STATE"));
        country = cursor.getString(cursor.getColumnIndexOrThrow("COUNTRY"));

        Fname.setText(firstname);
        Lname.setText(lastname);
        Userid.setText(userid);
        Add1.setText(add1);
        Add2.setText(add2);
        City.setText(city);
        State.setText(state);
        Country.setText(country);

    }
    public void update(View view) {

        Intent updateAccount =  new Intent(ViewAccount.this,UpdateAccount.class);
        updateAccount.putExtra("id",id);
        updateAccount.putExtra("Fname",firstname);
        updateAccount.putExtra("Lname",lastname);
        updateAccount.putExtra("Uid",userid);
        updateAccount.putExtra("Add1",add1);
        updateAccount.putExtra("Add2",add2);
        updateAccount.putExtra("City",city);
        updateAccount.putExtra("State",state);
        updateAccount.putExtra("Country",country);


        startActivity(updateAccount);
        this.finish();
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


