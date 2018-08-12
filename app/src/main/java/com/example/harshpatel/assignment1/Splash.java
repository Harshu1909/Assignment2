package com.example.harshpatel.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    Handler handler;

    Book_Table book;
    Login_Registration_Backend login_registration_backend;


    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        login_registration_backend = new Login_Registration_Backend(this);
        login_registration_backend = login_registration_backend.open();




        login_registration_backend.Insert("Harsh","Patel","Harshu","Harshu1514","420 Linden Dr","18","Cambridge","Ontario","Canada");
        login_registration_backend.close();







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

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
