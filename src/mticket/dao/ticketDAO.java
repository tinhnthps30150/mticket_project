/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mticket.entity.Ticket;
import mticket.entity.TicketDetail;
import mticket.utils.JdbcHelper;

/**
 *
 * @author HuyTinh
 */
public class ticketDAO implements mticketDAO<Ticket, String> {

    private final String selectAll = "select * from Ve";
    private final String selectById = "select * from Ve where maVe like ?";
    private final String insert_sql = "insert into Ve (maVe, maNhanVien, maLichChieu, maLoaiGhe, soGhe, giaPhim, giaLoaiGhe) values (?,?,?,?,?,?,?)";

    @Override
    public List<Ticket> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public void insert(Ticket entity) {
        JdbcHelper.update(insert_sql, entity.getMaVe(), entity.getMaNhanVien(), entity.getMaLichChieu(), entity.getMaLoaiGhe(), entity.getSoGhe(), entity.getGiaPhim(), entity.getGiaLoaiGhe());
    }

    public static List<Object[]> getSeatByIdMovieSchedule(String idLichChieu, String ngayChieuPhim) {
        String sql = "select soGhe from ve Where maLichChieu = ? and ngayDatVe like ?";
        String[] cols = {"soGhe"};
        return JdbcHelper.getListOfArray(sql, cols, idLichChieu, ngayChieuPhim);
    }

    @Override
    public Ticket selectById(String id) {
        List<Ticket> lT = selectBySql(selectById, id);
        if (!lT.isEmpty()) {
            return lT.get(0);
        }
        return null;
    }

    @Override
    public List<Ticket> selectBySql(String sql, Object... args) {
        List<Ticket> list = new ArrayList<>();
        try {
            ResultSet re = JdbcHelper.query(sql, args);
            while (re.next()) {
                list.add(new Ticket(re.getString(1), re.getString(2), re.getInt(3), re.getString(4), re.getString(5), re.getDouble(6), re.getDouble(7)));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    public Ticket getFullTicket(String id) {
        ticketDAO td = new ticketDAO();
        ticketDetailDAO tdd = new ticketDetailDAO();
        Ticket tk = td.selectById(id);
        for (TicketDetail arg1 : tdd.selectByIdTicket(tk.getMaVe())) {
            tk.getThucAnDat().put(arg1.getThucAn(), arg1);
        }
        return tk;
    }

//    public static void main(String[] args) {
//        Ticket tk = new ticketDAO().getFullTicket();
//        int total = 0;
//        List<Object[]> tik = new movieDAO().selectByIDMovieSchedule(tk.getMaLichChieu());
//
//        System.out.println("Mã vé:        " + tk.getMaVe());
//        System.out.println("Tên phim:   " + tik.get(0)[0]);
//        System.out.println("Thời gian:   " + tik.get(0)[2]);
//        System.out.println("Phòng:        " + tik.get(0)[1]);
//        System.out.println("Ghế:            " + tk.getSoGhe());
//        System.out.println("Thức ăn:     " + (tk.getThucAnDat().size() == 0 ? 0 : ""));
//
//        for (String string : tk.getThucAnDat().keySet()) {
//            foodDAO fD = new foodDAO();
//            TicketDetail tD = tk.getThucAnDat().get(string);
//            System.out.println("             " + string + " " + (fD.selectById(tD.getThucAn()).getTenThucAn()) + " x " + tD.getSoLuong());
//            total += tD.getGiaThucAn() * tD.getSoLuong();
//        }
//        System.out.println("Total: " + (Double.parseDouble(String.valueOf(tk.getGiaPhim() + tk.getGiaLoaiGhe() + total))) + " VND");
//    }
}
