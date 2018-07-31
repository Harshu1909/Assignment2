package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_details);
    }

    public void checkoutItem(View view) {

        Intent checkoutItem = new Intent(ViewItemDetails.this,CheckoutItem.class);
        startActivity(checkoutItem);
    }

    public void cancel(View view) {

        Intent cancel = new Intent(ViewItemDetails.this,ViewItemList.class);
        startActivity(cancel);
        this.finish();
    }


}
