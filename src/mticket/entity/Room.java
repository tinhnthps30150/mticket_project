/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

/**
 *
 * @author HuyTinh
 */
public class Room {
    private String maPhong;
    private String tenPhong;

    public Room(String maPhong, String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }
    
    
}
