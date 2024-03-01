/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author HuyTinh
 */
public class createQRPayment {
    // Function to create the QR code

    // Tạo mã QR Code để thanh toán
    public static void createQR(String data, String path,
            String charset, Map hashMap,
            int height, int width)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }

    // Xử lý dữ liệu thành nén lại thành mã QR Code 
    public static Image createQRForPayment(int p) {
        try {
            String content;
            if (p > 999999) {
                content = "00020101021238540010A00000072701240006970415011007798532380208QRIBFTTA53037045407" + String.valueOf(p) + "5802VN62220818Thanh toan MTicket6304A73E";
            } else {
                content = "00020101021238540010A00000072701240006970415011007798532380208QRIBFTTA53037045406" + String.valueOf(p) + "5802VN62220818Thanh toan MTicket63045968";
            }

            String pathToStore = createQRPayment.class.getResource("/mticket/image/logo-QR.png").getPath();
// The path where the image will get saved
            // Encoding charset
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 450, 450);
            MatrixToImageConfig imageConfig = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);

            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, imageConfig);
// Getting logo image
            BufferedImage logoImage = ImageIO.read(new File(pathToStore));
            int finalImageHeight = qrImage.getHeight() - 64;
            int finalImageWidth = qrImage.getWidth() - 64;
//Merging both images
            BufferedImage finalImage = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = (Graphics2D) finalImage.getGraphics();
            graphics.drawImage(qrImage, 0, 0, null);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics.drawImage(logoImage.getScaledInstance(64, 64, 0), (int) Math.round(finalImageWidth / 2), (int) Math.round(finalImageHeight / 2), null);
//            ImageIO.write(finalImage, "png", new File(createQRPayment.class.getResource("/mticket/image/").getPath().replace("build/classes", "src") + "QRcode.png"));
//            ImageIO.write(finalImage, "png", new File("D:\\QRcodeTicket.png"));
            return finalImage;
        } catch (WriterException ex) {
            Logger.getLogger(createQRPayment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(createQRPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
