package com.example.demosll.ui.hocsinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.model.HocPhi;
import com.example.demosll.R;
import com.example.demosll.adapter.TableAdapter;

import java.util.ArrayList;
import java.util.List;

public class HocPhiActivity extends AppCompatActivity {
    DatabaseHelper dbh;
    TableAdapter adapter;
    List<HocPhi> hp0;
    ImageView iv_Profile, iv_ThongBao;
    TextView tv_SoTienTong;
    Button btn_ThanhToan;
    RecyclerView rv_HocPhi;
    String maHS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_phi);

        // Khởi tạo các giá trị ban đầu
        initWidgets();

        // Tạo tên các cột của bảng học phí
        rv_HocPhi.setLayoutManager(new GridLayoutManager(this, 1));
        hp0 = new ArrayList<>();
        hp0.add(new HocPhi( "Tên phí", "Hạn đóng", "Số tiền", "Trạng thái"));
        adapter = new TableAdapter(hp0);
        rv_HocPhi.setAdapter(adapter);

        // Load dữ liệu vào bảng
        loadHocPhiData(maHS);

        // Sự kiện thanh toán
        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HocPhiActivity.this, ThanhToanHocPhi.class);
                startActivity(intent);
            }
        });
    }

    private void initWidgets(){
        tv_SoTienTong = findViewById(R.id.textView_SoTienTong);
        iv_Profile = findViewById(R.id.imageView_Profile);
        iv_ThongBao = findViewById(R.id.imageView_ThongBao);
        btn_ThanhToan = findViewById(R.id.button_ThanhToan);
        rv_HocPhi = findViewById(R.id.recyclerView_HocPhi);

        dbh = new DatabaseHelper(this);

        // Lấy mã học sinh đang đăng nhập
        Intent intent = getIntent();
        maHS = intent.getStringExtra("MaHS");
    }

    // Đọc dữ liệu từ database vào bảng
    private void loadHocPhiData(String MaHS){

        SQLiteDatabase db = dbh.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM HocPhi where MaHS = ? and TrangThai = ?", new String[]{MaHS, "Chưa đóng"});

        boolean exists = cursor.getCount() > 0;
        double tongTien = 0;

        if(exists){
            rv_HocPhi.setLayoutManager(new GridLayoutManager(this, 1));

            if(cursor.moveToFirst()){
                do{
                    String tenPhi = cursor.getString(1);
                    String hanDong = cursor.getString(4);
                    Double soTien = cursor.getDouble(2);
                    String trangThai = cursor.getString(3);
                    hp0.add(new HocPhi(tenPhi, hanDong, soTien.toString(), trangThai));
                    tongTien += soTien;
                }while (cursor.moveToNext());
            }

            adapter = new TableAdapter(hp0);
            rv_HocPhi.setAdapter(adapter);
            tv_SoTienTong.setText(Double.toString(tongTien));
        }
        cursor.close();
    }
}