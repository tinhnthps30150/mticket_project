/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.entity;

import mticket.dao.ticketDAO;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class TicketDetail {

    private int idChiTietVe;
    private String maVe = mticketUtils.autoIncreaseId(new ticketDAO().selectAll().get(new ticketDAO().selectAll().size() - 1).getMaVe());
    private String thucAn;
    private double giaThucAn;
    private int soLuong;

    public TicketDetail() {
    }

    public TicketDetail(int idChiTietVe, String thucAn, double giaThucAn, int soLuong) {
        this.idChiTietVe = idChiTietVe;
        this.thucAn = thucAn;
        this.giaThucAn = giaThucAn;
        this.soLuong = soLuong;
    }

    public TicketDetail(int idChiTietVe, String maVe, String thucAn, double giaThucAn, int soLuong) {
        this.idChiTietVe = idChiTietVe;
        this.maVe = maVe;
        this.thucAn = thucAn;
        this.giaThucAn = giaThucAn;
        this.soLuong = soLuong;
    }

    public int getIdChiTietVe() {
        return idChiTietVe;
    }

    public void setIdChiTietVe(int idChiTietVe) {
        this.idChiTietVe = idChiTietVe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getThucAn() {
        return thucAn;
    }

    public void setThucAn(String thucAn) {
        this.thucAn = thucAn;
    }

    public double getGiaThucAn() {
        return giaThucAn;
    }

    public void setGiaThucAn(double giaThucAn) {
        this.giaThucAn = giaThucAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}
