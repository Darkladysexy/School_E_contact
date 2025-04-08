package com.example.demosll.ui.giaovien;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;

import com.example.demosll.R;
import com.example.demosll.database.DatabaseHelper;

public class DanhSachHocSinh extends Activity {

    Spinner spinnerLop;
    LinearLayout containerHocSinh;
    DatabaseHelper dbHelper;
    TextView tvXemThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoc_sinh);

        spinnerLop = findViewById(R.id.spinnerLop);
        containerHocSinh = findViewById(R.id.containerHocSinh);
        tvXemThem = findViewById(R.id.tvXemThem);

        dbHelper = new DatabaseHelper(this);

        // Load danh sách lớp vào Spinner
        loadLopToSpinner();

        // Xử lý khi chọn lớp
        spinnerLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenLop = parent.getItemAtPosition(position).toString();
                loadHocSinhTheoLop(tenLop);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Xem thêm (hiện tại chỉ toast hoặc xử lý sau)
        tvXemThem.setOnClickListener(view -> {
            // Xử lý tùy ý
        });
    }

    private void loadLopToSpinner() {
        Cursor cursor = dbHelper.getAllLop(); // Tạo hàm này để lấy danh sách lớp
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (cursor.moveToFirst()) {
            do {
                String tenLop = cursor.getString(cursor.getColumnIndex("TenLop"));
                adapter.add(tenLop);
            } while (cursor.moveToNext());
        }
        spinnerLop.setAdapter(adapter);
    }

    private void loadHocSinhTheoLop(String tenLop) {
        containerHocSinh.removeAllViews(); // Clear danh sách cũ

        Cursor cursor = dbHelper.getHocSinhTheoLop(tenLop); // Tạo hàm này để lấy học sinh theo lớp
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String hoTen = cursor.getString(cursor.getColumnIndex("HoTen"));
                String gioiTinh = cursor.getString(cursor.getColumnIndex("GioiTinh"));
                String sdt = cursor.getString(cursor.getColumnIndex("SDT")); // hoặc SDTPhuHuynh

                View view = createHocSinhView(hoTen, gioiTinh, sdt);
                containerHocSinh.addView(view);

            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    private View createHocSinhView(String hoTen, String gioiTinh, String sdtPhuHuynh) {
        // Tạo layout học sinh
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(16, 16, 16, 16);
        layout.setBackgroundResource(R.drawable.bg_item_student); // viền bo tròn
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 24);
        layout.setLayoutParams(params);

        // Đặt màu nền theo giới tính
        if (gioiTinh.equalsIgnoreCase("Nữ")) {
            layout.setBackgroundResource(R.drawable.bg_item_nu);
        } else {
            layout.setBackgroundResource(R.drawable.bg_item_nam);
        }


        // Icon trái
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.user); // icon avatar
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(100, 100);
        imgParams.gravity = Gravity.CENTER_VERTICAL;
        imgParams.setMargins(0, 0, 16, 0);
        img.setLayoutParams(imgParams);
        layout.addView(img);

        // Nội dung văn bản
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tvTen = new TextView(this);
        tvTen.setText(hoTen);
        tvTen.setTextSize(18);
        tvTen.setTypeface(null, Typeface.BOLD);

        TextView tvGioiTinh = new TextView(this);
        tvGioiTinh.setText(gioiTinh);

        TextView tvSDT = new TextView(this);
        tvSDT.setText("SDT Phụ huynh: " + (sdtPhuHuynh == null ? "09xxxx.xxx" : sdtPhuHuynh));

        contentLayout.addView(tvTen);
        contentLayout.addView(tvGioiTinh);
        contentLayout.addView(tvSDT);

        layout.addView(contentLayout);

        return layout;
    }
}
