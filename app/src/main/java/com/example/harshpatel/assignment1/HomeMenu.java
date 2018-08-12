package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
    }

    public void Booklist(View view) {


    }

    public void viewaccount(View view) {

        Intent viewaccount =  new Intent(HomeMenu.this,ViewAccount.class);
        startActivity(viewaccount);
    }

    public void viewhistory(View view) {


    }

    public void cancelorder(View view) {


    }
}
