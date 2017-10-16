/**
 * A concrete class representing squares on
 * a chess board
 * @author Sylvia Li
 *
 */
public class Square {
    /**
     * An immutable char representing the file
     * ('a' to 'h')
     */
    private final char file;
    /**
     * An immutable char representing the rank
     * ('1' to '8')
     */
    private final char rank;

    /**
     * Creates a Square with file
     * and rank as given if the file
     * and the rank are valid
     * @param file
     * @param rank
     */
    public Square(char file, char rank) {
        if (fileInRange(file)
             && rankInRange(rank)) {
            this.file = file;
            this.rank = rank;
        } else {
            this.file = 'i';
            this.rank = '0';
        }
    }

    /**
     * Creates a Square from a String
     * representing rank and file
     * @param notation
     */
    public Square(String notation) {
        if (fileInRange(notation.charAt(0))
             && rankInRange(notation.charAt(1))) {
            this.file = notation.charAt(0);
            this.rank = notation.charAt(1);
        } else {
            this.file = 'i';
            this.rank = '0';
        }
    }

    /**
     * @return the file of the Square
     */
    public char getFile() {
        return this.file;
    }
    /**
     * @return the rank of the Square
     */
    public char getRank() {
        return this.rank;
    }

    /**
     * @return the String notation of the Square
     */
    public String toString() {
        return (Character.toString(this.file)
                + Character.toString(this.rank));
    }

    /**
     * @return if the parameter is not a Square,
     * return false; if the parameter is a Square,
     * return if they have the same rank and file.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Square) {
            return ((((Square) other).rank == this.rank)
                    && (((Square) other).file == this.file));
        }
        return false;
    }

    /**
     * @param char f
     * @return if the file is valid
     */
    public boolean fileInRange(char f) {
        return ((f >= 'a') && (f <= 'h'));
    }

    /**
     * @param rank
     * @return if the rank is valid
     */
    public boolean rankInRange(char r) {
        return ((r >= '1') && (r <= '8'));
    }

}