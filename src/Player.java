import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Player {

    protected String name;
    protected Mark mark = new Mark();
    protected Board board;

    public Player(){
    }

    public Player(String name, char label, Board board){
        this.name = name;
        this.mark.setValue(label);
        this.board = board;
    }

    public void makeMove() throws java.io.IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ходит " + name + " ("+mark.getValue()+").");
        System.out.print("  X = ");
        mark.setX(Integer.parseInt(input.readLine())-1);
        System.out.print("  Y = ");
        mark.setY(Integer.parseInt(input.readLine())-1);

        while (!board.isFree(mark)){
            System.out.println("Клетка занята, повторите ввод.");
            board.drawBoard();
            System.out.print("  X = ");
            mark.setX(Integer.parseInt(input.readLine())-1);
            System.out.print("  Y = ");
            mark.setY(Integer.parseInt(input.readLine())-1);
        }
        board.stamp(this);
    }

    public void sayWin(){
        System.out.println("Победил " + name+" !");
    }


    public Mark getMark(){
        return this.mark;
    }

}
