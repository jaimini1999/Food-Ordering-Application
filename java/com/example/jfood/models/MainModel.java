package com.example.jfood.models;

public class MainModel {

    String userid;
    String email;
    String name;
    String password;
    String mobile;
    String address;
    String cpass;
    byte[] image;

    public MainModel(String userid,String email, String name, String password,String mobile,String address,String cpass,byte[] im) {
        this.userid = userid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.cpass = cpass;
        this.image=im;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
