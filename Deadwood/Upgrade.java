/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Upgrade: Upgrade object to store upgrade data
 */

public class Upgrade {
    // fields
    private int rank;
    private String currency;
    private int price;
    private Area area;

    // constructor
    public Upgrade(int r, String c, int p, Area a) {
        setRank(r);
        setCurrency(c);
        setPrice(p);
        setArea(a);
    }

    // getters and setters
    public void setRank(int r) {
        this.rank = r;
    }

    public int getRank() {
        return rank;
    }

    public void setCurrency(String c) {
        this.currency = c;
    }

    public String getCurrency() {
        return currency;
    }

    public void setPrice(int p) {
        this.price = p;
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
