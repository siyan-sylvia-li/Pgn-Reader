/**
 * A subclass of Piece that represents a Rook
 * @author Sylvia Li
 */
public class Rook extends Piece {
    /**
     * Creates a Rook with the given param color
     * @param color
     */
    public Rook(Color color) {
        super(color);
    }

    /**
     * @return the fenName of a Rook piece.
     * ("r" if black Rook, "R" if white Rook)
     */
    @Override
    public String fenName() {
        if (super.getColor() == Color.BLACK) {
            return "r";
        }
        return "R";
    }

    /**
     * @return the name of the Rook
     */
    @Override
    public String algebraicName() {
        return "R";
    }

    /**
     * @param the square from which the Rook moves
     * @return an array of Square representing the
     * possible squares the Rook can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        // Row: 7; Column: 7
        Square[] squares = new Square[14];
        int index = 0;
        for (char f = 'a'; f <= 'h'; f++) {
            for (char r = '1'; r <= '8'; r++) {
                // Same row, same column or on the same diagonal
                if ((f == square.getFile())
                        || (r == square.getRank())) {
                    Square toSquare = new Square("" + f + r);
                    if (!toSquare.equals(square)) {
                        squares[index] = toSquare;
                        index++;
                    }
                }

            }
        }
        return squares;
    }
}