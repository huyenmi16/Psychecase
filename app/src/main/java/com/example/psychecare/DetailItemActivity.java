package com.example.psychecare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psychecare.models.Item;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.psychecare.database.DoctorDatabase;
import com.example.psychecare.database.ItemDatabase;
import com.example.psychecare.models.Doctor;

public class DetailItemActivity extends AppCompatActivity {

    private ItemDatabase itemDatabase;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        itemDatabase = new ItemDatabase(this);

        // Lấy doctorId từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            itemId = intent.getIntExtra("item_id", -1);
            if (itemId != -1) {
                // Lấy thông tin của bác sĩ từ cơ sở dữ liệu
                Item item = itemDatabase.getItemById(itemId);
                if (item != null) {



                    TextView tvName = findViewById(R.id.textViewTitle);
                    tvName.setText(item.getTitle());

                    TextView tvSpecialty = findViewById(R.id.textViewDescription);
                    tvSpecialty.setText(item.getDescription());

                    TextView tvGender = findViewById(R.id.textViewSolution);
                    tvGender.setText(item.getSolution());



                    // Đặt sự kiện click cho nút đặt lịch tư vấn
                    Button btnBack = findViewById(R.id.btnBack);
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Chuyển đến trang đặt lịch tư vấn
                            Intent intent = new Intent(DetailItemActivity.this, MainActivity.class);


                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }
}