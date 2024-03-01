/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

/**
 *
 * @author HuyTinh
 */
public class Food {
    private String maThucAn;
    private String hinhThucAn;
    private String tenThucAn;
    private double giaThucAn;
    private String moTaThucAn;

    public Food(String maThucAn, String hinhThucAn, String tenThucAn, double giaThucAn, String moTaThucAn) {
        this.maThucAn = maThucAn;
        this.hinhThucAn = hinhThucAn;
        this.tenThucAn = tenThucAn;
        this.giaThucAn = giaThucAn;
        this.moTaThucAn = moTaThucAn;
    }

    public String getMaThucAn() {
        return maThucAn;
    }

    public void setMaThucAn(String maThucAn) {
        this.maThucAn = maThucAn;
    }

    public String getHinhThucAn() {
        return hinhThucAn;
    }

    public void setHinhThucAn(String hinhThucAn) {
        this.hinhThucAn = hinhThucAn;
    }

    public String getTenThucAn() {
        return tenThucAn;
    }

    public void setTenThucAn(String tenThucAn) {
        this.tenThucAn = tenThucAn;
    }

    public double getGiaThucAn() {
        return giaThucAn;
    }

    public void setGiaThucAn(double giaThucAn) {
        this.giaThucAn = giaThucAn;
    }

    public String getMoTaThucAn() {
        return moTaThucAn;
    }

    public void setMoTaThucAn(String moTaThucAn) {
        this.moTaThucAn = moTaThucAn;
    }
   
}
