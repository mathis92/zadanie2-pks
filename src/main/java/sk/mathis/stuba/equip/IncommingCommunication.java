/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.equip;

import com.sun.accessibility.internal.resources.accessibility;
import java.util.ArrayList;

/**
 *
 * @author Mathis
 */
public class IncommingCommunication {

    private ArrayList<IncommingPacket> incPacketList;
    private final String ipAddress;
    private Integer receivedPacketCount;
    private Integer state;

    public IncommingCommunication(String IpAddress, IncommingPacket incPacket) {
        this.ipAddress = IpAddress;
        incPacketList = new ArrayList<>();
        incPacketList.add(incPacket);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ArrayList<IncommingPacket> getIncPacketList() {
        return incPacketList;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setIncPacketList(ArrayList<IncommingPacket> incPacketList) {
        this.incPacketList = incPacketList;
    }

    public void setReceivedPacketCount() {
        this.receivedPacketCount = incPacketList.size();
    }

    public Integer getReceivedPacketCount() {
        return receivedPacketCount;
    }
    

}
