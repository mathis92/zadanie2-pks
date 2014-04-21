/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.networkcommunicator.guicontroller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
                    guiPanel.getGui().getLogArea().append("prijimam packet z " + receivePacket.getAddress().getHostAddress() + "\n");

                    savePacket(receiveData, receivePacket.getAddress().getHostAddress());
                    transferCompleted();

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

    public void printSentence(String sentence) {
        guiPanel.getCommunicationArea().append(sentence + "\n");
    }

    public void transferCompleted() {
        int i = 0;
        for (IncommingCommunication incComm : communications) {
            System.out.println("som v transferi " + incComm.getState());
            if (incComm.getState().equals(1)) {
                Collections.sort(incComm.getIncPacketList());
                printSentence(createSentence(incComm.getIncPacketList()));
                communications.remove(i);
                break;
            }
            i++;
        }
    }

    public String createSentence(ArrayList<IncommingPacket> incPacket) {
        StringBuilder sentence = new StringBuilder();
        try {
            sentence.append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
            sentence.append((new String(incPacket.get(0).getName(),"UTF-8") + " : \n"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (IncommingPacket temp : incPacket) {
            try {
                String decoded = new String(temp.getData(), "UTF-8");
                sentence.append(decoded);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        incPacket.clear();
        incPacket = null;
        System.out.println(sentence.toString());
        return sentence.toString();
    }

    public void savePacket(byte[] receivedData, String sourceIpAddress) {
        IncommingPacket incPacket = new IncommingPacket(receivedData);
        if (communications == null) {
            communications = new ArrayList<>();
            IncommingCommunication incComm = new IncommingCommunication(sourceIpAddress, incPacket);
            communications.add(incComm);
            if (incPacket.getType().equals(1)) {
                incComm.setState(1);
            } else if (incPacket.getType().equals(0)) {
                incComm.setState(0);
            } else {
                incComm.setState(2);
            }

        } else {
            int foundIncComm = 0;
            for (IncommingCommunication incComm : communications) {
                if (incComm.getIpAddress().equalsIgnoreCase(sourceIpAddress)) {
                    incComm.getIncPacketList().add(incPacket);
                    if (incPacket.getType().equals(1)) {
                        incComm.setState(1);
                    } else if (incPacket.getType().equals(0)) {
                        incComm.setState(0);
                    } else {
                        incComm.setState(2);
                    }
                    foundIncComm = 1;
                }
            }
            if (foundIncComm == 0) {
                IncommingCommunication incComm = new IncommingCommunication(sourceIpAddress, incPacket);
                communications.add(incComm);
                if (incPacket.getType().equals(1)) {
                    incComm.setState(1);
                } else if (incPacket.getType().equals(0)) {
                    incComm.setState(0);
                } else {
                    incComm.setState(2);
                }
            }
        }
    }

}
