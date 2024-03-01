/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.util.List;
import mticket.entity.Food;
import mticket.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HuyTinh
 */
public class foodDAO implements mticketDAO<Food, String>{

    public static String insert_sql = "insert into ThucAn values (?,?,?,?,?)";
    private final String update_sql = "update ThucAn set hinhThucAn = ?, tenThucAn = ?, giaThucAn = ?, moTa = ? where maThucAn = ?";
    private final String delete_sql = "delete ThucAn where maThucAn = ?";
    private final String selectAll = "select * from ThucAn";
    private final String selectByIdFood = "select * from ThucAn where maThucAn like ?";

    
    @Override
     public void update(Food entity) {
        JdbcHelper.update(update_sql, entity.getHinhThucAn(), entity.getTenThucAn(), entity.getGiaThucAn(), entity.getMoTaThucAn(), entity.getMaThucAn());
    }

    @Override
    public void insert(Food entity) {
        JdbcHelper.update(insert_sql, entity.getMaThucAn(), entity.getHinhThucAn(), entity.getTenThucAn(), entity.getGiaThucAn(), entity.getMoTaThucAn());
    }

    @Override
    public void delete(Food entity) {
        JdbcHelper.update(delete_sql, entity.getMaThucAn());
    }
    
    @Override
    public List<Food> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public Food selectById(String id) {
        List<Food> lMovie = selectBySql(selectByIdFood, id);
        if (lMovie.size() > 0) {
            return lMovie.get(0);
        }
        return null;
    }

    @Override
    public List<Food> selectBySql(String sql, Object... args) {
        List<Food> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new Food(re.getString(1), re.getString(2), re.getString(3), re.getDouble(4), re.getString(5)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
