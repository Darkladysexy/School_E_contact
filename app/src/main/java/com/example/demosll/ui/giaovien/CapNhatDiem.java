package com.example.demosll.ui.giaovien;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demosll.R;
import com.example.demosll.adapter.TeacherAdapter;
import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.model.ListItem;

import java.util.ArrayList;

public class CapNhatDiem extends AppCompatActivity {
    ListView lv;
    TeacherAdapter adapter;
    ArrayList<ListItem> items;

    Spinner spinnerLop;
    ArrayAdapter<String> lopAdapter;
    ArrayList<String> danhSachLop;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cap_nhat_diem);

        lv = findViewById(R.id.lv);
        spinnerLop = findViewById(R.id.spinnerLop);

        // Khởi tạo danh sách lớp
        danhSachLop = new ArrayList<>();

        // Lấy danh sách lớp từ cơ sở dữ liệu
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<String> danhSachLopFromDB = getLopList(dbHelper);
        danhSachLop.addAll(danhSachLopFromDB);

        // Thiết lập Adapter cho Spinner
        lopAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachLop);
        lopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLop.setAdapter(lopAdapter);


        items = new ArrayList<>();
        items.add(new ListItem(R.drawable.toan, "Cập nhật điểm toán"));
        items.add(new ListItem(R.drawable.hoa, "Cập nhật điểm hóa"));
        adapter = new TeacherAdapter(this, items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myintent;
                if (i==0){
                    myintent=new Intent(CapNhatDiem.this, CapNhatDiemGiaoVien.class);
                    startActivity(myintent);
                }
                if (i==1){
                    myintent=new Intent(CapNhatDiem.this, CapNhatDiemGiaoVien.class);
                    startActivity(myintent);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Phương thức để lấy danh sách lớp từ cơ sở dữ liệu
    private ArrayList<String> getLopList(DatabaseHelper dbHelper) {
        ArrayList<String> lopList = new ArrayList<>();
        // Truy vấn cơ sở dữ liệu để lấy tên lớp
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT TenLop FROM Lop", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Kiểm tra sự tồn tại của cột "TenLop"
                int columnIndex = cursor.getColumnIndex("TenLop");
                if (columnIndex != -1) { // Kiểm tra nếu cột tồn tại
                    do {
                        String tenLop = cursor.getString(columnIndex);
                        lopList.add(tenLop);
                    } while (cursor.moveToNext());
                } else {
                    // Nếu không tồn tại cột "TenLop", log lỗi hoặc thông báo
                    Log.e("CapNhatDiem", "Cột 'TenLop' không tồn tại trong cơ sở dữ liệu");
                }
            }
            cursor.close();
        }

        // Kiểm tra nếu không có lớp nào được tìm thấy
        if (lopList.isEmpty()) {
            Toast.makeText(this, "Không có lớp nào được tìm thấy.", Toast.LENGTH_SHORT).show();
        }

        return lopList;
    }
}
