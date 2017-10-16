/**
 * An abstract class representing a chess
 * piece (King, Queen, Knight, Bishop,
 * Rook and Pawn)
 * @author Sylvia Li
 *
 */
public abstract class Piece {
    private Color color;

    /**
     * Creates a Piece with the given param color
     * @param color
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * @return the color of the piece
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     * @return the algebraicName of the Piece
     */
    public abstract String algebraicName();

    /**
     * {@inheritDoc}
     * @return the fenName of the Piece
     */
    public abstract String fenName();

    /**
     * @param the square from which the Piece
     * moves from
     * @return an array of Square representing
     * the possible squares to move to
     */
    public abstract Square[] movesFrom(Square square);

    /**
     * @param a number
     * @return the absolute value of the number
     */
    public int abs(int n) {
        if (n >= 0) {
            return n;
        }
        return (-n);
    }


}