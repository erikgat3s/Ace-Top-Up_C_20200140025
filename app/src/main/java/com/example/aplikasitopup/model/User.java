package com.example.aplikasitopup.model;

public class User {
    private String id,item,harga, diamond, qty, userid, hargapesan;

    public User(String item, String harga){
        this.item = item;
        this.harga = harga;

    }



    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getItem(){
        return item;
    }
    public void setItem(String item){
        this.item = item;
    }
    public String getHarga(){
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }



}
