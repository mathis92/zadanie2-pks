/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.networkcommunicator.guicontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mathis.stuba.equip.OutgoingPacket;
import sk.mathis.stuba.networkcommunicator.NcGuiClientPanel;

/**
 *
 * @author Mathis
 */
public class NcGuiClientPanelController implements Runnable {

    NcGuiClientPanel guiPanel;

    public NcGuiClientPanelController(NcGuiClientPanel guiPanel) {
        this.guiPanel = guiPanel;
    }

    @Override
    public void run() {

        guiPanel.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (DatagramSocket clientSocket = new DatagramSocket()) {
                    final InetAddress IPAddress = InetAddress.getByName(guiPanel.getGui().getDestinationIpAddress().getText());
                    byte[] dataToBeSend = null;// = new byte[1024];
                    final String sentence = guiPanel.getSendTextField().getText();
                    dataToBeSend = sentence.getBytes();

                    for (OutgoingPacket outPacket : splitSentence(sentence)) {

                        final DatagramPacket outgoingPacket = new DatagramPacket(outPacket.getOutgoingPacket(), outPacket.getOutgoingPacket().length, IPAddress, Integer.parseInt(guiPanel.getGui().getCommunicationPort().getText()));

                        try {
                            clientSocket.send(outgoingPacket);
                            String logSentence = "odosielam packet z ip " + IPAddress.getHostAddress();
                            guiPanel.getGui().getLogArea().append(logSentence + "\n");
                            guiPanel.getGui().getLogArea().append("sentence sent :" + sentence + "\n");
                            guiPanel.getCommunicationArea().append(sentence + "\n");
                        } catch (IOException ex) {
                            Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    clientSocket.close();

                } catch (SocketException | UnknownHostException ex) {
                    Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

    }

    public ArrayList<OutgoingPacket> splitSentence(String sentence) {
        // packet header 4B packet count, 4B packet packetNum, 4B packetSize, packet rest data
        ArrayList<OutgoingPacket> packetList = new ArrayList<>();
        Integer packetCount;
        Integer packetNum = 0;
        Integer packetSize = 0;
        Integer dataLength = 0;
        byte type[] = null;
        Integer sentenceLength = sentence.getBytes().length;
        byte[] data = sentence.getBytes();

        packetSize = Integer.parseInt((String) guiPanel.getPacketSize().getSelectedItem());
        dataLength = packetSize - 17;

        packetCount = (int) Math.ceil((double) sentence.getBytes().length / dataLength);
        for (int i = 0; i < packetCount; i++) {
            if(i == 0){
                type[0] = 0x00;
            } else if (i == packetCount -1){
                type[0] = 0x01;
            } else {
                type[0] = 0x02;
            }
            byte[] tempData = new byte[dataLength];
            System.out.println(data);
            System.out.println("packetSize*packetNum:" + packetSize * packetNum + "    " + sentenceLength);
            System.out.println("packet count " + packetCount);
            System.arraycopy(data, dataLength * packetNum, tempData, 0, ((sentenceLength > dataLength) ? dataLength : sentenceLength));
            packetList.add(new OutgoingPacket(packetCount, packetNum, packetSize, ((sentenceLength > dataLength) ? dataLength : sentenceLength),type, tempData));
            packetNum++;
            sentenceLength -= dataLength;
        }
        return packetList;
    }
}
