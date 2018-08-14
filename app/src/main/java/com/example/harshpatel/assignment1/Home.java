package com.example.harshpatel.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.harshpatel.assignment1.MainActivity.MyPREFERENCES;
import static com.example.harshpatel.assignment1.Splash.BitMapToString;
import static com.loopj.android.http.AsyncHttpClient.log;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView books;
    Book_Table book_table;
    CursorAdapter cursorAdapter;

    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        book_table = new Book_Table(this);
        book_table = book_table.openRead();


        Integer image1 = R.drawable.itemimage1;
        Integer image2 = R.drawable.itemimage2;
        Integer image3 = R.drawable.itemimage3;
        Integer image4 = R.drawable.itemimage4;
        Integer image5 = R.drawable.itemimage5;
        Integer image6 = R.drawable.itemimage6;
        Integer image7 = R.drawable.itemimage7;
        Integer image8 = R.drawable.itemimage8;
        Integer image9 = R.drawable.itemimage9;



        book_table.InsertBook("The Thirty-nine Steps","John Buchan",image1,"https://www.gutenberg.org/files/558/558-h/558-h.htm","The Thirty-nine Steps by John Buchan (1875-1940)");
        book_table.InsertBook("Tarzan of the Apes","Edgar Rice Burroughs",image2,"https://www.gutenberg.org/files/78/78-h/78-h.htm","Tarzan of the Apes by Edgar Rice Burroughs (1875-1950)");
        book_table.InsertBook("The Three Musketeer","Alexander Dumas",image3,"https://www.gutenberg.org/files/1257/1257-h/1257-h.htm","The Three Musketeer by Alexander Dumas (1802-1870)");
        book_table.InsertBook("White Fang","Jack London",image4,"https://www.gutenberg.org/files/910/910-h/910-h.htm","White Fang by Jack London (1876-1916");
        book_table.InsertBook("Moby Dick","Herman Melville",image5,"http://www.gutenberg.org/cache/epub/15/pg15.html","Moby Dick by Herman Melville (1819-1891)");
        book_table.InsertBook("Deed-Box","Arthur Morrison",image6,"https://www.gutenberg.org/files/53341/53341-h/53341-h.htm","Deed-Box by Arthur Morrison (1863-1945)");
        book_table.InsertBook("Affair in Araby","Telbot Mundy",image7,"http://www.gutenberg.org/cache/epub/10551/pg10551.html","Affair in Araby by Telbot Mundy (1879-1940)");
        book_table.InsertBook("Bones","Edgar Wallace",image8,"https://www.gutenberg.org/files/24450/24450-h/24450-h.htm","Bones by Edgar Wallece (1875-1932)");
        book_table.InsertBook("Five Weeks in a Balloon","Jules Verne",image9,"https://www.gutenberg.org/files/3526/3526-h/3526-h.htm","Five Weeks in a Balloon by Jules Verne (1828-1905)");


        Cursor cursor = book_table.queueAll();





        books = (ListView) findViewById(R.id.list_view);

        String[] from = { "BOOKNAME", "BOOKAUTHOR","BOOKIMAGE"};
        int[] to = { R.id.ItemText1, R.id.ItemSubText1,R.id.ItemImage1};




        cursorAdapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from, to, 0);
        books.setAdapter(cursorAdapter);

        books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cursor cursor = (Cursor) books.getItemAtPosition(i);

                String id = cursor.getString(cursor.getColumnIndexOrThrow("book._id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("BOOKNAME"));
                Integer image = cursor.getInt(cursor.getColumnIndexOrThrow("BOOKIMAGE"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("BOOKAUTHOR"));
                String discription = cursor.getString(cursor.getColumnIndexOrThrow("BOOKDESCRIPTION"));
                String link = cursor.getString(cursor.getColumnIndexOrThrow("BOOKLINK"));

                Intent data = new Intent(Home.this, ViewItemDetails.class);
                data.putExtra("id", id);
                data.putExtra("name", name);
                data.putExtra("author", author);
                data.putExtra("image", image);
                data.putExtra("discription", discription);
                data.putExtra("link", link);
                startActivity(data);
                Home.this.finish();

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Issue_Books_list) {

            Intent intent =new Intent(Home.this,Issue_Books_list.class);
            startActivity(intent);



        }
        else if (id == R.id.account) {

            Intent viewaccount =  new Intent(Home.this,ViewAccount.class);
            startActivity(viewaccount);

        }

        else if (id == R.id.About){

            Intent intent = new Intent(this,Aboutus.class);
            startActivity(intent);

        }

        else if(id == R.id.Logout){

            sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("EMAIL", null);
            editor.apply();
            editor.commit();
            Intent intent = new Intent(Home.this, MainActivity.class);
            startActivity(intent);
            Home.this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
