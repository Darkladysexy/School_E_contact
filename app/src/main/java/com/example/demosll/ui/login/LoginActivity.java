package com.example.demosll.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.ui.giaovien.GiaoVienActivity;
import com.example.demosll.ui.hocsinh.HocSinhActivity;
import com.example.demosll.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    DatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;
    String email_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Kiểm tra nếu đã đăng nhập trước đó
        sharedPreferences = getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("LOGGED_IN", false)) {
            String loaiTK = sharedPreferences.getString("LOAI_TK", "");
            dieuHuongTheoLoaiTK(loaiTK, email_login);
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
                    dieuHuongTheoLoaiTK(loaiTK, email);
                    finish();
                } else {
                    Toast.makeText(this, "Lỗi hệ thống! Không xác định được loại tài khoản.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dieuHuongTheoLoaiTK(String loaiTK, String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        email_login = email;

        if ("GV".equals(loaiTK)) {

            // Lấy mã GV và gửi sang các Activity khác
            Cursor cursor = db.rawQuery("select * from TaiKhoan where Email = ?", new String[]{email});

            if (cursor != null && cursor.moveToFirst()){
                int indexColumn_MaGV = cursor.getColumnIndex("MaTK");
                int maGV = cursor.getInt(indexColumn_MaGV);
                cursor.close();

                Intent intent = new Intent(this, GiaoVienActivity.class);
                intent.putExtra("MaGV", Integer.toString(maGV));
                startActivity(intent);
            }
        }else if ("HS".equals(loaiTK)) {
        // Lấy mã HS và gửi sang Activity khác
            Cursor cursor = db.rawQuery("SELECT * FROM TaiKhoan JOIN HocSinh ON TaiKhoan.MaTK = HocSinh.MaPhuHuynh WHERE TaiKhoan.Email = ?", new String[]{email});

            if (cursor != null && cursor.moveToFirst()) { // Kiểm tra nếu có dữ liệu
                int indexColumn_MaHS = cursor.getColumnIndex("MaHS");

                if (indexColumn_MaHS != -1) { // Đảm bảo cột tồn tại
                    int maHS = cursor.getInt(indexColumn_MaHS);
                    int maTK = cursor.getInt(12);
                    cursor.close(); // Đóng cursor sau khi dùng xong

                    Intent intent = new Intent(this, HocSinhActivity.class);
                    intent.putExtra("MaHS", Integer.toString(maHS)); // Gửi trực tiếp số nguyên thay vì chuỗi
                    startActivity(intent);
                }

            }else {
                Toast.makeText(this, "Không tìm thấy học sinh!", Toast.LENGTH_SHORT).show();
            }

            if (cursor != null) cursor.close(); // Đảm bảo cursor luôn được đóng

        }else {
            Toast.makeText(this, "Loại tài khoản không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
