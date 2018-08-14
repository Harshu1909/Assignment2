package com.example.harshpatel.assignment1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Issue_Books_list extends AppCompatActivity {

    Book_Table book_table;
    ListView issuelist;
    CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue__books_list);

        book_table = new Book_Table(this);
        book_table = book_table.openRead();

        Cursor cursor = book_table.IssuequeueAll();
        issuelist = (ListView) findViewById(R.id.issue_list_view);

        String[] from = { "BOOKNAME", "BOOKAUTHOR","BOOKIMAGE","ISSUEDATE","DUEDATE"};
        int[] to = { R.id.issuebookname, R.id.issuebookauthorname,R.id.issueimage,R.id.issuedate,R.id.duedate};




        cursorAdapter = new SimpleCursorAdapter(this, R.layout.issue_list, cursor, from, to, 0);
        issuelist.setAdapter(cursorAdapter);

        issuelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) issuelist.getItemAtPosition(i);

                String id = cursor.getString(cursor.getColumnIndexOrThrow("issue._id"));
                String bookid = cursor.getString(cursor.getColumnIndexOrThrow("BOOKID"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("BOOKNAME"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("BOOKAUTHOR"));
                Integer image = cursor.getInt(cursor.getColumnIndexOrThrow("BOOKIMAGE"));
                String issuedate = cursor.getString(cursor.getColumnIndexOrThrow("ISSUEDATE"));
                String duedate = cursor.getString(cursor.getColumnIndexOrThrow("DUEDATE"));
                String link = cursor.getString(cursor.getColumnIndexOrThrow("BOOKLINK"));
                String extension = cursor.getString(cursor.getColumnIndexOrThrow("EXTENSION"));

                Intent data = new Intent(Issue_Books_list.this, View_issue_book.class);
                data.putExtra("id", id);
                data.putExtra("bookid", bookid);
                data.putExtra("name", name);
                data.putExtra("author", author);
                data.putExtra("image", image);
                data.putExtra("issuedate", issuedate);
                data.putExtra("duedate", duedate);
                data.putExtra("link", link);
                data.putExtra("extension", extension);
                startActivity(data);

                Issue_Books_list.this.finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        this.finish();
    }
}
