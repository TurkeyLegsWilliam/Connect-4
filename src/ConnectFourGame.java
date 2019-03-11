import java.util.Observable;

/**
 * Game Engine for Connect Four Game. Usable with both text client and GUI
 *
 * @author William Ma 101004624
 * @version 1.0
 */
public class ConnectFourGame extends Observable {
    private int nColumns;
    private int nRows;
    private int numToWin;
    private ConnectFourEnum[][] grid;
    private ConnectFourEnum turn;
    private ConnectFourEnum gameState;
    private int nMarks;

    /**
     * Default Constructor, creates 8 by 8 game with 4 to win
     *
     * @param initialTurn Whichever Turn occurs first
     */
    public ConnectFourGame(ConnectFourEnum initialTurn) {
        this(8, 8, 4, initialTurn);

    }

    /**
     * Constructor for Connect Four Game with most settings.
     *
     * @param nRows       Number of Rows on board
     * @param nColumns    Number of Columns on board
     * @param numToWin    Number in a row required to win
     * @param initialTurn Whichever turn occurs first.
     * @throws IllegalArgumentException when invalid parameters
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn) {
        if (nRows < numToWin || nColumns < numToWin || numToWin < 2 ||
                (initialTurn != ConnectFourEnum.RED && initialTurn != ConnectFourEnum.BLACK)) {
            throw new IllegalArgumentException("Invalid parameters for board");
        }
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;
        this.grid = new ConnectFourEnum[nRows][nColumns];

        // The following variables are set in reset():
        // this.turn = initialTurn;
        // this.gameState = ConnectFourEnum.IN_PROGRESS;
        // this.nMarks = 0;

        reset(initialTurn);
    }

    /**
     * Resets game; resets instance variables to original values
     *
     * @param initialTurn First turn in reset game
     */
    public void reset(ConnectFourEnum initialTurn) {
        turn = initialTurn;
        gameState = ConnectFourEnum.IN_PROGRESS;
        turn = initialTurn;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                grid[i][j] = ConnectFourEnum.EMPTY;
            }
        }
    }

    /**
     * Updates empty grid location to char.
     * Finds winner if applicable and changes gameState.
     * Flips turn otherwise
     * Notifies observers if board changes
     *
     * @param row    Entry row location
     * @param column Entry coumn location
     * @return gamestate
     * @throws IllegalArgumentException when above empty space
     */
    public ConnectFourEnum takeTurn(int row, int column) throws IllegalArgumentException {

        // check for valid inputs
        // does not flip turn or update nMarks
        // try again
        if (row >= nRows || column >= nColumns || column < 0 || row < 0) {
            System.out.println("Space does not exist, try again using valid input");
            return ConnectFourEnum.IN_PROGRESS;
        }

        // check space is empty
        // does not flip turn or update nMarks
        // try again
        if (grid[row][column] != ConnectFourEnum.EMPTY) {
            System.out.println("Cannot use space already occupied");
            return ConnectFourEnum.IN_PROGRESS;
        }

        //check no empty below
        if (row != 0) {
            if (grid[row - 1][column] == ConnectFourEnum.EMPTY) {
                throw new IllegalArgumentException("Space Below Empty");

            }
        }

        grid[row][column] = turn;
        nMarks++; // increment number of turns
        setChanged(); // if reached this point, something has changed -> notify observer active

        // important to keep turn same while running findWinner
        gameState = findWinner(row, column);

        ConnectFourEnum oldTurn;

        // flip turn
        if (gameState == ConnectFourEnum.IN_PROGRESS) {
            if (turn == ConnectFourEnum.BLACK) {
                oldTurn = ConnectFourEnum.BLACK;
                turn = ConnectFourEnum.RED;
            }
            else {
                oldTurn = ConnectFourEnum.RED;
                turn = ConnectFourEnum.BLACK;
            }
        }
        else {
            oldTurn = turn;
        }


        // notify Application with row, column, state
        notifyObservers(new ConnectMove(row, column, oldTurn));
        return gameState;
    }

    /**
     * Checks for winner or if there is a draw.
     * A winner is when numToWin elements in a row.
     * Instance variable 'turn' must not be modified between selecting a space
     * and flipping 'turn'.
     *
     * @param row    Row of turn
     * @param column Column of turn
     * @return gameState DRAW, RED, BLACK, or IN_PROGRESS
     */
    private ConnectFourEnum findWinner(int row, int column) {
        // check if vertical connect four:
        if (row >= (numToWin - 1)) {
            // check three below
            for (int i = row - (numToWin - 1); i < row; i++) {
                if (grid[i][column] != turn) {
                    break;
                }

                if (i == row - 1) {
                    // vertical has been found
                    return turn;
                }
            }
        }

        // check columns left to right
        int numStraight = 0;
        for (int i = 0; i < nColumns; i++) {
            if (grid[row][i] != turn) {
                numStraight = 0;
                continue;
            }

            numStraight++;

            if (numStraight == numToWin) {
                return turn;
            }
        }

        // check for draw when board is full
        if (nMarks >= nRows * nColumns) {
            return ConnectFourEnum.DRAW;
        }

        // reaches this point if winner not found
        return ConnectFourEnum.IN_PROGRESS;
    }


    /**
     * Getter for gameState
     *
     * @return gameState from ConnectFourEnum
     */
    public ConnectFourEnum getGameState() {
        return gameState;
    }

    /**
     * Getter for turn
     *
     * @return turn from getTurnEnum
     */
    public ConnectFourEnum getTurn() {
        return turn;
    }

    /**
     * Returns string representation of state of board
     *
     * @return Upside down string representation of board, 0,0 in top left corner
     */
    @Override
    public String toString() {
        String board = grid[0][0].toString() + " | ";
        for (int j = 1; j < nColumns; j++) {
            board = board + grid[0][j].toString() + " | ";
        }
        board = board + "\n";

        // rest of rows
        for (int i = 1; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                board = board + grid[i][j].toString() + " | ";
            }
            board = board + "\n";
        }

        return board;
    }
}
