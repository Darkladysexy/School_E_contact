package com.example.demosll.ui.giaovien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.model.ListItem;
import com.example.demosll.R;
import com.example.demosll.adapter.TeacherAdapter;

import java.util.ArrayList;

public class GiaoVienActivity extends AppCompatActivity {
    ListView lv;
    TeacherAdapter adapter;
    ArrayList<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_vien);

        lv = findViewById(R.id.lv);

        // Danh sách menu cho Giáo Viên
        items = new ArrayList<>();
        items.add(new ListItem(R.drawable.gv1, "Danh sách học sinh"));
        items.add(new ListItem(R.drawable.gv2, "Cập nhật điểm"));
        items.add(new ListItem(R.drawable.gv3, "Liên hệ phụ huynh"));

        adapter = new TeacherAdapter(this, items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent;
                if(i==0){
                    myIntent=new Intent(GiaoVienActivity.this, DanhSachHocSinh.class);
                    startActivity(myIntent);
                }
                if(i==1){
                    myIntent=new Intent(GiaoVienActivity.this, CapNhatDiem.class);
                    startActivity(myIntent);
                }
                if(i==2){
                    myIntent=new Intent(GiaoVienActivity.this, LienHePhuHuynh.class);
                    startActivity(myIntent);
                }
            }
        });
    }
}
