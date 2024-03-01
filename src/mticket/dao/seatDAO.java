/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mticket.entity.Seat;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class seatDAO implements mticketDAO<Seat, String>{

    private final String selectAll = "select * from LoaiGhe";

    @Override
    public List<Seat> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public List<Seat> selectBySql(String sql, Object... args) {
        List<Seat> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new Seat(re.getString(1), re.getDouble(2)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

}
