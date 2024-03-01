/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

/**
 *
 * @author HuyTinh
 */
public class ShowTime {
    private String maSuatChieu;
    private String thoiGian;

    public ShowTime(String maSuatChieu, String thoiGian) {
        this.maSuatChieu = maSuatChieu;
        this.thoiGian = thoiGian;
    }

    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
    
    
}
