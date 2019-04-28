package com.lanfairy.elly.androidsummary.DataStore.model;

import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private String author;
    private double price;
    private int pages;
    private String name;
    private String press;

    public void setPress(String press) {
        this.press = press;
    }

    public String getPress() {
        return press;
    }



    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "作者:"+
                getAuthor() + "  书名:"
                +getName() +"  页数:"
                +getPages() + "  价格:"
                +getPrice() + "  出版社:"
                +getPress();
    }
}
