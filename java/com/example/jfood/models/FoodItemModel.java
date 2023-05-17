package com.example.jfood.models;

public class FoodItemModel {

    String itemid;
    String fname;
    String fdescription;
    String fprice;
    String fcname;
    byte[] image;

    public FoodItemModel(String itemid, String fname, String fdescription, String fprice, String fcname, byte[] im2) {
        this.itemid = itemid;
        this.fname = fname;
        this.fdescription = fdescription;
        this.fprice = fprice;
        this.fcname = fcname;
        this.image=im2;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }

    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    public String getFcname() {
        return fcname;
    }

    public void setFcname(String fcname) {
        this.fcname = fcname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
