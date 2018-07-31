package com.example.harshpatel.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Harsh on 3/12/2016.
 */
public class Login_Registration_Backend {

    static final String DATABASE_NAME = "Assignment2.db";
    static final int DATABASE_VERSION = 1;


    static final String DATABASE_CREATE = "create table login_registration (ID integer primary key autoincrement,FNAME  text,LNAME text,USERID text,PASSWORD text);";
    public SQLiteDatabase db;
    private final Context context;
    private DataBase dbHelper;

    public Login_Registration_Backend(Context _context) {
        context = _context;
        dbHelper = new DataBase(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Login_Registration_Backend open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public Login_Registration_Backend openRead() throws SQLException{
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void Insert(String FName, String LName ,String Userid,String Password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FNAME", FName);
        contentValues.put("LNAME", LName);
        contentValues.put("USERID", Userid);
        contentValues.put("PASSWORD", Password);
        db.insert("login_registration", null, contentValues);
    }

    public String Login(String userid) {


        //db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  USERID,PASSWORD FROM login_registration WHERE USERID=?",new String[]{userid});
        String a,b = null;
        if (cursor.moveToFirst()) {
            do {
                a=cursor.getString(0).toString();
                if(a.equals(userid)){
                    b=cursor.getString(1).toString();
                    break;
                }
            } while (cursor.moveToNext());
        }return b;
    }

    public String CheckEmail(String email) {

        Cursor cursor = db.rawQuery("SELECT  EMAIL  FROM login_registration WHERE EMAIL=?", new String[]{email});
        String a,b = null;
        if (cursor.moveToFirst()) {
            do {
                a=cursor.getString(0).toString();
                if(a.equals(email)){
                    b=cursor.getString(0).toString();
                    break;
                }
            } while (cursor.moveToNext());
        }return b;
    }
    public void newpass(String mail,String pass){
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("PASSWORD", pass);


        String where="EMAIL = ?";
        db.update("login_registration",updatedValues, where, new String[]{mail});

    }
    public String Getname(String email) {


        //db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  EMAIL,NAME FROM login_registration WHERE EMAIL=?",new String[]{email});
        String a,b = null;
        if (cursor.moveToFirst()) {
            do {
                a=cursor.getString(0).toString();
                if(a.equals(email)){
                    b=cursor.getString(1).toString();
                    break;
                }
            } while (cursor.moveToNext());
        }return b;
    }
    public Cursor infoAll(String username) {
        Cursor cursor=db.rawQuery("SELECT * from login_registration where USERID = ?",new String[]{username},null );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }


}



