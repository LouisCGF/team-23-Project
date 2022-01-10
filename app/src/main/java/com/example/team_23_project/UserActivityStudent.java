package com.example.team_23_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivityStudent extends AppCompatActivity {

    // Fields in the student register page
    EditText firstNameStudent;
    EditText lastNameStudent;
    EditText studentNumber;
    EditText emailAdrStudent;
    EditText courseStudent;
    EditText stageStudent;
    EditText passwordStudentReg;
    EditText passwordConfirmStudentReg;

    Button submitStudentReg;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Getting connection.
        setContentView(R.layout.register_student);

        firstNameStudent = findViewById(R.id.firstNameStudentTxt);
        lastNameStudent = findViewById(R.id.lastNameStudentTxt);
        studentNumber = findViewById(R.id.studentNumberTxt);
        emailAdrStudent = findViewById(R.id.emailAdrStudentTxt);
        courseStudent = findViewById(R.id.courseStudentTxt);
        stageStudent = findViewById(R.id.stageStudentTxt);
        passwordStudentReg = findViewById(R.id.passwordStudentRegTxt);
        passwordConfirmStudentReg = findViewById(R.id.passwordConfirmStudentRegTxt);

        submitStudentReg = findViewById(R.id.submitStudentRegBtn);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }

        if (userId > 0) {
            // Connection and reading of the Student_info table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_STUDENT_INFO
                    + " where " + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            studentNumber.setText(userCursor.getString(1));
            courseStudent.setText(userCursor.getString(2));
            stageStudent.setText(userCursor.getString(3));
            userCursor.close();

            // Connection and reading of the Users table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
                    + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            firstNameStudent.setText(userCursor.getString(1));
            lastNameStudent.setText(userCursor.getString(2));
            emailAdrStudent.setText(userCursor.getString(3));
            passwordStudentReg.setText(userCursor.getString(4));
            passwordConfirmStudentReg.setText(userCursor.getString(5));
            userCursor.close();

        } else {
            // If some fields are empty - make button disappear
            submitStudentReg.setVisibility(View.GONE);
        }

    }

    public void submitStudent(View view) {
        // Putting data into the database after clicking on the submit button.
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();
        cv1.put(DatabaseHelper.COLUMN_FIRST_NAME,firstNameStudent.getText().toString());
        cv1.put(DatabaseHelper.COLUMN_LAST_NAME, lastNameStudent.getText().toString());
        cv1.put(DatabaseHelper.COLUMN_EMAIL_ADDRESS, emailAdrStudent.getText().toString());
        cv1.put(DatabaseHelper.COLUMN_PASSWORD, passwordStudentReg.getText().toString());
        cv2.put(DatabaseHelper.COLUMN_COURSE, courseStudent.getText().toString());
        cv2.put(DatabaseHelper.COLUMN_STAGE, stageStudent.getText().toString());

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE_STUDENT_INFO, cv2, DatabaseHelper.COLUMN_USER_ID
                    + "=" + userId, null);
            db.update(DatabaseHelper.TABLE_USERS, cv1, DatabaseHelper.COLUMN_USER_ID
                    + "=" + userId, null);
        } else {
            db.insert(DatabaseHelper.TABLE_STUDENT_INFO, null, cv2);
            db.insert(DatabaseHelper.TABLE_USERS, null, cv1);
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
