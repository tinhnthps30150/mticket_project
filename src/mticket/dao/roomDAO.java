/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.util.ArrayList;
import java.util.List;
import mticket.entity.Room;
import mticket.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HuyTinh
 */
public class roomDAO implements mticketDAO<Room, String> {

    public static String insert_sql = "insert into Phong values (?,?)";
    private final String update_sql = "update Phong set tenPhong = ? where maPhong like ?";
    private final String delete_sql = "delete Phong where maPhong like ?";
    private String selectAll = "select * from Phong";

    @Override
    public List<Room> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public void insert(Room entity) {
        JdbcHelper.update(insert_sql, entity.getMaPhong(),entity.getTenPhong());
    }

    @Override
    public void delete(Room entity) {
        JdbcHelper.update(delete_sql, entity.getMaPhong());
    }

    @Override
    public void update(Room entity) {
        JdbcHelper.update(update_sql, entity.getTenPhong(), entity.getMaPhong());
    }

    public String getMaPhong(String tenPhong) {
        List<Room> lP = selectBySql("select * from Phong where tenPhong like ?", tenPhong);
        if (lP.size() > 0) {
            return lP.get(0).getMaPhong();
        }
        return null;
    }

    @Override
    public List<Room> selectBySql(String sql, Object... args) {
        List<Room> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new Room(re.getString(1), re.getString(2)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
}
