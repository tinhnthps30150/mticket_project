/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.util.ArrayList;
import java.util.List;
import mticket.entity.ShowTime;
import mticket.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HuyTinh
 */
public class showTimeDAO implements mticketDAO<ShowTime, Integer>{
    
     public static String insert_sql = "insert into SuatChieu (thoiGian) values (?)";
    private final String update_sql = "update SuatChieu set thoiGian = ? where maSuat = ?";
    private final String delete_sql = "delete SuatChieu where maSuat = ?";
    private final String selectAll = "select * from SuatChieu";
    
    @Override
    public List<ShowTime> selectAll() {
        return selectBySql(selectAll);
    }
    
    @Override
    public void insert(ShowTime entity) {
        JdbcHelper.update(insert_sql, entity.getThoiGian());
    }

    @Override
    public void delete(ShowTime entity) {
        JdbcHelper.update(delete_sql, entity.getMaSuatChieu());
    }

    @Override
    public void update(ShowTime entity) {
        JdbcHelper.update(update_sql, entity.getThoiGian(), entity.getMaSuatChieu());
    }
    
    public String getMaSuatChieu(String thoiGian){
        List<ShowTime> lST = selectBySql("select * from SuatChieu where thoiGian like ?", thoiGian);
        if(lST.size() > 0){
            return lST.get(0).getMaSuatChieu();
        }
        return null;
    }
    
    @Override
    public List<ShowTime> selectBySql(String sql, Object... args) {
        List<ShowTime> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()){
                list.add(new ShowTime(re.getString(1), re.getString(2)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
    
     public int getIncreadShowTimeID(){
        List<ShowTime> lST = selectAll();
        return Integer.parseInt(lST.get(lST.size() - 1).getMaSuatChieu()) + 1;
    }
}
