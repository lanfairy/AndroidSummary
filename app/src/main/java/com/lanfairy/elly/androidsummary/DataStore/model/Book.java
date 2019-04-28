package com.lanfairy.elly.androidsummary.DataStore.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private double price;
    @Column(ignore = false)
    private int pages;

    @Column(ignore = false)
    private int testPages;

    @Column(defaultValue = "豆瓣评分：☆☆☆☆☆")
    private String rating;//好评度
    //经测 @Column(unique = true, defaultValue = "unknown")  报错
    private String author;
    private String name;
    private String press;

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public int getTestPages() {
        return testPages;
    }

    public void setTestPages(int testPages) {
        this.testPages = testPages;
    }

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
        return "作者:" +
                getAuthor() + "  书名:"
                + getName() + "  页数:"
                + getPages() + "  价格:"
                + getPrice() + "  出版社:"
                + getPress() + "  test页数:"
                + getTestPages() + getRating();
    }
}
