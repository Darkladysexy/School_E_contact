package com.example.demosll.ui.giaovien;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosll.R;
import com.example.demosll.adapter.CapNhatDiemAdapter;
import com.example.demosll.database.DatabaseHelper;
import com.example.demosll.model.DiemSo;

import java.util.ArrayList;

public class CapNhatDiemGiaoVien extends AppCompatActivity {

    Spinner spinnerHocSinh;
    TextView textViewNamHoc;
    Button btnCapNhat;
    RecyclerView recyclerViewNhapDiem;
    CapNhatDiemAdapter adapterDiemSo;
    DatabaseHelper dbh = new DatabaseHelper(this);
    String MaGV, MaHS;
    ArrayList<String> listHocSinh = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_diem_giao_vien);

        // Khởi tạo các giá trị ban đầu
        initWidget();

        // Đọc db danh sách năm học và đưa vào spinner
        hienThiNamHoc();

        // Đọc db danh sách học sinh và đưa vào spinner
        hienThiHocSinh();

        // Event khi người dùng chọn học sinh khác trên spinner
        spinnerHocSinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteDatabase db = dbh.getReadableDatabase();
                Cursor cursor = db.rawQuery("select MaHS from HocSinh where HoTen = ?", new String[]{listHocSinh.get(position)});

                if(cursor.moveToFirst()){
                   MaHS = cursor.getString(0);
                }
                cursor.close();
                // Tạo bảng điểm và load các dữ liệu
                hienThiBangDiem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Event khi người dùng ấn nút cập nhật
        // Lưu trữ dữ liệu đã chỉnh sửa trên bảng
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterDiemSo.isChanged()){
                    ArrayList<DiemSo> listCapnhat = adapterDiemSo.getList();

                    SQLiteDatabase db = dbh.getWritableDatabase();

                    Cursor cursor;
                    for (DiemSo diem: listCapnhat) {
                        if(diem.getMonHoc().equals("Môn học"))
                            continue;
                        if(!diem.getDiemGK1().isEmpty() && !diem.getDiemCK1().isEmpty() &&
                                !diem.getDiemGK2().isEmpty() && !diem.getDiemCK2().isEmpty()){
                            cursor = db.rawQuery("select MaDS, NamHoc from DiemSo where MaHS = ? and MonHoc = ?", new String[]{MaHS, diem.getMonHoc()});
                            if(cursor.moveToFirst()){
                                db.execSQL("update DiemSo set DiemGK1 = ?, DiemCK1 = ?, DiemGK2 = ?, DiemCK2 = ? where MaHS = ? and MonHoc = ?",
                                        new String[]{diem.getDiemGK1(), diem.getDiemCK1(), diem.getDiemGK2(), diem.getDiemCK2(), MaHS, diem.getMonHoc()});
                            }
                            else {
                                db.execSQL("insert into DiemSo(NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) values (?,?,?,?,?,?,?)",
                                        new String[]{"2024-2025", diem.getMonHoc(), diem.getDiemGK1(), diem.getDiemCK1(), diem.getDiemGK2(), diem.getDiemCK2(), MaHS});
                            }

                            hienThiBangDiem();

                        } else if (diem.getDiemGK1().isEmpty() && diem.getDiemCK1().isEmpty() &&
                                diem.getDiemGK2().isEmpty() && diem.getDiemCK2().isEmpty()) {

                            db.execSQL("insert into DiemSo(NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) values (?,?,?,?,?,?,?)",
                                    new String[]{"2024-2025", diem.getMonHoc(), diem.getDiemGK1(), diem.getDiemCK1(), diem.getDiemGK2(), diem.getDiemCK2(), MaHS});
                            hienThiBangDiem();
                        } else {
                            Toast.makeText(CapNhatDiemGiaoVien.this, "Bạn đang để trống điểm số", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
                else {
                    if(!adapterDiemSo.isUpdated()){
                        Toast.makeText(CapNhatDiemGiaoVien.this, "Bạn đang để trống điểm số", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initWidget() {
        spinnerHocSinh = findViewById(R.id.spinnerHocSinh);
        textViewNamHoc = findViewById(R.id.textViewNamHoc);
        recyclerViewNhapDiem = findViewById(R.id.rvNhapDiem);
        btnCapNhat = findViewById(R.id.btnCapNhatDiem);
        Intent intent = getIntent();
        MaGV = intent.getStringExtra("MaGV");
    }

    private void hienThiHocSinh() {
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct MaHS, HoTen from HocSinh join Lop on Lop.MaLop = HocSinh.MaLop " +
                                        "where Lop.MaGV = ?" , new String[]{MaGV});


        String tmp;
        if (cursor.moveToFirst()){
            MaHS = cursor.getString(0);
            do{
                int hsIndexNumber = cursor.getColumnIndex("HoTen");
                tmp = cursor.getString(hsIndexNumber);

                listHocSinh.add(tmp);
            }while (cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_dropdown_item, listHocSinh);
        spinnerHocSinh.setAdapter(adapter);
        cursor.close();
    }

    private void hienThiNamHoc() {
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct NamHoc from HocSinh join DiemSo on HocSinh.MaHS = DiemSo.MaHS" +
                                    " join Lop on Lop.MaLop = HocSinh.MaLop where Lop.MaGV = ?", new String[]{MaGV});
        if (cursor.moveToFirst()){
            int index = cursor.getColumnIndex("NamHoc");
            textViewNamHoc.setText(cursor.getString(index));
        }

        cursor.close();
    }

    private void hienThiBangDiem(){
        SQLiteDatabase db = dbh.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from DiemSo where MaHS = ? and NamHoc = ?", new String[]{MaHS, textViewNamHoc.getText().toString()});
        ArrayList<DiemSo> listNamHoc = new ArrayList<>();
        listNamHoc.add(new DiemSo("Môn học", "GK1", "CK1", "GK2", "CK2"));

        DiemSo tmp;

        if (cursor.moveToFirst()){
            do{
                tmp = new DiemSo(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                listNamHoc.add(tmp);
            }while (cursor.moveToNext());
        }
        else{
            listNamHoc.add(new DiemSo("Toán học", "", "", "", ""));
            listNamHoc.add(new DiemSo("Ngữ văn", "", "", "", ""));
            listNamHoc.add(new DiemSo("Tiếng anh", "", "", "", ""));
            listNamHoc.add(new DiemSo("Sinh học", "", "", "", ""));
            listNamHoc.add(new DiemSo("Lịch sử", "", "", "", ""));
            listNamHoc.add(new DiemSo("Địa lý", "", "", "", ""));
            listNamHoc.add(new DiemSo("Vật lý", "", "", "", ""));
            listNamHoc.add(new DiemSo("Hóa học", "", "", "", ""));
        }


        CapNhatDiemAdapter adapter = new CapNhatDiemAdapter(this,listNamHoc);
        adapterDiemSo = adapter;
        recyclerViewNhapDiem.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNhapDiem.setAdapter(adapter);
        cursor.close();
    }
}