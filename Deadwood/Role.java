public class Role {
    private String name;
    private String flavorText;
    private int rank;
    private boolean onCard;
    private boolean taken;

    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getFlavorText(){
        return flavorText;
    }
    public void setFlavorText(String f){
        this.flavorText = f;
    }
    public int getRank(){
        return rank;
    }
    public void setRank(int r){
        this.rank = r;
    }
    public boolean isOnCard(){
        return onCard;
    }
    public void setOnCard(boolean b){
        this.onCard = b;
    }
    public boolean isTaken(){
        return taken;
    }
    public void setTaken(boolean t){
        this.taken = t;
    }
}
