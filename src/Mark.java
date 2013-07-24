public class Mark {
    private int x;
    private int y;
    private char value;


    public Mark(){
        this.x = -1;
        this.y = -1;
    }

    public Mark(int x, int y, char val){
        this.x = x;
        this.y = y;
        this.value = val;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public char getValue(){
        return this.value;
    }

    public void setValue(char value){
        this.value = value;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
