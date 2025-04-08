package com.example.demosll.model;

public class DiemSo {
    private String monHoc;
    private String diemGK1, diemCK1, diemGK2, diemCK2, diemTB;

    public DiemSo(){}
    public DiemSo(String monHoc, String diemGK1, String diemCK1, String diemGK2, String diemCK2) {
        this.monHoc = monHoc;
        this.diemGK1 = diemGK1;
        this.diemCK1 = diemCK1;
        this.diemGK2 = diemGK2;
        this.diemCK2 = diemCK2;
        if (monHoc.equals("Môn học")){
            this.diemTB = "Điểm TB";
        } else if (this.diemCK1.isEmpty()) {
            this.diemTB = "";
        } else this.diemTB = String.format("%.2f",
                (Float.parseFloat(diemGK1) + Float.parseFloat(diemCK1) +
                        Float.parseFloat(diemGK2) + Float.parseFloat(diemCK2)) / 4);
    }

    // Getters
    public String getMonHoc() { return monHoc; }
    public String getDiemGK1() { return diemGK1; }
    public String getDiemCK1() { return diemCK1; }
    public String getDiemGK2() { return diemGK2; }
    public String getDiemCK2() { return diemCK2; }
    public String getDiemTB() { return diemTB; }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public void setDiemGK1(String diemGK1) {
        this.diemGK1 = diemGK1;
    }

    public void setDiemCK1(String diemCK1) {
        this.diemCK1 = diemCK1;
    }

    public void setDiemGK2(String diemGK2) {
        this.diemGK2 = diemGK2;
    }

    public void setDiemCK2(String diemCK2) {
        this.diemCK2 = diemCK2;
    }

    public void setDiemTB(String diemTB) {
        this.diemTB = diemTB;
    }
}