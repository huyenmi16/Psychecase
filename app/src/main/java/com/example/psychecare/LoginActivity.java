package com.example.psychecare;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.psychecare.database.DatabaseHelper;
import com.example.psychecare.models.User;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText edtUser, edtPassWord;
    MaterialButton btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        edtUser = findViewById(R.id.edtUser);
        edtPassWord = findViewById(R.id.edtPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtUser.getText().toString();
                String password = edtPassWord.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    User user = db.getUser(email, password);
                    if (user != null) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        // Lưu thông tin người dùng vào SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_name", user.getName());
                        editor.putString("user_phone_number", user.getPhone());
                        editor.putString("user_email", user.getEmail());
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Tạo HomeActivity để chuyển hướng sau khi đăng nhập
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Thông tin đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
