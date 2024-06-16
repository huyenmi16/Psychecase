package com.example.psychecare.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.psychecare.models.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "appointment.db";
    private static final int DATABASE_VERSION = 1;

    // Appointment table
    private static final String TABLE_APPOINTMENT = "appointment";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DOCTOR_NAME = "doctor_name";
    private static final String COLUMN_DOCTOR_IMAGE = "doctor_image";
    private static final String COLUMN_APPOINTMENT_DATE = "appointment_date";
    private static final String COLUMN_APPOINTMENT_TIME = "appointment_time";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PHONE_NUMBER = "user_phone_number";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_CONSULTATION_FEE = "consultation_fee";
    private static final String COLUMN_ISSUE = "issue";

    // Create table query
    private static final String CREATE_APPOINTMENT_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DOCTOR_NAME + " TEXT, " +
            COLUMN_DOCTOR_IMAGE + " TEXT, " +
            COLUMN_APPOINTMENT_DATE + " TEXT, " +
            COLUMN_APPOINTMENT_TIME + " TEXT, " +
            COLUMN_USER_NAME + " TEXT, " +
            COLUMN_USER_PHONE_NUMBER + " TEXT, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            COLUMN_NOTE + " TEXT, " +
            COLUMN_CONSULTATION_FEE + " REAL, " +
            COLUMN_ISSUE + " TEXT)";

    public AppointmentDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_APPOINTMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
        onCreate(db);
    }

    public void addAppointment(Appointment appointment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR_NAME, appointment.getDoctorName());
        values.put(COLUMN_DOCTOR_IMAGE, appointment.getDoctorImage());
        values.put(COLUMN_APPOINTMENT_DATE, appointment.getAppointmentDate());
        values.put(COLUMN_APPOINTMENT_TIME, appointment.getAppointmentTime());
        values.put(COLUMN_USER_NAME, appointment.getUserName());
        values.put(COLUMN_USER_PHONE_NUMBER, appointment.getUserPhoneNumber());
        values.put(COLUMN_USER_EMAIL, appointment.getUserEmail());
        values.put(COLUMN_NOTE, appointment.getNote());
        values.put(COLUMN_CONSULTATION_FEE, appointment.getConsultationFee());
        values.put(COLUMN_ISSUE, appointment.getIssue());
        db.insert(TABLE_APPOINTMENT, null, values);
        db.close();

    }

    @SuppressLint("Range")
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENT, null);

        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment();
                appointment.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                appointment.setDoctorName(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_NAME)));
                appointment.setDoctorImage(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_IMAGE)));
                appointment.setAppointmentDate(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_DATE)));
                appointment.setAppointmentTime(cursor.getString(cursor.getColumnIndex(COLUMN_APPOINTMENT_TIME)));
                appointment.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                appointment.setUserPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE_NUMBER)));
                appointment.setUserEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                appointment.setNote(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));
                appointment.setConsultationFee(cursor.getString(cursor.getColumnIndex(COLUMN_CONSULTATION_FEE)));
                appointment.setIssue(cursor.getString(cursor.getColumnIndex(COLUMN_ISSUE)));
                appointments.add(appointment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return appointments;
    }
    public int countAppointments(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_APPOINTMENT + " WHERE " +
                COLUMN_ISSUE + " LIKE ?";
        Cursor cursor = db.rawQuery(countQuery, new String[]{"%" + query + "%"});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }
}
