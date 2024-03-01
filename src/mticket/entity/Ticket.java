/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HuyTinh
 */
public class Ticket {

    private String maVe;
    private String maNhanVien;
    private int maLichChieu;
    private String maLoaiGhe;
    private String soGhe;
    private Map<String, Seat> gheDat = new HashMap<>();
    private double giaPhim;
    private double giaLoaiGhe;
    private Map<String, TicketDetail> thucAnDat = new HashMap<>();

    public Ticket() {
    }

    public Ticket(String maVe, String maNhanVien, int maLichChieu, String maLoaiGhe, String soGhe, double giaPhim, double giaLoaiGhe) {
        this.maVe = maVe;
        this.maNhanVien = maNhanVien;
        this.maLichChieu = maLichChieu;
        this.maLoaiGhe = maLoaiGhe;
        this.soGhe = soGhe;
        this.giaPhim = giaPhim;
        this.giaLoaiGhe = giaLoaiGhe;
    }
    
    

    public Ticket newTicket(Ticket tk) {
        return new Ticket(tk.getMaVe(),tk.getMaNhanVien(),tk.getMaLichChieu(),tk.getMaLoaiGhe(),tk.getSoGhe(),tk.getGiaPhim(),tk.getGiaLoaiGhe());
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaLichChieu() {
        return maLichChieu;
    }

    public void setMaLichChieu(int maLichChieu) {
        this.maLichChieu = maLichChieu;
    }

    public String getMaLoaiGhe() {
        return maLoaiGhe;
    }

    public void setMaLoaiGhe(String maLoaiGhe) {
        this.maLoaiGhe = maLoaiGhe;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public Map<String, Seat> getGheDat() {
        return gheDat;
    }

    public void setGheDat(Map<String, Seat> gheDat) {
        this.gheDat = gheDat;
    }

    public double getGiaPhim() {
        return giaPhim;
    }

    public void setGiaPhim(double giaPhim) {
        this.giaPhim = giaPhim;
    }

    public double getGiaLoaiGhe() {
        return giaLoaiGhe;
    }

    public void setGiaLoaiGhe(double giaLoaiGhe) {
        this.giaLoaiGhe = giaLoaiGhe;
    }

    public Map<String, TicketDetail> getThucAnDat() {
        return thucAnDat;
    }

    public void setThucAnDat(Map<String, TicketDetail> thucAnDat) {
        this.thucAnDat = thucAnDat;
    }

}
