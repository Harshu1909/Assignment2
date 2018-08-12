package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    Login_Registration_Backend login_registration;

    EditText Fname,Lname,Username,Password,Cpassword,Add1,Add2,City,State,Country;
    String firstname,lastname,username,password,confirmpassword,add1,add2,city,state,country;

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
        Add1 = (EditText)findViewById(R.id.Address1);
        Add2 = (EditText)findViewById(R.id.Address2);
        City = (EditText)findViewById(R.id.City);
        State = (EditText)findViewById(R.id.State);
        Country = (EditText)findViewById(R.id.Country);

    }

    public void register(View view) {

        firstname = Fname.getText().toString();
        lastname = Lname.getText().toString();
        username = Username.getText().toString();
        password = Password.getText().toString();
        confirmpassword = Cpassword.getText().toString();
        add1=Add1.getText().toString();
        add2=Add2.getText().toString();
        city=City.getText().toString();
        state=State.getText().toString();
        country=Country.getText().toString();

        if(firstname.equals("")||lastname.equals("")||username.equals("")||password.equals("")||confirmpassword.equals("")||confirmpassword.equals("")
                ||add1.equals("")||add2.equals("")||city.equals("")||state.equals("")||country.equals("")) {
            Toast.makeText(registration.this, "Field Vacant", Toast.LENGTH_LONG).show();
            return;
        }

        else if (!firstname.matches("[A-Za-z._]*"))
        {
            //  Toast.makeText(Registration.this,"Enter  Only Alphbetical Character",Toast.LENGTH_SHORT).show();
            Fname.requestFocus();
            Fname.setError("Enter  Only Alphabetical Character ");
        }

        else if (!password.matches("[A-Z]+[a-z]+[0-9]+"))
        {
            Password.requestFocus();
            Password.setError("Enter Valid Password ,One letter must be capital ,One letter must be number");
        }
        else if(password.length()<=5){
            Toast.makeText(registration.this,"Passwords must contains at least 6 character", Toast.LENGTH_LONG).show();

        }
        else if(!password.equals(confirmpassword)) {

            Toast.makeText(registration.this, "Confirm password not same", Toast.LENGTH_LONG).show();
        }
        else{

            login_registration.Insert(firstname,lastname,username,password,add1,add2,city,state,country);


            Toast.makeText(this, "New User has been register", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(registration.this,MainActivity.class);
            startActivity(intent);

        }
    }

    public void clear(View view) {

        Fname.setText("");
        Lname.setText("");
        Username.setText("");
        Password.setText("");
        Cpassword.setText("");

    }
}
