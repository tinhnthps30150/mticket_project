/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mticket.entity.TicketDetail;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class ticketDetailDAO implements mticketDAO<TicketDetail, Integer>{

    private final String insert_sql = "insert into ChiTietVe (maVe, thucAn, giathucAn, soLuong) values (?,?,?,?)";
    private final String selectById = "select * from ChiTietVe where maVe like ?";
    private final String selectAll = "select * from ChiTietVe";

    @Override
    public void insert(TicketDetail entity) {
        JdbcHelper.update(insert_sql, entity.getMaVe(), entity.getThucAn(), entity.getGiaThucAn(), entity.getSoLuong());
    }

    @Override
    public List<TicketDetail> selectAll() {
        return selectBySql(selectAll);
    }

    public List<TicketDetail> selectByIdTicket(String idTicket){
        return selectBySql(selectById, idTicket);
    }

    
    @Override
    public List<TicketDetail> selectBySql(String sql, Object... args) {
        List<TicketDetail> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new TicketDetail(re.getInt(1), re.getString(2), re.getString(3), re.getDouble(4), re.getInt(5)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

}
