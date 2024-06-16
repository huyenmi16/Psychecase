package com.example.psychecare.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.psychecare.models.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PHONE";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PHONE TEXT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user.getPhone());
        contentValues.put(COL_3, user.getName());
        contentValues.put(COL_4, user.getEmail());
        contentValues.put(COL_5, user.getPassword());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Returns true if data is inserted, false otherwise
    }

    public User getUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL=? AND PASSWORD=?", new String[]{email, password});
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") User user = new User(
                    cursor.getInt(cursor.getColumnIndex(COL_1)),
                    cursor.getString(cursor.getColumnIndex(COL_2)),
                    cursor.getString(cursor.getColumnIndex(COL_3)),
                    cursor.getString(cursor.getColumnIndex(COL_4)),
                    cursor.getString(cursor.getColumnIndex(COL_5))
            );
            cursor.close();
            return user;
        }
        return null;
    }
}