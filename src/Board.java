public class Board {
    private int size;
    private char[][] boardField;
    private int win = 0;
    private char defaultSymbol = '□';

    public Board(int size){
        this.size = size;
        boardField = new char[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                boardField[i][j] = defaultSymbol;
            }

        }
    }

    public void drawBoard(){
        System.out.println();
        for (int i = 0; i<size; i++){
            System.out.print("    ");
            for (int j = 0; j<size; j++){
                System.out.print(boardField[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean checkWin(Player player){
        Mark mark = player.getMark();

        boolean win1 = false;
        boolean win2 = false;
        boolean win3 = false;
        boolean win4 = false;

        // Horizontal check
        for (int i = 0; i<size; i++){
            win1 = true;
            if (boardField[mark.getX()][i] != mark.getValue()){
                win1 = false;
                break;
            }
        }

        // Vertical check
        for (int i = 0; i<size; i++){
            win2 = true;
            if (boardField[i][mark.getY()] != mark.getValue()){
                win2 = false;
                break;
            }
        }

        // Diagonal check
        if ((size - mark.getX() - 1) == (mark.getY())){
            int j = size;
            win3 = true;
            for (int i = 0; i < size; i++){
                j--;
                if (boardField[i][j] != mark.getValue()){
                    win3 = false;
                    break;
                }
            }
        }

        if((mark.getX() == mark.getY())) {
            int j = -1;
            win4 = true;
            for (int i = 0; i < size; i++){
                j++;
                if (boardField[i][j] != mark.getValue()){
                    win4 = false;
                    break;
                }
            }
        }

        if (win1 || win2 || win3 || win4) {
            player.sayWin();
            return true;
        } else {
            return false;
        }

    }

    public void stamp(Player player){
        boardField[player.getMark().getX()][player.getMark().getY()] = player.getMark().getValue();
    }


    public int getSize(){
         return size;
    }

    public boolean isFree(Mark mark){
        if (boardField[mark.getX()][mark.getY()] == defaultSymbol){
            return true;
        } else{
            return false;
        }
    }

    public char getDefaultSymbol(){
        return defaultSymbol;
    }

    public char[][] getBoardField(){
        return boardField;
    }



}
