/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.networkcommunicator.guicontroller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mathis.stuba.equip.DataHelpers;
import sk.mathis.stuba.equip.IncommingCommunication;
import sk.mathis.stuba.equip.IncommingPacket;
import sk.mathis.stuba.networkcommunicator.NcGuiServerPanel;

/**
 *
 * @author Mathis
 */
public class NcGuiServerPanelController implements Runnable {

    NcGuiServerPanel guiPanel;
    private ArrayList<IncommingCommunication> communications = null;

    public NcGuiServerPanelController(NcGuiServerPanel guiPanel) {
        this.guiPanel = guiPanel;

    }

    @Override
    public void run() {

        while (true) {
            DatagramSocket serverSocket = null;
            try {
                serverSocket = new DatagramSocket(9876);

                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    savePacket(receiveData, receivePacket.getAddress().getHostAddress());
                    transferCompleted();

                    guiPanel.getGui().getLogArea().append("prijimam packet z " + receivePacket.getAddress().getHostAddress() + "\n");
                    guiPanel.getCommunicationArea().append(new String(receivePacket.getData()) + "\n");
                }
            } catch (SocketException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                serverSocket.close();
            }
        }
    }

    public void transferCompleted() {
        for (IncommingCommunication incComm : communications) {
            if (incComm.getState().equals(1)) {
                Collections.sort(incComm.getIncPacketList());
                
            }
        }
    }

    public void savePacket(byte[] receivedData, String sourceIpAddress) {
        IncommingPacket incPacket = new IncommingPacket(receivedData);
        if (communications == null) {
            communications = new ArrayList<>();
            communications.add(new IncommingCommunication(sourceIpAddress, incPacket));
        } else {
            int foundIncComm = 0;
            for (IncommingCommunication incComm : communications) {
                if (incComm.getIpAddress().equalsIgnoreCase(sourceIpAddress)) {
                    incComm.getIncPacketList().add(incPacket);
                    if (incPacket.getType().equals(1)) {
                        incComm.setState(1);
                    } else {
                        incComm.setState(2);
                    }
                    foundIncComm = 1;
                }
            }
            if (foundIncComm == 0) {
                communications.add(new IncommingCommunication(sourceIpAddress, incPacket));
            }
        }
    }

}
