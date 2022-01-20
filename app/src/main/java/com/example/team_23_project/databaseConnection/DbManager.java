package com.example.team_23_project.databaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    private static final String dbname="csc2033_team23.sqlite?allowMultiQueries=true";

    public DbManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "SELECT *";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("SELECT * FROM csc2033_team23.sqlite");
        onCreate(db);
    }

    public String addRecordUser(String user_id, String email_address, String first_name, String last_name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
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

    public String addRecordStudent(String user_id, String course, String stage, String expiry, String school){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id); //student number
        cv.put("course", course);
        cv.put("stage", stage);
        cv.put("expiry", expiry);
        cv.put("school", school);

        long res = db.insert("STUDENT_INFO", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordStaff(String user_id, String school, String admin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id); //staff id
        cv.put("school", school);
        cv.put("admin", admin);

        long res = db.insert("STAFF_INFO", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

}
