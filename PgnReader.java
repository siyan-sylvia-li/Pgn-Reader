import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PgnReader {
    public static void main(String[] args) {
        String[] tags = {
            "Event", "Site", "Date", "Round", "White", "Black", "Result"
        };
        // Parse args into the function
        String gameName = args[0];
        for (String tag : tags) {
            System.out.println(tagValue(tag, gameName));
        }
        System.out.println(finalPosition(gameName));        
    }

    public static String tagValue(String tagname, String game) {
        // Read pgn file
        File gameFile = new File(game);
        try {
            Scanner scanner = new Scanner(gameFile);
            String statement;
            String content;
            // Keep searching through each line for the key value
            // The lines we are looking for always has "["
            while (((statement = scanner.nextLine()).length() > 0) 
                && (statement.charAt(0) == '[')) {
                if (statement.contains(tagname)) {
                    // Find the segment between the quotes
                    content = statement.substring(statement.indexOf("\"") + 1,
                        statement.lastIndexOf("\""));
                    return (tagname + ": " + content);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (tagname + ": " + "NOT GIVEN");
    }

    public static void initialize(String[][] board) {
        String initialPos1 = "rnbqkbnr";
        board[0] = initialPos1.split("");
        board[7] = initialPos1.toUpperCase().split("");
        for (int i = 0; i < 8; i ++) {
            board[1][i] = "p";
            board[6][i] = "P";
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = "0";
            }
        }
        // for (String[] row : board) {
        //     for (String col : row) {
        //         System.out.print(col);
        //     }
        //     System.out.println();
        // }        
    }

    public static void capturePiece(String[][] board, String move, int side) {
        movePiece(board, move.replace("x", ""), side);
    }

    public static void movePiece(String[][] board, String move, int side) {
        // Identify which piece we are talking about
        String nonPawn = "kqrnbKQRNB";
        String piece = "";
        if ((move.charAt(0) == 'b') || (move.charAt(0) == 'B')) {
            // If no number follows b or B, then it must be a piece
            if (!Character.isDigit(move.charAt(1))){
                piece += move.charAt(0);
                move = move.substring(1);
            }
        } else if (nonPawn.contains("" + move.charAt(0))) {
            piece += move.charAt(0);
            move = move.substring(1);
            /////////////////////////////////////////////////
            ////////////////////////////////////////////////
        } else if (move.contains("=")){
            // Pawn promotion
            piece = "Q";
        } else{
            if (side == 1) {
                piece = "P";
            } else {
                piece = "p";
            }
        }
        // See if there is only the terminal square in the move
        String square = "";

        int i = move.length() - 1;
        // Look for the first digit and grab the character before it
        // That gives us our terminal square
        while ((i > 0) && (!Character.isDigit(move.charAt(i)))) {
            i --;
        }
        square = move.substring(i-1, i+1);
        System.out.println(square);
        // Find disambiguation markers, if they exist
        move = move.substring(move.indexOf(piece) + 1, i-1);
        // Figure out which piece made the move
        EmptyOriginal(board, piece, square, move);

        // Assigning the piece to the square
        // Almost forgot the letter is the column. Phew.
        board[square.charAt(1) - '1'][square.charAt(0) - 'a'] = piece;     
    }

    public static void FindOriginal(String[][] board, String piece,
        int row, int col, int mode) {
        /*
        Modes:
        1 - Up and Down a column;
        2 - Up and Down a row;
        3 - Diagonal (Major)
        4 - Diagonal
        */
        boolean found = false;
        int row1 = row;
        int row2 = row;
        int col1 = col;
        int col2 = col;

        while (!found) {
            switch (mode) {
                case 1: row1 ++;
                        row2 --;
                        break;
                case 2: col1 ++;
                        col2 --;
                        break;
                case 3: row1 ++;
                        col1 ++;
                        row2 --;
                        col2 --;
                        break;
                case 4: row1 ++;
                        col1 --;
                        row2 --;
                        col2 ++;
                        break;
            }
            if (inRange(row1) && (inRange(col1))) {
                //System.out.println("" + row1 + "" + col1);
                if (board[row1][col1].equals(piece)){
                    board[row1][col1] = "0";
                    found = true;
                }
            }

            if (inRange(row2) && (inRange(col2))) {
                //System.out.println("" + row2 + "" + col2);
                if (board[row2][col2].equals(piece)) {
                    board[row2][col2] = "0";
                    found = true;
                }
            }
        }
        
    }

    public static boolean inRange(int n) {
        return ((n >= 0) && (n <= 7));
    }

    public static void EmptyOriginal(String[][] board, String piece, 
        String terSquare, String orSquare) {

        // The terminal row and column
        int colTer = terSquare.charAt(0) - 'a';
        int rowTer = terSquare.charAt(1) - '1';
        int colOr; int rowOr;

        // If there is an disambiguation marker, just follow it
        if (orSquare.length() > 0) {
            colOr = orSquare.charAt(0) - 'a';
            if (orSquare.length() == 2) {
                rowOr = orSquare.charAt(1) - '1';
                board[rowOr][colOr] = "0";
            } else {
                boolean found = false;
                int j = 0;
                // Find the piece closest to the terminal square in the same file?
                FindOriginal(board, piece, rowTer, colOr, 1);
            }
        }
        // Pawn --> Move vertically but capture diagonally
        // To check whether it is capture --> See if the terminal square is occupied
        // If White then Add, if Black then Subtract
        if (piece.toUpperCase().equals("P")) {
            if (!board[rowTer][colTer].equals("0")){
                // It's a Capture! Look at four different squares
                int[] posRow = {rowTer - 1, rowTer + 1};
                int[] posCol = {colTer - 1, colTer + 1};
                for (int i : posRow) {
                    for (int j : posCol) {
                        if (board[i][j].equals(piece))
                            board[i][j] = "0";
                    }
                }
            } else {
                // If not occupied just move vertically
                FindOriginal(board, piece, rowTer, colTer, 1);

            }
            
        }
        // Rook --> Look for the same row and the same column
        if (piece.toUpperCase().equals("R")) {
            FindOriginal(board, piece, rowTer, colTer, 1);
            FindOriginal(board, piece, rowTer, colTer, 2);
        }

        // Bishop --> Diagonal
        if (piece.toUpperCase().equals("B")) {
            FindOriginal(board, piece, rowTer, colTer, 3);
            FindOriginal(board, piece, rowTer, colTer, 4);
        }

        // Queen --> Horizontal, Vertical, Diagonal
        if (piece.toUpperCase().equals("Q")) {
            FindOriginal(board, piece, rowTer, colTer, 1);
            FindOriginal(board, piece, rowTer, colTer, 2);
            FindOriginal(board, piece, rowTer, colTer, 3);
            FindOriginal(board, piece, rowTer, colTer, 4);
        }

        // King --> Look for the whole board??
        if (piece.toUpperCase().equals("K")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].equals(piece))
                        board[i][j] = "0";
                }
            }
        }

        // Knight --> L shape movements
        boolean found = false;
        int j = -2;
        while ((!found) && (j < 2)) {
            int colAdd = 3 - abs(j);
            if ((inRange(rowTer + j))
                && (inRange(colTer + colAdd))
                && (board[rowTer + j][colTer + colAdd].equals(piece))) {
                board[rowTer + j][colTer + colAdd] = "0";
                found = true;
            }
            if ((inRange(rowTer + j))
                && (inRange(colTer - colAdd))
                && (board[rowTer + j][colTer - colAdd].equals(piece))) {
                board[rowTer + j][colTer - colAdd] = "0";
                found = true;
            }
            j++;
        }
    }

    public static int abs(int n) {
        if (n <= 0) return (0-n);
        return n;
    }

    public static String outputFinal(String[][] board) {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            int num = 0;
            for (int j = 0; j < board[0].length; j++) {
                // Calculate number of free squares
                if (board[i][j].equals("0")) {
                    num += 1;
                } else {
                    if (num > 0) {
                        // Include the number of empty squares
                        result += num;
                        num = 0;
                    }
                    result += board[i][j];
                }
            }
            if (num > 0) {
                result += num;
            }
            // A slash per row
            result += "/";
        }
        return result;
    }

    public static String finalPosition(String game) {
        // Record sheer movements --> Record Capture --> Record castling
        // Contruct the board and give initial positions
        String[][] board = new String[8][8];
        initialize(board);

        // Read the pgn file
        File gameFile = new File(game);
        String movements = "";
        try {
            Scanner scanner = new Scanner(gameFile);
            String statement;
            // The line recording the moves start with a digit
            while (scanner.hasNext()) {
                if (((statement = scanner.nextLine()).length() > 0) && (Character.isDigit(statement.charAt(0)))) {
                    movements += statement;
                }               
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        movements = movements.replaceAll("\n"," ");
        // Split according to spaces
        String[] moves = movements.split(" ");
        int sideCheck = 0;
        for (String move : moves) {
            move = move.substring(move.indexOf(".") + 1);
            // Skip the indexes (tokens)
            if (move.length() > 0) {
                System.out.print(move + " ");
                // See which side is moving
                sideCheck ++;
                // Simple moves & capture
                if (move.contains("x")) {
                    capturePiece(board, move, sideCheck % 2);
                } else {
                    movePiece(board, move, sideCheck % 2);
                }
            }
        }
        return (outputFinal(board));
    }
}