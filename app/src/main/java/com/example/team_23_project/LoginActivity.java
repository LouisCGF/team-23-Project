package com.example.team_23_project;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team_23_project.Activities.FAQandQAActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout loginName;
    private TextInputLayout loginPassword;
    private TextView loginAttempts;

    private Button checkLogin;
    private TextView regStu;
    private TextView regSta;
    private TextView faq;
    private ImageView plusIcon;

    private Editable inputName;
    private Editable inputPassword;

    private boolean isValid = false;
    private int counter = 5;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

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
        loginAttempts = findViewById(R.id.numoOfAtemptstxt);

        checkLogin = findViewById(R.id.loginBtn);
        regStu = findViewById(R.id.regStuBtn);
        regSta = findViewById(R.id.regStaBtn);
        faq = findViewById(R.id.faqBtn);
        plusIcon = findViewById(R.id.plusIcon);

        inputName = loginName.getEditText().getText();
        inputPassword = loginPassword.getEditText().getText();

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();
        Resources res = getResources();

        checkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(inputName) || TextUtils.isEmpty(inputPassword)) {
                    loginName.setError("Please enter an email");
                    loginPassword.setError("Please enter a password");

                } else if (!validateLogin(inputName.toString(), inputPassword.toString())){

                    loginName.setError("Incorrect email or password");
                    loginPassword.setError("Incorrect email or password");

                } else {

                    Toast.makeText(LoginActivity.this, "Login was successful",
                            Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent( LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
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

        loginPassword.getEditText().addTextChangedListener(new TextWatcher() {
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

        regStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStudent.class ));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });

        regSta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStaff.class ));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity( new Intent( LoginActivity.this, FAQandQAActivity.class));
            }
        });

        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStudent.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
    }

    private boolean validateLogin(String name, String password) {

        try{
            userCursor = db.rawQuery("select COLUMN_EMAIL_ADDRESS from " + DatabaseHelper.TABLE_USERS + " where " + DatabaseHelper.COLUMN_EMAIL_ADDRESS +
                    "=" + name, new String[]{name});
        } catch (Exception e){
            return false;
        }
        String fetchedPassword = (db.rawQuery("select COLUMN_PASSWORD from" + DatabaseHelper.TABLE_USERS + "where" + DatabaseHelper.COLUMN_EMAIL_ADDRESS + "=" + name, new String[]{name})).toString();
        if (fetchedPassword.equals(password)){
            return true;
        }
        return false;

//        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
//                + DatabaseHelper.COLUMN_EMAIL_ADDRESS + "=" + name + " and " + DatabaseHelper.COLUMN_PASSWORD + "=" + password,
//                new String[] {name, password});
    }



}
