public class Take {
    private int number;
    private Area area;

    public Take(int n, Area a){
        setNumber(n);
        setArea(a);
    }

    public void setNumber(int n){
        this.number = n;
    }

    public int getNumber(){
        return this.number;
    }

    public void setArea(Area a){
        this.area = a;
    }

    public Area getArea(){
        return this.area;
    }
}
