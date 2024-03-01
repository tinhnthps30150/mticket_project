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
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import mticket.entity.Seat;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class movieSeat extends javax.swing.JToggleButton {

    public movieSeat(JLabel jl, List<String> seatBooking, Color start, Color end) {
        setPreferredSize(new Dimension(55, 35));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setUI(new BasicToggleButtonUI());
        setBackground(start);
        setForeground(Color.WHITE);
        mticketUtils.setFont(this, "Kodchasan-Medium.ttf", 16f);
        addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    listMovie.tk.getGheDat().put(getText(), new Seat(getToolTipText(), (int) (listMovie.tk.getGiaPhim() * (Double.parseDouble(getName())))));
                    setBackground(end);
                    listMovie.tk.setGiaLoaiGhe(listMovie.tk.getGiaPhim() * (Double.parseDouble(getName())));
                    jl.setText("Tạm tính: " + NumberFormat.getCurrencyInstance(new Locale("vn", "vn")).format(mticketUtils.toDouble(jl.getText()) + listMovie.tk.getGiaPhim() * (Double.parseDouble(getName()))));
                } else {
                    listMovie.tk.getGheDat().remove(getText());
                    seatBooking.remove(getText());
                    setBackground(start);
                    jl.setText("Tạm tính: " + NumberFormat.getCurrencyInstance(new Locale("vn", "vn")).format(mticketUtils.toDouble(jl.getText()) - listMovie.tk.getGiaPhim() * (Double.parseDouble(getName()))));
                }
            }
        });
        
    }

    @Override
    public void setEnabled(boolean b) {
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
