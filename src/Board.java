public class Board {
    private int size;
    private char[][] boardField;
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

        boolean win = false;


        // Horizontal check
        for (int i = 0; i < size; i++){
            win = true;
            if (boardField[mark.getX()][i] != mark.getValue()){
                win = false;
                break;
            }
        }
        if (win) return true;


        // Vertical check
        for (int i = 0; i < size; i++){
            win = true;
            if (boardField[i][mark.getY()] != mark.getValue()){
                win = false;
                break;
            }
        }
        if (win) return true;

        // Diagonal check #1
        if ((size - mark.getX() - 1) == (mark.getY())){
            int j = size;
            win = true;
            for (int i = 0; i < size; i++){
                j--;
                if (boardField[i][j] != mark.getValue()){
                    win = false;
                    break;
                }
            }
        }
        if (win) return true;

        // Diagonal check #2
        if((mark.getX() == mark.getY())) {
            int j = -1;
            win = true;
            for (int i = 0; i < size; i++){
                j++;
                if (boardField[i][j] != mark.getValue()){
                    win = false;
                    break;
                }
            }
        }
        if (win) return true;

        return false;

    }

    public void stamp(Player player){
        boardField[player.getMark().getX()][player.getMark().getY()] = player.getMark().getValue();
    }


    public int getSize(){
         return size;
    }

    public boolean isFree(Mark mark){
        return boardField[mark.getX()][mark.getY()] == defaultSymbol;
    }

    public char getDefaultSymbol(){
        return defaultSymbol;
    }

    public char[][] getBoardField(){
        return boardField;
    }



}
