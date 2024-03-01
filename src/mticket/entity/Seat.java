/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

/**
 *
 * @author HuyTinh
 */
public class Seat {
    private String maLoaiGhe;
    private double giaGhe;

    public Seat(String maLoaiGhe, double giaGhe) {
        this.maLoaiGhe = maLoaiGhe;
        this.giaGhe = giaGhe;
    }

    public String getMaLoaiGhe() {
        return maLoaiGhe;
    }

    public void setMaLoaiGhe(String maLoaiGhe) {
        this.maLoaiGhe = maLoaiGhe;
    }

    public double getGiaGhe() {
        return giaGhe;
    }

    public void setGiaGhe(double giaGhe) {
        this.giaGhe = giaGhe;
    }
   
}
