/**
 * A subclass of Piece that represents a Pawn
 * @author Sylvia Li
 */
public class Pawn extends Piece {
    /**
     * Creates a Pawn with the given param color
     * @param color
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * @return the fenName of a Pawn piece.
     * ("p" if black Pawn, "P" if white Pawn)
     */
    @Override
    public String fenName() {
        if (super.getColor() == Color.BLACK) {
            return "p";
        }
        return "P";
    }

    /**
     * @return the name of the Pawn
     */
    @Override
    public String algebraicName() {
        return "P";
    }

    /**
     * @param the square from which the Pawn moves
     * @return an array of Square representing the
     * possible squares the Pawn can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        // One or two moves ahead
        Square[] squares = new Square[2];
        if (getColor() == Color.WHITE) {
            squares[0] = new Square("" + square.getFile()
                    + (char) (square.getRank() + 1));
            squares[1] = new Square("" + square.getFile()
                    + (char) (square.getRank() + 2));
        } else {
            squares[0] = new Square("" + square.getFile()
                    + (char) (square.getRank() - 1));
            squares[1] = new Square("" + square.getFile()
                    + (char) (square.getRank() - 2));
        }
        int sum = 2;
        for (Square sq : squares) {
            if (sq.equals(new Square("i0"))) {
                sq = null;
                sum--;
            }
        }
        // Reassign if there are nulls
        Square[] realSquares = new Square[sum];
        for (int i = 0; i < sum; i++) {
            if (i >= 0) {
                realSquares[i] = squares[i];
            }
        }
        return realSquares;
    }
}