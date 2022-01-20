package com.example.team_23_project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

import com.example.team_23_project.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Class for activity_main.xml. Extends AppCompatActivity
 * Gets display on the app start-up
 *
 * @author Louis Ware, Bogdan Caplan, Nikita Artimenko
 * @version 1.0
 *
 */
public class StartUpActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    /**
     * Used for starting the activity
     *
     * @author Louis Ware, Bogdan Caplan, Nikita Artimenko
     *
     * @param savedInstanceState reference to a Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.team_23_project.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        // This shows the logo when the app opens for 2 seconds
        Runnable r = () -> StartUpActivity.this.startActivity(new Intent(StartUpActivity.this, LoginActivity.class));
        Handler h = new Handler();
        h.postDelayed(r, 2000);

        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    /**
     * Called when activity restarts
     *
     * @author Louis Ware, Bogdan Caplan, Nikita Artimenko
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();
    }

    /**
     * Specifies the options menu for the activity
     *
     * @author Louis Ware, Bogdan Caplan, Nikita Artimenko
     *
     * @param menu reference to a Menu object
     * @return true if the menu should be displayed, false if not
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles menu items
     *
     * @author Louis Ware, Bogdan Caplan, Nikita Artimenko
     *
     * @param item reference to a MenuItem object
     * @return true if it successfully handles a menu item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}