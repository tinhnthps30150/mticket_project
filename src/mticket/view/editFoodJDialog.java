/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package mticket.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.TransferHandler;
import mticket.component.sweetAlert.SweetAlert;
import mticket.component.food.listEditFoodCard;
import mticket.dao.foodDAO;
import mticket.entity.Food;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class editFoodJDialog extends SweetAlert {

    /**
     * Creates new form editMovieJDialog
     */
    foodDAO fDAO = new foodDAO();

    public editFoodJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setOpaque(false);
        getContentPane().setBackground(new Color(0, 0, 0, 0));
        setBackground(new Color(0, 0, 0, 0));
        lblAnh1.setVisible(false);
        lblAnh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblAnh1.setVisible(true);
            }
        });

        lblAnh1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblAnh1.setVisible(false);
            }
        });
        lblAnh.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return true;
            }

            @Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                    mticketUtils.save(files.get(0));
                    lblAnh.setToolTipText(files.get(0).getName());
                    mticket.utils.mticketUtils.setIcon(new ImageIcon(getClass().getResource("/mticket/image/" + files.get(0).getName())), lblAnh);
                } catch (UnsupportedFlavorException | IOException ex) {
                    Logger.getLogger(editFoodJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        });
        mticketUtils.setFont(lblTenThucAn, "Kodchasan-Bold.ttf", 14f);
        mticketUtils.setFont(lblGia, "Kodchasan-Medium.ttf", 12f);
          mticketUtils.setFont(txtTenThucAn, "Kodchasan-Medium.ttf", 14f);
        mticketUtils.setFont(txtGiaThucAn, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtMoTa, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(btnLuu, "Kodchasan-Medium.ttf", 14f);
        mticketUtils.setFont(btnThoat, "Kodchasan-Medium.ttf", 14f);
    }

    // Đổ dữ liệu lên form
    public void initData(Food f) {
        lblAnh.setName(f.getMaThucAn());
        txtTenThucAn.setText(f.getTenThucAn());
        txtGiaThucAn.setText(String.format("%.0f", f.getGiaThucAn()));
        txtMoTa.setText(f.getMoTaThucAn());
        lblAnh.setToolTipText(f.getHinhThucAn());
        mticket.utils.mticketUtils.setIcon(new ImageIcon(getClass().getResource("/mticket/image/" + f.getHinhThucAn())), lblAnh);
    }

    // Lấy dữ liệu từ form
    Food getForm() {
        List<Food> lf = fDAO.selectAll();
        if (lblAnh.getName() == null) {
            lblAnh.setName(mticketUtils.autoIncreaseId(lf.get(lf.size() - 1).getMaThucAn()));
        }
        return new Food(lblAnh.getName(), lblAnh.getToolTipText(), txtTenThucAn.getText(), Double.parseDouble(txtGiaThucAn.getText()), txtMoTa.getText());
    }

    // Thêm dữ liệu vào CSDL
    void insertFood() {
        Food f = getForm();
        if (fDAO.selectById(f.getMaThucAn()) != null) {
            fDAO.update(f);
            mticketUtils.alert("Cập nhật thành công!");
            this.dispose();
        } else {
            fDAO.insert(f);
            mticketUtils.alert("Thêm thành công!");
            this.dispose();
        }
        listEditFoodCard.lF.initData(fDAO.selectAll());
    }

    // Chọn Ảnh
    void chosePicture() {
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);
        if (jf.getSelectedFile() != null) {
            mticketUtils.save(jf.getSelectedFile());
            lblAnh.setToolTipText(jf.getSelectedFile().getName());
            mticket.utils.mticketUtils.setIcon(new ImageIcon(getClass().getResource("/mticket/image/" + jf.getSelectedFile().getName())), lblAnh);
        }
    }

    boolean validateForm() {
        if (txtTenThucAn.getText().isEmpty()) {
            mticketUtils.alertAnimation("Vui lòng nhập tên thức ăn!");
            return false;
        }
        if (txtGiaThucAn.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập giá thức ăn!");
            return false;
        }
        if (lblAnh.getToolTipText().isEmpty()) {
            mticketUtils.alert("Vui lòng chọn hình phim!");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        movie4 = new com.k33ptoo.components.KGradientPanel();
        movie3 = new com.k33ptoo.components.KGradientPanel();
        lblAnh1 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        lblTenThucAn = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        btnLuu = new com.k33ptoo.components.KButton();
        txtTenThucAn = new javax.swing.JTextField();
        txtGiaThucAn = new javax.swing.JTextField();
        btnThoat = new com.k33ptoo.components.KButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new Color(0,0,0,0));

        movie4.setBackground(new java.awt.Color(255, 255, 255));
        movie4.setkBorderRadius(18);
        movie4.setkEndColor(new Color(76,76,153));
        movie4.setkFillBackground(false);
        movie4.setkStartColor(new Color(159,153,255));
        movie4.setOpaque(false);
        movie4.setLayout(new java.awt.BorderLayout());

        movie3.setBackground(new java.awt.Color(255, 255, 255));
        movie3.setkBorderRadius(18);
        movie3.setkEndColor(new java.awt.Color(204, 204, 204));
        movie3.setkStartColor(new java.awt.Color(204, 204, 204));
        movie3.setOpaque(false);
        movie3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnh1.setBackground(new Color(0,0,0,155));
        lblAnh1.setForeground(new java.awt.Color(255, 255, 255));
        lblAnh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mticket/image/icons8-import-48.png"))); // NOI18N
        lblAnh1.setText("Đổi hình");
        lblAnh1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(159, 153, 255)));
        lblAnh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAnh1.setOpaque(true);
        lblAnh1.setPreferredSize(new java.awt.Dimension(210, 250));
        mticketUtils.setFont(lblAnh1, "Kodchasan-Medium.ttf", 18f);
        lblAnh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnh1MouseClicked(evt);
            }
        });
        movie3.add(lblAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 250));

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(159, 153, 255)));
        lblAnh.setOpaque(true);
        lblAnh.setPreferredSize(new java.awt.Dimension(210, 250));
        movie3.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 250));

        kGradientPanel1.setkBorderRadius(8);
        kGradientPanel1.setkEndColor(new Color(159, 153, 255));
        kGradientPanel1.setkStartColor(new Color(159, 153, 255));
        kGradientPanel1.setOpaque(false);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        movie3.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 16, 230));

        kGradientPanel2.setkEndColor(new Color(159, 153, 255));
        kGradientPanel2.setkStartColor(new Color(76, 76, 153));
        kGradientPanel2.setOpaque(false);

        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setOpaque(false);

        lblTenThucAn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenThucAn.setText("Tên Thức Ăn:");
        lblTenThucAn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(159,153,255)));

        lblGia.setText("Giá:");

        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLuu.setkEndColor(new Color(159,153,255));
        btnLuu.setkHoverEndColor(new Color(76,76,153));
        btnLuu.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnLuu.setkHoverStartColor(new Color(159,153,255));
        btnLuu.setkStartColor(new Color(76,76,153));
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuukButton3ActionPerformed(evt);
            }
        });

        txtGiaThucAn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaThucAnKeyPressed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThoat.setkEndColor(new Color(159,153,255));
        btnThoat.setkHoverEndColor(new Color(76,76,153));
        btnThoat.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnThoat.setkHoverStartColor(new Color(159,153,255));
        btnThoat.setkStartColor(new Color(76,76,153));
        btnThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatkButton3ActionPerformed(evt);
            }
        });

        jScrollPane4.setBackground(new Color(0,0,0,0));
        jScrollPane4.setBorder(null);
        jScrollPane4.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Mô tả:"));
        jScrollPane4.setAutoscrolls(true);
        jScrollPane4.setOpaque(false);

        txtMoTa.setColumns(20);
        txtMoTa.setLineWrap(true);
        txtMoTa.setRows(4);
        txtMoTa.setWrapStyleWord(true);
        txtMoTa.setBorder(null);
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtMoTa.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(txtMoTa);

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 168, Short.MAX_VALUE))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenThucAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaThucAn)
                            .addComponent(txtTenThucAn))))
                .addContainerGap())
            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel3Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jScrollPane4)
                    .addGap(5, 5, 5)))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenThucAn)
                    .addComponent(txtTenThucAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiaThucAn)
                    .addComponent(lblGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel3Layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(42, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        movie3.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 10, 460, 250));

        movie4.add(movie3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(movie4, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(movie4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuukButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuukButton3ActionPerformed
        // TODO add your handling code here:
        if(validateForm()){
            insertFood();
        }
    }//GEN-LAST:event_btnLuukButton3ActionPerformed

    private void btnThoatkButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatkButton3ActionPerformed
        // TODO add your handling code here:
        closeAlert();
    }//GEN-LAST:event_btnThoatkButton3ActionPerformed

    private void lblAnh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnh1MouseClicked
        // TODO add your handling code here:
        chosePicture();
    }//GEN-LAST:event_lblAnh1MouseClicked

    private void txtGiaThucAnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaThucAnKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyChar() >= 0 && evt.getKeyChar() <= '9' && txtGiaThucAn.getText().length() < 10) || (evt.getKeyCode() == 8)) {
            txtGiaThucAn.setEditable(true);
        } else {
            txtGiaThucAn.setEditable(false);
        }
    }//GEN-LAST:event_txtGiaThucAnKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editFoodJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editFoodJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editFoodJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editFoodJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                editFoodJDialog dialog = new editFoodJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnLuu;
    private com.k33ptoo.components.KButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JScrollPane jScrollPane4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblAnh1;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblTenThucAn;
    private com.k33ptoo.components.KGradientPanel movie3;
    private com.k33ptoo.components.KGradientPanel movie4;
    private javax.swing.JTextField txtGiaThucAn;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenThucAn;
    // End of variables declaration//GEN-END:variables
}
