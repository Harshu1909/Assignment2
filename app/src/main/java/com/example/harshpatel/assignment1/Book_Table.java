package com.example.harshpatel.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.text.Bidi;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book_Table {
    static final String DATABASE_NAME = "Assignment2.db";
    static final int DATABASE_VERSION = 1;


    static final String BOOK_TABLE_CREATE = "create table book (_id integer primary key autoincrement,BOOKNAME  text,BOOKAUTHOR text,BOOKIMAGE integer ,BOOKLINK text,BOOKDESCRIPTION text,BOOKSTATUS integer);";
    static final String ISSUE_TABLE_CREATE = "create table issue (_id integer primary key autoincrement,BOOKID text,BOOKNAME text,BOOKAUTHOR text,BOOKIMAGE integer,BOOKLINK text  integer,ISSUEDATE long ,DUEDATE long,EXTENSION integer,ISSUESTATUS integer,TODAYDATE long);";
    public SQLiteDatabase db;
    private final Context context;
    private DataBase dbHelper;

    public Book_Table(Context _context) {
        context = _context;
        dbHelper = new DataBase(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Book_Table open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public Book_Table openRead() throws SQLException{
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void InsertBook(String name, String author, Integer image,String link, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("BOOKNAME", name);
        contentValues.put("BOOKAUTHOR", author);
        contentValues.put("BOOKIMAGE", image);
        contentValues.put("BOOKLINK", link);
        contentValues.put("BOOKDESCRIPTION", description);
        contentValues.put("BOOKSTATUS", 1);

        db.insert("book", null, contentValues);
    }

    public void IssueBook(String id,String name,String author,Integer image,String link , String issuedate, String duedate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("BOOKID", id);
        contentValues.put("BOOKNAME", name);
        contentValues.put("BOOKIMAGE", image);
        contentValues.put("BOOKAUTHOR", author);
        contentValues.put("BOOKLINK", link);
        contentValues.put("ISSUEDATE", issuedate);
        contentValues.put("DUEDATE", duedate);
        contentValues.put("EXTENSION", 1);
        contentValues.put("ISSUESTATUS", 1);

        db.insert("issue", null, contentValues);
    }


    public Cursor queueAll() {
        //String[] columns = new String[]{"IssuePatient.P_ID","Details._id","Details.FNAME","Details.LNAME", "IssuePatient.ISSUE","IssuePatient.DETAILS","IssuePatient.DATE"};
        //String whereClause="IssuePatient.P_ID=Details._id";
        //Cursor cursor = db.query(true,"IssuePatient,Details",columns,null,null,null,null,null,null);
        Cursor cursor=db.rawQuery("SELECT * from book WHERE _id <= 9 and BOOKSTATUS = 1" ,null );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor IssuequeueAll() {
        //String[] columns = new String[]{"IssuePatient.P_ID","Details._id","Details.FNAME","Details.LNAME", "IssuePatient.ISSUE","IssuePatient.DETAILS","IssuePatient.DATE"};
        //String whereClause="IssuePatient.P_ID=Details._id";
        //Cursor cursor = db.query(true,"IssuePatient,Details",columns,null,null,null,null,null,null);
        Cursor cursor=db.rawQuery("SELECT * from issue WHERE ISSUESTATUS = 1",null );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void updateEntry(String id) {

        ContentValues updatedValues = new ContentValues();

        updatedValues.put("BOOKSTATUS", 0);


        String where = "_id = ?";
        db.update("book", updatedValues, where, new String[]{id});

    }

    public void updateExtension(String id,String due) {

        ContentValues updatedValues = new ContentValues();

        updatedValues.put("DUEDATE", due);
        updatedValues.put("EXTENSION", 0);


        String where = "_id = ?";
        db.update("issue", updatedValues, where, new String[]{id});

    }

    public void TodayDateBook(String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TODAYDATE", date);

        db.insert("issue", null, contentValues);
    }

    public Cursor dueBookid() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
        String expDate = sdf1.format(c.getTime());
        //String[] columns = new String[]{"IssuePatient.P_ID","Details._id","Details.FNAME","Details.LNAME", "IssuePatient.ISSUE","IssuePatient.DETAILS","IssuePatient.DATE"};
        //String whereClause="IssuePatient.P_ID=Details._id";
        //Cursor cursor = db.query(true,"IssuePatient,Details",columns,null,null,null,null,null,null);
        Cursor cursor=db.rawQuery("SELECT BOOKID from issue WHERE DUEDATE > TODAYDATE",new String[]{expDate});
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void dueBook(String date) {




        ContentValues updatedMainBook = new ContentValues();
        ContentValues updatedIssueBook = new ContentValues();

        updatedIssueBook.put("ISSUESTATUS", 0);
        updatedMainBook.put("BOOKSTATUS", 1);



        db.update("issue", updatedIssueBook, "BOOKID = ?",new String[]{date});
        db.update("book", updatedMainBook, "_id = ?",new String[]{date});

    }

    public void dueBook(String issueid,String bookid) {




        ContentValues updatedMainBook = new ContentValues();
        ContentValues updatedIssueBook = new ContentValues();

        updatedIssueBook.put("ISSUESTATUS", 0);
        updatedMainBook.put("BOOKSTATUS", 1);



        db.update("issue", updatedIssueBook, "_id = ?",new String[]{issueid});
        db.update("book", updatedMainBook, "_id = ?",new String[]{bookid});

    }

}
