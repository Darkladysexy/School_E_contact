package com.example.demosll.ui.hocsinh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.adapter.DiemAdapter;
import com.example.demosll.model.DiemSo;
import com.example.demosll.R;

import java.util.ArrayList;
import java.util.List;

public class BangDiemActivity extends AppCompatActivity {
    private Spinner spinnerNamHoc;
    private RecyclerView recyclerViewDiem;
    private DatabaseHelper databaseHelper;
    private DiemAdapter diemAdapter;
    private String selectedNamHoc = "2024-2025"; // Mặc định
    private String maHS;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_diem);

        // Khởi tạo DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Ánh xạ các view
        spinnerNamHoc = findViewById(R.id.spinnerNamHoc);
        recyclerViewDiem = findViewById(R.id.recyclerViewDiem);
        // Danh sách năm học
        List<String> namHocList = new ArrayList<>();
        namHocList.add("2024-2025");

        // Lấy mã hs
        Intent intent = getIntent();
        maHS = intent.getStringExtra("MaHS");

        // Thiết lập Spinner năm học
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namHocList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNamHoc.setAdapter(adapter);

        // Sự kiện chọn năm học
        spinnerNamHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNamHoc = namHocList.get(position);
                loadDiemSo(); // Tải lại điểm số khi chọn năm học
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Thiết lập RecyclerView
        recyclerViewDiem.setLayoutManager(new LinearLayoutManager(this));

        // Tải điểm số ban đầu
        loadDiemSo();
    }

    private void loadDiemSo() {
        List<DiemSo> diemSoList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Tạo tên cột
        DiemSo diemSo1 = new DiemSo("Môn học", "GK1", "CK1", "GK2", "CK2");
        diemSoList.add(diemSo1);
        // Truy vấn điểm số của học sinh theo năm học
        Cursor cursor = db.rawQuery(
                "SELECT MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2 " +
                        "FROM DiemSo " +
                        "WHERE MaHS = ? AND NamHoc = ?",
                new String[]{maHS, selectedNamHoc}
        );

        while (cursor.moveToNext()) {
            String monHoc = cursor.getString(0);
            String diemGK1 = cursor.getString(1);
            String diemCK1 = cursor.getString(2);
            String diemGK2 = cursor.getString(3);
            String diemCK2 = cursor.getString(4);

            DiemSo diemSo = new DiemSo(monHoc, diemGK1, diemCK1, diemGK2, diemCK2);

            diemSoList.add(diemSo);
        }

        cursor.close();
        db.close();

        // Thiết lập adapter cho RecyclerView
        diemAdapter = new DiemAdapter(diemSoList);
        recyclerViewDiem.setAdapter(diemAdapter);
    }
}