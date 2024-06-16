package com.example.psychecare;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;

import com.example.psychecare.database.AppointmentDatabase;
import com.example.psychecare.database.DoctorDatabase;
import com.example.psychecare.models.Appointment;
import com.example.psychecare.models.Doctor;


public class BookAppointmentActivity extends AppCompatActivity {
    private ImageView ivDoctor;
    private TextView tvDoctorName, tvConsultationFee;
    private Spinner spIssue, spTime;
    private EditText etUserName, etUserPhoneNumber, etUserEmail, etNote;
    private DatePicker datePickerAppointment;
    private Button btnSubmit;
    private DoctorDatabase doctorDatabase;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_appointment);
        ivDoctor = findViewById(R.id.ivDoctor);
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvConsultationFee = findViewById(R.id.tvConsultationFee);
        spIssue = findViewById(R.id.spIssue);
        spTime = findViewById(R.id.spTime);
        etUserName = findViewById(R.id.etUserName);
        etUserPhoneNumber = findViewById(R.id.etUserPhoneNumber);
        etUserEmail = findViewById(R.id.etUserEmail);
        etNote = findViewById(R.id.etNote);
        datePickerAppointment = findViewById(R.id.datePickerAppointment);
        btnSubmit = findViewById(R.id.btnSubmit);

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String doctorName = extras.getString("doctor_name");
//            String consultationFee = extras.getString("doctor_fee");
//            int doctorImage = extras.getInt("doctor_image"); // Assuming you pass image resource ID
//
//            // Set doctor information to views
//            tvDoctorName.setText(doctorName);
//            tvConsultationFee.setText(consultationFee);
//            ivDoctor.setImageResource(doctorImage);
//        }

        doctorDatabase = new DoctorDatabase(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int doctorId = extras.getInt("doctor_id");

            // Get doctor information based on doctor ID
            Doctor doctor = doctorDatabase.getDoctorById(doctorId);
            if (doctor != null) {
                // Set doctor information to views
                tvDoctorName.setText(doctor.getName());
                tvConsultationFee.setText("VNĐ" + String.valueOf(doctor.getConsultationFee()));
                int imageResId = getResources().getIdentifier(doctor.getImage(), "drawable", getPackageName());
                if (imageResId == 0) {
                    imageResId = R.drawable.ic_person; // Use default image
                }
                ivDoctor.setImageResource(imageResId);
            }
        }
        // Set user information to EditText fields from session
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userName = sharedPreferences.getString("user_name", "");
        String userPhoneNumber = sharedPreferences.getString("user_phone_number", "");
        String userEmail = sharedPreferences.getString("user_email", "");

        etUserName.setText(userName);
        etUserPhoneNumber.setText(userPhoneNumber);
        etUserEmail.setText(userEmail);

        Calendar calendar = Calendar.getInstance();
        datePickerAppointment.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);

        ArrayAdapter<CharSequence> issueAdapter = ArrayAdapter.createFromResource(this,
                R.array.issues_array, android.R.layout.simple_spinner_item);
        issueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIssue.setAdapter(issueAdapter);

        // Set up spinner for time selection
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.times_array, android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTime.setAdapter(timeAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input values
                String userName = etUserName.getText().toString();
                String userPhoneNumber = etUserPhoneNumber.getText().toString();
                String userEmail = etUserEmail.getText().toString();
                String note = etNote.getText().toString();
                String selectedIssue = spIssue.getSelectedItem().toString();
                String selectedTime = spTime.getSelectedItem().toString();

                // Create an instance of Appointment to store the appointment details
                Appointment appointment = new Appointment();
                appointment.setDoctorName(tvDoctorName.getText().toString());
                appointment.setDoctorImage(""); // You might want to add this information from your UI
                appointment.setAppointmentDate(datePickerAppointment.getDayOfMonth() + "/" +
                        (datePickerAppointment.getMonth() + 1) + "/" +
                        datePickerAppointment.getYear()); // Assuming date format is dd/MM/yyyy
                appointment.setAppointmentTime(selectedTime);
                appointment.setUserName(userName);
                appointment.setUserPhoneNumber(userPhoneNumber);
                appointment.setUserEmail(userEmail);
                appointment.setNote(note);
                appointment.setConsultationFee(tvConsultationFee.getText().toString());
                appointment.setIssue(selectedIssue);


                // Save the appointment to the database
                AppointmentDatabase appointmentDatabase = new AppointmentDatabase(BookAppointmentActivity.this);

                appointmentDatabase.addAppointment(appointment);
                Toast.makeText(BookAppointmentActivity.this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookAppointmentActivity.this, MainActivity.class); // Tạo HomeActivity để chuyển hướng sau khi đăng nhập
                startActivity(intent);
                createNotificationChannel(BookAppointmentActivity.this, "Đặt lịch thành công!");

            }

            @SuppressLint("MissingPermission")
            private void createNotificationChannel(Context context, String mess) {
                // Tạo NotificationChannel chỉ trên API 26+ vì lớp NotificationChannel mới và không có trong thư viện hỗ trợ
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "Thông báo";
                    String description = mess;
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel("1", name, importance);

                    channel.setDescription(description);
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                    notificationManager.createNotificationChannel(channel);
                }

                // Tạo Bitmap từ tập tin hình ảnh drawable
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

                // Xây dựng thông báo
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                        .setContentTitle("Thông báo")
                        .setContentText(mess)
                        .setColor(Color.RED)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setLargeIcon(bitmap)
                        .setSmallIcon(R.drawable.ic_doc)
                        .setCategory(NotificationCompat.CATEGORY_ALARM)
                        .setAutoCancel(true);

                // Hiển thị thông báo
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                managerCompat.notify(1, builder.build());
            }



        });

    }


}