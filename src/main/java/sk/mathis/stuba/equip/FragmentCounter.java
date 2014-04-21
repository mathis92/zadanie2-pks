/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.equip;

import sk.mathis.stuba.networkcommunicator.NcGuiClientPanel;

/**
 *
 * @author Mathis
 */
public class FragmentCounter implements Runnable {

    NcGuiClientPanel guiPanel;

    public FragmentCounter(NcGuiClientPanel guiPanel) {
        this.guiPanel = guiPanel;
    }

    @Override
    public void run() {
        while (true) {
            String text = guiPanel.getSendTextField().getText();
            Integer textLength = text.getBytes().length;
            Integer fragmentCount = (int) Math.ceil(((double)textLength / ((Integer.parseInt((String) guiPanel.getPacketSize().getSelectedItem())) - 17)));
            guiPanel.getFragmentCount().setText(fragmentCount.toString());
        }
    }

}
