package com.theson.filemanagers.model;

public class Items implements Comparable<Items>{
    private int resId;
    private String name;
    private String date;
    private String size;
    private String path;


    public Items(int resId, String name, String date, String size, String path) {
        this.resId = resId;
        this.name = name;
        this.date = date;
        this.size = size;
        this.path = path;
    }


    public int getResId() {
        return resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public String getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int compareTo(Items o) {
       if(this.name !=null){
           return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
       }else{
           throw  new IllegalArgumentException();
       }
    }
}
