package com.pechmod.protocol;

import com.pechmod.connection.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;

public class PacketConsumer implements Runnable{

    BlockingQueue<Packet> packets;
    ObjectOutputStream objectOutputStream;
    Server server;
    Long bytesProcessed;
    public PacketConsumer(BlockingQueue<Packet> packetsQueue, ObjectOutputStream oos, Server server, Long byteProcessed){
        this.packets = packetsQueue;
        objectOutputStream = oos;
        this.server = server;
        this.bytesProcessed = byteProcessed;
    }

    public void run(){
        try{
           while(true){
               Packet packet = packets.take();
               bytesProcessed += packet.getSize();
               double percent = ((double) bytesProcessed / server.getTotalBytes()) * 100;
               System.out.println("Bytes Processed " + bytesProcessed);
               System.out.println("Percent " + percent);
               server.setProgressValue((int) percent);
               objectOutputStream.writeObject(packet);
           }
        }catch(InterruptedException in){
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
