package com.example.harshpatel.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    Handler handler;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences=getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(3000);
                    String s1=sharedPreferences.getString("EMAIL",null);
                    //SharedPreferences sharedpreferences;*/
                    //String s1=session.getusename();
                    if(s1==null)
                    {
                        Intent intent = new Intent(Splash.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(Splash.this,Home.class);
                        startActivity(intent);
                    }


                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        },3000);
    }
}
