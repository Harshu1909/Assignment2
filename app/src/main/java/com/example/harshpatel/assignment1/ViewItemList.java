package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewItemList extends AppCompatActivity {

    String[] listviewTitle = new String[]{
            "Bring Me Back", "Ayesha At Last:A Novel", "The Perfect Mother", "Amulet #8: Supernova",
            "Positively Izzy"
    };


    int[] listviewImage = new int[]{
            R.drawable.itemimage1, R.drawable.itemimage2, R.drawable.itemimage3, R.drawable.itemimage4,
            R.drawable.itemimage5
    };

    String[] listviewShortDescription = new String[]{
            "A Novel Hardcover – Jun 19 2018", "Paperback – Deckle Edge, Jun 12 2018", "Hardcover – May 1 2018", "Paperback – Sep 25 2018",
            "Paperback – May 1 2018"
    };

    String[] listview_sub_description = new String[]{
            "by B.A Paris(Author)", "by Uzma Jalaluddin", "by Aimee Molloy", "by Kazu Kibuishi",
            "by Terri Libenson"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_list);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            hm.put("listview_sub_description", listview_sub_description[i]);
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription","listview_sub_description"};
        int[] to = {R.id.ItemImage1, R.id.ItemText1, R.id.ItemSubText1,R.id.ItemSub2Text1};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent firstitem = new Intent(ViewItemList.this,ViewItemDetails.class);
                startActivity(firstitem);
            }
        });


    }


}
