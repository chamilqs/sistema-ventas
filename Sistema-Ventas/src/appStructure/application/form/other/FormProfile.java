/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appStructure.application.form.other;

/**
 *
 * @author Samil Quezada Suriel
 */
public class FormProfile extends javax.swing.JPanel {

    /**
     * Creates new form FormProfile
     */
    public FormProfile() {
        initComponents();
        icon.setSvgImage("appStructure/icon/svg/CameraNightLandscape.svg", 600, 600);
        icon2.setSvgImage("appStructure/icon/svg/red.svg", 300, 300);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Content = new javax.swing.JPanel();
        icon2 = new Utils.SVGImage2();
        icon = new Utils.SVGImage2();

        Content.setMinimumSize(new java.awt.Dimension(1115, 760));
        Content.setPreferredSize(new java.awt.Dimension(1115, 760));
        Content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Content.add(icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 380, 340));

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Content.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 874, 649));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private Utils.SVGImage2 icon;
    private Utils.SVGImage2 icon2;
    // End of variables declaration//GEN-END:variables
}
