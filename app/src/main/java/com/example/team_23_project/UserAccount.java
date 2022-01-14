package com.example.team_23_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserAccount extends AppCompatActivity {

    TextView firstName;
    TextView lastName;
    TextView emailAdr;

    // Text fields for Student details (NO studentNumber field)
    TextView courseStudent;
    TextView stageStudent;

    // Text fields for Staff details
    TextView schoolStaff;
    TextView admin;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checks whether the user is a student or a staff member
        //TODO if statement with else redirecting the user to the appropriate page (student/staff)
        //if ()
        // counter=0 else counter=1
        setContentView(R.layout.user_account);

        //switch(counter)
        // case 0:
        firstName = findViewById(R.id.firstNameStudentTxt);
        lastName = findViewById(R.id.lastNameStudentTxt);
        emailAdr = findViewById(R.id.emailAdrStudentTxt);
        courseStudent = findViewById(R.id.courseStudentTxt);
        stageStudent = findViewById(R.id.stageStudentTxt);

        // case 1:
        firstName = findViewById(R.id.firstNameStaffTxt);
        lastName = findViewById(R.id.lastNameStaffTxt);
        emailAdr = findViewById(R.id.emailAdrStaffTxt);
        schoolStaff = findViewById(R.id.schoolStaffTxt);
        admin = findViewById(R.id.adminTxt);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }

        if (userId > 0) {
            // Connection and reading of the Student table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_STUDENT_INFO
                    + " where " + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();

            courseStudent.setText(userCursor.getString(1));
            stageStudent.setText(userCursor.getString(2));

            userCursor.close();

            // Connection and reading of the Users table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
                    + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            firstName.setText(userCursor.getString(1));
            lastName.setText(userCursor.getString(2));
            emailAdr.setText(userCursor.getString(3));


            userCursor.close();

        }

    }

}