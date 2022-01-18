package com.example.team_23_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.regex.Pattern;

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

    TextView lowerCaseLetter;
    TextView upperCaseLetter;
    TextView oneNumber;
    TextView characterCount;

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

        lowerCaseLetter = new TextView(this);
        upperCaseLetter = new TextView(this);
        oneNumber = new TextView(this);
        characterCount = new TextView(this);
        lowerCaseLetter.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        lowerCaseLetter.setText("One lower case letter");
        lowerCaseLetter.setTextSize(12);
        upperCaseLetter.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        upperCaseLetter.setText("One upper case letter");
        upperCaseLetter.setTextSize(12);
        oneNumber.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        oneNumber.setText("One number");
        oneNumber.setTextSize(12);
        characterCount.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        characterCount.setText("At least 8 character");
        characterCount.setTextSize(12);

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
                passwordStudentReg.removeView(lowerCaseLetter);
                passwordStudentReg.removeView(upperCaseLetter);
                passwordStudentReg.removeView(oneNumber);
                passwordStudentReg.removeView(characterCount);
                TextInputLayout[] inputFields = {firstNameStudent, lastNameStudent, emailAdrStudent, courseStudent, stageStudent, passwordStudentReg, passwordConfirmStudentReg};
                boolean valid = true;
                for (TextInputLayout inputField : inputFields){
                    if (TextUtils.isEmpty(inputField.getEditText().getText())){
                        valid = false;
                        inputField.setError("You must fill out this field");
                        continue;
                    }
                    inputField.setError(null);
                }
                if (!validatePassword(passwordStudentReg.getEditText().getText().toString())){
                    valid = false;
                }
                if (valid){
                    submitStudent();
                }
            }
        });

        Objects.requireNonNull(passwordStudentReg.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordStudentReg.removeView(lowerCaseLetter);
                passwordStudentReg.removeView(upperCaseLetter);
                passwordStudentReg.removeView(oneNumber);
                passwordStudentReg.removeView(characterCount);
                validatePassword(passwordStudentReg.getEditText().getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public boolean validatePassword(String password) {
        boolean valid = true;
        // check for pattern
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        passwordStudentReg.addView(lowerCaseLetter);
        passwordStudentReg.addView(upperCaseLetter);
        passwordStudentReg.addView(oneNumber);
        passwordStudentReg.addView(characterCount);

        // if lowercase character is not present
        if (!lowercase.matcher(password).find()) {
            lowerCaseLetter.setTextColor(Color.RED);
            valid = false;

        } else {
            // if lowercase character is  present
            lowerCaseLetter.setTextColor(Color.GREEN);
        }

        // if uppercase character is not present
        if (!uppercase.matcher(password).find()) {
            upperCaseLetter.setTextColor(Color.RED);
        } else {
            // if uppercase character is  present
            upperCaseLetter.setTextColor(Color.GREEN);
            valid = false;
        }
        // if digit is not present
        if (!digit.matcher(password).find()) {
            oneNumber.setTextColor(Color.RED);
        } else {
            // if digit is present
            oneNumber.setTextColor(Color.GREEN);
            valid = false;
        }
        // if password length is less than 8
        if (password.length() < 8) {
            characterCount.setTextColor(Color.RED);
        } else {
            characterCount.setTextColor(Color.GREEN);
            valid = false;
        }
        return valid;
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
