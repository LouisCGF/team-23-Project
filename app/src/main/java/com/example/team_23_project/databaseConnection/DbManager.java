package com.example.team_23_project.databaseConnection;

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

    public String addRecordSettings(String user_id, String theme, String text_size, Boolean colorblind){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
        cv.put("theme", theme);
        cv.put("text_size", text_size);
        cv.put("colorblind", colorblind);

        long res = db.insert("SETTINGS", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordSchoolBuilding(String school, String building_name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("school", school);
        cv.put("building_name", building_name);

        long res = db.insert("SCHOOL_BUILDING", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordBuildings(String building_name, String availability){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("building_name", building_name);
        cv.put("availability", availability);

        long res = db.insert("BUILDINGS", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordRoom(String room_id, String capacity, String type, String floor, String building_name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("room_id", room_id);
        cv.put("capacity", capacity);
        cv.put("type", type);
        cv.put("floor", floor);
        cv.put("building_name", building_name);

        long res = db.insert("ROOMS", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }

    public String addRecordAccessibility(String building_name, String room_id, String feature){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("building_name", building_name);
        cv.put("room_id", room_id);
        cv.put("feature", feature);

        long res = db.insert("ACCESSIBILITY", null, cv);

        if (res == -1)
            return "Failed!";
        else
            return "Successfully inserted!";
    }
}
