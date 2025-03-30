package com.example.demosll.model;

public class HocPhi {

    String TenPhi,SoTien, TrangThai, HanDong;

    public HocPhi(String tenPhi, String hanDong, String soTien, String trangThai) {
        TenPhi = tenPhi;
        TrangThai = trangThai;
        SoTien = soTien;
        HanDong = hanDong;
    }


    public String getTenPhi() {
        return TenPhi;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public String getSoTien() {
        return SoTien;
    }

    public String getHanDong() {
        return HanDong;
    }
}
