package com.example.aplikasitopup.model;

public class User1 {
    private String id1, diamond, qty, userid, hargapesan;

    public User1(String userid, String diamond, String qty, String hargapesan) {
        this.hargapesan = hargapesan;
        this.diamond = diamond;
        this.qty = qty;
        this.userid = userid;
    }
    public String getId1(){
        return id1;
    }
    public void setId1(String id1){
        this.id1= id1;
    }
    public String getDiamond(){
        return diamond;
    }
    public void setDiamond(String diamond){
        this.diamond = diamond;
    }
    public String getQty(){
        return qty;
    }
    public void setQty(){
        this.qty = qty;
    }
    public String getUserid(){
        return userid;
    }
    public void setUserid(String userid){
        this.userid = userid;
    }
    public String getHargapesan(){
        return hargapesan;
    }
    public void setHargapesan(String hargapesan) {
        this.hargapesan = hargapesan;
    }
}
