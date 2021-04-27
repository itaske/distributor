package com.pechmod.protocol;

import java.io.Serializable;

public class Packet implements Serializable {

    String destination;
    byte[] data;
    String name;
    long position;
    public static final int PACKET_DEFAULT_SIZE = 2048;
    long size;
    long totalPacketGroupNo;
    public Packet (String name, String destination, long position, long size, long totalPacketGroupNo){
        this.size = size;
        this.data = new byte[(int)size];
        this.name = name;
        this.destination = destination;
        this.position = position;
        this.totalPacketGroupNo = totalPacketGroupNo;
    }

    public Packet (String name, String destination, long position, long totalPacketGroupNo){
        this(name, destination,position, PACKET_DEFAULT_SIZE, totalPacketGroupNo);
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotalPacketGroupNo() {
        return totalPacketGroupNo;
    }

    public void setTotalPacketGroupNo(long totalPacketGroupNo) {
        this.totalPacketGroupNo = totalPacketGroupNo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "destination='" + destination + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", size=" + size +
                ", groupNo="+totalPacketGroupNo+
                '}';
    }
}
