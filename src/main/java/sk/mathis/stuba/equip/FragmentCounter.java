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
    boolean running = true;
    Integer fragmentCount;

    public FragmentCounter(NcGuiClientPanel guiPanel) {
        this.guiPanel = guiPanel;
    }
public void stopThread() {
        running = false;
    }
    @Override
    public void run() {
        while (running) {
            String text = guiPanel.getSendTextField().getText();
            String name = guiPanel.getNameField().getText();
            Integer textLength = text.getBytes().length;
            Integer nameLength = name.getBytes().length;
            fragmentCount = (int) Math.ceil((double)(textLength) / (Integer)guiPanel.getPacketSize().getValue());
            guiPanel.getFragmentCount().setText(fragmentCount.toString());
        }
    }

    public Integer getFragmentCount() {
        return fragmentCount;
    }

}
