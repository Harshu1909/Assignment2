package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewItemDetails extends AppCompatActivity {

    TextView Bookname,Bookdesciption;
    ImageView BookImage;
    Book_Table book_table;
    String id,name,author,link;
    Integer image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_details);

        book_table = new Book_Table(this);
        book_table = book_table.open();

        Intent bookdata = getIntent();

        id = bookdata.getStringExtra("id");
        name = bookdata.getStringExtra("name");
        author = bookdata.getStringExtra("author");
        image = bookdata.getIntExtra("image",0);
        link = bookdata.getStringExtra("link");

        Bookname = (TextView) findViewById(R.id.BookName);
        Bookdesciption = (TextView)findViewById(R.id.BookDescription);
        BookImage = (ImageView)findViewById(R.id.image);

        Bookname.setText(bookdata.getStringExtra("name"));
        Bookdesciption.setText((bookdata.getStringExtra("discription")));
        BookImage.setImageResource(image);


    }

    public void checkoutItem(View view) {

        book_table = new Book_Table(this);
        book_table = book_table.open();

        book_table.updateEntry(id);

        String issuedate = DateFormat.getDateInstance().format(new Date());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
        String expDate = sdf1.format(c.getTime());


        book_table.IssueBook(id,name,author,image,link,issuedate,expDate);
        Toast.makeText(this, "Book Has been issued", Toast.LENGTH_SHORT).show();

        Intent checkoutItem = new Intent(ViewItemDetails.this,Home.class);
        startActivity(checkoutItem);
        this.finish();
    }

    public void cancel(View view) {

        Intent cancel = new Intent(ViewItemDetails.this,Home.class);
        startActivity(cancel);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        this.finish();
    }
}
