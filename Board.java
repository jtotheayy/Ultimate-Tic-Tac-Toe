
public class Board {
    String symbol = "";
    Board board[][] = new Board[3][3];


    String PlayerX = "X";
    String PlayerO = "O";


    public static Boolean checkWinner(Board boardNum, String currentPlayer) {
        String playerWinCondition = currentPlayer + currentPlayer + currentPlayer;
        Board[][] thisBoard = boardNum.getBoard();
        String cases[] = new String[8];
        cases[0] = thisBoard[0][0].getSym() + thisBoard[0][1].getSym() + thisBoard[0][2].getSym();
        cases[1] = thisBoard[1][0].getSym() + thisBoard[1][1].getSym() + thisBoard[1][2].getSym();
        cases[2] = thisBoard[2][0].getSym() + thisBoard[2][1].getSym() + thisBoard[2][2].getSym();
        cases[3] = thisBoard[0][0].getSym() + thisBoard[1][0].getSym() + thisBoard[2][0].getSym();
        cases[4] = thisBoard[0][1].getSym() + thisBoard[1][1].getSym() + thisBoard[2][1].getSym();
        cases[5] = thisBoard[0][2].getSym() + thisBoard[1][2].getSym() + thisBoard[2][2].getSym();
        cases[6] = thisBoard[0][0].getSym() + thisBoard[1][1].getSym() + thisBoard[2][2].getSym();
        cases[7] = thisBoard[0][2].getSym() + thisBoard[1][1].getSym() + thisBoard[2][0].getSym();


        for (int i = 0; i < 8; i++) {
            if (cases[i].equals(playerWinCondition)) {
                return true;
            }
        }
        return false;

    }
    public static Boolean checkTie(Board boardNum){
        Board[][] thisBoard = boardNum.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!(thisBoard[i][j].getSym().equals("X") || thisBoard[i][j].getSym().equals("O") )){
                    return false;
                }
            }
        }
        return true;
    }

    public int[] availableMoves(){
        int[] openSpots = new int[9];
        Board[][] thisBoard = this.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!(thisBoard[i][j].getSym().equals("X") || thisBoard[i][j].getSym().equals("O") )){
                    int openSpot =  i * 3 + j;
                    openSpots[openSpot] = 0;
                }
                else{
                    int openSpot =  i * 3 + j;
                    openSpots[openSpot] = 1;
                }
            }
        }
        return openSpots;
    }
//Set the symbol "X" or "O" on the board's square
    public void setSym(String i) {
        this.symbol = i;
    }

    public String getSym() {
        if (this.symbol == null) {
            return "emp";
        }

        return symbol;
    }



    public Board[][] getBoard() {
        return this.board;
    }

    public Board getBoard(int num) {
        int row = (num) / 3;
        int col = (num) % 3;

        return this.board[row][col];
    }


//prints the game board
    public static void printBoard(Board game) {

        Board[][] thisBoard = game.getBoard();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Board[][] innerBoard = thisBoard[i][j].getBoard();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        System.out.print(innerBoard[x][y].getSym());
                    }
                    System.out.println();
                }
                System.out.println("_______________________________");

            }
        }

    }

//initialized the large game board
    public static void createBoard(Board[][] main) {


        Board board1 = new Board();
        main[0][0] = board1;
        Board board2 = new Board();
        main[0][1] = board2;
        Board board3 = new Board();
        main[0][2] = board3;
        Board board4 = new Board();
        main[1][0] = board4;
        Board board5 = new Board();
        main[1][1] = board5;
        Board board6 = new Board();
        main[1][2] = board6;
        Board board7 = new Board();
        main[2][0] = board7;
        Board board8 = new Board();
        main[2][1] = board8;
        Board board9 = new Board();
        main[2][2] = board9;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                main[i][j].setSym("-");
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                setInitial(main[i][j]);

            }
        }
    }

//initializes the symbols on the game board
    public static void setInitial(Board board) {
        Board[][] innerBoard = board.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                innerBoard[i][j] = new Board();
                innerBoard[i][j].setSym("-");
            }
        }
    }

//game functionality
    public static boolean playGame(int board, int row, int col, String currentPlayer, Board tttBoard, String Focus) {
        int focusedBoard = -1;
        try {
            focusedBoard = Integer.parseInt(Focus);

        } catch (Exception e) {
            if (Focus.equals("A")) {
                System.out.println("All boards are available to place a marker on :)");
            }
        }

        if (board == focusedBoard || Focus.equals("A")) {

            int boardNumber = board;
            int boxNumber = row * 3 + col;
            
            if(!Focus.equals("A")){
            System.out.println("Next turn must be in box: " + boxNumber);}

            Board targetBoard = tttBoard.getBoard(boardNumber);

            if (targetBoard != null) {
                Board targetBox = targetBoard.getBoard(boxNumber);
                if (targetBox != null && targetBox.getSym().equals("-")) {
                    targetBox.setSym(currentPlayer);
                    //printBoard(tttBoard);

                    if (checkWinner(targetBoard, currentPlayer)) {
                        System.out.println(currentPlayer + "WINS!!! board: " + targetBoard);
                    }

                    return true;

                } else {
                    System.out.println("Box is already taken. Try again.");
                    return false;
                }
            } else {
                System.out.println("Invalid board number. Try again. 1");
                return false;
            }
        } else {
            System.out.println("Invalid board number. Try again. 2");
            return false;
        }

        
    }



}