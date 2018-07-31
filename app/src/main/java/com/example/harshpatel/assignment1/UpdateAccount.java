package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateAccount extends AppCompatActivity {

    String firstName, lastName, username, gender, oldPW, newPW, CPW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        firstName = ((EditText)findViewById(R.id.Fname)).getText().toString();
        lastName = ((EditText)findViewById(R.id.Lname)).getText().toString();
        username = ((EditText)findViewById(R.id.Username)).getText().toString();
        gender = ((RadioGroup)findViewById(R.id.radioButtonGroup)).toString();
        oldPW = ((EditText)findViewById(R.id.oldPassword)).getText().toString();
        newPW = ((EditText)findViewById(R.id.newPassword)).getText().toString();
        CPW = ((EditText)findViewById(R.id.Cpassword)).getText().toString();
    }
    public void view(View view) {
        if(!newPW.equals(CPW)){
            Toast.makeText(this, "Check your new password and confirmed password.", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent viewAccount =  new Intent(UpdateAccount.this,ViewAccount.class);
            viewAccount.putExtra("firstName", firstName);
            viewAccount.putExtra("lastName", lastName);
            viewAccount.putExtra("username", username);
            viewAccount.putExtra("gender", gender);
            viewAccount.putExtra("password", newPW);
            startActivity(viewAccount);

        }
    }
}
