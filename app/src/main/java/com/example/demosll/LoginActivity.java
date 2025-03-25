package com.example.demosll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    DatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Kiểm tra nếu đã đăng nhập trước đó
        sharedPreferences = getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("LOGGED_IN", false)) {
            String loaiTK = sharedPreferences.getString("LOAI_TK", "");
            dieuHuongTheoLoaiTK(loaiTK);
            finish();
            return;
        }

        // Ánh xạ View
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        dbHelper = new DatabaseHelper(this);

        // Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.checkLogin(email, password)) {
                String loaiTK = dbHelper.getLoaiTaiKhoan(email);

                if (loaiTK != null) {
                    // Lưu session đăng nhập
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("LOGGED_IN", true);
                    editor.putString("EMAIL", email);
                    editor.putString("LOAI_TK", loaiTK);
                    editor.apply();

                    // Chuyển màn hình theo loại tài khoản
                    dieuHuongTheoLoaiTK(loaiTK);
                    finish();
                } else {
                    Toast.makeText(this, "Lỗi hệ thống! Không xác định được loại tài khoản.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dieuHuongTheoLoaiTK(String loaiTK) {
        if ("GV".equals(loaiTK)) {
            startActivity(new Intent(this, GiaoVienActivity.class));
        } else if ("HS".equals(loaiTK)) {
            startActivity(new Intent(this, HocSinhActivity.class));
        } else {
            Toast.makeText(this, "Loại tài khoản không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
