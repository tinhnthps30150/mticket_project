/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.QRCodeDetector;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author HuyTinh
 */
public class scanQrCodeForTicket {
// Load the OpenCV library
    static {
        URL location = scanQrCodeForTicket.class.getProtectionDomain().getCodeSource().getLocation();
        String path = (location.getPath().substring(0, location.getPath().lastIndexOf("/")));
        System.load(path + "/opencv_java480.dll");
        System.load(path + "/opencv_videoio_ffmpeg480_64.dll");
    }

    static Result result = null;
    // Create a VideoCapture object to access the camera (use 0 for the default camera)
    public static VideoCapture camera;
    
    // Hiển thị hình ảnh từ camera để detect QR code
    public static String scanQrCodeForTicket(JLabel jlbl) {

        camera = new VideoCapture(0);
        // Check if the camera is opened successfully
        if (!camera.isOpened()) {
            System.out.println("Error: Unable to access the camera.");
        }

        // Create a QRCodeDetector object
        QRCodeDetector qrCodeDetector = new QRCodeDetector();

        // Main loop to read frames from the camera and detect QR codes
        while (true) {
            // Read a frame from the camera
            Mat frameMat = new Mat();
            if (camera.isOpened()) {
                camera.read(frameMat);
                // Detect QR codes in the frame
                // Detect QR codes in the frame
                List<String> decodedTextList = new ArrayList<>();
                MatOfPoint2f points = new MatOfPoint2f();

                boolean success = qrCodeDetector.detectAndDecodeMulti(frameMat, decodedTextList, points);
                
                Point[] pointsArray = points.toArray();
                for (int j = 0; j < pointsArray.length; j++) {
                    Imgproc.line(frameMat, pointsArray[j], pointsArray[(j + 1) % pointsArray.length], new Scalar(0, 255, 0), 2);
                }
                // Display the frame with bounding box (if QR code detected)
                BufferedImage image = matToBufferedImage(frameMat);
                ImageIcon imageIcon = new ImageIcon(image);
                mticketUtils.setIcon(imageIcon, jlbl);

                if (success) {
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException e) {
                        //No result...
                    }

                    if (result != null) {
                        return (result.getText());
                    }
                    // Wait for a while (e.g., 33ms) to simulate real-time display
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
        }
    }

    // Xử lý hình ảnh tiếp nhận từ camera
    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }
}
