package com.example.demosll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class KenhThongTin extends AppCompatActivity {
    ListView listMsg;
    TeacherAdapter adapter;
    ArrayList<ListItem> Items;
//    ArrayList<TeacherAdapter> teacherAdapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kenh_thong_tin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listMsg = findViewById(R.id.listMsg);

        Items = new ArrayList<>();
        Items.add(new ListItem(R.drawable.profile,"Thầy Nguyễn Văn Hải \n Giáo Viên Chủ Nhiệm "));
        Items.add(new ListItem(R.drawable.meeting,"Nhóm phụ huynh lớp 8A3"));

        listMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(KenhThongTin.this,TinNhan.class);
                    intent.putExtra("Ten","Nguyễn Văn Hải");
                    intent.putExtra("profile",R.drawable.profile);
                    startActivity(intent);
                }

                if(position ==1) {
                    Intent intent = new Intent(KenhThongTin.this,TinNhan.class);
                    intent.putExtra("Ten","Nhóm");
                    intent.putExtra("profile",R.drawable.meeting);
                    startActivity(intent);

                }
            }
        });
        adapter = new TeacherAdapter(this,Items);
        listMsg.setAdapter(adapter);
    }
}