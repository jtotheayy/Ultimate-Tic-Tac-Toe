
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.Random;

//Created By Jay Santamaria
//Ultimate Tic Tac Toe Game
//Rules:
/*This game is a large tic-tac-toe board, with smaller tic-tac-toe boards in each of the squares. Turns are taken in
 * the small boards. When a player makes a turn in the small tic-tac-toe board, then the next player plays in the small board
 * that corresponds to the square the previous player played. For Example: Player "X" plays their turn in the "middle-right" board,
 * and in that board they play the square "top-middle". Now Player "O" is forced to play in the "top-middle" board and can click on 
 * any of the squares in that board. If,"X" plays a square, where the corresponding board is already won, then "O" can play their turn
 * on any square, from any un-won boards. Those are the rules, the goal is to create a three-in-a-row pattern in the large board
 * and win the game.
 * Good Luck
 */
public class UltTTT extends Board{
    static String currentPlayer = "X";

    //A, 1, 2, 3, 4, 5, 6, 7, 8, 9
    static String focusedBoard = "A";
    static boolean[] wonBoards = {
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false
    };
    static Board wholeBoardCheck = new Board(); 

// Run the game
    public static void main(String[] args) {

        display();

    }
//Changes the current player of the game
    public static void changePlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

//Changes the focus on the board, meaning it only sets the boards that are allowed for play by the rules of the game. All other boards are unplayable
    public static void changeFocus(int focus, JFrame wholeBoard) {
        Border unfocusBorder = BorderFactory.createDashedBorder(Color.BLACK, 4, 4, 4, false);

        for (int i = 0; i < 9; i++) {
            if (!wonBoards[i]) {
                JPanel unFocusedPanel = (JPanel) wholeBoard.getContentPane().getComponent(i);
                unFocusedPanel.setBorder(unfocusBorder);
            }
        }

        focusedBoard = focus + "";
        Border focusBorder = BorderFactory.createLineBorder(Color.GREEN, 6, false);

        if (focusedBoard.equals("-1") || wonBoards[focus]) {
            for (int i = 0; i < 9; i++) {
                if (!wonBoards[i]) {
                    JPanel focusPanel = (JPanel) wholeBoard.getContentPane().getComponent(i);
                    focusPanel.setBorder(focusBorder);
                }
            }
            focusedBoard = "A";
        } 
        else if(focus == -100){}
        else {
            JPanel focusPanel = (JPanel) wholeBoard.getContentPane().getComponent(focus);
            focusPanel.setBorder(focusBorder);
        }
    }

//Marks the player's turn onto the board
    public static void markBoard(int board, int row, int col, JFrame wholeBoard) {

        JPanel boardPanel = (JPanel) wholeBoard.getContentPane().getComponent(board);
        JButton clickedButton = (JButton) boardPanel.getComponent(row * 3 + col);
        Font symbol = new Font(clickedButton.getFont().getName(), Font.PLAIN, 64);

        if (clickedButton.getText().equals("-")) {
            clickedButton.setText(currentPlayer);
            clickedButton.setBackground(Color.BLACK);
            if (currentPlayer.equals("X")) {
                clickedButton.setBackground(Color.RED);
                clickedButton.setFont(symbol);
            } else {
                clickedButton.setBackground(Color.BLUE);
                clickedButton.setFont(symbol);

            }

            clickedButton.setEnabled(false);


        }

    }
//Marks the player's won boards, if any, and tracks it
    public static void markWonBoard(int board, JFrame wholeBoard) {
        JPanel boardPanel = (JPanel) wholeBoard.getContentPane().getComponent(board);

        if (wonBoards[board]) {

            if (currentPlayer.equals("X")) {
                JButton b1 = (JButton) boardPanel.getComponent(0);
                b1.setBackground(Color.RED);
                JButton b2 = (JButton) boardPanel.getComponent(1);
                b2.setBackground(Color.BLACK);
                JButton b3 = (JButton) boardPanel.getComponent(2);
                b3.setBackground(Color.RED);
                JButton b4 = (JButton) boardPanel.getComponent(3);
                b4.setBackground(Color.BLACK);
                JButton b5 = (JButton) boardPanel.getComponent(4);
                b5.setBackground(Color.RED);
                JButton b6 = (JButton) boardPanel.getComponent(5);
                b6.setBackground(Color.BLACK);
                JButton b7 = (JButton) boardPanel.getComponent(6);
                b7.setBackground(Color.RED);
                JButton b8 = (JButton) boardPanel.getComponent(7);
                b8.setBackground(Color.BLACK);
                JButton b9 = (JButton) boardPanel.getComponent(8);
                b9.setBackground(Color.RED);
            } else if (currentPlayer.equals("O")) {
                JButton b1 = (JButton) boardPanel.getComponent(0);
                b1.setBackground(Color.BLACK);
                JButton b2 = (JButton) boardPanel.getComponent(1);
                b2.setBackground(Color.BLUE);
                JButton b3 = (JButton) boardPanel.getComponent(2);
                b3.setBackground(Color.BLACK);
                JButton b4 = (JButton) boardPanel.getComponent(3);
                b4.setBackground(Color.BLUE);
                JButton b5 = (JButton) boardPanel.getComponent(4);
                b5.setBackground(Color.BLACK);
                JButton b6 = (JButton) boardPanel.getComponent(5);
                b6.setBackground(Color.BLUE);
                JButton b7 = (JButton) boardPanel.getComponent(6);
                b7.setBackground(Color.BLACK);
                JButton b8 = (JButton) boardPanel.getComponent(7);
                b8.setBackground(Color.BLUE);
                JButton b9 = (JButton) boardPanel.getComponent(8);
                b9.setBackground(Color.BLACK);

                JButton winButton = (JButton) boardPanel.getComponent(5);
                Font winSymbol = new Font(winButton.getFont().getName(), Font.PLAIN, 400);
                winButton.setFont(winSymbol);
            }
        }
    }

//Sets up the GUI and the games funcionality
    static void display() {


        setInitial(wholeBoardCheck);

        Board tttBoard = new Board();
        createBoard(tttBoard.getBoard());


        JFrame wholeBoard = new JFrame("Ultimate Tic-Tac-Toe    Current Player: X");
        wholeBoard.setFocusable(true); 
        wholeBoard.setSize(1200, 1500);
        wholeBoard.setVisible(true);



        GridLayout gridLayout = new GridLayout(3, 3);
        
        wholeBoard.setLayout(gridLayout);
        Border boardborder = BorderFactory.createDashedBorder(Color.BLACK, 4, 4, 4, false);
        Border wonBorder = BorderFactory.createLineBorder(Color.RED, 10);




        for (int board = 0; board < 9; board++) {
            JPanel singleBoard = new JPanel();
            singleBoard.setLayout(gridLayout);
            singleBoard.setBorder(boardborder);
            singleBoard.setVisible(true);

            JButton[][] buttons = new JButton[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    final int row = i;
                    final int col = j;
                    final int boardNumber = board;
                    JButton b = new JButton("-");
                    b.setVisible(true);
                    
                    b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            if (!checkWinner(tttBoard.getBoard(boardNumber), currentPlayer)) {


                                System.out.println("Row: " + row + " Col: " + col + " in Board " + boardNumber);

                                if (playGame(boardNumber, row, col, currentPlayer, tttBoard, focusedBoard)) {
                                    markBoard(boardNumber, row, col, wholeBoard);
                                    if (wonBoards[boardNumber] == false && !checkWinner(tttBoard.getBoard(row * 3 + col), currentPlayer)) {
                                        changeFocus(row * 3 + col, wholeBoard);
                                    } else {
                                        changeFocus(-1, wholeBoard);
                                    }
                                    
                                    if (checkWinner(tttBoard.getBoard(boardNumber), currentPlayer)) {
                                        wonBoards[boardNumber] = true;


                                        final int row = (boardNumber) / 3;
                                        final int col = (boardNumber) % 3;


                                        Board[][] wholeBoardSymbol = wholeBoardCheck.getBoard();


                                        wholeBoardSymbol[row][col].setSym(currentPlayer);
                                        

                                        markWonBoard(boardNumber, wholeBoard);
                                        JPanel wonPanel = (JPanel) wholeBoard.getContentPane().getComponent(boardNumber);
                                        wonPanel.setBorder(wonBorder);

                                    }
                                    if(checkTie(tttBoard.getBoard(boardNumber))){
                                        wonBoards[boardNumber] = true;
                                    }

                                         if(checkWinner(wholeBoardCheck, "X")){

                                            System.out.println(currentPlayer + " WINS THE GAME");
                                            wholeBoard.setTitle("Ultimate Tic-Tac-Toe    "+currentPlayer+" WINS THE GAME!");
                                            changeFocus(-100, wholeBoard);
                                            changePlayer();
                                        }
                                        else{
                                    changePlayer();
                                    wholeBoard.setTitle("Ultimate Tic-Tac-Toe    Current Player: "+currentPlayer);
                                        }

                                        //Generate computer turn
                                         final int boardNumber = row * 3 + col;
                                         
                                        if (currentPlayer.equals("O")) {


                                            int computerBoard = boardNumber;
                                            Random rand = new Random();

                                            if(wonBoards[computerBoard] == true){
                                                while(wonBoards[computerBoard] == true){
                                                    computerBoard = rand.nextInt(wonBoards.length);
                                                }
                                            }

                                            else{computerBoard = boardNumber;}

                                           int computerMove = ComputerOpp.findBestMove(tttBoard, computerBoard);
                                            final int compRow = computerMove / 3;
                                            final int compCol = computerMove % 3;
                                            
                                            System.out.println("Computer move: " + computerMove + " " + compRow + " " + compCol);
                                            // Make the computer's move
                                            if (playGame(computerBoard, compRow, compCol, currentPlayer, tttBoard, focusedBoard)) {
                                                markBoard(computerBoard, compRow, compCol, wholeBoard);
                                                if (wonBoards[computerBoard] == false && !checkWinner(tttBoard.getBoard(compRow * 3 + compCol), currentPlayer)) {
                                                    changeFocus(compRow * 3 + compCol, wholeBoard);
                                                } else {
                                                    changeFocus(-1, wholeBoard);
                                                }
                                                
                                                if (checkWinner(tttBoard.getBoard(computerBoard), currentPlayer)) {
                                                    wonBoards[computerBoard] = true;
                                                    markWonBoard(computerBoard, wholeBoard);

                                                    Board[][] wholeBoardSymbol = wholeBoardCheck.getBoard();
                                                    wholeBoardSymbol[row][col].setSym(currentPlayer);

                                                    JPanel wonPanel = (JPanel) wholeBoard.getContentPane().getComponent(computerBoard);
                                                    wonPanel.setBorder(wonBorder);

                                                } else if (checkTie(tttBoard.getBoard(computerBoard))) {
                                                    wonBoards[computerBoard] = true;
                                                }
                                                                                        
                                            }
                                        }
                                        
                            }


                            } else {
                                System.out.println("Board is won, cannot play in this board");
                            }
                            

                            printBoard(tttBoard);
                            System.out.println(currentPlayer);
                            if (checkWinner(wholeBoardCheck, "O")) {
                                                    System.out.println(currentPlayer + " WINS THE GAME");
                                                    wholeBoard.setTitle("Ultimate Tic-Tac-Toe    " + currentPlayer + " WINS THE GAME!");
                                                    changeFocus(-100, wholeBoard);

                                                    changePlayer();
                                                } else {
                                                    changePlayer();
                                                    wholeBoard.setTitle("Ultimate Tic-Tac-Toe    Current Player: " + currentPlayer);
                                                }
                        }
                    });

                    buttons[i][j] = b;
                    singleBoard.add(buttons[i][j]);
                }
            }
            wholeBoard.add(singleBoard); // Add the current singleBoard to the wholeBoard
        }
        wholeBoard.setVisible(true);


    }


}