package com.example.harshpatel.assignment1;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshpatel.assignment1.Book_Table;
import com.example.harshpatel.assignment1.BuildConfig;
import com.example.harshpatel.assignment1.DataBase;
import com.example.harshpatel.assignment1.MainActivity;
import com.example.harshpatel.assignment1.R;
import com.example.harshpatel.assignment1.UpdateAccount;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = Build.VERSION_CODES.LOLLIPOP)
public class CondorELibraryUnitTesting {

    Context appContext;
    ViewItemDetails detail;




    @Before
    public void setUp(){

        appContext= RuntimeEnvironment.application.getApplicationContext();



    }
    @Test//TestCase1
    public void CheckDatabaseConectivityOpen(){

        Book_Table book_table = new Book_Table(appContext);
        book_table = book_table.open();
        SQLiteDatabase db;

        assert(book_table.db.isOpen());
    }

    @Test//TestCase2
    public void CheckIfBookDetailsAreInserted(){

        Book_Table book_table = new Book_Table(appContext);
        book_table = book_table.open();


        long transactionId= book_table.InsertBook("test","test",123,"","test" );
        boolean areDetailsInserted= transactionId >0 ? true:false;
        assertEquals(true,areDetailsInserted);
    }

    @Test//TestCase3
    public void CheckIfIssueDetailsAreInserted(){

        Book_Table book_table = new Book_Table(appContext);
        book_table = book_table.open();


        long transactionId= book_table.IssueBook("2","test","test",123,"test" ,"9-09-2918","10-09-2018");
        boolean areDetailsInserted= transactionId >0 ? true:false;
        assertEquals(true,areDetailsInserted);
    }

    @Test//TestCase4
    public void CheckBookReturnBeforDueDate(){

        Book_Table book_table = new Book_Table(appContext);
        book_table = book_table.open();

        book_table.IssueBook("2","test","test",123,"test" ,"9-09-2918","10-09-2018");
        long transactionId= book_table.updateEntry("2");
        boolean areDetailsInserted= transactionId >0 ? true:false;
        assertEquals(false,areDetailsInserted);
    }

    @Test//TestCase5
    public void CheckBookUpdateExtension(){

        Book_Table book_table = new Book_Table(appContext);
        book_table = book_table.open();
        book_table.IssueBook("2","test","test",123,"test" ,"9-09-2918","09-09-2018");
        long transactionId= book_table.updateExtension("2","10-09-2018");
        boolean areDetailsInserted= transactionId < 0 ? true:false;
        assertEquals(false,areDetailsInserted);
    }

    @Test//TestCase6
    public void CheckNewRegistration(){

        Login_Registration_Backend login_registration_backend = new Login_Registration_Backend(appContext);
        login_registration_backend = login_registration_backend.open();
        long transactionId = login_registration_backend.Insert("test","test","test","test","test" ,"test","test","test","test");
        boolean areDetailsInserted= transactionId < 0 ? true:false;
        assertEquals(false,areDetailsInserted);
    }

    @Test//TestCase7
    public void CheckUpdateRegistration(){

        Login_Registration_Backend login_registration_backend = new Login_Registration_Backend(appContext);
        login_registration_backend = login_registration_backend.open();
        login_registration_backend.Insert("test","test","test","test","test" ,"test","test","test","test");
        long transactionId = login_registration_backend.UpdateAccount("test","test","test","test","test" ,"test","test","test","test");
        boolean areDetailsInserted= transactionId < 0 ? true:false;
        assertEquals(false,areDetailsInserted);
    }

    @Test//TestCase8
    public void CheckResetPassword(){

        Login_Registration_Backend login_registration_backend = new Login_Registration_Backend(appContext);
        login_registration_backend = login_registration_backend.open();
        login_registration_backend.Insert("test","test","test","test","test" ,"test","test","test","test");
        long transactionId = login_registration_backend.newpass("test","test1");
        boolean areDetailsInserted= transactionId < 0 ? true:false;
        assertEquals(false,areDetailsInserted);
    }

}

