package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CheckoutItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_item);
    }

    public void checkout(View view) {

        Toast.makeText(this, "Order Confirm", Toast.LENGTH_SHORT).show();
        Intent booklist = new Intent(CheckoutItem.this,ViewItemList.class);

        startActivity(booklist);
        this.finish();
    }
}
