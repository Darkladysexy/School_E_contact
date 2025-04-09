package com.example.demosll;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.ui.login.MainActivity;

import org.w3c.dom.Text;

public class ProfileGiaoVien extends AppCompatActivity {
    EditText editTextPhone;
    EditText editTextEmail;
    TextView textViewName;

    Button btnLogout;
    Button btnUpdate;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_giao_vien);

        Intent intent = getIntent();
        String maGV = intent.getStringExtra("MaGV");

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        textViewName = findViewById(R.id.textViewName);
        btnLogout = findViewById(R.id.buttonLogout);
        btnUpdate = findViewById(R.id.btnUpdate);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM TaiKhoan WHERE MaTK = ?", new String[] { String.valueOf(maGV)});
        if (cursor!=null && cursor.moveToFirst())
        {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("HoTen"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("SDT"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));

            textViewName.setText(name);
            editTextEmail.setText(email);
            editTextPhone.setText(phone);

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = editTextPhone.getText().toString().trim();
                if(sdt.isEmpty())
                {
                    Toast.makeText(ProfileGiaoVien.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues values = new ContentValues();
                    values.put("SDT", editTextPhone.getText().toString());

                    int rowsAffected = db.update(
                            "TaiKhoan",                      // Tên bảng
                            values,                          // Dữ liệu cần cập nhật
                            "MaTK = ?",                      // Điều kiện WHERE
                            new String[]{String.valueOf(maGV)} // Tham số WHERE
                    );

                    if (rowsAffected > 0) {
                        Toast.makeText(ProfileGiaoVien.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileGiaoVien.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileGiaoVien.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });






    }
}