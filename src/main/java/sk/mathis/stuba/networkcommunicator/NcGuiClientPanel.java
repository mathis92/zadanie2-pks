/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.networkcommunicator;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Mathis
 */
public class NcGuiClientPanel extends javax.swing.JPanel {

    NcGui gui;

    public NcGuiClientPanel(NcGui gui) {
        this.gui = gui;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        communicationArea = new javax.swing.JTextArea();
        sendTextField = new javax.swing.JFormattedTextField();
        packetSize = new javax.swing.JComboBox();
        sendButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(360, 390));
        setMinimumSize(new java.awt.Dimension(360, 390));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(360, 390));

        communicationArea.setColumns(20);
        communicationArea.setRows(5);
        jScrollPane1.setViewportView(communicationArea);

        sendTextField.setText("jFormattedTextField1");

        packetSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        sendButton.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(sendTextField)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(packetSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(packetSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton))
                .addGap(0, 46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JFormattedTextField getSendTextField() {
        return sendTextField;
    }

    public JButton getSendButton() {
        return sendButton;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea communicationArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox packetSize;
    private javax.swing.JButton sendButton;
    private javax.swing.JFormattedTextField sendTextField;
    // End of variables declaration//GEN-END:variables
}