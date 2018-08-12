package com.example.harshpatel.assignment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Harsh on 3/12/2016.
 */
public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Login_Registration_Backend.DATABASE_CREATE);
        db.execSQL(Book_Table.BOOK_TABLE_CREATE);
        db.execSQL(Book_Table.ISSUE_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(db);

    }
}


