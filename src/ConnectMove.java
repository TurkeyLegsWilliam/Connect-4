/**
 * Class which acts as a struct that contains information
 * necessary to update GUI
 */
public class ConnectMove {
    private int row;
    private int column;
    private ConnectFourEnum colour;

    public ConnectMove(int row, int column, ConnectFourEnum colour) {
        this.row = row;
        this.column = column;
        this.colour = colour;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ConnectFourEnum getColour() {
        return colour;
    }
}
