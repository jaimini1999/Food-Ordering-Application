package com.example.jfood.models;

public class OrderModel {

    String orderid;
    String oitemid;
    String oemail;
    String oprice;
    String oquantity;
    String odelivery;
    String ototal;

    public OrderModel(String orderid, String oitemid, String oemail, String oprice, String oquantity, String odelivery,String ototal) {
        this.orderid = orderid;
        this.oitemid = oitemid;
        this.oemail = oemail;
        this.oprice = oprice;
        this.oquantity = oquantity;
        this.odelivery = odelivery;
        this.ototal = ototal;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOitemid() {
        return oitemid;
    }

    public void setOitemid(String oitemid) {
        this.oitemid = oitemid;
    }

    public String getOemail() {
        return oemail;
    }

    public void setOemail(String oemail) {
        this.oemail = oemail;
    }

    public String getOprice() {
        return oprice;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public String getOquantity() {
        return oquantity;
    }

    public void setOquantity(String oquantity) {
        this.oquantity = oquantity;
    }

    public String getOdelivery() {
        return odelivery;
    }

    public void setOdelivery(String odelivery) {
        this.odelivery = odelivery;
    }

    public String getOtotal() {
        return ototal;
    }

    public void setOtotal(String ototal) {
        this.ototal = ototal;
    }
}
