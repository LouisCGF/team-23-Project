package com.example.team_23_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {

    private static final String dbname="csc2033_team23.sqlite?allowMultiQueries=true";

    public DbManager(Context context) {
        super(context, dbname, null, 1);
    }

    // This is for creating the table users (student and staff missing)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "SELECT *";
        db.execSQL(query);
    }

    // This is for deleting users table (student and staff missing)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("SELECT * FROM csc2033_team23.sqlite");
        onCreate(db);
    }

    public String addRecordUser(String email_address, String first_name, String last_name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("email_address", email_address);
        cv.put("first_name", first_name);
        cv.put("last_name", last_name);
        cv.put("password", password);

        long res = db.insert("USERS", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordStudent(String student_number, String course, String stage){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("student_number", student_number);
        cv.put("course", course);
        cv.put("stage", stage);

        long res = db.insert("STUDENT_INFO", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordStaff(String school, String admin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("school", school);
        cv.put("admin", admin);

        long res = db.insert("STAFF_INFO", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }
}
