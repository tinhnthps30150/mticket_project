package mticket.component.glasspanepopup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import mticket.utils.mticketUtils;

/**
 *
 * @author RAVEN
 */
public class Message extends javax.swing.JPanel {

    public Message(String text) {
        initComponents();
        setOpaque(false);
        txtText.setBackground(new Color(0, 0, 0, 0));
        txtText.setSelectionColor(new Color(48, 170, 63, 200));
        txtText.setOpaque(false);
        txtText.setText(text);
        mticketUtils.setFont(jLabel1, "Kodchasan-Medium.ttf", 24f);
        mticketUtils.setFont(txtText, "Kodchasan-Regular.ttf", 18f);
        mticketUtils.setFont(cmdOK, "Kodchasan-Regular.ttf", 18f);
        mticketUtils.setIcon(new ImageIcon(this.getClass().getResource("/mticket/image/infor-removebg-preview.png")), pictureBox1, 20);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtText = new javax.swing.JTextPane();
        cmdOK = new mticket.component.glasspanepopup.Button();
        pictureBox1 = new mticket.component.pictureBox.PictureBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(80, 80, 80));
        jLabel1.setText("Mticket");

        txtText.setEditable(false);
        txtText.setForeground(new java.awt.Color(133, 133, 133));
        txtText.setText("This is part of a series of short tutorials about specific elements, components, or interactions. We’ll cover the UX, the UI, and the construction inside of Sketch. Plus, there’s a freebie for you at the end!");

        cmdOK.setBackground(new java.awt.Color(204, 204, 255));
        cmdOK.setText("Ok");
        cmdOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdOKMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdOKMouseClicked
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cmdOKMouseClicked

    public void eventOK(ActionListener event) {
        cmdOK.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mticket.component.glasspanepopup.Button cmdOK;
    private javax.swing.JLabel jLabel1;
    private mticket.component.pictureBox.PictureBox pictureBox1;
    private javax.swing.JTextPane txtText;
    // End of variables declaration//GEN-END:variables
}
