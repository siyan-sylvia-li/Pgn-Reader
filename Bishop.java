/**
 * A subclass of Piece that represents a Bishop
 * @author Sylvia Li
 */
public class Bishop extends Piece {
    /**
     * Creates a Bishop with the given param color
     * @param color
     */
    public Bishop(Color color) {
        super(color);
    }

    /**
     * @return the fenName of a Bishop piece.
     * ("b" if black Bishop, "B" if white Bishop)
     */
    @Override
    public String fenName() {
        if (super.getColor() == Color.BLACK) {
            return "b";
        }
        return "B";
    }

    /**
     * @return the name of the Bishop
     */
    @Override
    public String algebraicName() {
        return "B";
    }

    /**
     * @param the square from which the Bishop moves
     * @return an array of Square representing the
     * possible squares the Bishop can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        // Diagonals: 2 * 7 = 14
        Square[] squares = new Square[14];
        int index = 0;
        for (char f = 'a'; f <= 'h'; f++) {
            for (char r = '1'; r <= '8'; r++) {
                // Same row, same column or on the same diagonal
                if (abs(f - square.getFile()) == abs(r - square.getRank())) {
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