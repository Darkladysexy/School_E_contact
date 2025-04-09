package com.example.demosll.ui.hocsinh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.R;
import com.example.demosll.adapter.ThongBaoAdapter;
import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.model.ThongBao;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ListView lvThongBao;
    ArrayList<ThongBao> thongBaos;
    ThongBaoAdapter adapter;

    DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        lvThongBao = findViewById(R.id.lvThongBao);
        thongBaos = new ArrayList<ThongBao>();

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM ThongBao",null);

        if (cursor.moveToFirst())
        {
            do
            {
                String tieuDe = cursor.getString(cursor.getColumnIndexOrThrow("TieuDe"));
                String noiDung = cursor.getString(cursor.getColumnIndexOrThrow("NoiDung"));
                thongBaos.add(new ThongBao(tieuDe,noiDung));
            }while(cursor.moveToNext());
        }

        lvThongBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Notification.this, DetailNotification.class);
                String noiDung = thongBaos.get(position).NoiDung;
                intent.putExtra("NoiDung",noiDung);
                startActivity(intent);
            }
        });

        adapter = new ThongBaoAdapter(this,thongBaos);
        lvThongBao.setAdapter(adapter);


    }
}