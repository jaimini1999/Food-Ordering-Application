package com.example.jfood.models;

public class CategoryModel {

    String id;
    String categoryname;
    byte[] image;

    public CategoryModel(String id, String categoryname, byte[] im1) {
        this.id = id;
        this.categoryname = categoryname;
        this.image=im1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
