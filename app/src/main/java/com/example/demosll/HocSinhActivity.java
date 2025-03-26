package com.example.demosll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HocSinhActivity extends AppCompatActivity {
    ListView lv;
    StudentAdapter adapter;
    ArrayList<ListItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_sinh);
        lv = findViewById(R.id.lv);

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
                    myIntent=new Intent(HocSinhActivity.this,ThongTinHocSinh.class);
                    startActivity(myIntent);
                }
                if(i==1){
                    myIntent=new Intent(HocSinhActivity.this, KenhThongTin.class);
                    startActivity(myIntent);
                }
                if(i==2){
                    myIntent=new Intent(HocSinhActivity.this, ThanhToanHocPhi.class);
                    startActivity(myIntent);
                }
            }
        });
        adapter = new StudentAdapter(this, items);
        lv.setAdapter(adapter);
    }
}
