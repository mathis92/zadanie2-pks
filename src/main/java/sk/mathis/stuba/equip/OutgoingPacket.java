/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.mathis.stuba.equip;

/**
 *
 * @author Mathis
 */
public class OutgoingPacket {

    private Integer packetCount;
    private Integer packetNum;
    private Integer packetSize;
    private byte[] data;
    private byte[] outgoingPacket;
    private Integer sentenceLength;
    private byte[] type;
    public OutgoingPacket(Integer packetCount, Integer PacketNum, Integer packetSize, Integer sentenceLength,byte[] type, byte[] data) {
        this.packetCount = packetCount;
        this.packetNum = PacketNum;
        this.packetSize = packetSize;
        this.data = data;
        outgoingPacket = new byte[packetSize];
        this.sentenceLength = sentenceLength;
        this.type = type;
        formOutgoingPacket();
    }

    private void formOutgoingPacket() {
        System.out.println(" packetCount " + packetCount + " packetNum " + packetNum + " packetSize "  + packetSize);
        System.arraycopy(DataHelpers.intToByte(packetCount), 0, outgoingPacket, 0, 4);
        System.arraycopy(DataHelpers.intToByte(packetNum), 0, outgoingPacket, 4, 4);
        System.arraycopy(DataHelpers.intToByte(packetSize), 0, outgoingPacket, 8, 4);
        System.arraycopy(DataHelpers.intToByte(sentenceLength),0,outgoingPacket,12,4);
        System.arraycopy(type, 0, outgoingPacket, 16, 1);
        System.arraycopy(data, 0, outgoingPacket, 17, sentenceLength);
    }

    public byte[] getOutgoingPacket() {
        return outgoingPacket;
    }

}
