/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mticket.view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mticket.component.glasspanepopup.GlassPanePopup;
import mticket.component.movie.listMovie;
import mticket.dao.foodDAO;
import mticket.dao.movieDAO;
import mticket.dao.ticketDAO;
import mticket.dao.ticketDetailDAO;
import mticket.entity.Movie;
import mticket.entity.Ticket;
import mticket.entity.TicketDetail;
import mticket.utils.createQRPayment;
import mticket.utils.createTicketPDF;
import mticket.utils.mticketUtils;

/**
 *
 * @author HuyTinh
 */
public class mainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainJFrame
     */
    Map<Integer, Movie> foodBooking = new HashMap<>();
    ticketDAO tDAO = new ticketDAO();
    ticketDetailDAO tDDAO = new ticketDetailDAO();
    public static List<Ticket> lTK;

    public mainJFrame() {
        initComponents();
        GlassPanePopup.install(this);
        init();
        dispose();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        mticketUtils.setFont(lblThanhTien1, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(lblThanhTien2, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(btnGetticket, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(btnLogOut, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton10, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton11, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton12, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton13, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton14, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton15, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton17, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton18, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton19, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton21, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(kButton22, "Kodchasan-Medium.ttf", 24f);
    }

    //  Đổ dữ liệu lên form
    void init() {
        listMovie1.initData(new movieDAO().selectByDate(LocalDate.now().toString()), detailMovie1, lblThanhTien);
        foodList1.initFood(new foodDAO().selectAll(), lblThanhTien1);
    }

    // Đi đến form danh sách phim
    void toListMovie() {
        if (mticket.utils.scanQrCodeForTicket.camera != null) {
            if (mticket.utils.scanQrCodeForTicket.camera.isOpened()) {
                mticket.utils.scanQrCodeForTicket.camera.release();
            }
        }
        jTabbedPane1.setSelectedIndex(0);
    }

    // Đi đến form chi tiết phim
    void toDetailMovie() {
        jTabbedPane1.setSelectedIndex(1);
    }

    // Đi đén form đặt thức ăn
    void toFoodOrder() {
        if (listMovie.tk.getGheDat().size() > 0) {
            jTabbedPane1.setSelectedIndex(3);
            lblThanhTien1.setText("Tạm tính: " + mticketUtils.toCurrency(0));
        } else {
            mticketUtils.alertAnimation("Vui lòng đặt ghế !");
        }
    }

    // Đi đến form đặt ghế xem phim
    void toSeat() {
        seatBooking2.initShowTime(lblThanhTien2, detailMovie1.getName());
        lblThanhTien2.setText("Tạm tính: " + mticketUtils.toCurrency(0));
        jTabbedPane1.setSelectedIndex(2);
    }

    // Đi đến form thanh toán
    void toPayment() {
        lblThanhTien3.setText("Thành tiền: " + mticketUtils.toCurrency((int) mticketUtils.toRound(mticketUtils.toDouble(lblThanhTien1.getText())) + (int) mticketUtils.toRound(mticketUtils.toDouble(lblThanhTien2.getText())) + (int) mticketUtils.toRound(mticketUtils.toDouble(lblThanhTien.getText()))));
        jTabbedPane1.setSelectedIndex(4);
    }

    // Đi đến form thanh toán bằng QR code
    void toPayByQrCode() {
        insertVe();
        createTicketPDF.createTicket();
        foodList1.initFood(new foodDAO().selectAll(), lblThanhTien1);
        lblAnh2.setIcon(new ImageIcon(createQRPayment.createQRForPayment((int) mticketUtils.toDouble(lblThanhTien3.getText()))));
        jTabbedPane1.setSelectedIndex(6);
    }

    // Tải lại 
    void refresh() {
        foodList1.initFood(new foodDAO().selectAll(), lblThanhTien1);
        jTabbedPane1.setSelectedIndex(0);
    }

    // Thêm vé vào CSDL
    void insertVe() {
        lTK = new ArrayList<>();
        listMovie.tk.setMaNhanVien(mticketUtils.user.getMaNhanVien());
        int index = 0;
        for (String obj : listMovie.tk.getGheDat().keySet()) {
            listMovie.tk.setSoGhe(obj);
            listMovie.tk.setMaLoaiGhe(listMovie.tk.getGheDat().get(obj).getMaLoaiGhe());
            listMovie.tk.setGiaLoaiGhe(listMovie.tk.getGheDat().get(obj).getGiaGhe());
            listMovie.tk.setMaVe(mticketUtils.autoIncreaseId(new ticketDAO().selectAll().get(new ticketDAO().selectAll().size() - 1).getMaVe()));
            tDAO.insert(listMovie.tk);
            Ticket tK = new Ticket().newTicket(listMovie.tk);
            if (index == 0) {
                tK.setThucAnDat(listMovie.tk.getThucAnDat());
            }
            lTK.add(tK);
            index++;
        }
        insertChiTietVe();
    }

    // Thêm chi tiết vé vào CSDL
    void insertChiTietVe() {
        for (String string : listMovie.tk.getThucAnDat().keySet()) {
            TicketDetail tD = listMovie.tk.getThucAnDat().get(string);
            tDDAO.insert(tD);
        }
    }

    // Tạo vé bằng file word
    void createTicketReport() {
        insertVe();
        createTicketPDF.createTicket();
//        createTicketWord.createTicket();
        foodList1.initFood(new foodDAO().selectAll(), lblThanhTien1);
        jTabbedPane1.setSelectedIndex(0);
    }

    // Nhận vé bằng QR code
    void getTicketByQrCode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String maVe = mticket.utils.scanQrCodeForTicket.scanQrCodeForTicket(lblAnh1);
                if (maVe != null) {
                    Ticket tK = new ticketDAO().getFullTicket(maVe);
                    lTK = new ArrayList<>();
                    lTK.add(tK);
                    createTicketPDF.createTicket();
                    jTabbedPane1.setSelectedIndex(0);
                    mticket.utils.scanQrCodeForTicket.camera.release();
                }
            }
        }).start();
        jTabbedPane1.setSelectedIndex(5);
    }

    void logOut() {
        this.dispose();
        loginJDialog lDL = new loginJDialog(this, true);
        lDL.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        movie3 = new com.k33ptoo.components.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabListMovie = new javax.swing.JPanel();
        btnGetticket = new com.k33ptoo.components.KButton();
        srlListMovie = new javax.swing.JScrollPane();
        listMovie1 = new mticket.component.movie.listMovie();
        btnLogOut = new com.k33ptoo.components.KButton();
        tabDetailMoviePanel = new javax.swing.JPanel();
        kButton10 = new com.k33ptoo.components.KButton();
        kButton11 = new com.k33ptoo.components.KButton();
        detailMoviePanel = new javax.swing.JPanel();
        detailMovie1 = new mticket.component.movie.detailMovie();
        lblThanhTien = new javax.swing.JLabel();
        tabBookingMoviePanel = new javax.swing.JPanel();
        kButton12 = new com.k33ptoo.components.KButton();
        kButton13 = new com.k33ptoo.components.KButton();
        jPanel8 = new javax.swing.JPanel();
        seatBooking2 = new mticket.component.seat.seatBooking();
        lblThanhTien2 = new javax.swing.JLabel();
        tabAddFoodPanel = new javax.swing.JPanel();
        movie7 = new com.k33ptoo.components.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        foodList1 = new mticket.component.food.listFood();
        kButton15 = new com.k33ptoo.components.KButton();
        kButton17 = new com.k33ptoo.components.KButton();
        lblThanhTien1 = new javax.swing.JLabel();
        tabPayment = new javax.swing.JPanel();
        movie9 = new com.k33ptoo.components.KGradientPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        kButton21 = new com.k33ptoo.components.KButton();
        kButton22 = new com.k33ptoo.components.KButton();
        kButton19 = new com.k33ptoo.components.KButton();
        lblThanhTien3 = new javax.swing.JLabel();
        getTicketByQr = new javax.swing.JPanel();
        movie8 = new com.k33ptoo.components.KGradientPanel();
        lblAnh1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        kButton14 = new com.k33ptoo.components.KButton();
        payByQr = new javax.swing.JPanel();
        movie10 = new com.k33ptoo.components.KGradientPanel();
        lblAnh2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        kButton18 = new com.k33ptoo.components.KButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MTicket");
        setIconImage(new ImageIcon(this.getClass().getResource("/mticket/image/logo.png")).getImage());
        setPreferredSize(new java.awt.Dimension(1084, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        movie3.setBackground(new java.awt.Color(255, 255, 255));
        movie3.setkBorderRadius(18);
        movie3.setkEndColor(new Color(76,76,153));
        movie3.setkStartColor(new Color(159,153,255));
        movie3.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1500, 1033));

        tabListMovie.setBackground(new java.awt.Color(255, 255, 255));

        btnGetticket.setText("Get ticket");
        btnGetticket.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGetticket.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGetticket.setkEndColor(new Color(159,153,255));
        btnGetticket.setkHoverEndColor(new Color(76,76,153));
        btnGetticket.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnGetticket.setkHoverStartColor(new Color(159,153,255));
        btnGetticket.setkStartColor(new Color(76,76,153));
        btnGetticket.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGetticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetticketkButton3ActionPerformed(evt);
            }
        });

        srlListMovie.setBorder(null);
        srlListMovie.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        srlListMovie.getVerticalScrollBar().setPreferredSize(new Dimension(0, 1));

        listMovie1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 10));
        srlListMovie.setViewportView(listMovie1);

        btnLogOut.setForeground(new Color(76,76,153));
        btnLogOut.setText("LogOut");
        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogOut.setkEndColor(new Color(148,187,233));
        btnLogOut.setkHoverEndColor(new Color(238,174,202));
        btnLogOut.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnLogOut.setkHoverStartColor(new Color(238,174,202));
        btnLogOut.setkIndicatorColor(new Color(76,76,153));
        btnLogOut.setkPressedColor(new Color(148,187,233));
        btnLogOut.setkStartColor(new Color(238,174,202));
        btnLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutkButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabListMovieLayout = new javax.swing.GroupLayout(tabListMovie);
        tabListMovie.setLayout(tabListMovieLayout);
        tabListMovieLayout.setHorizontalGroup(
            tabListMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabListMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGetticket, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1142, Short.MAX_VALUE)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
            .addComponent(srlListMovie)
        );
        tabListMovieLayout.setVerticalGroup(
            tabListMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabListMovieLayout.createSequentialGroup()
                .addComponent(srlListMovie, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabListMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetticket, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", tabListMovie);

        tabDetailMoviePanel.setBackground(new java.awt.Color(255, 255, 255));

        kButton10.setText("Tiếp tục");
        kButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton10.setkEndColor(new Color(159,153,255));
        kButton10.setkHoverEndColor(new Color(76,76,153));
        kButton10.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton10.setkHoverStartColor(new Color(159,153,255));
        kButton10.setkStartColor(new Color(76,76,153));
        kButton10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton10kButton3ActionPerformed(evt);
            }
        });

        kButton11.setText("Quay lại");
        kButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton11.setkEndColor(new Color(159,153,255));
        kButton11.setkHoverEndColor(new Color(76,76,153));
        kButton11.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton11.setkHoverStartColor(new Color(159,153,255));
        kButton11.setkStartColor(new Color(76,76,153));
        kButton11.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton11kButton3ActionPerformed(evt);
            }
        });

        detailMoviePanel.setBackground(new java.awt.Color(255, 255, 255));
        detailMoviePanel.setLayout(new java.awt.BorderLayout());
        detailMoviePanel.add(detailMovie1, java.awt.BorderLayout.CENTER);

        lblThanhTien.setText("Tạm tính:");
        mticketUtils.setFont(lblThanhTien, "Kodchasan-Medium.ttf", 24f);

        javax.swing.GroupLayout tabDetailMoviePanelLayout = new javax.swing.GroupLayout(tabDetailMoviePanel);
        tabDetailMoviePanel.setLayout(tabDetailMoviePanelLayout);
        tabDetailMoviePanelLayout.setHorizontalGroup(
            tabDetailMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDetailMoviePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDetailMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDetailMoviePanelLayout.createSequentialGroup()
                        .addComponent(detailMoviePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabDetailMoviePanelLayout.createSequentialGroup()
                        .addComponent(kButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(500, 500, 500)
                        .addComponent(lblThanhTien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabDetailMoviePanelLayout.setVerticalGroup(
            tabDetailMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDetailMoviePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(detailMoviePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDetailMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhTien))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", tabDetailMoviePanel);

        tabBookingMoviePanel.setBackground(new java.awt.Color(255, 255, 255));

        kButton12.setText("Tiếp tục");
        kButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton12.setkEndColor(new Color(159,153,255));
        kButton12.setkHoverEndColor(new Color(76,76,153));
        kButton12.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton12.setkHoverStartColor(new Color(159,153,255));
        kButton12.setkStartColor(new Color(76,76,153));
        kButton12.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton12kButton3ActionPerformed(evt);
            }
        });

        kButton13.setText("Quay lại");
        kButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton13.setkEndColor(new Color(159,153,255));
        kButton13.setkHoverEndColor(new Color(76,76,153));
        kButton13.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton13.setkHoverStartColor(new Color(159,153,255));
        kButton13.setkStartColor(new Color(76,76,153));
        kButton13.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton13kButton3ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.add(seatBooking2, java.awt.BorderLayout.CENTER);

        lblThanhTien2.setText("Tạm tính:");
        mticketUtils.setFont(lblThanhTien2, "Kodchasan-Medium.ttf", 24f);

        javax.swing.GroupLayout tabBookingMoviePanelLayout = new javax.swing.GroupLayout(tabBookingMoviePanel);
        tabBookingMoviePanel.setLayout(tabBookingMoviePanelLayout);
        tabBookingMoviePanelLayout.setHorizontalGroup(
            tabBookingMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabBookingMoviePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabBookingMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabBookingMoviePanelLayout.createSequentialGroup()
                        .addComponent(kButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(500, 500, 500)
                        .addComponent(lblThanhTien2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1501, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabBookingMoviePanelLayout.setVerticalGroup(
            tabBookingMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabBookingMoviePanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabBookingMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhTien2))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", tabBookingMoviePanel);

        tabAddFoodPanel.setBackground(new java.awt.Color(255, 255, 255));

        movie7.setBackground(new java.awt.Color(255, 255, 255));
        movie7.setkBorderRadius(18);
        movie7.setkEndColor(new Color(76,76,153));
        movie7.setkStartColor(new Color(159,153,255));
        movie7.setOpaque(false);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(930, 912));
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 1));
        jScrollPane1.setViewportView(foodList1);

        javax.swing.GroupLayout movie7Layout = new javax.swing.GroupLayout(movie7);
        movie7.setLayout(movie7Layout);
        movie7Layout.setHorizontalGroup(
            movie7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        movie7Layout.setVerticalGroup(
            movie7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                .addContainerGap())
        );

        kButton15.setText("Đặt vé");
        kButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton15.setkEndColor(new Color(159,153,255));
        kButton15.setkHoverEndColor(new Color(76,76,153));
        kButton15.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton15.setkHoverStartColor(new Color(159,153,255));
        kButton15.setkStartColor(new Color(76,76,153));
        kButton15.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton15kButton3ActionPerformed(evt);
            }
        });

        kButton17.setText("Quay lại");
        kButton17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton17.setkEndColor(new Color(159,153,255));
        kButton17.setkHoverEndColor(new Color(76,76,153));
        kButton17.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton17.setkHoverStartColor(new Color(159,153,255));
        kButton17.setkStartColor(new Color(76,76,153));
        kButton17.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton17kButton3ActionPerformed(evt);
            }
        });

        lblThanhTien1.setText("Tạm tính:");
        mticketUtils.setFont(lblThanhTien1, "Kodchasan-Medium.ttf", 24f);

        javax.swing.GroupLayout tabAddFoodPanelLayout = new javax.swing.GroupLayout(tabAddFoodPanel);
        tabAddFoodPanel.setLayout(tabAddFoodPanelLayout);
        tabAddFoodPanelLayout.setHorizontalGroup(
            tabAddFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddFoodPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabAddFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movie7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabAddFoodPanelLayout.createSequentialGroup()
                        .addComponent(kButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(500, 500, 500)
                        .addComponent(lblThanhTien1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 592, Short.MAX_VALUE)
                        .addComponent(kButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabAddFoodPanelLayout.setVerticalGroup(
            tabAddFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddFoodPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(movie7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabAddFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhTien1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", tabAddFoodPanel);

        tabPayment.setBackground(new java.awt.Color(255, 255, 255));

        movie9.setBackground(new java.awt.Color(255, 255, 255));
        movie9.setkBorderRadius(18);
        movie9.setkEndColor(new Color(76,76,153));
        movie9.setkStartColor(new Color(159,153,255));
        movie9.setOpaque(false);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Payment");
        mticket.utils.mticketUtils.setFont(jLabel7, "Kodchasan-Medium.ttf", 55f);

        kButton21.setText("Qr");
        kButton21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton21.setkEndColor(new Color(159,153,255));
        kButton21.setkHoverEndColor(new Color(76,76,153));
        kButton21.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton21.setkHoverStartColor(new Color(159,153,255));
        kButton21.setkStartColor(new Color(76,76,153));
        kButton21.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mticket.utils.mticketUtils.setFont(kButton21, "Kodchasan-Medium.ttf", 36f);
        kButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton21kButton3ActionPerformed(evt);
            }
        });

        kButton22.setText("Cash");
        kButton22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton22.setkEndColor(new Color(159,153,255));
        kButton22.setkHoverEndColor(new Color(76,76,153));
        kButton22.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton22.setkHoverStartColor(new Color(159,153,255));
        kButton22.setkStartColor(new Color(76,76,153));
        kButton22.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mticket.utils.mticketUtils.setFont(kButton22, "Kodchasan-Medium.ttf", 36f);
        kButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton22kButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(489, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1476, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(kButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(kButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(674, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout movie9Layout = new javax.swing.GroupLayout(movie9);
        movie9.setLayout(movie9Layout);
        movie9Layout.setHorizontalGroup(
            movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        movie9Layout.setVerticalGroup(
            movie9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        kButton19.setText("Quay lại");
        kButton19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton19.setkEndColor(new Color(159,153,255));
        kButton19.setkHoverEndColor(new Color(76,76,153));
        kButton19.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton19.setkHoverStartColor(new Color(159,153,255));
        kButton19.setkStartColor(new Color(76,76,153));
        kButton19.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton19kButton3ActionPerformed(evt);
            }
        });

        lblThanhTien3.setText("Thành tiền:");
        mticketUtils.setFont(lblThanhTien3, "Kodchasan-Medium.ttf", 24f);

        javax.swing.GroupLayout tabPaymentLayout = new javax.swing.GroupLayout(tabPayment);
        tabPayment.setLayout(tabPaymentLayout);
        tabPaymentLayout.setHorizontalGroup(
            tabPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPaymentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movie9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabPaymentLayout.createSequentialGroup()
                        .addComponent(kButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(500, 500, 500)
                        .addComponent(lblThanhTien3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabPaymentLayout.setVerticalGroup(
            tabPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPaymentLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(movie9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhTien3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", tabPayment);

        getTicketByQr.setBackground(new java.awt.Color(255, 255, 255));

        movie8.setBackground(new java.awt.Color(255, 255, 255));
        movie8.setkBorderRadius(18);
        movie8.setkEndColor(new Color(76,76,153));
        movie8.setkStartColor(new Color(159,153,255));
        movie8.setOpaque(false);
        movie8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh1.setIconTextGap(0);
        lblAnh1.setPreferredSize(new java.awt.Dimension(730, 630));
        movie8.add(lblAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 730, 630));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Your QR Code");
        mticket.utils.mticketUtils.setFont(jLabel1, "Kodchasan-Medium.ttf", 40f);

        kButton14.setText("Quay lại");
        kButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton14.setkEndColor(new Color(159,153,255));
        kButton14.setkHoverEndColor(new Color(76,76,153));
        kButton14.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton14.setkHoverStartColor(new Color(159,153,255));
        kButton14.setkStartColor(new Color(76,76,153));
        kButton10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton14kButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout getTicketByQrLayout = new javax.swing.GroupLayout(getTicketByQr);
        getTicketByQr.setLayout(getTicketByQrLayout);
        getTicketByQrLayout.setHorizontalGroup(
            getTicketByQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getTicketByQrLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(getTicketByQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(getTicketByQrLayout.createSequentialGroup()
                        .addComponent(kButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, getTicketByQrLayout.createSequentialGroup()
                .addContainerGap(388, Short.MAX_VALUE)
                .addComponent(movie8, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(375, 375, 375))
        );
        getTicketByQrLayout.setVerticalGroup(
            getTicketByQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getTicketByQrLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 37, Short.MAX_VALUE)
                .addComponent(movie8, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(kButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", getTicketByQr);

        payByQr.setBackground(new java.awt.Color(255, 255, 255));

        movie10.setBackground(new java.awt.Color(255, 255, 255));
        movie10.setkBorderRadius(18);
        movie10.setkEndColor(new Color(76,76,153));
        movie10.setkStartColor(new Color(159,153,255));
        movie10.setOpaque(false);
        movie10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh2.setIconTextGap(0);
        lblAnh2.setPreferredSize(new java.awt.Dimension(350, 470));
        movie10.add(lblAnh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 630));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Your QR Code");
        mticket.utils.mticketUtils.setFont(jLabel8, "Kodchasan-Medium.ttf", 40f);

        kButton18.setText("Quay lại");
        kButton18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButton18.setkEndColor(new Color(159,153,255));
        kButton18.setkHoverEndColor(new Color(76,76,153));
        kButton18.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton18.setkHoverStartColor(new Color(159,153,255));
        kButton18.setkStartColor(new Color(76,76,153));
        kButton10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton18kButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout payByQrLayout = new javax.swing.GroupLayout(payByQr);
        payByQr.setLayout(payByQrLayout);
        payByQrLayout.setHorizontalGroup(
            payByQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payByQrLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payByQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(payByQrLayout.createSequentialGroup()
                        .addComponent(kButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payByQrLayout.createSequentialGroup()
                .addContainerGap(438, Short.MAX_VALUE)
                .addComponent(movie10, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(425, 425, 425))
        );
        payByQrLayout.setVerticalGroup(
            payByQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payByQrLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(movie10, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(kButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", payByQr);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -36, 1513, 880));

        javax.swing.GroupLayout movie3Layout = new javax.swing.GroupLayout(movie3);
        movie3.setLayout(movie3Layout);
        movie3Layout.setHorizontalGroup(
            movie3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movie3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1514, Short.MAX_VALUE)
                .addContainerGap())
        );
        movie3Layout.setVerticalGroup(
            movie3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movie3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movie3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(movie3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kButton10kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton10kButton3ActionPerformed
        // TODO add your handling code here:
        toSeat();
    }//GEN-LAST:event_kButton10kButton3ActionPerformed

    private void kButton11kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton11kButton3ActionPerformed
        // TODO add your handling code here:
        toListMovie();
    }//GEN-LAST:event_kButton11kButton3ActionPerformed

    private void kButton13kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton13kButton3ActionPerformed
        // TODO add your handling code here:
        toDetailMovie();
    }//GEN-LAST:event_kButton13kButton3ActionPerformed

    private void kButton12kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton12kButton3ActionPerformed
        // TODO add your handling code here:
        toFoodOrder();
    }//GEN-LAST:event_kButton12kButton3ActionPerformed

    private void kButton14kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton14kButton3ActionPerformed
        // TODO add your handling code here:
        toListMovie();
    }//GEN-LAST:event_kButton14kButton3ActionPerformed

    private void btnGetticketkButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetticketkButton3ActionPerformed
        // TODO add your handling code here:
        getTicketByQrCode();
    }//GEN-LAST:event_btnGetticketkButton3ActionPerformed

    private void kButton15kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton15kButton3ActionPerformed
        // TODO add your handling code here:
        toPayment();
    }//GEN-LAST:event_kButton15kButton3ActionPerformed

    private void kButton17kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton17kButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_kButton17kButton3ActionPerformed

    private void kButton19kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton19kButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_kButton19kButton3ActionPerformed

    private void kButton21kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton21kButton3ActionPerformed
        // TODO add your handling code here:
        toPayByQrCode();
    }//GEN-LAST:event_kButton21kButton3ActionPerformed

    private void kButton22kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton22kButton3ActionPerformed
        // TODO add your handling code here:
        createTicketReport();
    }//GEN-LAST:event_kButton22kButton3ActionPerformed

    private void kButton18kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton18kButton3ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_kButton18kButton3ActionPerformed

    private void btnLogOutkButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutkButton3ActionPerformed
        // TODO add your handling code here:
        logOut();
    }//GEN-LAST:event_btnLogOutkButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(mainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(mainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(mainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(mainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        FlatLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnGetticket;
    private com.k33ptoo.components.KButton btnLogOut;
    private mticket.component.movie.detailMovie detailMovie1;
    private javax.swing.JPanel detailMoviePanel;
    private mticket.component.food.listFood foodList1;
    private javax.swing.JPanel getTicketByQr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.k33ptoo.components.KButton kButton10;
    private com.k33ptoo.components.KButton kButton11;
    private com.k33ptoo.components.KButton kButton12;
    private com.k33ptoo.components.KButton kButton13;
    private com.k33ptoo.components.KButton kButton14;
    private com.k33ptoo.components.KButton kButton15;
    private com.k33ptoo.components.KButton kButton17;
    private com.k33ptoo.components.KButton kButton18;
    private com.k33ptoo.components.KButton kButton19;
    private com.k33ptoo.components.KButton kButton21;
    private com.k33ptoo.components.KButton kButton22;
    private javax.swing.JLabel lblAnh1;
    private javax.swing.JLabel lblAnh2;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThanhTien1;
    private javax.swing.JLabel lblThanhTien2;
    private javax.swing.JLabel lblThanhTien3;
    private mticket.component.movie.listMovie listMovie1;
    private com.k33ptoo.components.KGradientPanel movie10;
    private com.k33ptoo.components.KGradientPanel movie3;
    private com.k33ptoo.components.KGradientPanel movie7;
    private com.k33ptoo.components.KGradientPanel movie8;
    private com.k33ptoo.components.KGradientPanel movie9;
    private javax.swing.JPanel payByQr;
    private mticket.component.seat.seatBooking seatBooking2;
    private javax.swing.JScrollPane srlListMovie;
    private javax.swing.JPanel tabAddFoodPanel;
    private javax.swing.JPanel tabBookingMoviePanel;
    private javax.swing.JPanel tabDetailMoviePanel;
    private javax.swing.JPanel tabListMovie;
    private javax.swing.JPanel tabPayment;
    // End of variables declaration//GEN-END:variables
}
