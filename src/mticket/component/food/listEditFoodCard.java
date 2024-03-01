/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mticket.component.food;

import mticket.component.food.editFoodCard;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import mticket.component.searchBar.SearchOption;
import mticket.component.sweetAlert.SweetAlert;
import mticket.dao.foodDAO;
import mticket.entity.Food;
import mticket.utils.importHelper;
import mticket.utils.mticketUtils;
import mticket.view.editFoodJDialog;
import mticket.view.editStaffJDialog;
import mticket.view.managerJFrame;

/**
 *
 * @author HuyTinh
 */
public class listEditFoodCard extends javax.swing.JPanel {

    /**
     * Creates new form listEditMovieCard
     */
    public static listEditFoodCard lF;
    foodDAO fDAO = new foodDAO();

    public listEditFoodCard() {
        initComponents();
        this.lF = this;
        jTextFieldSearchOption1.addOption(new SearchOption("ID", new ImageIcon(getClass().getResource("/mticket/image/id.png"))));
        jTextFieldSearchOption1.addOption(new SearchOption("Name", new ImageIcon(getClass().getResource("/mticket/image/user.png"))));
        jTextFieldSearchOption1.addOption(new SearchOption("Price (from Price - to Price)", new ImageIcon(getClass().getResource("/mticket/image/price.png"))));
        setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return true;
            }

            @Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : files) {
                        if (file.isFile()) {
                            importHelper.importFileExcel(fDAO.insert_sql, file);
                        } else {
                            for (File listFile : file.listFiles()) {
                                mticketUtils.save(listFile);
                            }
                        }
                    }
                    initData(fDAO.selectAll());
                } catch (UnsupportedFlavorException | IOException ex) {
                    Logger.getLogger(editStaffJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        });
    }

    public void initData(List<Food> lFood) {
        jPanel1.setPreferredSize(new Dimension(getParent().getPreferredSize().width, 10));
        jPanel1.removeAll();
        for (int i = 0; i < lFood.size(); i++) {
            editFoodCard eFC = new editFoodCard(lFood.get(i));
            eFC.setName(String.valueOf(i));
            jPanel1.add(eFC);
            if (i % 7 == 0) {
                jPanel1.setPreferredSize(new Dimension(getParent().getPreferredSize().width, jPanel1.getPreferredSize().height + eFC.getPreferredSize().height + 5));
            }
        }
        jPanel1.repaint();
        jPanel1.validate();
    }

    void search(String str) {
        String searchStr = jTextFieldSearchOption1.getText();
        if (str.equals("ID")) {
            initData(fDAO.selectBySql("select * from ThucAn where maThucAn like ?", "%" + searchStr + "%"));
        } else if (str.equals("Name")) {
            initData(fDAO.selectBySql("select * from ThucAn where tenThucAn like ?", "%" + searchStr + "%"));
        } else {
            if (searchStr.contains("-")) {
                String[] spliStr = searchStr.split("-");
                if (spliStr.length == 2) {
                    if (!spliStr[0].trim().isEmpty() && !spliStr[1].trim().isEmpty()) {
                        double num_1 = Double.parseDouble(spliStr[0].trim());
                        double num_2 = Double.parseDouble(spliStr[1].trim());
                        initData(fDAO.selectBySql("select * from ThucAn where giaThucAn > ? and giaThucAn < ?", num_1, num_2));
                    } else if (!spliStr[1].trim().isEmpty()) {
                        double num_2 = Double.parseDouble(spliStr[1].trim());
                        initData(fDAO.selectBySql("select * from ThucAn where giaThucAn < ?", num_2));
                    }
                } else if (spliStr.length == 1) {
                    if (!spliStr[0].trim().isEmpty()) {
                        double num_1 = Double.parseDouble(spliStr[0].trim());
                        initData(fDAO.selectBySql("select * from ThucAn where giaThucAn > ?", num_1));
                    }
                }
            } else if (searchStr.length() != 0) {
                initData(fDAO.selectBySql("select * from ThucAn where giaThucAn = ? ", searchStr));
            } else {
                initData(fDAO.selectAll());
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldSearchOption1 = new mticket.component.searchBar.JTextFieldSearchOption();
        btnAdd = new com.k33ptoo.components.KButton();

        setPreferredSize(new java.awt.Dimension(1090, 479));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 1));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane1.setViewportView(jPanel1);

        jTextFieldSearchOption1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchOption1KeyReleased(evt);
            }
        });

        btnAdd.setText("ADD");
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setkEndColor(new Color(159,153,255));
        btnAdd.setkHoverEndColor(new Color(76,76,153));
        mticketUtils.setFont(btnAdd, "Kodchasan-Medium.ttf", 18f);
        btnAdd.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnAdd.setkHoverStartColor(new Color(159,153,255));
        btnAdd.setkStartColor(new Color(76,76,153));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddkButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldSearchOption1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSearchOption1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchOption1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchOption1KeyReleased
        // TODO add your handling code here:
        search(jTextFieldSearchOption1.getSelectedOption().getName());
    }//GEN-LAST:event_jTextFieldSearchOption1KeyReleased

    private void btnAddkButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddkButton3ActionPerformed
        // TODO add your handling code here:
        SweetAlert sA = new editFoodJDialog(managerJFrame.mFrame, true);
        sA.showAlert();
    }//GEN-LAST:event_btnAddkButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnAdd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private mticket.component.searchBar.JTextFieldSearchOption jTextFieldSearchOption1;
    // End of variables declaration//GEN-END:variables
}
