package com.example.team_23_project.databaseConnection;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_23_project.R;

public class InsertData extends AppCompatActivity {

    // Variable for the fields
    EditText firstNameTxt;
    EditText lastnameTxt;
    EditText emailTxt;
    EditText courseTxt;
    EditText stageTxt;
    EditText passwordTxt;
    EditText schoolTxt;
    EditText adminTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_student);
        setContentView(R.layout.register_staff);

        // USERS TABLE BINDING
        firstNameTxt = findViewById(R.id.firstNameStudentTxt);
        lastnameTxt = findViewById(R.id.lastNameStudentTxt);
        emailTxt = findViewById(R.id.emailAdrStudentTxt);
        passwordTxt = findViewById(R.id.passwordStudentRegTxt);

        // STUDENT TABLE BINDING
        courseTxt = findViewById(R.id.courseStudentTxt);
        stageTxt = findViewById(R.id.stageStudentTxt);

        // STAFF TABLE BINDING
        schoolTxt = findViewById(R.id.schoolStaffTxt);
        adminTxt = findViewById(R.id.adminTxt);
    }

}
