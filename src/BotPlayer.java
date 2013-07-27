import java.util.ArrayList;

public class BotPlayer extends Player {

    public BotPlayer(Board board){
        this.name = "bot#1337";
        mark.setValue('O');
        this.board = board;
    }

    public BotPlayer(String name, char label, Board board){
        this.name = name;
        this.mark.setValue(label);
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
        if (this.board.getSize()-enemyChecks == target){
            mark.setX(x);
            mark.setY(y);
            board.stamp(this);
            return true;
        }
        return false;
    }

    private boolean successfulAttack(ArrayList<Position> freeChecks, int target){
        for (Position freeCheck : freeChecks) {
            int x = freeCheck.x;
            int y = freeCheck.y;
            int enemyChecks = 0;
            for (int i = 0; i < board.getSize(); i++) {
                if ((board.getBoardField()[x][i] == mark.getValue())) {
                    enemyChecks++;
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
            enemyChecks = 0;
            for (int i = 0; i < board.getSize(); i++) {
                if ((board.getBoardField()[i][y] == mark.getValue())) {
                    enemyChecks++;
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
            enemyChecks = 0;
            if ((board.getSize() - x - 1) == y) {
                int j = board.getSize();
                for (int i = 0; i < board.getSize(); i++) {
                    j--;
                    if ((board.getBoardField()[i][j] == mark.getValue())) {
                        enemyChecks++;
                        x = freeCheck.x;
                        y = freeCheck.y;
                    }
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
            enemyChecks = 0;
            if ((x == y)) {
                int j = -1;
                for (int i = 0; i < board.getSize(); i++) {
                    j++;
                    if ((board.getBoardField()[i][j] == mark.getValue())) {
                        enemyChecks++;
                    }
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
        }
        return false;
    }

    private boolean successfulDefend(ArrayList<Position> freeChecks, int target){
        for (Position freeCheck : freeChecks) {
            int x = freeCheck.x;
            int y = freeCheck.y;
            int enemyChecks = 0;
            for (int i = 0; i < board.getSize(); i++) {
                if ((board.getBoardField()[x][i] != mark.getValue()) && (board.getBoardField()[x][i] != board.getDefaultSymbol())) {
                    enemyChecks++;
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
            enemyChecks = 0;
            for (int i = 0; i < board.getSize(); i++) {
                if ((board.getBoardField()[i][y] != mark.getValue()) && (board.getBoardField()[i][y] != board.getDefaultSymbol())) {
                    enemyChecks++;
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
            enemyChecks = 0;
            if ((board.getSize() - x - 1) == y) {
                int j = board.getSize();
                for (int i = 0; i < board.getSize(); i++) {
                    j--;
                    if ((board.getBoardField()[i][j] != mark.getValue()) && (board.getBoardField()[i][j] != board.getDefaultSymbol())) {
                        enemyChecks++;
                    }
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
            enemyChecks = 0;
            if ((x == y)) {
                int j = -1;
                for (int i = 0; i < board.getSize(); i++) {
                    j++;
                    if ((board.getBoardField()[i][j] != mark.getValue()) && (board.getBoardField()[i][j] != board.getDefaultSymbol())) {
                        enemyChecks++;
                    }
                }
            }
            if (checkAndSet(x, y, enemyChecks, target, true)){
                return true;
            }
        }
        return false;
    }

    public void makeMove(){

        ArrayList<Position> freeChecks = new ArrayList<Position>();

        /* get Free Checks */
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoardField()[i][j] == board.getDefaultSymbol()) {
                    freeChecks.add(new Position(i, j));
                }
            }
        }

        /* Attack or Defend*/
        int i = 0;
        while (!successfulAttack(freeChecks, i) && !successfulDefend(freeChecks, i)) {
            i++;
        }

    }
}
