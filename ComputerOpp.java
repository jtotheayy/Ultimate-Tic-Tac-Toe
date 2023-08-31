class ComputerOpp extends Board {

    public static int evaluateBoard(Board board) {
        if (checkWinner(board, "X")) {
            return -10;
        } else if (checkWinner(board, "O")) {
            return 10;
        } else {
            return 0;
        }
    }

    public static int findBestMove(Board board, int boardNum) {
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        int[] moves = board.getBoard(boardNum).availableMoves();

        for (int moveIndex = 0; moveIndex < moves.length; moveIndex++) {
            if (moves[moveIndex] == 0) {
                Board newBoard = copyBoard(board);
                newBoard.getBoard(boardNum).getBoard(moveIndex).setSym("O");
                int score = minimax(newBoard, boardNum, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = moveIndex;
                }
            }
        }

        return bestMove;
    }

    public static int minimax(Board board, int boardNum, int depth, int alpha, int beta, boolean isMax) {
        if (checkWinner(board.getBoard(boardNum), "X") || checkWinner(board.getBoard(boardNum), "O") || checkTie(board.getBoard(boardNum))) {
            return evaluateBoard(board.getBoard(boardNum));
        }

        int bestScore;

        int[] availableMoves = board.getBoard(boardNum).availableMoves();
        if (isMax) {
            bestScore = Integer.MIN_VALUE;
            for (int moveIndex = 0; moveIndex < availableMoves.length; moveIndex++) {
                if (availableMoves[moveIndex] == 0) {
                    Board newBoard = copyBoard(board);
                    newBoard.getBoard(boardNum).getBoard(moveIndex).setSym("O");
                    int score = minimax(newBoard, boardNum, depth + 1, alpha, beta, false);
                    bestScore = Math.max(bestScore, score);
                    newBoard.getBoard(boardNum).getBoard(moveIndex).setSym("-");
                    alpha = Math.max(alpha, bestScore);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int moveIndex = 0; moveIndex < availableMoves.length; moveIndex++) {
                if (availableMoves[moveIndex] == 0) {
                    Board newBoard = copyBoard(board);
                    newBoard.getBoard(boardNum).getBoard(moveIndex).setSym("X");
                    int score = minimax(newBoard, boardNum, depth + 1, alpha, beta, true);
                    bestScore = Math.min(bestScore, score);
                    newBoard.getBoard(boardNum).getBoard(moveIndex).setSym("-");
                    beta = Math.min(beta, bestScore);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }

        return bestScore;
    }

    //method to copy a board
    public static Board copyBoard(Board original) {
        Board newBoard = new Board();
        createBoard(newBoard.getBoard());
        Board[][] newBoards = newBoard.getBoard();
        Board[][] origBoards = original.getBoard();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Board[][] innerNewBoards = newBoards[i][j].getBoard();
                Board[][] innerOrigBoards = origBoards[i][j].getBoard();

                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        innerNewBoards[x][y].setSym(innerOrigBoards[x][y].getSym());

                    }
                }

                newBoards[i][j].board = innerNewBoards;

            }
        }

        return newBoard;
    }
}
