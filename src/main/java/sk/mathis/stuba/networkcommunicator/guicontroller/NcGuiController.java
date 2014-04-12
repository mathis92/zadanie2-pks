/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.networkcommunicator.guicontroller;

import sk.mathis.stuba.networkcommunicator.NcGui;

/**
 *
 * @author Mathis
 */
public class NcGuiController {

    NcGui gui;
    NcGuiClientPanelController clientController;
    NcGuiServerPanelController serverController;

    public NcGuiController(NcGui gui) {
        this.gui = gui;
    }

    public void start() {
        if (gui.getClient() != null) {
            System.out.println("client");
            clientController = new NcGuiClientPanelController(gui.getClient());
            new Thread(clientController).start();
        } else if (gui.getServer() != null) {
            System.out.println("server");
            serverController = new NcGuiServerPanelController(gui.getServer());
            new Thread(serverController).start();

        }
    }
}
