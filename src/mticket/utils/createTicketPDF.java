/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mticket.utils;

import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mticket.dao.foodDAO;
import mticket.dao.movieDAO;
import mticket.entity.Ticket;
import mticket.entity.TicketDetail;
import mticket.view.mainJFrame;

/**
 *
 * @author HuyTinh
 */
public class createTicketPDF {
    //Font Chữ
    public static final String kodchasanBold = "mticket\\font\\Kodchasan-Bold.ttf";
    public static final String kodchasanMedium = "mticket\\font\\Kodchasan-Medium.ttf";
    // Format tiêu đề
    public static Paragraph formatParagraphTitle(Paragraph p) {
        try {
            PdfFont pdfFont = PdfFontFactory.createFont(kodchasanBold);
            p.setFont(pdfFont);
            p.setFontSize(32f);
            p.setMultipliedLeading(0.5f);
            p.setFontColor(WebColors.getRGBColor("#4c4c99"));
            p.setTextAlignment(TextAlignment.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(createTicketPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    // Format nội dung
    public static Paragraph formatParagraphText(Paragraph p, float ...size) {
        try {
            PdfFont pdfFont = PdfFontFactory.createFont(kodchasanMedium);
            p.setFont(pdfFont);
            p.setFontSize(size.length == 0 ? 18f : size[0]);
            p.setMultipliedLeading(1.0f);
        } catch (IOException ex) {
            Logger.getLogger(createTicketPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    // Tạo file PDF
    public static void createTicket() {
        PdfWriter pdfWriter = null;
        try {
            //created PDF document instance
            pdfWriter = new PdfWriter("D:\\ticket_1.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            for (Ticket tK : mainJFrame.lTK) {
                int total = 0;
                List<Object[]> tiK = new movieDAO().selectByIDMovieSchedule(tK.getMaLichChieu());

                document.add(formatParagraphTitle(new Paragraph("      Ticket")));
                document.add(formatParagraphText(new Paragraph("---ooOoo---")).setTextAlignment(TextAlignment.CENTER));
                document.add(formatParagraphText(new Paragraph("ID Ticket:        " + tK.getMaVe())));
                document.add(formatParagraphText(new Paragraph("Movie name:  " + tiK.get(0)[0])));
                document.add(formatParagraphText(new Paragraph("Show time:     " + tiK.get(0)[2])));
                document.add(formatParagraphText(new Paragraph("Room:              " + tiK.get(0)[1])));
                document.add(formatParagraphText(new Paragraph("Seat:                " + tK.getSoGhe())));
                document.add(formatParagraphText(new Paragraph("Food:               " + (tK.getThucAnDat().size() == 0 ? 0 : ""))));
                for (String string : tK.getThucAnDat().keySet()) {
                    TicketDetail tD = tK.getThucAnDat().get(string);
                    document.add(formatParagraphText(new Paragraph(string + "  " + (new foodDAO().selectById(tD.getThucAn()).getTenThucAn()) + " x " + tD.getSoLuong()), 10f).setTextAlignment(TextAlignment.RIGHT).setMultipliedLeading(0.5f));
                    total += tD.getGiaThucAn() * tD.getSoLuong();
                }
                if(total > 0){
                    total -= (tK.getGiaPhim() + tK.getGiaLoaiGhe())*(mainJFrame.lTK.size() - 1);
                }
                document.add(formatParagraphText(new Paragraph("-----------------------------------------------------------------------------"),14f));
                document.add(formatParagraphText(new Paragraph("Total: " + mticketUtils.toCurrency(Double.parseDouble(String.valueOf(tK.getGiaPhim() + tK.getGiaLoaiGhe() + total))).substring(2) + "VND")).setTextAlignment(TextAlignment.JUSTIFIED));
                if (mainJFrame.lTK.indexOf(tK) + 1 < mainJFrame.lTK.size()) {
                    document.add(new AreaBreak());
                }
            }
            document.close();
            Desktop.getDesktop().open(new File("D:\\ticket_1.pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(createTicketPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(createTicketPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pdfWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(createTicketPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
