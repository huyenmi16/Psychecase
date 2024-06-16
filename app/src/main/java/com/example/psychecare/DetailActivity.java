package com.example.psychecare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psychecare.R;
import com.example.psychecare.database.DoctorDatabase;
import com.example.psychecare.models.Doctor;

public class DetailActivity extends AppCompatActivity {
    private DoctorDatabase doctorDatabase;
    private int doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        doctorDatabase = new DoctorDatabase(this);

        // Lấy doctorId từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            doctorId = intent.getIntExtra("doctor_id", -1);
            if (doctorId != -1) {
                // Lấy thông tin của bác sĩ từ cơ sở dữ liệu
                Doctor doctor = doctorDatabase.getDoctorById(doctorId);
                if (doctor != null) {
                    // Hiển thị thông tin của bác sĩ trên giao diện
                    ImageView ivDoctor = findViewById(R.id.ivDoctor);


                    TextView tvName = findViewById(R.id.tvName);
                    tvName.setText(doctor.getName());

                    TextView tvSpecialty = findViewById(R.id.tvSpecialty);
                    tvSpecialty.setText(doctor.getSpecialty());

                    TextView tvGender = findViewById(R.id.tvGender);
                    tvGender.setText(doctor.getGender());

                    TextView tvExperience = findViewById(R.id.tvExperience);
                    tvExperience.setText(String.valueOf(doctor.getExperience()) + " years");

                    TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
                    tvPhoneNumber.setText(doctor.getPhoneNumber());

                    TextView tvConsultationFee = findViewById(R.id.tvConsultationFee);
                    tvConsultationFee.setText("VNĐ" + String.valueOf(doctor.getConsultationFee()));

                    // Đặt sự kiện click cho nút đặt lịch tư vấn
                    Button btnBookAppointment = findViewById(R.id.btnBookAppointment);
                    btnBookAppointment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Chuyển đến trang đặt lịch tư vấn
                            Intent intent = new Intent(DetailActivity.this, BookAppointmentActivity.class);
                            // Truyền doctorId qua Intent nếu cần thiết
                            intent.putExtra("doctor_id", doctorId);
                            intent.putExtra("doctor_name", doctor.getName());
                            intent.putExtra("doctor_image", doctor.getImage());
                            intent.putExtra("doctor_fee",doctor.getConsultationFee());

                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }
}
