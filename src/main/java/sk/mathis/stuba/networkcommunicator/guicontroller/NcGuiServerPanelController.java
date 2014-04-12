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
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mathis.stuba.networkcommunicator.NcGuiServerPanel;

/**
 *
 * @author Mathis
 */
public class NcGuiServerPanelController implements Runnable {

    NcGuiServerPanel guiPanel;

    public NcGuiServerPanelController(NcGuiServerPanel guiPanel) {
        this.guiPanel = guiPanel;
    }

    @Override
    public void run() {

        while (true) {

            try {
                System.out.println("tu som ");
                DatagramSocket serverSocket = new DatagramSocket(9876);
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                while (true) {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(2000);
                        System.out.println("tu som ");
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        System.out.println("prijimam packet z " + receivePacket.getAddress().getHostAddress());
                        String sentence = new String(receivePacket.getData());
                        System.out.println("RECEIVED: " + sentence);

                        InetAddress IPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();
                        String capitalizedSentence = sentence.toUpperCase();
                        sendData = capitalizedSentence.getBytes();

                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                    }
                }
            } catch (SocketException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(NcGuiServerPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
