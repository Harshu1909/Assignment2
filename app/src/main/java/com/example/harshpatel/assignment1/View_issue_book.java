package com.example.harshpatel.assignment1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class View_issue_book extends AppCompatActivity {

    TextView Bookname,BookAuthor,BookDue,BookExtension;
    Intent intent;
    String issueid,bookid,link,extension,due;
    Button Extension;

    Book_Table book;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_issue_book);
        book = new Book_Table(this);
        book = book.open();

        intent = getIntent();

        Extension = (Button)findViewById(R.id.extensionbtn);


        issueid = intent.getStringExtra("id");
        bookid = intent.getStringExtra("bookid");
        link = intent.getStringExtra("link");
        extension = intent.getStringExtra("extension");
        due = intent.getStringExtra("duedate");

        Bookname = (TextView)findViewById(R.id.bookname);
        BookAuthor = (TextView)findViewById(R.id.authorname);
        BookDue = (TextView)findViewById(R.id.duedate);
        BookExtension = (TextView)findViewById(R.id.extension);

        Bookname.setText(intent.getStringExtra("name"));
        BookAuthor.setText(intent.getStringExtra("author"));
        BookDue.setText(intent.getStringExtra("duedate"));

        if (extension.equals("1")){

            BookExtension.setText("Yes");

        }
        else{
            BookExtension.setText("No");
            Extension.setVisibility(View.GONE);

        }


    }

    public void ReadBook(View view) {

        Intent intent = new Intent(View_issue_book.this, Webview.class);

        intent.putExtra("link",link);

        startActivity(intent);


    }

    public void GetExtension(View view) {




            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle("Your Title");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Do yo want to extend this book!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            if (extension.equals("1")){

                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


                                Date myDate = null;

                                String[] separated = due.split("-");
                                int month = Integer.parseInt(separated[0]);
                                int day = Integer.parseInt(separated[1]);
                                int year = Integer.parseInt(separated[2]);


                                Calendar cal = new GregorianCalendar(year,month,day);

                                cal.add(Calendar.DAY_OF_MONTH,0);
                                SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
                                String expDate = sdf1.format(cal.getTime());


                                book.updateExtension(issueid,expDate);
                                Intent intent =new Intent(View_issue_book.this,Issue_Books_list.class);
                                startActivity(intent);

                            }

                            View_issue_book.this.finish();

                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

    public void ReturnBook(View view) {

        book.dueBook(issueid,bookid);
        Intent intent = new Intent(this,Issue_Books_list.class);
        startActivity(intent);
        this.finish();
    }


    public static class DateUtil
    {
        public static Date addDays(Date date, int days)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Issue_Books_list.class);
        startActivity(intent);
        this.finish();
    }
}
