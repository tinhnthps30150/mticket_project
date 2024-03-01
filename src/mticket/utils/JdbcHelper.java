/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.utils;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyTinh
 */
public class JdbcHelper {

    public static Connection getDatabase() {
        try {
            String url = "jdbc:sqlserver://localhost:1432;databaseName=MTICKET;user=sa;password=123456;encrypt=false";
            return DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public static PreparedStatement getStmt(String query, Object... args) throws SQLException {
        PreparedStatement pstmt = null;
        if (query.trim().startsWith("{")) {
            pstmt = getDatabase().prepareCall(query);
        } else {
            pstmt = getDatabase().prepareStatement(query);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    // Thực hiện các thao tác thêm, sửa, xóa tương tác với CSDL
    public static int update(String query, Object... args) {
        PreparedStatement stmt = null;
        try {
            stmt = getStmt(query, args);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    // Thực hiện các thao tác tìm kiếm dữ liệu tương tác với CSDL
    public static ResultSet query(String query, Object... args) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = getStmt(query, args);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Thực hiện các thao tác truy cập các Procedure trong CSDL
    public static List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = re.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
