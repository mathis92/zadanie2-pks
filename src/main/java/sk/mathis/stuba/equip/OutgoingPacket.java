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

    private final Integer packetCount;
    private final Integer packetNum;
    private final Integer packetSize;
    private final byte[] data;
    private final byte[] outgoingPacket;
    private final Integer sentenceLength;
    private final byte[] type;
    private final Integer nameLength;

    public OutgoingPacket(Integer packetCount, Integer PacketNum, Integer packetSize, Integer sentenceLength, byte[] type, Integer nameLength, byte[] data) {
        this.packetCount = packetCount;
        this.packetNum = PacketNum;
        this.packetSize = packetSize;
        this.data = data;
        outgoingPacket = new byte[packetSize];
        this.sentenceLength = sentenceLength;
        this.type = type;
        this.nameLength = nameLength;
        formOutgoingPacket();
    }

    private void formOutgoingPacket() {
    //    System.out.println(" packetCount " + packetCount + " packetNum " + packetNum + " packetSize " + packetSize);
        System.arraycopy(DataHelpers.intToByte(packetCount), 0, outgoingPacket, 0, 4);
        System.arraycopy(DataHelpers.intToByte(packetNum), 0, outgoingPacket, 4, 4);
        System.arraycopy(DataHelpers.intToByte(packetSize), 0, outgoingPacket, 8, 4);
        System.arraycopy(DataHelpers.intToByte(sentenceLength), 0, outgoingPacket, 12, 4);
        System.arraycopy(type, 0, outgoingPacket, 16, 1);
     //   System.out.println("type out " + type[0]);
        System.arraycopy(DataHelpers.intToByte(nameLength), 0, outgoingPacket, 17, 4);
        System.arraycopy(data, 0, outgoingPacket, (21), sentenceLength + nameLength);

    }

    public byte[] getOutgoingPacket() {
        return outgoingPacket;
    }

    public byte[] getType() {
        return type;
    }

    public Integer getSentenceLength() {
        return sentenceLength;
    }

    public Integer getPacketSize() {
        return packetSize;
    }

    public Integer getPacketNum() {
        return packetNum;
    }

    public Integer getPacketCount() {
        return packetCount;
    }

    public Integer getNameLength() {
        return nameLength;
    }

    public byte[] getData() {
        return data;
    }

}
