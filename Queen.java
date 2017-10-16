/**
 * A subclass of Piece that represents a Queen
 * @author Sylvia Li
 */
public class Queen extends Piece {
    /**
     * Creates a Queen with the given param color
     * @param color
     */
    public Queen(Color color) {
        super(color);
    }

    /**
     * @return the fenName of a Queen piece.
     * ("q" if black Queen, "Q" if white Queen)
     */
    @Override
    public String fenName() {
        if (super.getColor() == Color.BLACK) {
            return "q";
        }
        return "Q";
    }

    /**
     * @return the name of the Queen
     */
    @Override
    public String algebraicName() {
        return "Q";
    }

    /**
     * @param the square from which the Queen moves
     * @return an array of Square representing the
     * possible squares the Queen can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        // Row: 7; Column: 7; Diagonal: 7 * 2 = 14 --> 28
        Square[] squares = new Square[28];
        int index = 0;
        for (char f = 'a'; f <= 'h'; f++) {
            for (char r = '1'; r <= '8'; r++) {
                // Same row, same column or on the same diagonal
                if ((f == square.getFile())
                        || (r == square.getRank())
                        || (abs(f - square.getFile())
                             == abs(r - square.getRank()))) {
                    Square toSquare = new Square("" + f + r);
                    if (!toSquare.equals(square)) {
                        squares[index] = toSquare;
                        index++;
                    }
                }

            }
        }
        // Reassign if there are nulls
        Square[] realSquares = new Square[index];
        for (int i = 0; i < index; i++) {
            realSquares[i] = squares[i];
        }
        return realSquares;
    }
}