package com.pechmod.utils;

import java.io.File;

public class FileUtils {

    public static long fileLength(File file){
        if (file.isFile())
            return file.length();
        else{
            long fileLength = 0;
            for (File f : file.listFiles()){
                fileLength += fileLength(f);
            }
            return fileLength;
        }
    }
}
