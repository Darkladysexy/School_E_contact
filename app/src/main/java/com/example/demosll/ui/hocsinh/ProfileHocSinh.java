package com.example.demosll.ui.hocsinh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.R;
import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.ui.login.MainActivity;

public class ProfileHocSinh extends AppCompatActivity {

    String maTK;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_hoc_sinh);

        Intent intent = getIntent();
        String maHS = intent.getStringExtra("MaHS");


        TextView textViewName;
        TextView textViewSDT;
        TextView textViewEmail;
        Button btnLogout;
        Button btnUpdate;

        DatabaseHelper dbHelper;
        SQLiteDatabase db;

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.editTextEmail);
        textViewSDT = findViewById(R.id.editTextPhone);
        btnLogout = findViewById(R.id.buttonLogout);
        btnUpdate = findViewById(R.id.btnUpdate);

        Cursor cursor = db.rawQuery("SELECT * FROM TaiKhoan JOIN HocSinh ON TaiKhoan.MaTK = HocSinh.MaPhuHuynh WHERE MaHS = ?",new String[] { String.valueOf(maHS)});
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("HoTen"));
            String sdt = cursor.getString(cursor.getColumnIndexOrThrow("SDT"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            maTK = cursor.getString(cursor.getColumnIndexOrThrow("MaTK"));

            textViewName.setText(name);
            textViewSDT.setText(sdt);
            textViewEmail.setText(email);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = textViewSDT.getText().toString().trim();
                String email = textViewEmail.getText().toString().trim();
                if(sdt.isEmpty())
                {
                    Toast.makeText(ProfileHocSinh.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues values = new ContentValues();
                    values.put("SDT", textViewSDT.getText().toString());

                    int rowsAffected = db.update(
                            "TaiKhoan",                      // Tên bảng
                            values,                          // Dữ liệu cần cập nhật
                            "MaTK = ?",                      // Điều kiện WHERE
                            new String[]{String.valueOf(maTK)} // Tham số WHERE
                    );

                    if (rowsAffected > 0) {
                        Toast.makeText(ProfileHocSinh.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileHocSinh.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHocSinh.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        if (cursor != null) {
            cursor.close();
        }
    }
}