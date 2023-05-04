public class Upgrade {

    private int rank;
    private String currency;
    private int price;
    private Area area;

    public Upgrade(int r, String c, int p, Area a) {
        setRank(r);
        setCurrency(c);
        setPrice(p);
        setArea(a);
    }

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
