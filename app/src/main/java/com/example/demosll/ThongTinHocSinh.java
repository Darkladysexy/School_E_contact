package com.example.demosll;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ThongTinHocSinh extends AppCompatActivity {
    ListView listViewTTHS;
    StudentAdapter adapter;
    ArrayList<ListItem> items;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_hoc_sinh);

        Contructor();
        adapter = new StudentAdapter(this, items);
        listViewTTHS.setAdapter(adapter);
        listViewTTHS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent;
                if(position==0){
                    myIntent=new Intent(ThongTinHocSinh.this,bang_diem_hoc_sinh.class);
                    startActivity(myIntent);
                }
                if(position==1){
                    myIntent=new Intent(ThongTinHocSinh.this, hanh_kiem_hoc_sinh.class);
                    startActivity(myIntent);
                }
                if(position==2){
                    myIntent=new Intent(ThongTinHocSinh.this, khen_thuong_hoc_sinh.class);
                    startActivity(myIntent);
                }
            }
        });
    }

    private void Contructor() {
        listViewTTHS = findViewById(R.id.listViewTTHS);
        items = new ArrayList<ListItem>();
        items.add(new ListItem(R.drawable.group2s, "Bảng điểm"));
        items.add(new ListItem(R.drawable.group2n, "Hạnh kiểm"));
        items.add(new ListItem(R.drawable.group2m, "Khen thưởng"));
    }
}