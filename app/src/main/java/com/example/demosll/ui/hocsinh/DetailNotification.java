package com.example.demosll.ui.hocsinh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosll.R;

public class DetailNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_notification);

        TextView textViewNoiDung;
        Intent intent = getIntent();
        String noiDung = intent.getStringExtra("NoiDung");
        textViewNoiDung = findViewById(R.id.textViewNoidung);
        textViewNoiDung.setText(noiDung);
    }
}