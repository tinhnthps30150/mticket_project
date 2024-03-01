/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mticket.entity.MovieSchedule;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class movieScheduleDAO implements mticketDAO<MovieSchedule, Integer> {

    private final String selectByIdMovie = "select * from LichChieu where maPhim = ?";
    private final String insert_sql = "insert into lichChieu (maPhim, maPhong, maSuat, ngayChieu) values (?,?,?,?)";
    private final String update_sql = "update lichChieu set maPhim = ?, maPhong = ?, maSuat = ?, ngayChieu = ? where maLichChieu = ?";
    private final String delete_sql = "delete from lichChieu where maLichChieu = ?";
    private final String selectAll = "select * from LichChieu";

    public List<MovieSchedule> selectByIdMovie() {
        return selectBySql(selectByIdMovie);
    }

    public static List<Object[]> getShowTimeByIdMovieAndDate(String idMaPhim, String ngayChieu) {
        String sql = "{call sp_SelectShowTimeByMovieIDAndDate (?, ?) }";
        String[] cols = {"maSuat", "thoiGian"};
        return JdbcHelper.getListOfArray(sql, cols, idMaPhim, ngayChieu);
    }

    public static List<Object[]> getRoomByIdMovieAndShowTimeAndDate(String idMaPhim, int showTime, String date) {
        String sql = "{ call sp_SelectRoomByMovieIDAndShowTimeAndDate (?,?,?) }";
        String[] cols = {"maPhong", "tenPhong"};
        return JdbcHelper.getListOfArray(sql, cols, idMaPhim, showTime, date);
    }

    public static List<Object[]> getIDMovieScheduleByRoomAndShowTime(String idPhong, int showTime, String date) {
        String sql = "select maLichChieu from LichChieu where maPhong like ? and maSuat = ? and ngayChieu like ?";
        String[] cols = {"maLichChieu"};
        return JdbcHelper.getListOfArray(sql, cols, idPhong, showTime, date);
    }

    public static List<Object[]> selectMovieScheduleByIdMovie(String maPhim) {
        String sql = "{call sp_SelectMovieScheduleByIdMovie (?) }";
        String[] cols = {"ngayChieu", "thoiGian", "tenPhong"};
        return JdbcHelper.getListOfArray(sql, cols, maPhim);
    }

    public MovieSchedule getMovieSchedule(String maPhim, String tenPhong, String ngayChieu) {
        List<MovieSchedule> lMS = selectBySql("{call sp_SelectMovieSchedule(?,?,?) }", maPhim, tenPhong, ngayChieu);
        if (lMS.size() > 0) {
            return lMS.get(0);
        }
        return null;
    }

    @Override
    public void insert(MovieSchedule entity) {
        JdbcHelper.update(insert_sql, entity.getMaPhim(), entity.getMaPhong(), entity.getMaSuat(), entity.getNgayChieu());
    }

    @Override
    public void update(MovieSchedule entity) {
        JdbcHelper.update(update_sql, entity.getMaPhim(), entity.getMaPhong(), entity.getMaSuat(), entity.getNgayChieu(), entity.getMaLichChieu());
    }

    @Override
    public void delete(MovieSchedule entity) {
        JdbcHelper.update(delete_sql, entity.getMaLichChieu());
    }

    @Override
    public List<MovieSchedule> selectBySql(String sql, Object... args) {
        List<MovieSchedule> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new MovieSchedule(re.getInt(1), re.getString(2), re.getString(3), String.valueOf(re.getInt(4)), re.getDate(5)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<MovieSchedule> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public MovieSchedule selectById(Integer id) {
        return mticketDAO.super.selectById(id); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public int getIncreadMovieScheduleID(){
        List<MovieSchedule> lSM = selectAll();
        return lSM.get(lSM.size() - 1).getMaLichChieu() + 1;
    }
}
