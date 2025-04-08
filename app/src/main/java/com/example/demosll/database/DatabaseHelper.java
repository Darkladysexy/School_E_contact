package com.example.demosll.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sll.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng TaiKhoan
        String createTaiKhoan = "CREATE TABLE TaiKhoan (" +
                "MaTK INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Email TEXT UNIQUE, " +
                "MatKhau TEXT, " +
                "LoaiTK TEXT CHECK(LoaiTK IN ('GV', 'HS')), " +
                "HoTen TEXT, " +
                "SDT TEXT, " +
                "NgayTao TEXT)";
        db.execSQL(createTaiKhoan);

        // Tạo bảng Truong
        String createTruong = "CREATE TABLE Truong (" +
                "MaTruong INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenTruong TEXT, " +
                "DiaChi TEXT, " +
                "Email TEXT, " +
                "SDT TEXT)";
        db.execSQL(createTruong);

        // Tạo bảng Lop
        String createLop = "CREATE TABLE Lop (" +
                "MaLop INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenLop TEXT, " +
                "MaGV INTEGER, " +
                "MaTruong INTEGER, " +
                "FOREIGN KEY (MaGV) REFERENCES TaiKhoan(MaTK), " +
                "FOREIGN KEY (MaTruong) REFERENCES Truong(MaTruong))";
        db.execSQL(createLop);

        // Tạo bảng HocSinh
        String createHocSinh = "CREATE TABLE HocSinh (" +
                "MaHS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "HoTen TEXT, " +
                "GioiTinh TEXT, " +
                "NgaySinh TEXT, " +
                "MaLop INTEGER, " +
                "MaPhuHuynh INTEGER, " +
                "FOREIGN KEY (MaLop) REFERENCES Lop(MaLop), " +
                "FOREIGN KEY (MaPhuHuynh) REFERENCES TaiKhoan(MaTK))";
        db.execSQL(createHocSinh);

        // Tạo bảng DiemSo
        String createDiemSo = "CREATE TABLE DiemSo (" +
                "MaDS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NamHoc TEXT, " +
                "MonHoc TEXT, " +
                "DiemGK1 REAL, " +
                "DiemCK1 REAL, " +
                "DiemGK2 REAL, " +
                "DiemCK2 REAL, " +
                "MaHS INTEGER, " +
                "FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS))";
        db.execSQL(createDiemSo);

        // Tạo bảng HanhKiem
        String createHanhKiem = "CREATE TABLE HanhKiem (" +
                "MaHK INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MucHK TEXT, " +
                "HocKy TEXT, " +
                "NamHoc TEXT, " +
                "MaHS INTEGER, " +
                "FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS))";
        db.execSQL(createHanhKiem);

        // Tạo bảng HocPhi
        String createHocPhi = "CREATE TABLE HocPhi (" +
                "MaHP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TieuDe TEXT, " +
                "SoTien REAL, " +
                "TrangThai TEXT, " +
                "HanDong TEXT, " +
                "NgayThanhToan TEXT, " +
                "MaHS INTEGER, " +
                "FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS))";
        db.execSQL(createHocPhi);

        // Tạo bảng HoiThoai
        String createHoiThoai = "CREATE TABLE HoiThoai (" +
                "MaHT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenHT TEXT, " +
                "NguoiTao INTEGER, " +
                "DSTV TEXT, " +
                "NgayTao TEXT, " +
                "FOREIGN KEY (NguoiTao) REFERENCES TaiKhoan(MaTK))";
        db.execSQL(createHoiThoai);

        // Tạo bảng TinNhan
        String createTinNhan = "CREATE TABLE TinNhan (" +
                "MaTN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NguoiGui INTEGER, " +
                "NoiDung TEXT, " +
                "ThoiGianGui TEXT, " +
                "MaHT INTEGER, " +
                "FOREIGN KEY (NguoiGui) REFERENCES TaiKhoan(MaTK), " +
                "FOREIGN KEY (MaHT) REFERENCES HoiThoai(MaHT))";
        db.execSQL(createTinNhan);

        // Tạo bảng ThongBao
        String createThongBao = "CREATE TABLE ThongBao (" +
                "MaTB INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TieuDe TEXT, " +
                "NoiDung TEXT, " +
                "NgayDang TEXT, " +
                "MaTruong INTEGER, " +
                "FOREIGN KEY (MaTruong) REFERENCES Truong(MaTruong))";
        db.execSQL(createThongBao);

        // Thêm dữ liệu mẫu
        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Thêm trường học
        db.execSQL("INSERT INTO Truong (TenTruong, DiaChi, Email, SDT) VALUES ('Trường THPT A', '123 Đường ABC', 'thptA@example.com', '0123456789')");

        // Thêm giáo viên
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('giaovien@example.com', '123456', 'GV', 'Nguyễn Văn A', '0987654321', '2025-03-25')");

        // Thêm lớp học
        db.execSQL("INSERT INTO Lop (TenLop, MaGV, MaTruong) VALUES ('Lớp 8A', 1, 1)");

        // Thêm tài khoản
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh1@example.com', '123456', 'HS', 'Trần Văn A', '0912345678', '2025-03-25')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh2@example.com', '123456', 'HS', 'Nguyễn Văn B', '0912342478', '2025-03-26')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh3@example.com', '123456', 'HS', 'Lê Văn E', '0911111111', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh4@example.com', '123456', 'HS', 'Trần Thị F', '0911111112', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh5@example.com', '123456', 'HS', 'Nguyễn Văn G', '0911111113', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh6@example.com', '123456', 'HS', 'Phạm Thị H', '0911111114', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh7@example.com', '123456', 'HS', 'Đỗ Văn I', '0911111115', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh8@example.com', '123456', 'HS', 'Võ Thị K', '0911111116', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh9@example.com', '123456', 'HS', 'Ngô Văn L', '0911111117', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh10@example.com', '123456', 'HS', 'Lý Thị M', '0911111118', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh11@example.com', '123456', 'HS', 'Trương Văn N', '0911111119', '2025-03-27')");
        db.execSQL("INSERT INTO TaiKhoan (Email, MatKhau, LoaiTK, HoTen, SDT, NgayTao) VALUES " +
                "('phuhuynh12@example.com', '123456', 'HS', 'Bùi Thị O', '0911111120', '2025-03-27')");

        // Thêm học sinh
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Trần Văn C', 'Nam', '2010-05-10', 1, 2)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Nguyễn Thị D', 'Nữ', '2010-08-08', 1, 3)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Phạm Văn Q', 'Nam', '2010-04-01', 1, 4)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Lê Thị R', 'Nữ', '2010-09-15', 1, 5)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Trần Văn S', 'Nam', '2010-06-22', 1, 6)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Nguyễn Thị T', 'Nữ', '2010-12-10', 1, 7)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Đặng Văn U', 'Nam', '2010-03-30', 1, 8)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Vũ Thị V', 'Nữ', '2010-11-05', 1, 9)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Bùi Văn W', 'Nam', '2010-07-19', 1, 10)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Phan Thị X', 'Nữ', '2010-10-03', 1, 11)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Đoàn Văn Y', 'Nam', '2010-01-28', 1, 12)");
        db.execSQL("INSERT INTO HocSinh (HoTen, GioiTinh, NgaySinh, MaLop, MaPhuHuynh) VALUES " +
                "('Lý Thị Z', 'Nữ', '2010-02-14', 1, 13)");


        // Thêm điểm số
        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Toán học', 7.5, 8.0, 8.5, 8, 1)");
        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Ngữ văn', 7.0, 7.5, 6.5, 7, 1)");
        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Tiếng anh', 6.5, 7.0, 7.5, 8.5, 1)");
        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Sinh học', 6.8, 7.2, 7.0, 8.1, 1)");

        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Lịch sử', 7.5, 6.9, 7.8, 8.0, 1)");

        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Địa lý', 5.9, 6.3, 6.8, 7.4, 1)");

        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Vật lý', 7.1, 7.6, 6.9, 8.2, 1)");

        db.execSQL("INSERT INTO DiemSo (NamHoc, MonHoc, DiemGK1, DiemCK1, DiemGK2, DiemCK2, MaHS) VALUES " +
                "('2024-2025', 'Hóa học', 6.4, 6.8, 7.2, 8.0, 1)");


        // Thêm hạnh kiểm
        db.execSQL("INSERT INTO HanhKiem (MucHK, HocKy, NamHoc, MaHS) VALUES ('Tốt', 'HK1', '2024-2025', 1)");

        // Thêm học phí
        db.execSQL("INSERT INTO HocPhi (TieuDe, SoTien, TrangThai, HanDong, NgayThanhToan, MaHS) VALUES ('Học phí học kì 1', '2400000', 'Đã đóng', '31/12/2024', '20/12/2024', '1')");
        db.execSQL("INSERT INTO HocPhi (TieuDe, SoTien, TrangThai, HanDong, NgayThanhToan, MaHS) VALUES ('Học phí học kì 2', '2556000', 'Chưa đóng', '31/5/2025', '', '1')");
        db.execSQL("INSERT INTO HocPhi (TieuDe, SoTien, TrangThai, HanDong, NgayThanhToan, MaHS) VALUES ('Phí BHYT năm 2025', '824000', 'Chưa đóng', '31/5/2025', '', '1')");
        db.execSQL("INSERT INTO HocPhi (TieuDe, SoTien, TrangThai, HanDong, NgayThanhToan, MaHS) VALUES ('Quần áo đồng phục, quần áo thể dục thể thao, phù hiệu', '572000', 'Chưa đóng', '31/5/2025', '', '1')");

        // Thêm hội thoại và tin nhắn
        db.execSQL("INSERT INTO HoiThoai (TenHT, NguoiTao, DSTV, NgayTao) VALUES ('Nhóm lớp 8A', 1, '1,2', '2025-03-25')");
        db.execSQL("INSERT INTO TinNhan (NguoiGui, NoiDung, ThoiGianGui, MaHT) VALUES (1, 'Chào các em', '2025-03-25 10:00', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
        onCreate(db);
    }
    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TaiKhoan WHERE Email = ? AND MatKhau = ?",
                new String[]{email, password});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();

        return exists;
    }

    public String getLoaiTaiKhoan(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT LoaiTK FROM TaiKhoan WHERE Email = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            String loaiTK = cursor.getString(0);
            cursor.close();
            return loaiTK;
        }

        return null;
    }

}
