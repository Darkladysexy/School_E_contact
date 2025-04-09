package com.example.demosll.ui.giaovien;

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

import com.example.demosll.R;
import com.example.demosll.adapter.TeacherAdapter;
import com.example.demosll.model.ListItem;
import com.example.demosll.ui.hocsinh.KenhThongTin;
import com.example.demosll.ui.hocsinh.TinNhan;

import java.util.ArrayList;

public class LienHePhuHuynh extends AppCompatActivity {
    ListView listMsg;
    TeacherAdapter adapter;
    ArrayList<ListItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lien_he_phu_huynh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listMsg = findViewById(R.id.listMsg);

        items = new ArrayList<ListItem>();
        items.add(new ListItem(R.drawable.profile,"Học Sinh : Nguyễn Ngụy Như "));
        items.add(new ListItem(R.drawable.profile,"Học Sinh :  Nguyễn Văn A"));
        items.add(new ListItem(R.drawable.profile,"Học Sinh :  Nguyễn Văn B"));
        items.add(new ListItem(R.drawable.profile,"Học Sinh :  Nguyễn Văn C"));
        items.add(new ListItem(R.drawable.profile,"Học Sinh :  Nguyễn Văn D"));
        items.add(new ListItem(R.drawable.profile,"Học Sinh :  Nguyễn Văn E"));
        adapter = new TeacherAdapter(this,items);
        listMsg.setAdapter(adapter);

    }
}