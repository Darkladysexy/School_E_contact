package com.example.demosll.model;

public class DiemSo {
    private String monHoc;
    private String diemGK1, diemCK1, diemGK2, diemCK2, diemTB;
    private String maHS;

    public DiemSo(String monHoc, String diemGK1, String diemCK1, String diemGK2, String diemCK2, String maHS) {
        this.monHoc = monHoc;
        this.diemGK1 = diemGK1;
        this.diemCK1 = diemCK1;
        this.diemGK2 = diemGK2;
        this.diemCK2 = diemCK2;
        this.maHS = maHS;
        if (monHoc.equals("Môn học")){
            this.diemTB = "Điểm TB";
        }
        else this.diemTB = String.valueOf(((Float.parseFloat(diemGK1) + Float.parseFloat(diemCK1) + Float.parseFloat(diemGK2) + Float.parseFloat(diemCK2)))/4);


    }

    // Getters
    public String getMonHoc() { return monHoc; }
    public String getDiemGK1() { return diemGK1; }
    public String getDiemCK1() { return diemCK1; }
    public String getDiemGK2() { return diemGK2; }
    public String getDiemCK2() { return diemCK2; }
    public String getDiemTB() { return diemTB; }
    public String getMaHS() { return maHS; }
}