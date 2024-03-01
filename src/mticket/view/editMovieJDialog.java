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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.TransferHandler;
import mticket.component.sweetAlert.SweetAlert;
import mticket.component.movie.listEditMovieCard;
import mticket.dao.movieDAO;
import mticket.entity.Movie;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class editMovieJDialog extends SweetAlert {

    /**
     * Creates new form editMovieJDialog
     */
    movieDAO mDAO = new movieDAO();

    public editMovieJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setOpaque(false);
        getContentPane().setBackground(new Color(0, 0, 0, 0));
        setBackground(new Color(0, 0, 0, 0));
        dpkNgayChieu.updateUI();
        dpkNgayChieu.setLocale(new Locale("vi-VN", "VN"));
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
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(editStaffJDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(editStaffJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        });
        mticketUtils.setFont(lblTenPhim, "Kodchasan-Bold.ttf", 14f);
        mticketUtils.setFont(lblDaoDien, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(lblDienVien, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(lblGia, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtMoTa, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(lblTheLoai, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(lblThoiLuong, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(lblNgayChieu, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtTenPhim, "Kodchasan-Medium.ttf", 14f);
        mticketUtils.setFont(txtDaoDien, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtDienVien, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtGia, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtTheLoai, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(txtThoiLuong, "Kodchasan-Medium.ttf", 12f);
        mticketUtils.setFont(btnLuu, "Kodchasan-Bold.ttf", 14f);
        mticketUtils.setFont(btnThoat, "Kodchasan-Bold.ttf", 14f);
    }

    // Đổ dữ liệu lên form
    public void initData(Movie mv) {
        lblAnh.setName(mv.getMaPhim());
        txtTenPhim.setText(mv.getTenPhim());
        txtDaoDien.setText(mv.getDaoDien());
        txtDienVien.setText(mv.getDienVien());
        txtTheLoai.setText(mv.getTheLoai());
        txtThoiLuong.setText(mv.getThoiLuong());
        txtMoTa.setText(mv.getMoTa());
        txtGia.setText(String.format("%.0f", mv.getGiaPhim()));
        dpkNgayChieu.setDate(mv.getNgayChieu().toLocalDate());
        mticket.utils.mticketUtils.setIcon(new ImageIcon(getClass().getResource("/mticket/image/" + mv.getHinhAnh())), lblAnh);
        lblAnh.setToolTipText(mv.getHinhAnh());
    }

    // Lấy dữ liệu từ form
    Movie getForm() {
        if (lblAnh.getName() == null) {
            lblAnh.setName(mticketUtils.autoIncreaseId(mDAO.selectAll().get(mDAO.selectAll().size() - 1).getMaPhim()));
        }
        return new Movie(lblAnh.getName(), lblAnh.getToolTipText(), txtTenPhim.getText(), txtTheLoai.getText(), txtDaoDien.getText(), txtThoiLuong.getText(), txtMoTa.getText(), java.sql.Date.valueOf(dpkNgayChieu.getDate()), Double.parseDouble(txtGia.getText()), txtDienVien.getText());
    }

    // Thêm dữ liệu vào CSDL
    void insertMovie() {
        Movie mv = getForm();
        if (mDAO.selectById(mv.getMaPhim()) != null) {
            mDAO.update(mv);
            mticketUtils.alert("Cập nhật thành công!");
            this.dispose();
        } else {
            mDAO.insert(mv);
            mticketUtils.alert("Thêm thành công!");
            this.dispose();
        }
        listEditMovieCard.lM.initData(mDAO.selectAll());
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
        if (txtTenPhim.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập tên phim!");
            return false;
        }
        if (txtTheLoai.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập thể loại phim!");
            return false;
        }
        if (txtDaoDien.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập đạo diễn phim!");
            return false;
        }
        if (txtDienVien.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập diễn viên phim!");
            return false;
        }
        if (txtGia.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập giá phim!");
            return false;
        }
        if (txtThoiLuong.getText().isEmpty()) {
            mticketUtils.alert("Vui lòng nhập thời lượng phim!");
            return false;
        }
        if (dpkNgayChieu.getDate() == null) {
            mticketUtils.alert("Vui lòng nhập ngày chiếu phim!");
            return false;
        }
        if (lblAnh.getToolTipText() == null) {
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

        movie4 = new com.k33ptoo.components.KGradientPanel();
        movie3 = new com.k33ptoo.components.KGradientPanel();
        lblAnh1 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        detailMovie = new javax.swing.JPanel();
        lblTenPhim = new javax.swing.JLabel();
        lblTheLoai = new javax.swing.JLabel();
        lblDaoDien = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnLuu = new com.k33ptoo.components.KButton();
        txtTenPhim = new javax.swing.JTextField();
        txtTheLoai = new javax.swing.JTextField();
        txtDaoDien = new javax.swing.JTextField();
        lblGia = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        lblThoiLuong = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        lblNgayChieu = new javax.swing.JLabel();
        btnThoat = new com.k33ptoo.components.KButton();
        dpkNgayChieu = new com.github.lgooddatepicker.components.DatePicker();
        lblDienVien = new javax.swing.JLabel();
        txtDienVien = new javax.swing.JTextField();

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
        movie3.setkEndColor(new java.awt.Color(255, 255, 255));
        movie3.setkStartColor(new java.awt.Color(255, 255, 255));
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
        movie3.add(lblAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 380));

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(159, 153, 255)));
        lblAnh.setOpaque(true);
        lblAnh.setPreferredSize(new java.awt.Dimension(290, 380));
        movie3.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 380));

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
            .addGap(0, 360, Short.MAX_VALUE)
        );

        movie3.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 16, 360));

        kGradientPanel2.setkEndColor(new Color(159, 153, 255));
        kGradientPanel2.setkStartColor(new Color(76, 76, 153));
        kGradientPanel2.setOpaque(false);

        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setOpaque(false);
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailMovie.setBackground(new java.awt.Color(255, 255, 255));

        lblTenPhim.setText("Tên phim:");
        lblTenPhim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(159,153,255)));
        lblTenPhim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenPhim.setPreferredSize(new java.awt.Dimension(68, 22));

        lblTheLoai.setText("Thể loại:");

        lblDaoDien.setText("Đạo diễn:");

        jScrollPane4.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Mô tả:"));
        jScrollPane4.setAutoscrolls(true);
        jScrollPane4.setBackground(new Color(0,0,0,0));
        jScrollPane4.setBorder(null);
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

        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        txtTenPhim.setPreferredSize(new java.awt.Dimension(362, 22));

        txtTheLoai.setPreferredSize(new java.awt.Dimension(362, 22));

        txtDaoDien.setPreferredSize(new java.awt.Dimension(362, 22));

        lblGia.setText("Giá:");

        txtGia.setPreferredSize(new java.awt.Dimension(362, 22));
        txtGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaKeyPressed(evt);
            }
        });

        lblThoiLuong.setText("Thời lượng:");

        txtThoiLuong.setPreferredSize(new java.awt.Dimension(362, 22));

        lblNgayChieu.setText("Ngày chiếu:");

        btnThoat.setText("Thoát");
        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        lblDienVien.setText("Diễn viên:");

        txtDienVien.setPreferredSize(new java.awt.Dimension(362, 22));

        javax.swing.GroupLayout detailMovieLayout = new javax.swing.GroupLayout(detailMovie);
        detailMovie.setLayout(detailMovieLayout);
        detailMovieLayout.setHorizontalGroup(
            detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(detailMovieLayout.createSequentialGroup()
                        .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenPhim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTheLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenPhim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(detailMovieLayout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(detailMovieLayout.createSequentialGroup()
                        .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDienVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblThoiLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayChieu, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThoiLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dpkNgayChieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDienVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        detailMovieLayout.setVerticalGroup(
            detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtTenPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDienVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDienVien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dpkNgayChieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        kGradientPanel3.add(detailMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        movie3.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 10, 460, 380));

        movie4.add(movie3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movie4, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movie4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuukButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuukButton3ActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            insertMovie();
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

    private void txtGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyChar() >= 0 && evt.getKeyChar() <= '9' && txtGia.getText().length() < 10) || (evt.getKeyCode() == 8)) {
            txtGia.setEditable(true);
        } else {
            txtGia.setEditable(false);
        }
    }//GEN-LAST:event_txtGiaKeyPressed

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
            java.util.logging.Logger.getLogger(editMovieJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editMovieJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editMovieJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editMovieJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                editMovieJDialog dialog = new editMovieJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel detailMovie;
    private com.github.lgooddatepicker.components.DatePicker dpkNgayChieu;
    private javax.swing.JScrollPane jScrollPane4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblAnh1;
    private javax.swing.JLabel lblDaoDien;
    private javax.swing.JLabel lblDienVien;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblNgayChieu;
    private javax.swing.JLabel lblTenPhim;
    private javax.swing.JLabel lblTheLoai;
    private javax.swing.JLabel lblThoiLuong;
    private com.k33ptoo.components.KGradientPanel movie3;
    private com.k33ptoo.components.KGradientPanel movie4;
    private javax.swing.JTextField txtDaoDien;
    private javax.swing.JTextField txtDienVien;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenPhim;
    private javax.swing.JTextField txtTheLoai;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
