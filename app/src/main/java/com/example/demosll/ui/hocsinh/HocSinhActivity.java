package com.example.demosll.ui.hocsinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.model.ListItem;
import com.example.demosll.R;
import com.example.demosll.adapter.StudentAdapter;

import java.util.ArrayList;

public class HocSinhActivity extends AppCompatActivity {
    ListView lv;
    StudentAdapter adapter;
    ArrayList<ListItem> items;
    String MaHS;
    DatabaseHelper dbh = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_sinh);
        lv = findViewById(R.id.lv);

        // Lấy mã học sinh đang đăng nhập
        Intent intent = getIntent();
        MaHS = intent.getStringExtra("MaHS");

        // Danh sách menu cho Học Sinh
        items = new ArrayList<>();
        items.add(new ListItem(R.drawable.group20, "Thông tin của con"));
        items.add(new ListItem(R.drawable.group2, "Kênh thông báo, tin nhắn "));
        items.add(new ListItem(R.drawable.group21, "Thanh toán học phí"));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent;
                if(i==0){
                    myIntent=new Intent(HocSinhActivity.this, ThongTinHocSinh.class);
                    myIntent.putExtra("MaHS", MaHS);
                    startActivity(myIntent);
                }
                if(i==1){
                    myIntent=new Intent(HocSinhActivity.this, KenhThongTin.class);
                    startActivity(myIntent);
                }
                if(i==2){
                    myIntent=new Intent(HocSinhActivity.this, HocPhiActivity.class);
                    myIntent.putExtra("MaHS", MaHS);
                    startActivity(myIntent);
                }
            }
        });
        adapter = new StudentAdapter(this, items);
        lv.setAdapter(adapter);
    }
}
