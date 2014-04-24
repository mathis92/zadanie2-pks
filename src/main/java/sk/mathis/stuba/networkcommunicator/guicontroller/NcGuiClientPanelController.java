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
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mathis.stuba.equip.DataHelpers;
import sk.mathis.stuba.equip.FragmentCounter;
import sk.mathis.stuba.equip.LogFiller;
import sk.mathis.stuba.equip.OutgoingPacket;
import sk.mathis.stuba.networkcommunicator.NcGuiClientPanel;

/**
 *
 * @author Mathis
 */
public class NcGuiClientPanelController implements Runnable {

    private NcGuiClientPanel guiPanel;

    private LogFiller lf;
    private FragmentCounter fc;

    public NcGuiClientPanelController(NcGuiClientPanel guiPanel) {
        this.guiPanel = guiPanel;
        fc = new FragmentCounter(guiPanel);
        lf = new LogFiller(guiPanel);
        new Thread(fc).start();
    }

    @Override
    public void run() {

        guiPanel.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (DatagramSocket clientSocket = new DatagramSocket()) {
                    final InetAddress IPAddress = InetAddress.getByName(guiPanel.getGui().getDestinationIpAddress().getText());
                    final String sentence = guiPanel.getSendTextField().getText();
                    guiPanel.getCommunicationArea().append(guiPanel.getNameField().getText() + " sent : " + sentence + "\n\t" + "fragment count : " + fc.getFragmentCount() + "\n");
                    for (OutgoingPacket outPacket : splitSentence(sentence)) {

                        final DatagramPacket outgoingPacket = new DatagramPacket(outPacket.getOutgoingPacket(), outPacket.getOutgoingPacket().length, IPAddress, Integer.parseInt(guiPanel.getGui().getCommunicationPort().getText()));

                        try {
                            clientSocket.send(outgoingPacket);
                            lf.fillLog(outPacket);
                        } catch (IOException ex) {
                            Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
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
        byte[] name = guiPanel.getNameField().getText().getBytes();
        byte type[] = new byte[1];
        Integer sentenceLength = sentence.getBytes().length;
        byte[] data = sentence.getBytes();

        packetSize = Integer.parseInt((String) guiPanel.getPacketSize().getSelectedItem());
        dataLength = packetSize - (21 + name.length);

        packetCount = (int) Math.ceil((double) sentence.getBytes().length / dataLength);
    //    System.out.println("sentence length " + sentence.getBytes().length + " data length " + dataLength + " packetCount " + packetCount);
        for (int i = 0; i < packetCount; i++) {
            if (packetCount == 1) {
                type[0] = 0x01;
            } else if (i == 0) {
                type[0] = 0x00;
            } else if (i == (packetCount - 1)) {
           //     System.out.println((packetCount - 1) + " = " + i);
                type[0] = 0x01;
            } else {
                type[0] = 0x02;
            }
         //   System.out.println(type[0]);
            byte[] tempData = new byte[dataLength + name.length];
         //   System.out.println(data);
        //    System.out.println("packetSize*packetNum:" + packetSize * packetNum + "    " + sentenceLength);
        //    System.out.println("packet count " + packetCount);
            System.arraycopy(name, 0, tempData, 0, name.length);
            System.arraycopy(data, (dataLength * packetNum), tempData, name.length, ((sentenceLength > dataLength) ? dataLength : sentenceLength));
            System.out.println("\n data length " + ((sentenceLength > dataLength) ? dataLength : sentenceLength));

        //    System.out.println("data " + data + " dataLength * packetNum " + dataLength * packetNum + ", tempData" + tempData + ", ((sentenceLength > dataLength) ? dataLength : sentenceLength)" + ((sentenceLength > dataLength) ? dataLength : sentenceLength));
            packetList.add(new OutgoingPacket(packetCount, packetNum, packetSize, ((sentenceLength > dataLength) ? dataLength : sentenceLength), type, name.length, tempData));
            packetNum++;
            System.out.println("\n" + sentenceLength + " "  + dataLength);
            sentenceLength -= dataLength;
           // System.out.println("\n" + sentenceLength + " "  + dataLength);
        }
        return packetList;
    }

    public FragmentCounter getFc() {
        return fc;
    }
    
    
}
