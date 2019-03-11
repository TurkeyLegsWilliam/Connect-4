import javafx.scene.control.Button;

/**
 * A button representing a square on the Connect Four Grid
 *
 * @author William Ma 101004624
 * @version 1.0
 */
public class ConnectButton extends Button {

    private int row;
    private int column;

    /**
     * Constructor for Button
     *
     * @param label  Displayed Label for Button
     * @param row    Row position of button
     * @param column Column Position of Button
     */
    public ConnectButton(String label, int row, int column) {
        super(label);
        this.row = row;
        this.column = column;
    }

    /**
     * Getter for instance variable row
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for instance variable column
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns string showing position of button
     *
     * @return “(<row>,<column>)”
     */
    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }
}
