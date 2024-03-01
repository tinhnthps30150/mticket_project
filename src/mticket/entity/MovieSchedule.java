/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

import java.sql.Date;

/**
 *
 * @author HuyTinh
 */
public class MovieSchedule {

    private int maLichChieu;
    private String maPhim;
    private String maPhong;
    private String maSuat;
    private java.sql.Date ngayChieu;

    public MovieSchedule(int maLichChieu, String maPhim, String maPhong, String maSuat, Date ngayChieu) {
        this.maLichChieu = maLichChieu;
        this.maPhim = maPhim;
        this.maPhong = maPhong;
        this.maSuat = maSuat;
        this.ngayChieu = ngayChieu;
    }

    public int getMaLichChieu() {
        return maLichChieu;
    }

    public void setMaLichChieu(int maLichChieu) {
        this.maLichChieu = maLichChieu;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaSuat() {
        return maSuat;
    }

    public void setMaSuat(String maSuat) {
        this.maSuat = maSuat;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    
}
