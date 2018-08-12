package com.example.harshpatel.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Harsh on 3/12/2016.
 */
public class Login_Registration_Backend {

    static final String DATABASE_NAME = "Assignment2.db";
    static final int DATABASE_VERSION = 1;


    static final String DATABASE_CREATE = "create table login_registration (ID integer primary key autoincrement,FNAME  text,LNAME text,USERID text,PASSWORD text,ADDRESS1 text,ADDRESS2 text,CITY text,STATE text,COUNTRY text);";
    public SQLiteDatabase db;
    private final Context context;
    private DataBase dbHelper;

    public Login_Registration_Backend(Context _context) {
        context = _context;
        dbHelper = new DataBase(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Login_Registration_Backend open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public Login_Registration_Backend openRead() throws SQLException {
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void Insert(String FName, String LName, String Userid, String Password, String Add1, String Add2, String City, String State, String Country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FNAME", FName);
        contentValues.put("LNAME", LName);
        contentValues.put("USERID", Userid);
        contentValues.put("PASSWORD", Password);
        contentValues.put("ADDRESS1", Add1);
        contentValues.put("ADDRESS2", Add2);
        contentValues.put("CITY", City);
        contentValues.put("STATE", State);
        contentValues.put("COUNTRY", Country);

        db.insert("login_registration", null, contentValues);
    }

    public String Login(String userid) {


        //db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  USERID,PASSWORD FROM login_registration WHERE USERID=?", new String[]{userid});
        String a, b = null;
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0).toString();
                if (a.equals(userid)) {
                    b = cursor.getString(1).toString();
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public String CheckEmail(String email) {

        Cursor cursor = db.rawQuery("SELECT  EMAIL  FROM login_registration WHERE EMAIL=?", new String[]{email});
        String a, b = null;
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0).toString();
                if (a.equals(email)) {
                    b = cursor.getString(0).toString();
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public void newpass(String mail, String pass) {
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("PASSWORD", pass);


        String where = "EMAIL = ?";
        db.update("login_registration", updatedValues, where, new String[]{mail});

    }

    public String Getname(String email) {


        //db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  EMAIL,NAME FROM login_registration WHERE EMAIL=?", new String[]{email});
        String a, b = null;
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0).toString();
                if (a.equals(email)) {
                    b = cursor.getString(1).toString();
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public Cursor infoAll(String username) {
        Cursor cursor = db.rawQuery("SELECT * from login_registration where USERID = ?", new String[]{username}, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }


    public void UpdateAccount(String id, String fname, String lname, String username,String add1, String add2, String city, String state, String country) {


        ContentValues contentValues = new ContentValues();
        contentValues.put("FNAME", fname);
        contentValues.put("LNAME", lname);
        contentValues.put("USERID", username);
        contentValues.put("ADDRESS1", add1);
        contentValues.put("ADDRESS2", add2);
        contentValues.put("CITY", city);
        contentValues.put("STATE", state);
        contentValues.put("COUNTRY", country);


        String where = "ID = ?";
        db.update("login_registration", contentValues, where, new String[]{id});
    }

}



