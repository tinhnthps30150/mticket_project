/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.component.movie;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import mticket.component.pictureBox.PictureBox;
import mticket.utils.mticketUtils;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author HuyTinh
 */
public class movieCard extends com.k33ptoo.components.KGradientPanel {

    private JLabel lblImageMovie = new JLabel();
    private JLabel lblDetailMovie = new JLabel();
    private PictureBox pB = new PictureBox();

    public movieCard() {
        setPreferredSize(new Dimension(244, 285));
        setkStartColor(new Color(76, 76, 153));
        setLayout(new AbsoluteLayout());
        setkEndColor(new Color(159, 153, 255));
        setOpaque(false);
    }

    void initData(String imgPath, String text) {
        setDetailMovie(text);
        setImageMovie(imgPath);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblDetailMovie.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblDetailMovie.setVisible(false);
            }
        });
    }

    void setImageMovie(String imgPath) {

//        lblImageMovie.setPreferredSize(new Dimension(234, 275));
//        lblImageMovie.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        mticketUtils.setIcon(new ImageIcon(getClass().getResource("/mticket/image/" + imgPath)), lblImageMovie);
//        add(lblImageMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, lblImageMovie.getPreferredSize().width, lblImageMovie.getPreferredSize().height));
        pB.setPreferredSize(new Dimension(234, 275));
        pB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mticketUtils.setIcon(new ImageIcon(getClass().getResource("/mticket/image/" + imgPath)), pB, 12);
        add(pB, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, pB.getPreferredSize().width, pB.getPreferredSize().height));
    }

    void setDetailMovie(String text) {
        lblDetailMovie.setText(text);
        mticket.utils.mticketUtils.setFont(lblDetailMovie, "Kodchasan-Bold.ttf", 16f);
        lblDetailMovie.setPreferredSize(new Dimension(234, 275));
        lblDetailMovie.setBackground(new Color(0, 0, 0, 150));
        lblDetailMovie.setForeground(Color.WHITE);
        lblDetailMovie.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblDetailMovie.setIcon(new ImageIcon(this.getClass().getResource("/mticket/image/icons8-ticket-26.png")));
        lblDetailMovie.setOpaque(true);
        lblDetailMovie.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(lblDetailMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, lblDetailMovie.getPreferredSize().width, lblDetailMovie.getPreferredSize().height));
        lblDetailMovie.setVisible(false);

    }

    public JLabel getLblImageMovie() {
        return lblImageMovie;
    }

    public void setLblImageMovie(JLabel lblImageMovie) {
        this.lblImageMovie = lblImageMovie;
    }

    public JLabel getLblDetailMovie() {
        return lblDetailMovie;
    }

    public void setLblDetailMovie(JLabel lblDetailMovie) {
        this.lblDetailMovie = lblDetailMovie;
    }

}
