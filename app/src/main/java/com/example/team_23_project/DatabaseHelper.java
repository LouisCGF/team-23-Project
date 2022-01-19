package com.example.team_23_project;

import  android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app.db";
    private static final int SCHEMA = 1;

    // Created all of the tables needed for the database
    static final String TABLE_ACCESSIBILITY = "ACCESSIBILITY";
    static final String TABLE_BUILDINGS = "BUILDINGS";
    static final String TABLE_ROOMS = "ROOMS";
    static final String TABLE_SCHOOL_BUILDING = "SCHOOL_BUILDING";
    static final String TABLE_SETTINGS = "SETTINGS";
    static final String TABLE_STAFF_INFO = "STAFF_INFO";
    static final String TABLE_STUDENT_INFO = "STUDENT_INFO";
    static final String TABLE_USERS = "USERS";

    // TABLE_ACCESSIBILITY
    public static final String COLUMN_BUILDING_NAME = "building_name";
    public static final String COLUMN_ROOM_ID = "room_id";
    public static final String COLUMN_FEATURE = "feature";

    // TABLE_BUILDINGS
    public static final String COLUMN_AVAILABILITY = "availability";

    // TABLE_ROOMS
    public static final String COLUMN_CAPACITY = "capacity";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_FLOOR = "floor";

    // TABLE_SCHOOL_BUILDING
    public static final String COLUMN_SCHOOL = "school";

    // TABLE_SETTINGS
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_THEME = "theme";
    public static final String COLUMN_TEXT_SIZE = "text_size";
    public static final String COLUMN_COLORBLIND = "colorblind";

    // TABLE_STAFF_INFO
    public static final String COLUMN_ADMIN = "admin";

    // TABLE_STUDENT_INFO
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_STAGE = "stage";
    public static final String COLUMN_EXPIRY = "expiry";

    // TABLE_USERS
    public static final String COLUMN_EMAIL_ADDRESS = "email_address";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    // Declare each table in a different sql query (It makes easier to see a declaration of each table)
    @Override
    public void onCreate(SQLiteDatabase db) {

        //TABLE_ACCESSIBILITY
        db.execSQL("CREATE TABLE ACCESSIBILITY (" + COLUMN_BUILDING_NAME
                + " TEXT PRIMARY KEY ," + COLUMN_ROOM_ID
                + " INTEGER , " + COLUMN_FEATURE + " TEXT);");

        //TABLE_BUILDINGS
        db.execSQL("CREATE TABLE BUILDINGS (" + COLUMN_BUILDING_NAME
                + " TEXT PRIMARY KEY ," + COLUMN_AVAILABILITY + " TEXT);");

        //TABLE_ROOMS
        db.execSQL("CREATE TABLE ROOMS (" + COLUMN_ROOM_ID
                + " INTEGER PRIMARY KEY," + COLUMN_CAPACITY
                + " INTEGER ," + COLUMN_TYPE
                + " TEXT ," + COLUMN_FLOOR
                + " INTEGER ," + COLUMN_BUILDING_NAME + " TEXT);");

        //TABLE_SCHOOL_BUILDING
        db.execSQL("CREATE TABLE SCHOOL_BUILDING (" + COLUMN_BUILDING_NAME
                + " TEXT ," + COLUMN_SCHOOL
                + " TEXT PRIMARY KEY);");

        //TABLE_SETTINGS
        db.execSQL("CREATE TABLE SETTINGS (" + COLUMN_USER_ID
                + " INTEGER PRIMARY KEY ," + COLUMN_THEME
                + " TEXT ," + COLUMN_TEXT_SIZE
                + " TEXT ," + COLUMN_COLORBLIND + " INTEGER);");

        //TABLE_STAFF_INFO
        db.execSQL("CREATE TABLE STAFF_INFO (" + COLUMN_USER_ID
                + " INTEGER PRIMARY KEY ," + COLUMN_SCHOOL
                + " TEXT ," + COLUMN_ADMIN + " INTEGER);");

        //TABLE_STUDENT_INFO
        db.execSQL("CREATE TABLE STUDENT_INFO (" + COLUMN_USER_ID
                + " INTEGER PRIMARY KEY ," + COLUMN_COURSE
                + " TEXT ," + COLUMN_STAGE
                + " INTEGER ," + COLUMN_EXPIRY
                + " DATE ," + COLUMN_SCHOOL + " TEXT);");

        //TABLE_USERS
        db.execSQL("CREATE TABLE USERS (" + COLUMN_USER_ID
                + " INTEGER PRIMARY KEY ," + COLUMN_EMAIL_ADDRESS
                + " VARCHAR(219) ," + COLUMN_FIRST_NAME
                + " TEXT ," + COLUMN_LAST_NAME
                + " TEXT ," + COLUMN_PASSWORD + " TEXT);");


        // Add initial values into the table (if some are incorrect, change them)

        db.execSQL("INSERT INTO "+ TABLE_ACCESSIBILITY +" (" + COLUMN_BUILDING_NAME
                + ", " + COLUMN_ROOM_ID  + ", " + COLUMN_FEATURE + ") VALUES " +
                "('Urban Science Building', 1, 'Cool Building');");

        db.execSQL("INSERT INTO "+ TABLE_BUILDINGS +" (" + COLUMN_AVAILABILITY + ") VALUES ('Free');");

        db.execSQL("INSERT INTO "+ TABLE_ROOMS + " (" + COLUMN_ROOM_ID + ", " + COLUMN_CAPACITY
                + ", " + COLUMN_TYPE + ", " + COLUMN_FLOOR + ", " + COLUMN_BUILDING_NAME + ") VALUES" +
                "( 1, 32, 'Library', 3, 'Urban Science Building');");

        // Not sure if the field for school is computer science
        db.execSQL("INSERT INTO "+ TABLE_SCHOOL_BUILDING +" (" + COLUMN_BUILDING_NAME + ", "
                + COLUMN_SCHOOL + ") VALUES" + "('Urban Science Building', 'Computer Science');");

        db.execSQL("INSERT INTO "+ TABLE_SETTINGS +" (" + COLUMN_USER_ID + ", "
                + COLUMN_THEME + ", " + COLUMN_COLORBLIND + ") VALUES"
                + "(1, 'white', true);");

        db.execSQL("INSERT INTO "+ TABLE_STAFF_INFO +" (" + COLUMN_USER_ID + ", "
                + COLUMN_SCHOOL + ", " + COLUMN_ADMIN + ") VALUES"
                + "(1, 'Computer Science', true);");

        // COLUMN_SCHOOL same issue
        db.execSQL("INSERT INTO "+ TABLE_STUDENT_INFO + " (" + COLUMN_USER_ID + ", " + COLUMN_COURSE
                + ", " + COLUMN_STAGE + ", " + COLUMN_EXPIRY + ", " + COLUMN_SCHOOL + ") VALUES"
                + "( 1, 'G400', 2, 30/06/2023, 'Urban Science Building');");

        db.execSQL("INSERT INTO "+ TABLE_USERS + " (" + COLUMN_USER_ID + ", " + COLUMN_EMAIL_ADDRESS
                + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", " + COLUMN_PASSWORD + ") VALUES"
                + "( 1, 'paul@email.com', 'Paul', 'Smith', 'Smith123!');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACCESSIBILITY + TABLE_BUILDINGS + TABLE_ROOMS
                + TABLE_SCHOOL_BUILDING + TABLE_SETTINGS + TABLE_STAFF_INFO + TABLE_STUDENT_INFO
                + TABLE_USERS);
        onCreate(db);
    }


}
