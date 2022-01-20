package com.example.team_23_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserRegisterActivityStudent extends AppCompatActivity {

    // Fields in the student register page
    private TextInputLayout firstNameStudent;
    private TextInputLayout lastNameStudent;
    private TextInputLayout emailAdrStudent;
    private TextInputLayout courseStudent;
    private TextInputLayout stageStudent;
    private TextInputLayout passwordStudentReg;
    private TextInputLayout passwordConfirmStudentReg;

    private TextView lowerCaseLetter;
    private TextView upperCaseLetter;
    private TextView oneNumber;
    private TextView characterCount;

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

// -------------------------------------------------------------------------------------------------
                        // -- All fields --
        firstNameStudent = findViewById(R.id.studentFirstNameInput);
        lastNameStudent = findViewById(R.id.studentLastNameInput);
        emailAdrStudent = findViewById(R.id.studentEmailInput);
        courseStudent = findViewById(R.id.studentCourseInput);
        stageStudent = findViewById(R.id.studentStageInput);
        passwordStudentReg = findViewById(R.id.studentPassInput);
        passwordConfirmStudentReg = findViewById(R.id.studentConfPassInput);

// -------------------------------------------------------------------------------------------------
            // -- Password validation text --
        lowerCaseLetter = new TextView(this);
        upperCaseLetter = new TextView(this);
        oneNumber = new TextView(this);
        characterCount = new TextView(this);
        lowerCaseLetter.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        lowerCaseLetter.setText(R.string.one_lower_case_letter);
        lowerCaseLetter.setTextSize(11);
        upperCaseLetter.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        upperCaseLetter.setText(R.string.one_upper_case_letter);
        upperCaseLetter.setTextSize(11);
        oneNumber.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        oneNumber.setText(R.string.at_least_one_number);
        oneNumber.setTextSize(11);
        characterCount.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        characterCount.setText(R.string.at_least_8_characters);
        characterCount.setTextSize(11);

// -------------------------------------------------------------------------------------------------

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
            Objects.requireNonNull(courseStudent.getEditText()).setText(userCursor.getString(2));
            Objects.requireNonNull(stageStudent.getEditText()).setText(userCursor.getString(3));
            userCursor.close();

            // Connection and reading of the Users table
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
                    + DatabaseHelper.COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
            Objects.requireNonNull(firstNameStudent.getEditText()).setText(userCursor.getString(1));
            Objects.requireNonNull(lastNameStudent.getEditText()).setText(userCursor.getString(2));
            Objects.requireNonNull(emailAdrStudent.getEditText()).setText(userCursor.getString(3));
            Objects.requireNonNull(passwordStudentReg.getEditText()).setText(userCursor.getString(4));
            Objects.requireNonNull(passwordConfirmStudentReg.getEditText()).setText(userCursor.getString(5));
            userCursor.close();
        }

// -------------------------------------------------------------------------------------------------
           // -- All on click listeners
        minusButton.setOnClickListener(v -> {
            UserRegisterActivityStudent.this.startActivity(new Intent(UserRegisterActivityStudent.this, LoginActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        });

        submitStudentReg.setOnClickListener(v -> {
            removePasswordValidationText();
            TextInputLayout[] inputFields = {firstNameStudent, lastNameStudent, emailAdrStudent, courseStudent, stageStudent, passwordStudentReg, passwordConfirmStudentReg};
            boolean valid = true;
            for (TextInputLayout inputField : inputFields){ // <- If any field is left blank
                if (TextUtils.isEmpty(Objects.requireNonNull(inputField.getEditText()).getText())){
                    valid = false;
                    inputField.setError("You must fill out this field");
                    continue;
                }
                inputField.setError(null);
            }
            if (!validatePassword(Objects.requireNonNull(passwordStudentReg.getEditText()).getText().toString())){ // <- If password doesn't match password rules
                valid = false;
            }
            if (!passwordStudentReg.getEditText().getText().toString().equals(Objects.requireNonNull(passwordConfirmStudentReg.getEditText()).getText().toString())){ // <- If both password fields do not match
                passwordStudentReg.setError("Passwords do not match");
                passwordConfirmStudentReg.setError("Passwords do not match");
                valid = false;
            }
            if (valid){
                try {
                    submitStudent();
                } catch (InvalidKeySpecException | NoSuchAlgorithmException e) { // <- Password hash failed
                    Toast.makeText(UserRegisterActivityStudent.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        Objects.requireNonNull(passwordStudentReg.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Can ignore this method, don't need it but cannot remove as it is required by TextWatcher
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                removePasswordValidationText();
                validatePassword(passwordStudentReg.getEditText().getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Can ignore this method, don't need it but cannot remove as it is required by TextWatcher
            }
        });
    }

// -------------------------------------------------------------------------------------------------
            // -- Password Validation
    public boolean validatePassword(String password) {
        boolean valid = true;
        // check for pattern
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        addPasswordValidationText();

        if (TextUtils.isEmpty(Objects.requireNonNull(passwordStudentReg.getEditText()).getText())){
            removePasswordValidationText();
        }

        if (!lowercase.matcher(password).find()) { // <- If lowercase character is not present
            lowerCaseLetter.setTextColor(-43230);
            valid = false;
        } else { // <- If lowercase character is  present
            lowerCaseLetter.setTextColor(Color.parseColor("#6EFF94"));
        }

        if (!uppercase.matcher(password).find()) { // <- If uppercase character is not present
            upperCaseLetter.setTextColor(-43230);
            valid = false;
        } else { // <- If uppercase character is  present
            upperCaseLetter.setTextColor(Color.parseColor("#6EFF94"));
        }

        if (!digit.matcher(password).find()) { // <- If digit is not present
            oneNumber.setTextColor(-43230);
            valid = false;
        } else { // <- If digit is present
            oneNumber.setTextColor(Color.parseColor("#6EFF94")); // <- If digit is present
        }

        if (password.length() < 8) { // <- If password length is less than 8
            characterCount.setTextColor(-43230);
            valid = false;
        } else { // <- If password length is at least 8
            characterCount.setTextColor(Color.parseColor("#6EFF94"));
        }
        return valid;
    }

    public void addPasswordValidationText(){
        TextView[] textViews = {lowerCaseLetter, upperCaseLetter, oneNumber, characterCount};
        for (TextView textView : textViews) {
            passwordStudentReg.addView(textView);
        }
    }

    public void removePasswordValidationText(){
        TextView[] textViews = {lowerCaseLetter, upperCaseLetter, oneNumber, characterCount};
        for (TextView textView : textViews) {
            passwordStudentReg.removeView(textView);
        }
    }

// -------------------------------------------------------------------------------------------------

    public void submitStudent() throws InvalidKeySpecException, NoSuchAlgorithmException {
        // Putting data into the database after clicking on the submit button.
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();

        // -- HASHING PASSWORD --
        PBKDF2WithHmacSHA512Hash hasher = new PBKDF2WithHmacSHA512Hash();
        String hashedPassword = hasher.hashPBKDF2WithHmacSHA512Password(Objects.requireNonNull(passwordStudentReg.getEditText()).getText().toString());

        cv1.put(DatabaseHelper.COLUMN_FIRST_NAME, Objects.requireNonNull(firstNameStudent.getEditText()).getText().toString());
        cv1.put(DatabaseHelper.COLUMN_LAST_NAME, Objects.requireNonNull(lastNameStudent.getEditText()).getText().toString());
        cv1.put(DatabaseHelper.COLUMN_EMAIL_ADDRESS, Objects.requireNonNull(emailAdrStudent.getEditText()).getText().toString());
        cv1.put(DatabaseHelper.COLUMN_PASSWORD, hashedPassword);
        cv2.put(DatabaseHelper.COLUMN_COURSE, Objects.requireNonNull(courseStudent.getEditText()).getText().toString());
        cv2.put(DatabaseHelper.COLUMN_STAGE, Objects.requireNonNull(stageStudent.getEditText()).getText().toString());

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

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
