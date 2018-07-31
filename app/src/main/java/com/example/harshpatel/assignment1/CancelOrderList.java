package com.example.harshpatel.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CancelOrderList extends AppCompatActivity {

    String[] listviewTitle = new String[]{
            "Amulet #8: Supernova",
            "Positively Izzy"
    };


    int[] listviewImage = new int[]{
            R.drawable.itemimage4,
            R.drawable.itemimage5
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order_list);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 2; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            hm.put("listview_title", listviewTitle[i]);



            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.CancelItemImage1, R.id.CancelItemText1};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.cancel_list, from, to);
        ListView androidListView = (ListView) findViewById(R.id.cancel_order_list);
        androidListView.setAdapter(simpleAdapter);
    }
}
