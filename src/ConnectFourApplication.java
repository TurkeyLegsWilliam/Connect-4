import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * GUI Connect Four game
 *
 * @author William Ma 101004624
 * @version 1.0
 */
public class ConnectFourApplication extends Application implements Observer {
    public static final int NUM_COLUMNS = 8;
    public static final int NUM_ROWS = 8;
    public static final int NUM_TO_WIN = 4;
    public static final int BUTTON_SIZE = 20;

    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;

    private TextField tf;
    private Point location;

    /**
     * Constructor for the application. Randomizes first player.
     */
    public ConnectFourApplication() {
        // randomize first player
        Random random = new Random();
        boolean redFirst = random.nextBoolean();
        ConnectFourEnum first; // first player

        if (redFirst == true) {
            first = ConnectFourEnum.RED;
        }
        else {
            first = ConnectFourEnum.BLACK;
        }

        gameEngine = new ConnectFourGame(NUM_ROWS, NUM_COLUMNS, NUM_TO_WIN, first);
        location = new Point();
        // link observer / observable
        gameEngine.addObserver(this);
        buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];

        // Initialize text field
        tf = new TextField();
        tf.setEditable(false);
        tf.setText(first + " begins.");
    }

    /**
     * Method for running the GUI
     *
     * @param primaryStage Primary Stage of the GUI
     */
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 510, 380);


        EventHandler<ActionEvent> sharedHandler = new ButtonHandler();

        GridPane grid = new GridPane();

        // initialize buttons
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
                buttons[i][j].setMinHeight(BUTTON_SIZE);
                buttons[i][j].setMaxWidth(Double.MAX_VALUE);
                buttons[i][j].setOnAction(sharedHandler);

                // fill in from bottom up
                // column, row
                grid.add(buttons[i][j], j, NUM_ROWS - 1 - i);
            }
        }


        //Initialize take turn button
        Button takeTurnButton = new Button("Take My Turn");
        takeTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Runs when takeTurnButton is clicked
             * @param event Button when takeTurnButton is clicked
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Drop the Checker");

                // if change happens, notify observers occurs and update() runs
                // catch trying to place above an empty space
                try {
                    gameEngine.takeTurn((int) location.getY(), (int) location.getX());
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();
                }


            }
        });

        root.setTop(tf);
        root.setCenter(grid);
        root.setBottom(takeTurnButton);

        primaryStage.setTitle("ConnectFour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Launches application
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Resets buttons
     */
    private void reset() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                buttons[i][j].setText(ConnectFourEnum.EMPTY.toString());
                buttons[i][j].setDisable(false);
            }
        }
    }

    /**
     * Updates GUI based on notification from Observable, when notifyObservers() is called
     * Sets button to checker value and disables button
     * If won or draw, resets board
     *
     * @param o   this class
     * @param arg ConnectMove containing information on what must be updated
     */
    @Override
    public void update(Observable o, Object arg) {
        ConnectMove connectMove = (ConnectMove) arg;
        buttons[connectMove.getRow()][connectMove.getColumn()].setText(connectMove.getColour().toString());
        buttons[connectMove.getRow()][connectMove.getColumn()].setDisable(true);

        // if game over
        if (gameEngine.getGameState() != ConnectFourEnum.IN_PROGRESS) {
            Alert alert;
            // check if draw or won
            if (gameEngine.getGameState() == ConnectFourEnum.DRAW) {
                alert = new Alert(Alert.AlertType.INFORMATION, gameEngine.getGameState().toString());
            }
            else {
                alert = new Alert(Alert.AlertType.INFORMATION, gameEngine.getGameState() + " WON");
            }

            alert.setHeaderText("Game Over");
            alert.showAndWait();

            // reset game
            // pick random to go first
            Random random = new Random();
            boolean redFirst = random.nextBoolean();
            ConnectFourEnum first;

            if (redFirst == true) {
                first = ConnectFourEnum.RED;
            }
            else {
                first = ConnectFourEnum.BLACK;
            }

            // reset game
            gameEngine.reset(first);
            // reset buttons
            reset();

            tf.setText(first + " begins.");
        }
        else {
            // reset text
            tf.setText("It's " + gameEngine.getTurn().toString() + "'s turn");
        }
    }

    /**
     * Sets location when button on grid is pressed
     */
    class ButtonHandler implements EventHandler<ActionEvent> {

        /**
         * Runs when button is clicked
         *
         * @param event Button event when it is clicked
         */
        @Override
        public void handle(ActionEvent event) {

            ConnectButton button = (ConnectButton) event.getSource();
            System.out.println(event.getSource());
            // set location to be used by takeTurnButton
            location.setLocation(button.getColumn(), button.getRow());
        }
    }
}
