import java.util.Scanner;
public class tictactoe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args) {
        boolean finished = false;
        Scanner in = new Scanner(System.in);

        while (!finished) {
            boolean playing = true;
            String player = "X";
            int moveCount = 0;
            int row, col;
            final int MOVES_FOR_WIN = 5;
            final int MOVES_FOR_TIE = 9;

            clearBoard();

            while (playing) {
                showBoard();
                System.out.println("Enter the move for " + player);

                do {
                    row = SafeInput.getRangedInt(in, "Enter row", 1, 3);
                    col = SafeInput.getRangedInt(in, "Enter column", 1, 3);
                    row--;
                    col--;
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCount++;

                if (moveCount >= MOVES_FOR_WIN && isWin(player)) {
                    showBoard();
                    System.out.println("Player " + player + " wins!");
                    playing = false;
                } else if (moveCount == MOVES_FOR_TIE && isTie()) {
                    showBoard();
                    System.out.println("It's a tie!");
                    playing = false;
                }

                if (playing) {
                    player = (player.equals("X")) ? "O" : "X";
                }
            }

            finished = !SafeInput.getYNConfirm(in, "Done again?");
        }
    }

    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void showBoard() {
        System.out.println(" 1 2 3");
        for (int i = 0; i < ROW; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("  -----");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
