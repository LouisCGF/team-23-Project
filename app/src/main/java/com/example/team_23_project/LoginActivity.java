package com.example.team_23_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_23_project.Activities.FAQandQAActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout loginName;
    private TextInputLayout loginPassword;

    private Editable inputName;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This changes the status bar icon color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.fragment_first);

        loginName = findViewById(R.id.textInputEmail);
        loginPassword = findViewById(R.id.textInputPassword);

        Button checkLogin = findViewById(R.id.loginBtn);

        TextView regStu = findViewById(R.id.regStuBtn);
        TextView regSta = findViewById(R.id.regStaBtn);
        TextView faq = findViewById(R.id.faqBtn);

        ImageView plusIcon = findViewById(R.id.plusIcon);

        inputName = Objects.requireNonNull(loginName.getEditText()).getText();

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

// -------------------------------------------------------------------------------------------------

        checkLogin.setOnClickListener(view -> {

            if (TextUtils.isEmpty(inputName) || TextUtils.isEmpty(Objects.requireNonNull(loginPassword.getEditText()).getText())) {
                loginName.setError("Please enter an email");
                loginPassword.setError("Please enter a password");

            } else {
                try {
                    if (!validateLogin(inputName.toString(), loginPassword.getEditText().getText().toString())){

                        loginName.setError("Incorrect email or password");
                        loginPassword.setError("Incorrect email or password");

                    } else {

                        Toast.makeText(LoginActivity.this, "Login was successful",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent( LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                } catch (InvalidKeySpecException | NoSuchAlgorithmException e) { // <- Password hash failed
                    Toast.makeText(LoginActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        loginName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Can ignore this method, don't need it but cannot remove as it is required by TextWatcher
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(loginName.getEditText().getText())){
                    loginName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Can ignore this method, don't need it but cannot remove as it is required by TextWatcher
            }
        });

        Objects.requireNonNull(loginPassword.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Can ignore this method, don't need it but cannot remove as it is required by TextWatcher
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(loginPassword.getEditText().getText())){
                    loginPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Can ignore this method, don't need it but cannot remove as it is required by TextWatcher
            }
        });

// -------------------------------------------------------------------------------------------------

        regStu.setOnClickListener(v -> {
            LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStudent.class ));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        });

        regSta.setOnClickListener(v -> {
            LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStaff.class ));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        });

        faq.setOnClickListener(v -> LoginActivity.this.startActivity( new Intent( LoginActivity.this, FAQandQAActivity.class)));

        plusIcon.setOnClickListener(v -> {
            LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStudent.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        });
    }

// -------------------------------------------------------------------------------------------------

    private boolean validateLogin(String email, String inputtedPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {

        PBKDF2WithHmacSHA512Hash passwordHasher = new PBKDF2WithHmacSHA512Hash();

        Cursor cursor = db.rawQuery("select " + DatabaseHelper.COLUMN_USER_ID + " from "
                + DatabaseHelper.TABLE_USERS + " where " + DatabaseHelper.COLUMN_EMAIL_ADDRESS + "=?", new String[]{email});

        if (cursor.getCount() == 0){ // <- Email does not exist in database
            return false;
        }
        Cursor cursor1 = db.rawQuery("select " + DatabaseHelper.COLUMN_PASSWORD + " from "
                + DatabaseHelper.TABLE_USERS + " where " + DatabaseHelper.COLUMN_EMAIL_ADDRESS + "=?", new String[]{email});
        cursor1.moveToNext();

        String retrievedPassword = cursor1.getString(0);

        cursor.close();
        cursor1.close();

        return passwordHasher.validatePBKDF2WithHmacSHA512Password(inputtedPassword, retrievedPassword);
    }

}
