package sk.mathis.stuba.networkcommunicator;

import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.DefaultCaret;
import sk.mathis.stuba.equip.DataHelpers;
import sk.mathis.stuba.networkcommunicator.guicontroller.NcGuiController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mathis
 */
public class NcGui extends javax.swing.JFrame {

    private NcGuiClientPanel client = null;
    private NcGuiServerPanel server = null;
    private NcGuiController controller = null;

    public NcGui() {
        controller = new NcGuiController(this);
        initComponents();
        fillComboBoxes();
        updateComboBoxes();
        myIpAddress.setText(DataHelpers.getMyIpAddress());
        this.setTitle("Network Communicator");
        DefaultCaret caret = (DefaultCaret) logArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        showLog = new javax.swing.JButton();
        interfaceType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        destinationIpAddress = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        myIpAddress = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel4 = new javax.swing.JLabel();
        communicationPort = new javax.swing.JFormattedTextField();
        stopButton = new javax.swing.JButton();

        jDialog1.setMinimumSize(new java.awt.Dimension(700, 500));

        logArea.setColumns(20);
        logArea.setRows(5);
        jScrollPane1.setViewportView(logArea);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 500));
        setMinimumSize(new java.awt.Dimension(400, 500));

        showLog.setText("Show log");
        showLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLogActionPerformed(evt);
            }
        });

        interfaceType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        interfaceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interfaceTypeActionPerformed(evt);
            }
        });

        jLabel1.setText("Interface type : ");

        jLabel2.setText("Destination ip address : ");

        destinationIpAddress.setEnabled(false);
        destinationIpAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationIpAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                destinationIpAddressFocusLost(evt);
            }
        });

        jLabel3.setText("My ip address");

        myIpAddress.setText("jLabel4");

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Communication Port :");

        communicationPort.setText("9876");
        communicationPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                communicationPortFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                communicationPortFocusLost(evt);
            }
        });
        communicationPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                communicationPortActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(myIpAddress)
                                    .addComponent(interfaceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showLog))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(communicationPort, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(destinationIpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stopButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(myIpAddress)
                    .addComponent(showLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(interfaceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(destinationIpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(communicationPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startButton)
                            .addComponent(stopButton))
                        .addGap(35, 35, 35)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void interfaceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interfaceTypeActionPerformed
        updateComboBoxes();
    }//GEN-LAST:event_interfaceTypeActionPerformed

    private void showLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLogActionPerformed
        jDialog1.setVisible(true);
        jDialog1.setLocationRelativeTo(null);
    }//GEN-LAST:event_showLogActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        if (interfaceType.getSelectedIndex() == 0) {
            if (!communicationPort.getText().matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "You have to fill Communication port!", "Notification !!!!", JOptionPane.WARNING_MESSAGE);
            } else {
                server = new NcGuiServerPanel(this);
                jTabbedPane1.addTab("Listener", server);
                controller.start();
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                interfaceType.setEnabled(false);
                communicationPort.setEnabled(false);
                destinationIpAddress.setEnabled(false);
            }
        } else {
            if (!destinationIpAddress.getText().matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
                JOptionPane.showMessageDialog(this, "You have to fill destination IP address!", "Notification !!!!", JOptionPane.WARNING_MESSAGE);
            } else if (!communicationPort.getText().matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "You have to fill Communication port!", "Notification !!!!", JOptionPane.WARNING_MESSAGE);
            } else {
                client = new NcGuiClientPanel(this);
                jTabbedPane1.addTab("Sender", client);
                controller.start();
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                interfaceType.setEnabled(false);
                communicationPort.setEnabled(false);
                destinationIpAddress.setEnabled(false);
            }
        }

    }//GEN-LAST:event_startButtonActionPerformed

    private void destinationIpAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationIpAddressFocusGained

    }//GEN-LAST:event_destinationIpAddressFocusGained

    private void destinationIpAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationIpAddressFocusLost

    }//GEN-LAST:event_destinationIpAddressFocusLost

    private void communicationPortFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_communicationPortFocusGained
    }//GEN-LAST:event_communicationPortFocusGained

    private void communicationPortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_communicationPortFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_communicationPortFocusLost

    private void communicationPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_communicationPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_communicationPortActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        if (interfaceType.getSelectedIndex() == 0) {
            if (server != null) {
                try {
                    controller.getServerController().stopThread();
                } catch (SocketException ex) {
                    Logger.getLogger(NcGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                controller.setServerController(null);

                server = null;
                jTabbedPane1.remove(0);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                interfaceType.setSelectedIndex(0);
                interfaceType.setEnabled(true);
                communicationPort.setEnabled(true);
                destinationIpAddress.setEnabled(false);
                logArea.setText(null);
            }
        }
        if (interfaceType.getSelectedIndex() == 1) {
            if (client != null) {
                controller.getClientController().getFc().stopThread();
                controller.setClientController(null);

                client = null;
                jTabbedPane1.remove(0);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                interfaceType.setSelectedIndex(0);
                interfaceType.setEnabled(true);
                communicationPort.setEnabled(true);
                destinationIpAddress.setEnabled(false);
                logArea.setText(null);
            }
        }

    }//GEN-LAST:event_stopButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NcGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NcGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NcGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NcGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new HiFiLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NcGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NcGui().setVisible(true);

            }
        });
    }

    public void fillComboBoxes() {
        interfaceType.setModel(new DefaultComboBoxModel(new String[]{"Listener", "Sender"}));
    }

    public void updateComboBoxes() {
        if (interfaceType.getSelectedIndex() == 1) {
            destinationIpAddress.setEnabled(true);
            destinationIpAddress.setText("fill IP address");
            jLabel2.setEnabled(true);
        } else if (interfaceType.getSelectedIndex() == 0) {
            destinationIpAddress.setEnabled(false);
            destinationIpAddress.setText("n/a");
            jLabel2.setEnabled(false);
        }
    }

    public NcGuiClientPanel getClient() {
        return client;
    }

    public NcGuiServerPanel getServer() {
        return server;
    }

    public JFormattedTextField getDestinationIpAddress() {
        return destinationIpAddress;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JFormattedTextField getCommunicationPort() {
        return communicationPort;
    }

    public JTextArea getLogArea() {
        return logArea;
    }

    public JDialog getjDialog1() {
        return jDialog1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField communicationPort;
    private javax.swing.JFormattedTextField destinationIpAddress;
    private javax.swing.JComboBox interfaceType;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea logArea;
    private javax.swing.JLabel myIpAddress;
    private javax.swing.JButton showLog;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
