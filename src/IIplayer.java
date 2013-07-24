import java.util.ArrayList;

public class IIplayer extends Player {

    private Board board;


    public IIplayer(Board board){
        this.name = "bot#1337";
        this.label = 'O';
        this.board = board;
    }

    class Position {
        public int x;
        public int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private boolean checkAndSet(int x, int y, int enemyChecks, int target, boolean a){
        if (board.getSize()-enemyChecks == target){
            mark.setX(x);
            mark.setY(y);
            return true;
        }
        return false;
    }

    private void checkAndSet(int x, int y, int enemyChecks, int target){
        if (board.getSize()-enemyChecks == target){
            mark.setX(x);
            mark.setY(y);
        }
    }

    private boolean succesfullAttack(ArrayList<Position> freeChecks, int target){
        for (int a = 0; a<freeChecks.size(); a++){
            int x = freeChecks.get(a).x;
            int y = freeChecks.get(a).y;
            int enemyChecks = 0;
            Position dangerCheck = new Position(x,y);
            for (int i = 0; i<board.getSize(); i++){
                if ((board.getBoardField()[x][i] == this.label)){
                    enemyChecks++;
                }
            }
            checkAndSet(x, y, enemyChecks, target, true);
            enemyChecks = 0;
            for (int i = 0; i<board.getSize(); i++){
                if ((board.getBoardField()[i][y] == this.label)){
                    enemyChecks++;
                }
            }
            checkAndSet(x, y, enemyChecks, target, true);
            enemyChecks = 0;
            if ((board.getSize() - x - 1) == (y)){
                int j = board.getSize();
                for (int i = 0; i < board.getSize(); i++){
                    j--;
                    if ((board.getBoardField()[i][j] == this.label)){
                        enemyChecks++;
                        x = freeChecks.get(a).x;
                        y = freeChecks.get(a).y;
                    }
                }
            } else if((x == y)) {
                int j = -1;
                for (int i = 0; i < board.getSize(); i++){
                    j++;
                    if ((board.getBoardField()[i][j] == this.label)){
                        enemyChecks++;
                    }
                }
            }

            checkAndSet(x, y, enemyChecks, target, true);
        }
        return false;
    }

    private void defend(ArrayList<Position> freeChecks, int target){
        for (int a = 0; a<freeChecks.size(); a++){
            int x = freeChecks.get(a).x;
            int y = freeChecks.get(a).y;
            int enemyChecks = 0;
            for (int i = 0; i<board.getSize(); i++){
                if ((board.getBoardField()[x][i] != this.label)&&(board.getBoardField()[x][i] != board.getDefaultSymbol())){
                    enemyChecks++;
//                    System.out.println("VRAGOV po verticali - "+enemyChecks);
                }
            }
            checkAndSet(x, y, enemyChecks, target);
            enemyChecks = 0;
            for (int i = 0; i<board.getSize(); i++){
                if ((board.getBoardField()[i][y] != this.label)&&(board.getBoardField()[i][y] != board.getDefaultSymbol())){
                    enemyChecks++;
//                    System.out.println("VRAGOV po gorizontali - "+enemyChecks);
                }
            }
            checkAndSet(x, y, enemyChecks, target);
            enemyChecks = 0;
            if ((board.getSize() - x - 1) == (y)){
                int j = board.getSize();
                for (int i = 0; i < board.getSize(); i++){
                    j--;
                    if ((board.getBoardField()[i][j] != this.label)&&(board.getBoardField()[i][j] != board.getDefaultSymbol())){
                        enemyChecks++;
                    }
                }
                // System.out.println("VRAGOV po asd - "+enemyChecks);
            }
            checkAndSet(x, y, enemyChecks, target);
            enemyChecks = 0;
            if((x == y)) {
                int j = -1;
                for (int i = 0; i < board.getSize(); i++){
                    j++;
                    if ((board.getBoardField()[i][j] != this.label)&&(board.getBoardField()[i][j] != board.getDefaultSymbol())){
                        enemyChecks++;
                    }
                }
                // System.out.println("VRAGOV po dsa - "+enemyChecks);
            }

            checkAndSet(x, y, enemyChecks, target);
        }
    }


    public void makeMove(){

        ArrayList<Position> freeChecks = new ArrayList<Position>();

        /* get Free Checks */
        for (int i = 0; i<board.getSize(); i++){
            for (int j = 0; j<board.getSize(); j++){
                if (board.getBoardField()[i][j] == board.getDefaultSymbol()){
                    freeChecks.add(new Position(i,j));
                }
            }
        }

        /* Check dangerous */
        for (int i = board.getSize(); i > 0 ; i--) {
            if (!succesfullAttack(freeChecks, i)){
                defend(freeChecks, i);
            }
        }

    }
}
