package com.pechmod.protocol;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class PacketWriter implements Runnable {

    BlockingQueue<Packet> packets;
    File file;
    String fileDestination;
    public PacketWriter(File file, BlockingQueue<Packet> packetsQueue, String fileDestination){
      this.file = file;
      this.packets = packetsQueue;
      this.fileDestination = fileDestination;
    }
    @Override
    public void run() {
            generateFilePackets(file, fileDestination);
    }

    public void generatePackets(File file, String fileDestination) throws InterruptedException{
        try {
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
            long len = file.length() < Packet.PACKET_DEFAULT_SIZE ? file.length() : Packet.PACKET_DEFAULT_SIZE;
            byte b[] = new byte[(int)len];
            int packetNo = 1;
            long totalPacketGroupNo = (long)((double)file.length()/Packet.PACKET_DEFAULT_SIZE);
            if ((double) file.length() %Packet.PACKET_DEFAULT_SIZE != 0)
                totalPacketGroupNo++;
            while( len > 0 && br.read(b) != -1){
                Packet  packet = new Packet(file.getName(), fileDestination, packetNo, len, totalPacketGroupNo);
                packet.data = b;
                packets.put(packet);
                len = br.available() < Packet.PACKET_DEFAULT_SIZE ? br.available() : Packet.PACKET_DEFAULT_SIZE;
                b = new byte[(int)len];
                packetNo++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateFilePackets(File file, String fileDestination){
        try{
            if (file.isFile()){
                generatePackets(file, fileDestination);
            }else if (file.isDirectory()){

                for (File f: file.listFiles())
                    generateFilePackets(f, fileDestination+File.separator+file.getName());

            }
        }catch(InterruptedException in){

        }
    }

}
