/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import mticket.component.glasspanepopup.GlassPanePopup;
import mticket.component.glasspanepopup.Message;
import mticket.component.pictureBox.DefaultPictureBoxRender;
import mticket.component.pictureBox.PictureBox;
import mticket.entity.Staff;
import mticket.view.loginJDialog;

/**
 *
 * @author HuyTinh
 */
public class mticketUtils {

    public static Staff user = null;
    public static boolean logout = false;

    public static Staff user() {
        return mticketUtils.user;
    }

    public static void logoff() {
        mticketUtils.user = null;
    }

    // Set phông chữ
    public static void setFont(JComponent lbl, String fontpath, float size) {
        try {
            Font tf = Font.createFont(Font.TRUETYPE_FONT, new File(mticketUtils.class.getResource("/mticket/font/" + fontpath).toURI())).deriveFont(size);
            lbl.setFont(tf);
        } catch (FontFormatException | IOException | URISyntaxException ex) {
            Logger.getLogger(loginJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Set icon
    public static void setIcon(ImageIcon icon, JLabel jl) {
        jl.setIcon(new ImageIcon(icon.getImage().getScaledInstance(jl.getPreferredSize().width, jl.getPreferredSize().height, Image.SCALE_SMOOTH)));
    }

    // Set icon bo góc
    public static void setIcon(ImageIcon icon, PictureBox pB, int a) {
        pB.setImage(icon);
        pB.setRenderType(PictureBox.RenderType.IMAGE);
        pB.setBoxFit(PictureBox.BoxFit.FILL);
        pB.setPictureBoxRender(new DefaultPictureBoxRender() {
            @Override
            public Shape render(Rectangle rectangle) {
                return createRound(rectangle, a);
            }
        });
    }

    // Chuyền đổi double thành tiền tệ
    public static String toCurrency(double d) {
        return NumberFormat.getCurrencyInstance(new Locale("vn", "vn")).format(d);
    }

    // Chuyển đổi tiền tệ thành double
    public static double toDouble(String c) {
        return Double.parseDouble(c.split(" ")[2].substring(2).replace(",", ""));
    }

    // Làm tròn kiểu double
    public static double toRound(double r) {
        return Math.round(r / 1000) * 1000;
    }

    // Tự động tăng key khóa chính
    public static String autoIncreaseId(String id) {
        int n = Integer.parseInt(id.substring(2)) + 1;
        return n < 10 ? id.substring(0, 4) + n : n < 100 ? id.substring(0, 3) + n : id.substring(0, 2) + n;
    }

    // Lưu file ảnh
    public static boolean save(File src) {
        File dts = new File(mticketUtils.class.getResource("/mticket/image").getPath(), src.getName());
        if (!dts.getParentFile().exists()) {
            dts.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dts.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Thông báo
    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message, "MTicket", JOptionPane.INFORMATION_MESSAGE);
    }

    // Thông báo có tham số
    public static String prompt(String message) {
        return JOptionPane.showInputDialog(null, message, "MTicket", JOptionPane.OK_CANCEL_OPTION);
    }

    // Thông báo yes/no
    public static int comfirm(String message) {
        return JOptionPane.showConfirmDialog(null, message, "MTicket", JOptionPane.YES_NO_OPTION);
    }

    public static void alertAnimation(String text) {
        Message obj = new Message(text);
        GlassPanePopup.showPopup(obj);
    }
}
