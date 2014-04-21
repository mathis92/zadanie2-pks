/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.mathis.stuba.equip;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mathis
 */
public class IncommingPacket implements Comparable<IncommingPacket>{
     private Integer packetCount;
    private Integer packetNum;
    private Integer packetSize;
    private byte[] data;
    private Integer dataLength;
    private Integer type;
    private final byte[] receivedData;
    private Integer nameLength;
    private byte[] name;

    public IncommingPacket(byte[] receivedData) {

        this.receivedData = receivedData;
        unpackPacket();
    }

   private void unpackPacket(){
       // packet header 4B packet count, 4B packet packetNum, 4B packetSize, packet rest data
        byte[] packetCountByte = new byte[4];
        System.arraycopy(receivedData, 0, packetCountByte, 0, 4);
        packetCount = DataHelpers.toInt(packetCountByte);
        byte[] packetNumByte = new byte[4];
        System.arraycopy(receivedData, 4, packetNumByte, 0, 4);
        packetNum = DataHelpers.toInt(packetNumByte);
        byte[] packetSizeByte = new byte[4];
        System.arraycopy(receivedData, 8, packetSizeByte, 0, 4);
        packetSize = DataHelpers.toInt(packetSizeByte);
        byte[] dataLengthByte = new byte[4];
        System.arraycopy(receivedData, 12, dataLengthByte, 0, 4);
        dataLength = DataHelpers.toInt(dataLengthByte);
        System.out.println(" packet Count " + packetCount + " packet num " + packetNum + " packet size " + packetSize + " data length " + dataLength);
        byte[] typeByte = new byte[1];
        System.arraycopy(receivedData, 16, typeByte, 0, 1);
        type = DataHelpers.singleToInt(typeByte[0]);
        byte[] nameLengthByte = new byte[4];
        System.arraycopy(receivedData, 17, nameLengthByte, 0, 4);
        nameLength = DataHelpers.toInt(nameLengthByte);
        name = new byte[nameLength];
        System.arraycopy(receivedData, 21, name, 0, nameLength);
         try {
             System.out.println(new String(name,"UTF-8"));
         } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(IncommingPacket.class.getName()).log(Level.SEVERE, null, ex);
         }
        data = new byte[packetSize - (21+nameLength) ];
        System.arraycopy(receivedData,(21+nameLength) , data, 0, dataLength);
   }    

    public byte[] getData() {
        return data;
    }

    public Integer getNameLength() {
        return nameLength;
    }

    public byte[] getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public Integer getPacketCount() {
        return packetCount;
    }

    public Integer getPacketNum() {
        return packetNum;
    }

    public Integer getPacketSize() {
        return packetSize;
    }

    public byte[] getReceivedData() {
        return receivedData;
    }

    @Override
    public int compareTo(IncommingPacket packet){
        int last = this.packetNum.compareTo(packet.packetNum);
        return last == 0 ? this.packetNum.compareTo(packet.packetNum) : last;
    }
}
