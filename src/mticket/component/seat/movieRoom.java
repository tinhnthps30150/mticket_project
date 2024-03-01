/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.component.seat;

import mticket.component.movie.listMovie;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import mticket.dao.movieScheduleDAO;
import mticket.dao.seatDAO;
import mticket.dao.ticketDAO;
import mticket.entity.Seat;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class movieRoom extends javax.swing.JToggleButton {

    private List<Seat> sList = new seatDAO().selectAll();
    private List<String> seatBooking = new ArrayList<>();
    private List<Object[]> seatOrder = new ArrayList<>();

    public movieRoom(JPanel sInitPanel, JLabel jl, Color start, Color end) {
        mticketUtils.setFont(this, "Kodchasan-Medium.ttf", 24f);
        setPreferredSize(new Dimension(55, 35));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setUI(new BasicToggleButtonUI());
        setBackground(start);
        setForeground(Color.WHITE);
        addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setBackground(end);
                    String[] strS = getName().split(" ");
                    seatOrder.clear();
                    seatBooking.clear();
                    String idLichChieu = String.valueOf(movieScheduleDAO.getIDMovieScheduleByRoomAndShowTime(strS[0], Integer.parseInt(strS[1]), LocalDate.now().toString()).get(0)[0]);
                    seatOrder = ticketDAO.getSeatByIdMovieSchedule(idLichChieu, LocalDate.now().toString());
                    listMovie.tk.setMaLichChieu(Integer.parseInt(idLichChieu));
                    initSeat(sInitPanel, jl);
                } else {
                    setBackground(start);
                }
            }
        });
    }

    public void initSeat(JPanel jp, JLabel jl) {
        jp.removeAll();
        int count = 10;

        char[] character = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int charCount = 0;
        String seat = String.valueOf(character[charCount]);
        for (int i = 0; i < 70; i++) {
            if (i % 10 == 0) {
                if (i != 0) {
                    seat = String.valueOf(character[++charCount]);
                    count = 10;
                }
            }
            if (i < 60) {
                movieSeat normalSeat = new movieSeat(jl, seatBooking, new Color(159, 153, 255), new Color(76, 76, 153));
                normalSeat.setText(seat + count--);
                if (seatOrder.stream().filter(obj -> String.valueOf(obj[0]).equals(normalSeat.getText())).findFirst().isPresent()) {
                    normalSeat.setEnabled(false);
                }
                normalSeat.setPreferredSize(new Dimension((int) normalSeat.getPreferredSize().getWidth() +  32, (int) normalSeat.getPreferredSize().getHeight() + 20));
                normalSeat.setName(String.valueOf(sList.get(0).getGiaGhe()));
                normalSeat.setToolTipText(String.valueOf(sList.get(0).getMaLoaiGhe()));
                jp.add(normalSeat);
            } else {
                movieSeat doubleSeat = new movieSeat(jl, seatBooking, Color.ORANGE, new Color(76, 76, 153));
                doubleSeat.setText(seat + count--);
                if (seatOrder.stream().filter(obj -> String.valueOf(obj[0]).equals(doubleSeat.getText())).findFirst().isPresent()) {
                    doubleSeat.setEnabled(false);
                }
                doubleSeat.setPreferredSize(new Dimension((int) doubleSeat.getPreferredSize().getWidth() * 4, (int) doubleSeat.getPreferredSize().getHeight() * 2));
                doubleSeat.setName(String.valueOf(sList.get(1).getGiaGhe()));
                doubleSeat.setToolTipText(String.valueOf(sList.get(0).getMaLoaiGhe()));
                jp.add(doubleSeat);
            }
        }
        jp.validate();
        jl.setText("Thành tiền: " + mticketUtils.toCurrency(0));
    }

    @Override
    public void setEnabled(boolean b
    ) {
        super.setEnabled(b); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getDisabledTextColor() {
                return Color.WHITE;
            }
        });
        setBackground(new Color(76, 76, 153));
    }
}
