/**
 * A subclass of Piece that represents a King
 * @author Sylvia Li
 *
 */
public class King extends Piece {
    /**
     * Creates a King with the given param color
     * @param color
     */
    public King(Color color) {
        super(color);
    }

    /**
     * @return the fenName of a King piece.
     * ("k" if black King, "K" if white King)
     */
    @Override
    public String fenName() {
        if (super.getColor() == Color.BLACK) {
            return "k";
        }
        return "K";
    }

    /**
     * @return the name of the King piece.
     */
    @Override
    public String algebraicName() {
        return "K";
    }

    /**
     * @param the square from which the King moves
     * @return an array of Square representing the
     * possible squares the King can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        // 3 * 3 square
        Square[] squares = new Square[8];
        int index = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Square toSquare = new Square("" + (char) (square.getFile() + i)
                        + (char) (square.getRank() + j));
                if (!toSquare.equals(square)
                        && (!toSquare.equals(new Square("i0")))) {
                    squares[index] = toSquare;
                    index++;
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