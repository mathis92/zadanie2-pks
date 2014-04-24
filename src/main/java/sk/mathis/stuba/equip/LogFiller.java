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
import sk.mathis.stuba.networkcommunicator.guicontroller.NcGuiClientPanelController;

/**
 *
 * @author Mathis
 */
public class LogFiller {

    private final NcGuiClientPanel guiPanel;
    String sentence = null;

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

    public void fillLog(OutgoingPacket packet) throws InterruptedException {
        sentence = ("[OUT] sending packet " + (packet.getPacketNum() + 1) + " of " + packet.getPacketCount() + " ");
        this.fill();
        System.out.println(sentence);
        try {
            sentence = ("sentence part : " + (new String(packet.getData(), "UTF-8")) + "\n");
            this.fill();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NcGuiClientPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
