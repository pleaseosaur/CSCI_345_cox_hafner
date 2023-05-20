package model;/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: model.Upgrade: model.Upgrade object to store upgrade data
 */

import model.Area;

public class Upgrade {
    // fields
    private int rank;
    private final String currency;
    private final int price;
    private Area area;

    // constructor
    public Upgrade(int r, String c, int p, Area a) {
        setRank(r);
        this.currency = c;
        this.price = p;
        setArea(a);
    }

    // getters and setters
    public void setRank(int r) {
        this.rank = r;
    }

    public int getRank() {
        return rank;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPrice() {
        return price;
    }

    public void setArea(Area a) {
        this.area = a;
    }

    public Area getArea() {
        return area;
    }
}
