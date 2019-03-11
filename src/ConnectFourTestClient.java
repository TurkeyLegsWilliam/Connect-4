import java.util.Scanner;

/**
 * Runs text version of game
 *
 * @author William Ma 101004624
 * @version 1.0
 */
public class ConnectFourTestClient {

    /**
     * Runs game
     *
     * @param args
     */
    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.RED);
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(game.toString());
            System.out.println(game.getTurn() +
                    ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            game.takeTurn(row, column);

        } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
        System.out.println(game.getGameState() + " Won!");
    }
}
