package com.pechmod.protocol;

import com.pechmod.utils.User;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PacketMerger{

    Map<String, List<Packet>> packetPool= new ConcurrentHashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    User userDetails;

    public PacketMerger(){
        this.userDetails = new User();
    }


    public synchronized void addPacketToPool(Packet packet){
        String filePath = packet.destination + File.separator + packet.name;
        packetPool.putIfAbsent(filePath, new LinkedList<>());
        List<Packet> packetList = packetPool.get(filePath);
        if (packetList != null)
            packetList.add(packet);
        if (packet.totalPacketGroupNo == packetList.size()){
           executorService.execute(()->convertPacketsToFile(filePath, packetList));
            packetPool.remove(filePath);
        }
    }

    public void convertPacketsToFile(String filePath, List<Packet> packets){
        File file = new File(userDetails.getFileDestination(), filePath);
        File parentFile = new File(file.getParent());
        boolean foldersCreated = true;
        if (!parentFile.exists()){
            foldersCreated = parentFile.mkdirs();
        }
        if (foldersCreated){
            try(FileOutputStream fos = new FileOutputStream(file)){
                packets.sort(Comparator.comparingLong(p -> p.position));
                for (Packet packet: packets)
                    fos.write(packet.data);
            }catch(FileNotFoundException fileNotFoundException){
                fileNotFoundException.printStackTrace();
            }catch(IOException io){
                io.printStackTrace();
            }
        }else
            return ;
    }

}
