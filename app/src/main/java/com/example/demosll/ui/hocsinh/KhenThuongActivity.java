package com.example.demosll.ui.hocsinh;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demosll.R;

import java.util.ArrayList;
import java.util.List;

public class KhenThuongActivity extends AppCompatActivity {

    Spinner spinner ;
    TextView txtNamHoc;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khen_thuong_hoc_sinh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> namhoc = new ArrayList<>();
        namhoc.add("2022-2023");
        namhoc.add("2023-2024");
        namhoc.add("2024-2025");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,namhoc);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner = findViewById(R.id.spinner3);
        spinner.setAdapter(adapter);
        spinner.setSelection(-1);

        img = findViewById(R.id.imageView4);
        img.setImageResource(R.drawable.bangkhen);
        txtNamHoc = (TextView) findViewById(R.id.textViewn);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                txtNamHoc.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}