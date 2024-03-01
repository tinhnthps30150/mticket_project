/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

/**
 *
 * @author HuyTinh
 */
public class Staff {
    private String maNhanVien;
    private String taiKhoan;
    private String matKhau;
    private boolean vaiTro;
    private String tenNhanVien;
    private String cCCD;
    private String hinhAnh;
    private boolean gioiTinh;

    public Staff(String maNhanVien, String taiKhoan, String matKhau, boolean vaiTro, String tenNhanVien, String cCCD, String hinhAnh, boolean gioiTinh) {
        this.maNhanVien = maNhanVien;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.tenNhanVien = tenNhanVien;
        this.cCCD = cCCD;
        this.hinhAnh = hinhAnh;
        this.gioiTinh = gioiTinh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getcCCD() {
        return cCCD;
    }

    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
}
