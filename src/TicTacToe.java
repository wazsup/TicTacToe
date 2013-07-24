// wazsup@yandex.com
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {
    public static void main(String[] args) throws java.io.IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Привет, как тебя зовут?");
        String name = input.readLine();
        System.out.print(name+", введи ширину доски ");

        Board newGame = new Board(Integer.parseInt(input.readLine()));

        Player player1 = new Player(name,'X',newGame);

        System.out.println("C кем будем играть?");
        System.out.println("    1. С другом");
        System.out.println("    2. C компьютером");
        System.out.print("         Ожидаю ввод... ");

        Player player2;

        switch (Integer.parseInt(input.readLine())){
            case 1: System.out.println("Как зовут друга? ");
                    player2 = new Player(input.readLine(),'O',newGame);
                    break;
            default: player2= new BotPlayer(newGame);
        }

        int step = 0;

        newGame.drawBoard();

        Player tempPlayer;

        do {
            step++;

            if (step % 2 != 0) {
                player1.makeMove();
                tempPlayer = player1;
            } else {
                player2.makeMove();
                tempPlayer = player2;
            }

            newGame.stamp(tempPlayer);

            newGame.drawBoard();

            if (newGame.checkWin(tempPlayer)){
                break;
            } else if (step == newGame.getSize()*newGame.getSize()){
                System.out.println("У нас ничья.");
            }

        } while (step<newGame.getSize()*newGame.getSize());

    }
}
