/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mticket.component.schedule;

import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import mticket.component.comboSuggestion.ComboBoxSuggestionMovie;
import mticket.component.searchBar.SearchOption;
import mticket.dao.movieDAO;
import mticket.dao.roomDAO;
import mticket.dao.showTimeDAO;
import mticket.entity.Movie;
import mticket.entity.Room;
import mticket.entity.ShowTime;

/**
 *
 * @author HuyTinh
 */
public final class movieSchedulePanel extends javax.swing.JPanel {

    public static movieSchedulePanel mSP;

    public ComboBoxSuggestionMovie getCboNgay() {
        return cboNgay;
    }

    public void setCboNgay(ComboBoxSuggestionMovie cboNgay) {
        this.cboNgay = cboNgay;
    }

    public ComboBoxSuggestionMovie getCboPhong() {
        return cboPhong;
    }

    public void setCboPhong(ComboBoxSuggestionMovie cboPhong) {
        this.cboPhong = cboPhong;
    }

    public movieSchedulePanel() {
        initComponents();
        mSP = this;
        mticket.utils.mticketUtils.setFont(jLabel1, "Kodchasan-Medium.ttf", 18f);
        mticket.utils.mticketUtils.setFont(cboNgay, "Kodchasan-Medium.ttf", 17f);
        mticket.utils.mticketUtils.setFont(jLabel2, "Kodchasan-Medium.ttf", 18f);
        mticket.utils.mticketUtils.setFont(cboPhong, "Kodchasan-Medium.ttf", 18f);
        jTextFieldSearchOption1.addOption(new SearchOption("ID", new ImageIcon(getClass().getResource("/mticket/image/id.png"))));
        jTextFieldSearchOption1.addOption(new SearchOption("Name", new ImageIcon(getClass().getResource("/mticket/image/user.png"))));
    }

    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initComboBoxNgayChieu();
                initComboBoxPhong();
            }
        }).start();
    }

    void initComboBoxNgayChieu() {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboNgay.getModel();
        cboModel.removeAllElements();
        cboModel.addElement("Chọn ngày");
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear());
        while (firstDay.isBefore(lastDay)) {
            firstDay = firstDay.plus(1, ChronoUnit.DAYS);
            cboModel.addElement(java.sql.Date.valueOf(firstDay));
        }
    }

    void initComboBoxPhong() {
        List<Room> lR = new roomDAO().selectAll();
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboPhong.getModel();
        cboModel.removeAllElements();
        cboModel.addElement("Chọn Phòng");
        for (Room arg : lR) {
            cboModel.addElement(arg.getTenPhong());
        }
    }

    public void initMovieDrag(List<Movie> lMM) {
        movie15.removeAll();
        movie15.setPreferredSize(new Dimension(movie15.getPreferredSize().width, 0));
        for (int i = 0; i < lMM.size(); i++) {
            editMovieScheduleCardDrag eMSCD = new editMovieScheduleCardDrag(lMM.get(i));
            movie15.add(eMSCD);
            if (i % 3 == 0) {
                movie15.setPreferredSize(new Dimension(movie15.getPreferredSize().width, movie15.getPreferredSize().height + eMSCD.getPreferredSize().height + 6));
            }
        }
        repaint();
        validate();
    }

    void selectMovieSchedule() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movie12.removeAll();
                movie12.setPreferredSize(new Dimension(movie12.getPreferredSize().width, 0));
                List<ShowTime> lS = new showTimeDAO().selectAll();
                if (cboNgay.getSelectedIndex() != 0) {
                    if (cboPhong.getSelectedIndex() != 0) {
                        for (ShowTime object : lS) {
                            int i = lS.indexOf(object);
                            Movie mv = new movieDAO().selectMovieByMovieSchedule(String.valueOf(cboNgay.getSelectedItem()), String.valueOf(cboPhong.getSelectedItem()), object.getThoiGian());
                            editMovieScheduleCard eMSC = new editMovieScheduleCard(object.getThoiGian(), mv);
                            movie12.add(eMSC);
                            if (i % 3 == 0) {
                                movie12.setPreferredSize(new Dimension(movie12.getPreferredSize().width, movie12.getPreferredSize().height + eMSC.getPreferredSize().height + 10));
                            }
                        }
                    }
                } else {
                    movie12.removeAll();
                }
                movie12.repaint();
                movie12.validate();
            }
        }).start();
    }

    void search(String str) {
        if (str.equals("ID")) {
            initMovieDrag(new movieDAO().selectBySql("select * from Phim where maPhim like ?", "%" + jTextFieldSearchOption1.getText() + "%"));
        } else if (str.equals("Name")) {
            initMovieDrag(new movieDAO().selectBySql("select * from Phim where tenPhim like ?", "%" + jTextFieldSearchOption1.getText() + "%"));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movie8 = new com.k33ptoo.components.KGradientPanel();
        movie9 = new com.k33ptoo.components.KGradientPanel();
        movie10 = new com.k33ptoo.components.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        movie11 = new com.k33ptoo.components.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        movie13 = new com.k33ptoo.components.KGradientPanel();
        movie14 = new com.k33ptoo.components.KGradientPanel();
        scrollPaneWin111 = new raven.scroll.win11.ScrollPaneWin11();
        movie15 = new com.k33ptoo.components.KGradientPanel();
        jTextFieldSearchOption1 = new mticket.component.searchBar.JTextFieldSearchOption();
        scrollPaneWin112 = new raven.scroll.win11.ScrollPaneWin11();
        movie12 = new com.k33ptoo.components.KGradientPanel();
        cboNgay = new mticket.component.comboSuggestion.ComboBoxSuggestionMovie();
        cboPhong = new mticket.component.comboSuggestion.ComboBoxSuggestionMovie();

        setPreferredSize(new java.awt.Dimension(1510, 1000));
        setLayout(new java.awt.BorderLayout());

        movie8.setBackground(new java.awt.Color(255, 255, 255));
        movie8.setkBorderRadius(18);
        movie8.setkEndColor(new Color(76,76,153));
        movie8.setkFillBackground(false);
        movie8.setkStartColor(new Color(159,153,255));
        movie8.setOpaque(false);
        movie8.setPreferredSize(new java.awt.Dimension(1689, 700));

        movie9.setBackground(new java.awt.Color(255, 255, 255));
        movie9.setkBorderRadius(18);
        movie9.setkEndColor(new java.awt.Color(255, 255, 255));
        movie9.setkStartColor(new java.awt.Color(255, 255, 255));
        movie9.setOpaque(false);

        movie10.setBackground(new java.awt.Color(255, 255, 255));
        movie10.setkBorderRadius(18);
        movie10.setkEndColor(new Color(76,76,153));
        movie10.setkStartColor(new Color(159,153,255));
        movie10.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ngày Chiếu:");

        javax.swing.GroupLayout movie10Layout = new javax.swing.GroupLayout(movie10);
        movie10.setLayout(movie10Layout);
        movie10Layout.setHorizontalGroup(
            movie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movie10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        movie10Layout.setVerticalGroup(
            movie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        movie11.setBackground(new java.awt.Color(255, 255, 255));
        movie11.setkBorderRadius(18);
        movie11.setkEndColor(new Color(76,76,153));
        movie11.setkStartColor(new Color(159,153,255));
        movie11.setOpaque(false);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Phòng:");

        javax.swing.GroupLayout movie11Layout = new javax.swing.GroupLayout(movie11);
        movie11.setLayout(movie11Layout);
        movie11Layout.setHorizontalGroup(
            movie11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movie11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        movie11Layout.setVerticalGroup(
            movie11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        movie13.setBackground(new java.awt.Color(255, 255, 255));
        movie13.setkBorderRadius(18);
        movie13.setkEndColor(new Color(76,76,153));
        movie13.setkStartColor(new Color(159,153,255));
        movie13.setOpaque(false);

        movie14.setBackground(new java.awt.Color(255, 255, 255));
        movie14.setkBorderRadius(18);
        movie14.setkEndColor(new java.awt.Color(255, 255, 255));
        movie14.setkStartColor(new java.awt.Color(255, 255, 255));
        movie14.setOpaque(false);

        movie15.setBackground(new Color(159,153,255));
        movie15.setkBorderRadius(0);
        movie15.setkEndColor(new Color(76,76,153));
        movie15.setkStartColor(new Color(159,153,255));
        movie15.setOpaque(false);
        movie15.setPreferredSize(new java.awt.Dimension(500, 0));
        movie15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 5));
        scrollPaneWin111.setViewportView(movie15);

        javax.swing.GroupLayout movie14Layout = new javax.swing.GroupLayout(movie14);
        movie14.setLayout(movie14Layout);
        movie14Layout.setHorizontalGroup(
            movie14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );
        movie14Layout.setVerticalGroup(
            movie14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextFieldSearchOption1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchOption1KeyReleased(evt);
            }
        });

        movie12.setBackground(new java.awt.Color(255, 255, 255));
        movie12.setkBorderRadius(18);
        movie12.setkEndColor(new java.awt.Color(255, 255, 255));
        movie12.setkStartColor(new java.awt.Color(255, 255, 255));
        movie12.setOpaque(false);
        movie12.setPreferredSize(new java.awt.Dimension(100, 27));
        movie12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));
        scrollPaneWin112.setViewportView(movie12);

        javax.swing.GroupLayout movie13Layout = new javax.swing.GroupLayout(movie13);
        movie13.setLayout(movie13Layout);
        movie13Layout.setHorizontalGroup(
            movie13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(movie13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldSearchOption1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(movie13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(movie13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(movie14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(857, Short.MAX_VALUE)))
        );
        movie13Layout.setVerticalGroup(
            movie13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movie13Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(movie13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSearchOption1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneWin112, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(movie13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movie13Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(movie14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        cboNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNgayActionPerformed(evt);
            }
        });

        cboPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout movie9Layout = new javax.swing.GroupLayout(movie9);
        movie9.setLayout(movie9Layout);
        movie9Layout.setHorizontalGroup(
            movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movie13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(movie9Layout.createSequentialGroup()
                        .addComponent(movie10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movie11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        movie9Layout.setVerticalGroup(
            movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie9Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movie10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movie11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movie9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movie13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
        );

        javax.swing.GroupLayout movie8Layout = new javax.swing.GroupLayout(movie8);
        movie8.setLayout(movie8Layout);
        movie8Layout.setHorizontalGroup(
            movie8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movie9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        movie8Layout.setVerticalGroup(
            movie8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movie9, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(movie8, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    private void jTextFieldSearchOption1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchOption1KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            search(jTextFieldSearchOption1.getSelectedOption().getName());
        } else if (evt.getKeyCode() == 8 && jTextFieldSearchOption1.getText().isBlank()) {
            search(jTextFieldSearchOption1.getSelectedOption().getName());
        }
    }//GEN-LAST:event_jTextFieldSearchOption1KeyReleased

    private void cboNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNgayActionPerformed
        // TODO add your handling code here:
        selectMovieSchedule();
    }//GEN-LAST:event_cboNgayActionPerformed

    private void cboPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhongActionPerformed
        // TODO add your handling code here:
        selectMovieSchedule();
    }//GEN-LAST:event_cboPhongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mticket.component.comboSuggestion.ComboBoxSuggestionMovie cboNgay;
    private mticket.component.comboSuggestion.ComboBoxSuggestionMovie cboPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private mticket.component.searchBar.JTextFieldSearchOption jTextFieldSearchOption1;
    private com.k33ptoo.components.KGradientPanel movie10;
    private com.k33ptoo.components.KGradientPanel movie11;
    private com.k33ptoo.components.KGradientPanel movie12;
    private com.k33ptoo.components.KGradientPanel movie13;
    private com.k33ptoo.components.KGradientPanel movie14;
    private com.k33ptoo.components.KGradientPanel movie15;
    private com.k33ptoo.components.KGradientPanel movie8;
    private com.k33ptoo.components.KGradientPanel movie9;
    private raven.scroll.win11.ScrollPaneWin11 scrollPaneWin111;
    private raven.scroll.win11.ScrollPaneWin11 scrollPaneWin112;
    // End of variables declaration//GEN-END:variables
}
