/**
 * A class that scrambles a Rubik's cube and stores the cube states as it
 * works (for testing the display)
 */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Scrambler {
    public static final String[] moves = {"F", "F'", "F2",
                                          "B", "B'", "B2",
                                          "R", "R'", "R2",
                                          "L", "L'", "L2",
                                          "U", "U'", "U2",
                                          "D", "D'", "D2"};
    public static Random rng = new Random();

    public static Deque<Cube> scramble(Cube c) {
        Deque<Cube> l = new LinkedList<Cube>();
        int m = rng.nextInt(13) + 12;
        while (m --> 0) {
            l.addFirst(c.copy());
            c.rotate(Color.WHITE, moves[rng.nextInt(moves.length)]);
        }
        return l;
    }
}
