package com.example.psychecare.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.psychecare.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "doctor.db";
    private static final int DATABASE_VERSION = 1;

    // Doctor table
    private static final String TABLE_DOCTOR = "doctor";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPECIALTY = "specialty";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_EXPERIENCE = "experience";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_CONSULTATION_FEE = "consultation_fee";
    private static final String COLUMN_IS_FAVORITE = "is_favorite";

    // Create table query
    private static final String CREATE_DOCTOR_TABLE = "CREATE TABLE " + TABLE_DOCTOR + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_IMAGE + " TEXT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_SPECIALTY + " TEXT, " +
            COLUMN_GENDER + " TEXT, " +
            COLUMN_EXPERIENCE + " INTEGER, " +
            COLUMN_PHONE_NUMBER + " TEXT, " +
            COLUMN_CONSULTATION_FEE + " REAL, " +
            COLUMN_IS_FAVORITE + " INTEGER DEFAULT 0)";

    public DoctorDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE);
        addSampleDoctors(db);
    }
    private void addSampleDoctors(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Thêm bác sĩ mẫu 1
        values.put(COLUMN_IMAGE, "image1.jpg");
        values.put(COLUMN_NAME, "Nguyễn Văn A");
        values.put(COLUMN_SPECIALTY, "Tư vấn cảm xúc");
        values.put(COLUMN_GENDER, "Male");
        values.put(COLUMN_EXPERIENCE, 5);
        values.put(COLUMN_PHONE_NUMBER, "0123456789");
        values.put(COLUMN_CONSULTATION_FEE, 50.0);
        values.put(COLUMN_IS_FAVORITE, 0);
        db.insert(TABLE_DOCTOR, null, values);

        // Thêm bác sĩ mẫu 2
        values.clear(); // Xóa giá trị hiện tại của ContentValues để sử dụng cho bác sĩ mẫu tiếp theo
        values.put(COLUMN_IMAGE, "image2.jpg");
        values.put(COLUMN_NAME, "Trần Thị B");
        values.put(COLUMN_SPECIALTY, "Tư vấn hôn nhân");
        values.put(COLUMN_GENDER, "Female");
        values.put(COLUMN_EXPERIENCE, 10);
        values.put(COLUMN_PHONE_NUMBER, "0987654321");
        values.put(COLUMN_CONSULTATION_FEE, 60.0);
        values.put(COLUMN_IS_FAVORITE, 0);
        db.insert(TABLE_DOCTOR, null, values);


        // Thêm các bác sĩ mẫu khác tại đây nếu cần
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        onCreate(db);
    }
    @SuppressLint("Range")
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DOCTOR, null);

        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                doctor.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                doctor.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                doctor.setSpecialty(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALTY)));
                doctor.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                doctor.setExperience(cursor.getInt(cursor.getColumnIndex(COLUMN_EXPERIENCE)));
                doctor.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
                doctor.setConsultationFee(cursor.getDouble(cursor.getColumnIndex(COLUMN_CONSULTATION_FEE)));
                doctor.setFavorite(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1);
                doctors.add(doctor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return doctors;
    }
    @SuppressLint("Range")
    public Doctor getDoctorById(int doctorId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(doctorId) };

        Cursor cursor = db.query(
                TABLE_DOCTOR,                        // Bảng để truy vấn
                null,                                // Danh sách các cột để trả về, null sẽ trả về tất cả các cột
                selection,                           // Câu lệnh WHERE
                selectionArgs,                       // Giá trị của WHERE
                null,                                // GROUP BY
                null,                                // HAVING
                null                                 // ORDER BY
        );

        Doctor doctor = null;
        if (cursor != null && cursor.moveToFirst()) {
            doctor = new Doctor();
            doctor.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            doctor.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
            doctor.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            doctor.setSpecialty(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALTY)));
            doctor.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            doctor.setExperience(cursor.getInt(cursor.getColumnIndex(COLUMN_EXPERIENCE)));
            doctor.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
            doctor.setConsultationFee(cursor.getDouble(cursor.getColumnIndex(COLUMN_CONSULTATION_FEE)));
            doctor.setFavorite(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE)) == 1);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return doctor;
    }


}
