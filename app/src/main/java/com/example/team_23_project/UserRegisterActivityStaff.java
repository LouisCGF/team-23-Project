package com.example.team_23_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegisterActivityStaff extends AppCompatActivity {

    // Fields in the staff register page
    EditText firstNameStaff;
    EditText lastNameStaff;
    EditText emailAdrStaff;
    EditText schoolStaff;
    EditText admin;
    EditText passwordStaffReg;
    EditText passwordConfirmStaffReg;

    Button submitStaffReg;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Getting connection.
        setContentView(R.layout.register_staff);

        firstNameStaff = findViewById(R.id.firstNameStaffTxt);
        lastNameStaff = findViewById(R.id.lastNameStaffTxt);
        emailAdrStaff = findViewById(R.id.emailAdrStaffTxt);
        schoolStaff = findViewById(R.id.schoolStaffTxt);
        admin = findViewById(R.id.adminTxt);
        passwordStaffReg = findViewById(R.id.passwordStaffRegTxt);
        passwordConfirmStaffReg = findViewById(R.id.passwordConfirmStaffRegTxt);

        submitStaffReg = findViewById(R.id.submitStaffRegBtn);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }

        if (userId > 0) {
            // Connection and reading of the Staff_info table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_STAFF_INFO
                    + " where " + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            schoolStaff.setText(userCursor.getString(1));
            admin.setText(userCursor.getString(2));
            userCursor.close();

            // Connection and reading of the Users table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
                    + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            firstNameStaff.setText(userCursor.getString(1));
            lastNameStaff.setText(userCursor.getString(2));
            emailAdrStaff.setText(userCursor.getString(3));
            passwordStaffReg.setText(userCursor.getString(4));
            passwordConfirmStaffReg.setText(userCursor.getString(5));
            userCursor.close();

        } else {
            // If some fields are empty - make button disappear
            submitStaffReg.setVisibility(View.GONE);
        }

    }

    public void submitStaff(View view) {
        // Putting data into the database after clicking on the submit button.
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();
        cv1.put(DatabaseHelper.COLUMN_FIRST_NAME,firstNameStaff.getText().toString());
        cv1.put(DatabaseHelper.COLUMN_LAST_NAME, lastNameStaff.getText().toString());
        cv1.put(DatabaseHelper.COLUMN_EMAIL_ADDRESS, emailAdrStaff.getText().toString());
        cv1.put(DatabaseHelper.COLUMN_PASSWORD, passwordStaffReg.getText().toString());
        cv2.put(DatabaseHelper.COLUMN_SCHOOL, schoolStaff.getText().toString());
        cv2.put(DatabaseHelper.COLUMN_ADMIN, admin.getText().toString());

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE_STAFF_INFO, cv2, DatabaseHelper.COLUMN_USER_ID
                    + "=" + userId, null);
            db.update(DatabaseHelper.TABLE_USERS, cv1, DatabaseHelper.COLUMN_USER_ID
                    + "=" + userId, null);
        } else {
            db.insert(DatabaseHelper.TABLE_STAFF_INFO, null, cv2);
            db.insert(DatabaseHelper.TABLE_USERS, null, cv1);
        }
        goHome();
    }

    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    // Method for closing the database and transferring user to the next stage. Name and properties of the class can be changed.
    private void goHome() {
        db.close();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
