package com.theson.filemanagers.manager;


import com.theson.filemanagers.model.Items;

import java.util.ArrayList;

public class FileManager {
    private static  FileManager instance;
    private static ArrayList<Items> items;
    public static FileManager getInstance(){
        if(instance == null){
            instance = new FileManager(items);
        }
        return instance;
    }

    public FileManager(ArrayList<Items> items){
        this.items = items;
    }

    public int getCounts(){
        return items.size();
    }

    public Items getFile(int position){
        return items.get(position);
    }
}
