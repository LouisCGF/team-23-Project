package com.example.team_23_project;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_23_project.Activities.FAQandQAActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginName;
    private EditText loginPassword;
    private TextView loginAttempts;

    private Button checkLogin;
    private TextView regStu;
    private TextView regSta;
    private TextView faq;
    private ImageView plusIcon;

    private String inputName;
    private String inputPassword;

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

        loginName = findViewById(R.id.editEmailTxt);
        loginPassword = findViewById(R.id.editPasswordTxt);
        loginAttempts = findViewById(R.id.numoOfAtemptstxt);

        checkLogin = findViewById(R.id.loginBtn);
        regStu = findViewById(R.id.regStuBtn);
        regSta = findViewById(R.id.regStaBtn);
        faq = findViewById(R.id.faqBtn);
        plusIcon = findViewById(R.id.plusIcon);

        inputName = loginName.getText().toString();
        inputPassword = loginPassword.getText().toString();

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();
        Resources res = getResources();

        checkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter valid details",
                            Toast.LENGTH_SHORT).show();

                } else {

                    isValid = validateLogin(inputName, inputPassword);

                    if (!isValid) {

                        counter--;

                        Toast.makeText(LoginActivity.this, "The details are incorrect",
                                Toast.LENGTH_SHORT).show();


                        String text = String.format(res.getString(R.string.num_of_attempts), counter); // <- more robust way instead of setText("num attempts remaining" + counter)
                        loginAttempts.setText(text);

                        if (counter == 0) {
                            checkLogin.setEnabled(false);
                        }

                    } else {

                        Toast.makeText(LoginActivity.this, "Login was successful",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent( LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        regStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStudent.class ));
            }
        });

        regSta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity( new Intent( LoginActivity.this, UserRegisterActivityStaff.class ));
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
            }
        });
    }

    private boolean validateLogin(String name, String password) {

        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USERS + " where "
                + DatabaseHelper.COLUMN_USER_ID + "=? and " + DatabaseHelper.COLUMN_PASSWORD + "=?",
                new String[] {name, password});

        if (userCursor.getCount()>0) {

            return true;

        }

        return false;
    }



}