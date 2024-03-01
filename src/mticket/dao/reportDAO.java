/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.util.List;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class reportDAO {

    public static List<Object[]> getAmountInMonth() {
        String sql = "{call sp_SelectAmount}";
        String[] cols = {"thangSo", "thangChu", "doanhThuPhim", "doanhThuThucAn", "tongDoanhThu"};
        return JdbcHelper.getListOfArray(sql, cols);
    }

    public static List<Object[]> getAmountMovieTopThreeInMonth(String month) {
        String sql = "{call  sp_SelectAmountTopThreeMovieInMonth(?)}";
        String[] cols = {"maPhim", "doanhThu", "phanTramDoanhThu"};
        return JdbcHelper.getListOfArray(sql, cols, month);
    }

    public static List<Object[]> getAmountFoodTopThreeInMonth(String month) {
        String sql = "{call  sp_SelectAmountTopThreeFoodInMonth(?)}";
        String[] cols = {"thucAn", "doanhThu", "phanTramDoanhThu"};
        return JdbcHelper.getListOfArray(sql, cols, month);
    }

    public static List<Object[]> getAmountMovieById(String idMovie) {
        String sql = "{call  sp_SelectAmountMovieById(?)}";
        String[] cols = {"thangSo", "thangChu", "doanhThuPhim"};
        return JdbcHelper.getListOfArray(sql, cols, idMovie);
    }

    public static List<Object[]> getAmountMovieByIdAdMonth(String idMovie, String month) {
        String sql = "{call  sp_SelectAmountMovieByIdAdMonth(?,?)}";
        String[] cols = {"doanhThuPhim"};
        return JdbcHelper.getListOfArray(sql, cols, idMovie, month);
    }

    public static List<Object[]> getAmountFoodByIdAdMonth(String idMovie, String month) {
        String sql = "{call  sp_SelectAmountFoodByIdAdMonth(?,?)}";
        String[] cols = {"doanhThuThucAn"};
        return JdbcHelper.getListOfArray(sql, cols, idMovie, month);
    }
    
    public static List<Object[]> getAmountMovieByIdAdDate(String idMovie, String date) {
        String sql = "{call  sp_SelectAmountMovieByIdAdDate(?,?)}";
        String[] cols = {"doanhThuPhim"};
        return JdbcHelper.getListOfArray(sql, cols, idMovie, date);
    }
    
    public static List<Object[]> getAmountFoodByIdAdDate(String idFood, String date) {
        String sql = "{call  sp_SelectAmountFoodByIdAdDate(?,?)}";
        String[] cols = {"doanhThuThucAn"};
        return JdbcHelper.getListOfArray(sql, cols, idFood, date);
    }
}
