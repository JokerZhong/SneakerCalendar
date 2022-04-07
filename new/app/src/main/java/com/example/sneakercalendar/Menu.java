package com.example.sneakercalendar;

public class Menu {
    private String name;
    private String price;
    private String image;
    private String date;
    private int like;



    public Menu(String name,String price,String image,String date,int like){
        this.name = name;
        this.price = price;
        this.image = image;
        this.date = date;
        this.like = like;



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLike() {
        return like;
    }

    public void setLike(String like) {
        this.date = like;
    }



}