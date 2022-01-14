package com.example.team_23_project.databaseConnection;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_23_project.R;
import com.example.team_23_project.databaseConnection.DbManager;

public class InsertData extends AppCompatActivity {

    // Variable for the fields
    EditText firstNameTxt, lastnameTxt, studentNumberTxt, emailTxt, courseTxt, stageTxt, passwordTxt, schoolTxt, adminTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_student);
        setContentView(R.layout.register_staff);

        // USERS TABLE BINDING
        firstNameTxt = (EditText) findViewById(R.id.firstNameStudentTxt);
        lastnameTxt = (EditText) findViewById(R.id.lastNameStudentTxt);
        emailTxt = (EditText) findViewById(R.id.emailAdrStudentTxt);
        passwordTxt = (EditText) findViewById(R.id.passwordStudentRegTxt);

        // STUDENT TABLE BINDING
        courseTxt = (EditText) findViewById(R.id.courseStudentTxt);
        stageTxt = (EditText) findViewById(R.id.stageStudentTxt);

        // STAFF TABLE BINDING
        schoolTxt = (EditText) findViewById(R.id.schoolStaffTxt);
        adminTxt = (EditText) findViewById(R.id.adminTxt);
    }

    public void addRecordUser(View view){
        DbManager db = new DbManager(this);

        // Temporary user_id null
        String resUser = db.addRecordUser(null, emailTxt.getText().toString(), firstNameTxt.getText().toString(), lastnameTxt.getText().toString(), passwordTxt.getText().toString());
        Toast.makeText(this, resUser, Toast.LENGTH_LONG).show();

        emailTxt.setText("");
        firstNameTxt.setText("");
        lastnameTxt.setText("");
        passwordTxt.setText("");
    }

    public void addRecordStudent(View view) {
        DbManager db = new DbManager(this);

        // Temporary user_id and expiry date null
        String resStudent = db.addRecordStudent(null, courseTxt.getText().toString(), stageTxt.getText().toString(), null, schoolTxt.getText().toString());
        Toast.makeText(this, resStudent, Toast.LENGTH_LONG).show();

        studentNumberTxt.setText("");
        courseTxt.setText("");
        stageTxt.setText("");
    }

    public void addRecordStaff(View view) {
        DbManager db = new DbManager(this);

        // Temporary user_id null
        String resStaff = db.addRecordStaff(null, schoolTxt.getText().toString(), adminTxt.getText().toString());
        Toast.makeText(this, resStaff, Toast.LENGTH_LONG).show();

        schoolTxt.setText("");
        adminTxt.setText("");
    }
}
