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
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mathis.stuba.equip.IncommingCommunication;
import sk.mathis.stuba.equip.IncommingPacket;
import sk.mathis.stuba.equip.LogFiller;
import sk.mathis.stuba.networkcommunicator.NcGuiServerPanel;

/**
 *
 * @author Mathis
 */
public class NcGuiServerPanelController implements Runnable {

    NcGuiServerPanel guiPanel;
    boolean running = true;
    private DatagramSocket serverSocket = null;
    private ArrayList<IncommingCommunication> communications = null;
    private LogFiller lf;

    public NcGuiServerPanelController(NcGuiServerPanel guiPanel) {
        this.guiPanel = guiPanel;
        lf = new LogFiller(guiPanel);
    }

    public void stopThread() throws SocketException {
        running = false;
        //  this.serverSocket.setSoTimeout(200);

    }

    @Override
    public void run() {
        // while (running) {

        try {
            this.serverSocket = new DatagramSocket(Integer.parseInt(guiPanel.getGui().getCommunicationPort().getText()));
            this.serverSocket.setSoTimeout(200);
            while (running) {
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    serverSocket.receive(receivePacket);
                    //  guiPanel.getGui().getLogArea().append("prijimam packet z " + receivePacket.getAddress().getHostAddress() + "\n");
                    storePacket(receiveData, receivePacket.getAddress().getHostAddress());
                    transferCompleted();

                } catch (SocketTimeoutException ex) {

                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
            //     System.out.println(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (this.serverSocket != null && this.serverSocket.isBound()) {
                this.serverSocket.close();
            }
        }
        //  }
    }

    public void printSentence(String sentence) {
        guiPanel.getCommunicationArea().append(sentence);
    }

    public void transferCompleted() {
        int i = 0;
        for (IncommingCommunication incComm : communications) {
            //    System.out.println("som v transferi " + incComm.getState());
            if (incComm.getState().equals(1)) {
                Collections.sort(incComm.getIncPacketList());
                incComm.setReceivedPacketCount();
                printSentence(createSentence(incComm.getIncPacketList()));
                guiPanel.getCommunicationArea().append("\nreceived " + incComm.getReceivedPacketCount() + "/" + incComm.getIncPacketList().get(0).getPacketCount() + " fragments ");
                if (incComm.getReceivedPacketCount().equals(incComm.getIncPacketList().get(0).getPacketCount())) {
                    guiPanel.getCommunicationArea().append("Complete \n");
                } else {
                    guiPanel.getCommunicationArea().append("Incomplete \n");
                }
                incComm.getIncPacketList().clear();
                incComm.setIncPacketList(null);
                communications.remove(i);
                guiPanel.getGui().getLogArea().append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
                break;
            }
            i++;
        }

    }

    public String createSentence(ArrayList<IncommingPacket> incPackets) {
        StringBuilder sentence = new StringBuilder();
        try {
            sentence.append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
            sentence.append((new String(incPackets.get(0).getName(), "UTF-8") + " : \n"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (IncommingPacket temp : incPackets) {
            try {
                String decoded = new String(temp.getData(), "UTF-8");
                sentence.append(decoded);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        sentence.append("\n\t").append("Fragment count : \t").append(incPackets.get(0).getPacketCount());

        //  System.out.println(sentence.toString());
        return sentence.toString();
    }

    public void storePacket(byte[] receivedData, String sourceIpAddress) {
        IncommingPacket incPacket = new IncommingPacket(receivedData, sourceIpAddress);
        lf.fillInLog(incPacket);
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
