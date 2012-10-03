/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TextVisualizerJPanel.java
 *
 * Created on 22.09.2012, 17:28:38
 */
package org.oobd.ui.swing.desk;

import java.awt.Graphics;
import org.oobd.base.*;
import org.oobd.base.visualizer.*;
import org.oobd.base.support.Onion;

/**
 *
 * @author steffen
 */
public class TextVisualizerJPanel extends javax.swing.JPanel implements IFvisualizer {

    boolean toBePlaced = true; //indicates, if the actual instance is already been placed on an canvas or not
    boolean awaitingUpdate = false;
    Visualizer value;

    /** Creates new form TextVisualizerJPanel */
    public TextVisualizerJPanel() {
        super();
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Paint request...");
        if (value != null) {
            functionName.setText("<html>"+value.getToolTip()+"</html>");
            functionValue.setText("<html>"+value.toString()+"</html>");
        }
        super.paintComponent(g);
    }

    public static IFvisualizer getInstance(String pageID, String vizName) {

        return new TextVisualizerJPanel();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        functionValue = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        functionName = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        backImageLabel = new javax.swing.JLabel();
        updateImageLabel = new javax.swing.JLabel();
        timerImageLabel = new javax.swing.JLabel();
        logImageLabel = new javax.swing.JLabel();
        forwardImageLabel = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(org.oobd.ui.swing.desk.swing.class).getContext().getResourceMap(TextVisualizerJPanel.class);
        functionValue.setFont(resourceMap.getFont("functionValue.font")); // NOI18N
        functionValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        functionValue.setText(resourceMap.getString("functionValue.text")); // NOI18N
        functionValue.setName("functionValue"); // NOI18N
        add(functionValue);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        functionName.setText(resourceMap.getString("titleLable.text")); // NOI18N
        functionName.setName("titleLable"); // NOI18N
        jPanel1.add(functionName);

        filler1.setName("filler1"); // NOI18N
        jPanel1.add(filler1);

        backImageLabel.setName("backImageLabel"); // NOI18N
        jPanel1.add(backImageLabel);

        updateImageLabel.setName("updateImageLabel"); // NOI18N
        jPanel1.add(updateImageLabel);

        timerImageLabel.setName("timerImageLabel"); // NOI18N
        jPanel1.add(timerImageLabel);

        logImageLabel.setName("logImageLabel"); // NOI18N
        jPanel1.add(logImageLabel);

        forwardImageLabel.setName("forwardImageLabel"); // NOI18N
        jPanel1.add(forwardImageLabel);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (evt.getClickCount() == 2) {

            value.updateRequest(OOBDConstants.UR_USER);
        }

    }//GEN-LAST:event_formMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backImageLabel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel forwardImageLabel;
    private javax.swing.JLabel functionName;
    private javax.swing.JLabel functionValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logImageLabel;
    private javax.swing.JLabel timerImageLabel;
    private javax.swing.JLabel updateImageLabel;
    // End of variables declaration//GEN-END:variables

    public boolean isGroup() {
        return false;
    }

    public void setVisualizer(Visualizer viz) {
        this.value = viz;
    }

    public void initValue(Visualizer viz, Onion onion) {
        functionName.setText(onion.getOnionString("tooltip"));
        this.value = viz;
    }

    public boolean update(int level) {
        switch (level) {
            case 0: {
                awaitingUpdate = true;
                return false;
            }
            case 2: {
                if (awaitingUpdate == true) {
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    awaitingUpdate = false;
                    return true;
                }
            }
            default:
                return false;
        }
    }

    public void setRemove(String pageID) {
        //nothing to be done here
    }
}
