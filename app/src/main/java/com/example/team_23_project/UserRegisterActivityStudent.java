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
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserRegisterActivityStudent extends AppCompatActivity {

    // Fields in the student register page
    TextInputLayout firstNameStudent;
    TextInputLayout lastNameStudent;
    TextInputLayout studentNumber;
    TextInputLayout emailAdrStudent;
    TextInputLayout courseStudent;
    TextInputLayout stageStudent;
    TextInputLayout passwordStudentReg;
    TextInputLayout passwordConfirmStudentReg;

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

        firstNameStudent = findViewById(R.id.studentFirstNameInput);
        lastNameStudent = findViewById(R.id.studentLastNameInput);
        emailAdrStudent = findViewById(R.id.studentEmailInput);
        courseStudent = findViewById(R.id.studentCourseInput);
        stageStudent = findViewById(R.id.studentStageInput);
        passwordStudentReg = findViewById(R.id.studentPassInput);
        passwordConfirmStudentReg = findViewById(R.id.studentConfPassInput);

        submitStudentReg = findViewById(R.id.submitStudentRegBtn);
        ImageView minusButton = findViewById(R.id.minusButton);

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
            studentNumber.getEditText().setText(userCursor.getString(1));
            courseStudent.getEditText().setText(userCursor.getString(2));
            stageStudent.getEditText().setText(userCursor.getString(3));
            userCursor.close();

            // Connection and reading of the Users table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
                    + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            firstNameStudent.getEditText().setText(userCursor.getString(1));
            lastNameStudent.getEditText().setText(userCursor.getString(2));
            emailAdrStudent.getEditText().setText(userCursor.getString(3));
            passwordStudentReg.getEditText().setText(userCursor.getString(4));
            passwordConfirmStudentReg.getEditText().setText(userCursor.getString(5));
            userCursor.close();

        }

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegisterActivityStudent.this.startActivity(new Intent(UserRegisterActivityStudent.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            }
        });

        submitStudentReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitStudent();
            }
        });

    }

    public void submitStudent() {
        // Putting data into the database after clicking on the submit button.
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();
        cv1.put(DatabaseHelper.COLUMN_FIRST_NAME,firstNameStudent.getEditText().getText().toString());
        cv1.put(DatabaseHelper.COLUMN_LAST_NAME, lastNameStudent.getEditText().getText().toString());
        cv1.put(DatabaseHelper.COLUMN_EMAIL_ADDRESS, emailAdrStudent.getEditText().getText().toString());
        cv1.put(DatabaseHelper.COLUMN_PASSWORD, passwordStudentReg.getEditText().getText().toString());
        cv2.put(DatabaseHelper.COLUMN_COURSE, courseStudent.getEditText().getText().toString());
        cv2.put(DatabaseHelper.COLUMN_STAGE, stageStudent.getEditText().getText().toString());

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
