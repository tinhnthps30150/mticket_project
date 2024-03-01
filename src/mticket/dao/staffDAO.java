/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mticket.entity.Staff;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class staffDAO implements mticketDAO<Staff, String> {

    public static String insert_sql = "insert into NhanVien values (?,?,?,?,?,?,?,?)";
    private final String update_sql = "update NhanVien set taiKhoan = ?, matKhau = ?, vaiTro = ?, tenNhanVien = ?, Cccd = ?, hinhAnh = ?, gioiTinh = ? where maNhanVien = ?";
    private final String delete_sql = "delete NhanVien where maNhanVien = ?";
    private final String selectAll = "select * from NhanVien";
    private final String selectByID = "select * from NhanVien  where maNhanVien like ?";

    @Override
    public void insert(Staff entity) {
        JdbcHelper.update(insert_sql, entity.getMaNhanVien(), entity.getTaiKhoan(), entity.getMatKhau(), entity.isVaiTro(), entity.getTenNhanVien(), entity.getcCCD(), entity.getHinhAnh(), entity.isGioiTinh());
    }

    @Override
    public void update(Staff entity) {
        JdbcHelper.update(update_sql, entity.getTaiKhoan(), entity.getMatKhau(), entity.isVaiTro(), entity.getTenNhanVien(), entity.getcCCD(), entity.getHinhAnh(), entity.isGioiTinh(), entity.getMaNhanVien());
    }

    @Override
    public void delete(Staff entity) {
        JdbcHelper.update(delete_sql, entity.getMaNhanVien());
    }

    @Override
    public List<Staff> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public Staff selectById(String id) {
        List<Staff> lStaff = selectBySql(selectByID, id);
        if (lStaff.size() > 0) {
            return lStaff.get(0);
        }
        return null;
    }

    public Staff selectByAccount(String email) {
        List<Staff> lStaff = selectBySql("select * from NhanVien where taiKhoan like ?", email);
        if (lStaff.size() > 0) {
            return lStaff.get(0);
        }
        return null;
    }

    @Override
    public List<Staff> selectBySql(String sql, Object... args) {
        List<Staff> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new Staff(re.getString(1), re.getString(2), re.getString(3), re.getBoolean(4), re.getString(5), re.getString(6), re.getString(7), re.getBoolean(8)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
}
