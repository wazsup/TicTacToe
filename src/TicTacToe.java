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

        System.out.println("C кем будем играть?");
        System.out.println("    1. С другом");
        System.out.println("    2. C компьютером");
        System.out.println("    3. Будем наблюдать за игрой");
        System.out.print("         Ожидаю ввод... ");

        Player player1;
        Player player2;
        Player tempPlayer;

        switch (Integer.parseInt(input.readLine())){
            case 1: System.out.println("Как зовут друга? ");
                    player1 = new Player(name,'X',newGame);
                    player2 = new Player(input.readLine(),'O',newGame);
                    break;
            case 2: player1 = new Player(name,'X',newGame);
                    player2 = new BotPlayer(newGame);
                    break;
            default: player1 = new BotPlayer("Bot#1",'X',newGame);
                     player2 = new BotPlayer("Bot#2",'O',newGame);
                     break;
        }

        newGame.drawBoard();

        int step = 0;

        boolean draw = false;

        while (!draw) {
            step++;

            tempPlayer = step % 2 != 0 ? player1 : player2;

            tempPlayer.makeMove();

            newGame.drawBoard();

            if (newGame.checkWin(tempPlayer)){
                tempPlayer.sayWin();
                break;
            } else if (step == newGame.getSize()*newGame.getSize()){
                draw = true;
                System.out.println("У нас ничья.");
            }
        }

    }
}
