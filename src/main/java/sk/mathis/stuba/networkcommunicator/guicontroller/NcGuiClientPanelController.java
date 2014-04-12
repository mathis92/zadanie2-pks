/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.networkcommunicator.guicontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        while (true) {
            try {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("169.254.255.108");
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];
                String sentence = guiPanel.getSendTextField().getText();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                for (int i = 0; i < 10; i++) {
                    clientSocket.send(sendPacket);
                System.out.println("odosielam packet z ip " + IPAddress.getHostAddress() );
                Thread.sleep(2000);
                    
                }
                
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence);
                clientSocket.close();
            } catch (SocketException ex) {
                Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
