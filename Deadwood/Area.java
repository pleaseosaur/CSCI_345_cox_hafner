public class Area {

    private int x;
    private int y;
    private int h;
    private int w;


    public Area(int x, int y, int h, int w) {
        setLocation(x, y);
        setDimensions(h, w);

    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDimensions(int h, int w) {
        this.h = h;
        this.w = w;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}
