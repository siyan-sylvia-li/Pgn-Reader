/**
 * A subclass of Piece that represents a Knight
 * @author Sylvia Li
 *
 */
public class Knight extends Piece {
    /**
     * Creates a Knight with the given param color
     * @param color
     */
    public Knight(Color color) {
        super(color);
    }

    /**
     * @return the fenName of a Knight piece.
     * ("n" if black Knight, "N" if white Knight)
     */
    @Override
    public String fenName() {
        if (super.getColor() == Color.BLACK) {
            return "n";
        }
        return "N";
    }

    /**
     * @return the name of the Knight piece.
     */
    @Override
    public String algebraicName() {
        return "N";
    }

    /**
     * @param the square from which the Knight moves
     * @return an array of Square representing the
     * possible squares the Knight can move to
     */
    @Override
    public Square[] movesFrom(Square square) {
        Square[] squares = new Square[8];
        int index = 0;
        int[] pos = new int[]{-2, -1, 1, 2};
        for (int i : pos) {
            Square sq = new Square("" + (char) (square.getFile() + i)
                    + (char) (square.getRank() + (3 - abs(i))));
            if (!sq.equals(new Square("i0"))) {
                squares[index] = sq;
                index++;
            }
            sq = new Square("" + (char) (square.getFile() + i)
                    + (char) (square.getRank() - (3 - abs(i))));
            if (!sq.equals(new Square("i0"))) {
                squares[index] = sq;
                index++;
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