import java.util.Scanner;

public class TicTacToe {
    private static class Board {
        private char[][] board = new char[3][3];

        public Board() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = '-';
                }
            }
        }

        public void printBoard() {
            System.out.println("-------------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println();
                System.out.println("-------------");
            }
        }

        public boolean makeMove(int row, int col, int player) {
            if (board[row][col] == '-') {
                board[row][col] = (player == 1) ? 'X' : 'O';
                return true;
            } else {
                return false;
            }
        }

        public boolean checkForWin(int player) {
            // Check rows
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                    return true;
                }
            }

            // Check columns
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                    return true;
                }
            }

            // Check diagonals
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
                return true;
            }
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
                return true;
            }

            return false;
        }

        public boolean checkForDraw() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
        play(board);
    }

    public static void play(Board board) {
        Scanner scanner = new Scanner(System.in);
        int currentPlayer = 1;
        boolean gameEnded = false;

        while (!gameEnded) {
            // Get the player's move
            System.out.println("Player " + currentPlayer + ", enter a cell number (1-9):");
            int cellNum = scanner.nextInt();

            // Convert the cell number to row and column indices
            int row = (cellNum - 1) / 3;
            int col = (cellNum - 1) % 3;

            // Make the move and check for a winner
            boolean validMove = board.makeMove(row, col, currentPlayer);
            if (validMove) {
                boolean winner = board.checkForWin(currentPlayer);
                if (winner) {
                    gameEnded = true;
                    System.out.println("Congratulations, Player " + currentPlayer + ", you have won!");
                    board.printBoard();
                } else {
                    boolean draw = board.checkForDraw();
                    if (draw) {
                        gameEnded = true;
                        System.out.println("The game is a draw!");
                        board.printBoard();
                    } else {
                        // Switch to the other player
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                        board.printBoard();
                    }
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }
}

