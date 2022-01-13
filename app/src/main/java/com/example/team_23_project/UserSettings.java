package com.example.team_23_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.ContentValues;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class UserSettings extends AppCompatActivity {

    // Unsure! These normally are fields, but they should work like buttons because the users have to select them
    Button theme;
    Button textSize;
    Button colorblind;

    Button saveSettings;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Getting connection
        setContentView(R.layout.user_settings);

        // For Light and Dark Mode Spinner:
        Spinner dropdown = findViewById(R.id.lightDarkSpinner);
        String[] items = new String[]{"Light Mode", "Dark Mode"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        // In order for the Settings functionality to work:

        // TODO -> the Settings layout/fragment needs to be updated with these fields and buttons
        //theme = findViewById(R.id.themeButton);
        //textSize = findViewById(R.id.textSizeButton);
        //colorblind = findViewById(R.id.colorblindButton);

        //saveSettings = findViewById(R.id.saveSettingsButton);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }

        /**
        if (userId > 0) {
            // Connection and reading of the Settings table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_SETTINGS
                    + " where " + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();

            theme.setText(userCursor.getString(1));
            textSize.setText(userCursor.getString(2));
            colorblind.setText(userCursor.getString(3));

            userCursor.close();

        } else {
            saveSettings.setVisibility(View.GONE);
        }
        **/
    }


    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_THEME, theme.getText().toString());
        cv.put(DatabaseHelper.COLUMN_TEXT_SIZE, textSize.getText().toString());
        cv.put(DatabaseHelper.COLUMN_COLORBLIND, colorblind.getText().toString());

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE_SETTINGS, cv, DatabaseHelper.COLUMN_USER_ID + "=" + userId, null);
        } else {
            db.insert(DatabaseHelper.TABLE_SETTINGS, null, cv);
        }
        goHome();
    }

    // Method for closing the database and transferring user to the next stage. Name and properties of the class can be changed.
    private void goHome() {
        db.close();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
