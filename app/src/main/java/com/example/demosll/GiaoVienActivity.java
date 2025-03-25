package com.example.demosll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GiaoVienActivity extends AppCompatActivity {
    Button btnQuanLyDiem, btnQuanLyHanhKiem, btnQuanLyHocPhi, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_vien);

        // Ánh xạ View
        btnQuanLyDiem = findViewById(R.id.btnQuanLyDiem);
        btnQuanLyHanhKiem = findViewById(R.id.btnQuanLyHanhKiem);
        btnQuanLyHocPhi = findViewById(R.id.btnQuanLyHocPhi);
        btnLogout = findViewById(R.id.btnLogout);

        // Sự kiện mở màn hình quản lý điểm số
//        btnQuanLyDiem.setOnClickListener(v -> {
//            startActivity(new Intent(GiaoVienActivity.this, QuanLyDiemActivity.class));
//        });
//
//        // Sự kiện mở màn hình quản lý hạnh kiểm
//        btnQuanLyHanhKiem.setOnClickListener(v -> {
//            startActivity(new Intent(GiaoVienActivity.this, QuanLyHanhKiemActivity.class));
//        });
//
//        // Sự kiện mở màn hình quản lý học phí
//        btnQuanLyHocPhi.setOnClickListener(v -> {
//            startActivity(new Intent(GiaoVienActivity.this, QuanLyHocPhiActivity.class));
//        });

        // Đăng xuất
        btnLogout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("LOGGED_IN", false).apply();
            startActivity(new Intent(GiaoVienActivity.this, LoginActivity.class));
            finish();
        });
    }
}
