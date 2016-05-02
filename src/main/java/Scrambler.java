/**
 * A class that scrambles a Rubik's cube and stores the cube states as it
 * works (for testing the display)
 */
import java.util.ArrayList;
import java.util.Random;

public class Scrambler {
    public static final char[] moves = {'F', 'B', 'R', 'L', 'U', 'D'};
    public static Random rng = new Random();

    public static ArrayList<Cube> scramble(Cube c) {
        ArrayList<Cube> l = new ArrayList<Cube>();
        int m = rng.nextInt(40) + 40;
        while (m --> 0) {
            l.add(c.copy());
            c.rotate(moves[rng.nextInt(moves.length)], "cc");
        }
        return l;
    }
}
