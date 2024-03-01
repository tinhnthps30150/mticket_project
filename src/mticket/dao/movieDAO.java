/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mticket.entity.Movie;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class movieDAO implements mticketDAO<Movie, String>{

    public static String insert_sql = "insert into Phim values (?,?,?,?,?,?,?,?,?,?)";
    private final String update_sql = "update Phim set hinhAnh = ?, tenPhim = ?, theLoai = ?, daoDien = ?, thoiLuong = ?, thongTinChiTiet = ?, ngayCongChieu = ?, giaPhim = ?, dienVien = ? where maPhim = ?";
    private final String delete_sql = "delete Phim where maPhim = ?";
    private final String selectAll = "select * from Phim";
    private final String selectByID = "select * from Phim  where maPhim like ?";

    @Override
    public void update(Movie entity) {
        JdbcHelper.update(update_sql, entity.getHinhAnh(), entity.getTenPhim(), entity.getTheLoai(), entity.getDaoDien(), entity.getThoiLuong(), entity.getMoTa(), entity.getNgayChieu(), entity.getGiaPhim(), entity.getDienVien(), entity.getMaPhim());
    }

    @Override
    public void insert(Movie entity) {
        JdbcHelper.update(insert_sql, entity.getMaPhim(), entity.getHinhAnh(), entity.getTenPhim(), entity.getTheLoai(), entity.getDaoDien(), entity.getThoiLuong(), entity.getMoTa(), entity.getNgayChieu(), entity.getGiaPhim(), entity.getDienVien());
    }

    @Override
    public void delete(Movie entity) {
        JdbcHelper.update(delete_sql, entity.getMaPhim());
    }

    @Override
    public List<Movie> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public Movie selectById(String id) {
        List<Movie> lMovie = selectBySql(selectByID, id);
        if (lMovie.size() > 0) {
            return lMovie.get(0);
        }
        return null;
    }

    public List<Movie> selectByDate(String date) {
        return selectBySql("{ call sp_SelectMovieByDate (?)}", date);
    }

    public Movie selectMovieByMovieSchedule(String ngayChieu, String phong, String thoiGian) {
        List<Movie> lMovie = selectBySql("{call sp_SelectMovieByMovieSchedule (?,?,?) }", ngayChieu, phong, thoiGian);
        if(lMovie.size() == 0){
            return null;
        }
        return lMovie.get(0);
    }

    public static List<Object[]> selectByIDMovieSchedule(int id) {
        String sql = "{call sp_SelectMovieByIDMovieSchedule (?) }";
        String[] cols = {"tenPhim", "tenPhong", "thoiGian"};
        return JdbcHelper.getListOfArray(sql, cols, id);
    }

    @Override
    public List<Movie> selectBySql(String sql, Object... args) {
        List<Movie> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new Movie(re.getString(1), re.getString(2), re.getString(3), re.getString(4), re.getString(5), re.getString(6), re.getString(7), re.getDate(8), re.getDouble(9), re.getString(10)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
