package com.example.team_23_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginName;
    private EditText loginPassword;
    private Button checkLogin;
    private TextView loginAttempts;

    private boolean isValid = false;
    private int counter = 5;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    String inputName = loginName.getText().toString();
    String inputPassword = loginPassword.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_demo);

        loginName = findViewById(R.id.loginNametxt);
        loginPassword = findViewById(R.id.loginPasswordtxt);
        checkLogin = findViewById(R.id.checkLoginbtn);
        loginAttempts = findViewById(R.id.numoOfAtemptstxt);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

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

                        loginAttempts.setText("Number of attempts left: " + counter);

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
