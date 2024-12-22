package com.example.petbook;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "petBooking.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CAREGIVERS = "caregivers";
    private static final String TABLE_PAYMENT_METHODS = "payment_methods"; // New table for payment methods

    // Common column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CONTACT_NUMBER = "contact_number";

    // User-specific columns
    private static final String COLUMN_ADDRESS = "address";

    // Caregiver-specific columns
    private static final String COLUMN_RATING = "rating"; // Placeholder for future use

    // Payment method columns
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_PAYMENT_DETAILS = "payment_details"; // Simplified for example
    private String username;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_CONTACT_NUMBER + " TEXT, " +
                COLUMN_ADDRESS + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_CAREGIVERS_TABLE = "CREATE TABLE " + TABLE_CAREGIVERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_CONTACT_NUMBER + " TEXT, " +
                COLUMN_RATING + " REAL)";
        db.execSQL(CREATE_CAREGIVERS_TABLE);

        String CREATE_PAYMENT_METHODS_TABLE = "CREATE TABLE " + TABLE_PAYMENT_METHODS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_ID + " INTEGER, " +
                COLUMN_PAYMENT_DETAILS + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "))";
        db.execSQL(CREATE_PAYMENT_METHODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAREGIVERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_METHODS);
        onCreate(db);
    }

    public boolean addUser(String username, String password, String email, String contactNumber, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_CONTACT_NUMBER, contactNumber);
        contentValues.put(COLUMN_ADDRESS, address);

        long result = db.insert(TABLE_USERS, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean addCaregiver(String username, String password, String email, String contactNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_CONTACT_NUMBER, contactNumber);

        long result = db.insert(TABLE_CAREGIVERS, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public boolean checkCaregiver(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CAREGIVERS,
                new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    @SuppressLint("Range")
    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_USERNAME, COLUMN_EMAIL, COLUMN_CONTACT_NUMBER, COLUMN_ADDRESS},
                COLUMN_USERNAME + "=?",
                new String[]{username},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))
            );
            cursor.close();
        }

        db.close();
        return user;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_CONTACT_NUMBER, user.getContactNumber());
        values.put(COLUMN_ADDRESS, user.getAddress());

        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_USERNAME + "=?", new String[]{user.getUsername()});
        db.close();
        return rowsAffected > 0;
    }

    public boolean addPaymentMethod(int userId, String paymentDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_PAYMENT_DETAILS, paymentDetails);

        long result = db.insert(TABLE_PAYMENT_METHODS, null, values);
        db.close();
        return result != -1;
    }
    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                null,
                COLUMN_USERNAME + "=?",
                new String[]{username},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") User user = new User(
                    cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))
            );
            cursor.close();
            db.close();
            return user;
        }

        if (cursor != null) cursor.close();
        db.close();
        return null;
    }

    public boolean updateUser(String oldUsername, String newUsername, String email, String contactNumber, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, newUsername);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_CONTACT_NUMBER, contactNumber);
        contentValues.put(COLUMN_ADDRESS, address);

        int rows = db.update(TABLE_USERS, contentValues, COLUMN_USERNAME + "=?", new String[]{oldUsername});
        db.close();
        return rows > 0;
    }

}
