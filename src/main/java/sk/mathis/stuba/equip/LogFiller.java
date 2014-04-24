/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.equip;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mathis.stuba.networkcommunicator.NcGuiClientPanel;
import sk.mathis.stuba.networkcommunicator.NcGuiServerPanel;
import sk.mathis.stuba.networkcommunicator.guicontroller.NcGuiClientPanelController;

/**
 *
 * @author Mathis
 */
public class LogFiller {

    private NcGuiClientPanel guiPanel = null;
    String sentence = null;
    private NcGuiServerPanel guiServerPanel = null;

    public LogFiller(NcGuiServerPanel guiServerPanel) {
        this.guiServerPanel = guiServerPanel;
    }

    public LogFiller(NcGuiClientPanel guiPanel) {
        this.guiPanel = guiPanel;
    }

    public void fill() {

        // System.out.println(sentence);
        if (sentence != null) {
            guiPanel.getGui().getLogArea().append(sentence);
            guiPanel.getGui().getLogArea().repaint();
            guiPanel.getGui().getjDialog1().repaint();
            sentence = null;
        }

    }

    public void fillOutLog(OutgoingPacket packet) throws InterruptedException {
        sentence = ("[OUT] sending packet " + (packet.getPacketNum() + 1) + " of " + packet.getPacketCount() + " to server destination ip address " + guiPanel.getGui().getDestinationIpAddress().getText() + " on port " + guiPanel.getGui().getCommunicationPort().getText()) ;
        this.fill();
    //    System.out.println(sentence);
        try {
            byte[] sentencePart = new byte[packet.getSentenceLength()];
            System.arraycopy(packet.getData(), packet.getNameLength(), sentencePart, 0, packet.getSentenceLength());
            sentence = ("\n   sentence part : " + (new String(sentencePart, "UTF-8")) + "\n");
            this.fill();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillInLog(IncommingPacket packet) {
        try {
            sentence = ("[IN] receiving packet " + (packet.getPacketNum() + 1) + " of " + packet.getPacketCount() + " from " + (new String(packet.getName(),"UTF-8")) + " ip address " + packet.getSourceIPaddress() + " on port " + guiServerPanel.getGui().getCommunicationPort().getText());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LogFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
        guiServerPanel.getGui().getLogArea().append(sentence);
        //System.out.println(sentence);
        try {
            sentence = ("\n   sentence part : " + (new String(packet.getData(), "UTF-8")) + "\n");
            guiServerPanel.getGui().getLogArea().append(sentence);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
