public class Take {
    private int number;
    private int x;
    private int y;
    private int h;
    private int w;

    public Take(int n, int x, int y, int h, int w){
        this.number = n;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public int getNumber(){
        return this.number;
    }
    public void setNumber(int n){
        this.number = n;
    }
    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }
}
