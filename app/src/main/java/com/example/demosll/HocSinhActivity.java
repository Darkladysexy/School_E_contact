package com.example.demosll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HocSinhActivity extends AppCompatActivity {
    Button btnXemDiem, btnXemHanhKiem, btnXemHocPhi, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_sinh);

        // Ánh xạ View
        btnXemDiem = findViewById(R.id.btnXemDiem);
        btnXemHanhKiem = findViewById(R.id.btnXemHanhKiem);
        btnXemHocPhi = findViewById(R.id.btnXemHocPhi);
        btnLogout = findViewById(R.id.btnLogout);

//        // Sự kiện mở màn hình xem điểm số
//        btnXemDiem.setOnClickListener(v -> {
//            startActivity(new Intent(HocSinhActivity.this, XemDiemActivity.class));
//        });

        // Sự kiện mở màn hình xem hạnh kiểm
//        btnXemHanhKiem.setOnClickListener(v -> {
//            startActivity(new Intent(HocSinhActivity.this, XemHanhKiemActivity.class));
//        });
//
//        // Sự kiện mở màn hình xem học phí
//        btnXemHocPhi.setOnClickListener(v -> {
//            startActivity(new Intent(HocSinhActivity.this, XemHocPhiActivity.class));
//        });

        // Đăng xuất
        btnLogout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("LOGGED_IN", false).apply();
            startActivity(new Intent(HocSinhActivity.this, LoginActivity.class));
            finish();
        });
    }
}
