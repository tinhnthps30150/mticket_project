/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;


/**
 *
 * @author HuyTinh
 */
public class Movie {
    private String maPhim;
    private String hinhAnh;
    private String tenPhim;
    private String theLoai;
    private String daoDien;
    private String thoiLuong;
    private String moTa;
    private java.sql.Date ngayChieu;
    private double giaPhim;
    private String dienVien;

    public Movie() {
    }

    
    
    public Movie(String maPhim, String hinhAnh, String tenPhim, String theLoai, String daoDien, String thoiLuong, String moTa, java.sql.Date ngayChieu, double giaPhim, String dienVien) {
        this.maPhim = maPhim;
        this.hinhAnh = hinhAnh;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.daoDien = daoDien;
        this.thoiLuong = thoiLuong;
        this.moTa = moTa;
        this.ngayChieu = ngayChieu;
        this.giaPhim = giaPhim;
        this.dienVien = dienVien;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public java.sql.Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(java.sql.Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public double getGiaPhim() {
        return giaPhim;
    }

    public void setGiaPhim(double giaPhim) {
        this.giaPhim = giaPhim;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }
    
    
  

  

}
