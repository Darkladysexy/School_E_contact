package com.example.demosll.ui.hocsinh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.model.ListItem;
import com.example.demosll.R;
import com.example.demosll.adapter.StudentAdapter;

import java.util.ArrayList;

public class ThongTinHocSinh extends AppCompatActivity {
    ListView listViewTTHS;
    StudentAdapter adapter;
    ArrayList<ListItem> items;
    String maHS;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_hoc_sinh);

        Intent intent = getIntent();
        maHS = intent.getStringExtra("MaHS");

        Contructor();
        adapter = new StudentAdapter(this, items);
        listViewTTHS.setAdapter(adapter);
        listViewTTHS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent;
                if(position==0){
                    myIntent=new Intent(ThongTinHocSinh.this, BangDiemActivity.class);
                    myIntent.putExtra("MaHS", maHS);
                    startActivity(myIntent);
                }
                if(position==1){
                    myIntent=new Intent(ThongTinHocSinh.this, HanhKiemActivity.class);
                    myIntent.putExtra("MaHS", maHS);
                    startActivity(myIntent);
                }
                if(position==2){
                    myIntent=new Intent(ThongTinHocSinh.this, KhenThuongActivity.class);
                    myIntent.putExtra("MaHS", maHS);
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