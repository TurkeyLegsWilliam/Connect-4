import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Schramm
 */
public class ConnectFourGameTest {

    public ConnectFourGameTest() {
    }

    @Test
    public void test4ArgConstructor() {
        ConnectFourGame instance = new ConnectFourGame(4,4, 3, ConnectFourEnum.BLACK);
        assertEquals(ConnectFourEnum.BLACK, instance.getTurn());
        assertEquals(ConnectFourEnum.IN_PROGRESS, instance.getGameState());
        String s = "EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | \n";
        assertEquals(s, instance.toString() );
    }
    @Test
    public void testDefaultConstructor() {
        ConnectFourGame instance = new ConnectFourGame( ConnectFourEnum.BLACK);
        assertEquals(ConnectFourEnum.IN_PROGRESS, instance.getGameState());
        assertEquals( ConnectFourEnum.BLACK, instance.getTurn());
        String s = "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n";
        assertEquals(s, instance.toString() );
    }

    public void testReset() {
        ConnectFourGame instance = new ConnectFourGame( ConnectFourEnum.BLACK);
        instance.takeTurn(0, 0);
        instance.takeTurn(0, 1);
        instance.reset( ConnectFourEnum.RED);
        assertEquals(ConnectFourEnum.IN_PROGRESS, instance.getGameState());
        assertEquals( ConnectFourEnum.RED, instance.getTurn());
        String s = "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n";
        assertEquals(s, instance.toString() );
    }

    @Test
    public void testTakeTurn() {
        ConnectFourGame instance = new ConnectFourGame(ConnectFourEnum.BLACK);
        instance.takeTurn(0, 0);
        assertEquals(ConnectFourEnum.IN_PROGRESS, instance.getGameState());
        assertEquals( ConnectFourEnum.RED, instance.getTurn());
        String s = "BLACK | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n";
        assertEquals(s, instance.toString() );
    }


    @Test
    public void testTakeTurnNotOnBottom() {
        ConnectFourGame instance = new ConnectFourGame(ConnectFourEnum.BLACK);
        try {
            instance.takeTurn(1, 0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ConnectFourEnum.IN_PROGRESS, instance.getGameState());
            assertEquals( ConnectFourEnum.BLACK, instance.getTurn());
            String s = "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n"+
                    "EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | EMPTY | \n";
            assertEquals(s, instance.toString() );
        }

    }


}