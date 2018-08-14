package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateAccount extends AppCompatActivity {

    String id,firstName, lastName, username, add1, add2, city, state, country;

    EditText FNAME,LNAME,UID,ADD1,ADD2,CITY,STATE,COUNTRY;
    Intent intent;

    Login_Registration_Backend login_registration_backend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        login_registration_backend = new Login_Registration_Backend(this);
        login_registration_backend=login_registration_backend.open();

        intent = getIntent();


        id = intent.getStringExtra("id");
        FNAME = (EditText)findViewById(R.id.Fname);
        LNAME = (EditText)findViewById(R.id.Lname);
        UID = (EditText)findViewById(R.id.Username);
        ADD1 = (EditText)findViewById(R.id.oldAdd1);
        ADD2 = (EditText)findViewById(R.id.oldAdd2);
        CITY = (EditText)findViewById(R.id.oldCity);
        STATE = (EditText)findViewById(R.id.oldState);
        COUNTRY = (EditText)findViewById(R.id.oldCountry);

        FNAME.setText(intent.getStringExtra("Fname"));
        LNAME.setText(intent.getStringExtra("Lname"));
        UID.setText(intent.getStringExtra("Uid"));
        ADD1.setText(intent.getStringExtra("Add1"));
        ADD2.setText(intent.getStringExtra("Add2"));
        CITY.setText(intent.getStringExtra("City"));
        STATE.setText(intent.getStringExtra("State"));
        COUNTRY.setText(intent.getStringExtra("Country"));




    }

    public void update(View view) {

        firstName = FNAME.getText().toString();
        lastName = LNAME.getText().toString();
        username = UID.getText().toString();
        add1 = ADD1.getText().toString();
        add2 = ADD2.getText().toString();
        city = CITY.getText().toString();
        state = STATE.getText().toString();
        country = COUNTRY.getText().toString();

        if(firstName.equals("")||lastName.equals("")||username.equals("")
                ||add1.equals("")||add2.equals("")||city.equals("")||state.equals("")||country.equals("")) {
            Toast.makeText(UpdateAccount.this, "Field Vacant", Toast.LENGTH_LONG).show();
            return;
        }

        else if (!firstName.matches("[A-Za-z._]*"))
        {
            //  Toast.makeText(Registration.this,"Enter  Only Alphbetical Character",Toast.LENGTH_SHORT).show();
            FNAME.requestFocus();
            FNAME.setError("Enter  Only Alphabetical Character ");
        }

        else if (!lastName.matches("[A-Za-z._]*"))
        {
            //  Toast.makeText(Registration.this,"Enter  Only Alphbetical Character",Toast.LENGTH_SHORT).show();
            LNAME.requestFocus();
            LNAME.setError("Enter  Only Alphabetical Character ");
        }


        else{

            login_registration_backend.UpdateAccount(id,firstName,lastName,username,add1,add2,city,state,country);

            Intent intent =new Intent(UpdateAccount.this,ViewAccount.class);
            startActivity(intent);
            this.finish();


        }




    }

    public void cancel(View view) {

        Intent intent =  new Intent(this,ViewAccount.class);
        startActivity(intent);
        this.finish();

    }
}
