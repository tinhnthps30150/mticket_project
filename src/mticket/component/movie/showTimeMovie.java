/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.component.movie;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import mticket.component.seat.movieRoom;
import mticket.dao.movieScheduleDAO;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class showTimeMovie extends javax.swing.JToggleButton {

    public showTimeMovie(JPanel initPanel, JPanel sInitPanel, JLabel jl,String idPhim, Color start, Color end) {
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
                    initPanel.removeAll();
                    ButtonGroup bG = new ButtonGroup();
                    List<Object[]> listRoom = movieScheduleDAO.getRoomByIdMovieAndShowTimeAndDate(idPhim, Integer.parseInt(getName()),LocalDate.now().toString());
                    for (int i = 0; i < listRoom.size(); i++) {
                        movieRoom r = new movieRoom(sInitPanel, jl,new Color(159, 153, 255), new Color(76, 76, 153));
                        r.setText(String.valueOf(listRoom.get(i)[1]));
                        r.setName(String.valueOf(listRoom.get(i)[0])+" "+getName());
                        r.setPreferredSize(new Dimension(r.getPreferredSize().width + 80, r.getPreferredSize().height + 40));
                        bG.add(r);
                        if (i == 0) {
                            r.setSelected(true);
                        }
                        initPanel.add(r);
                    }
                    initPanel.repaint();
                    initPanel.validate();
                } else {
                    setBackground(start);
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
