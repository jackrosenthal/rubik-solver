/**
 * A class that scrambles a Rubik's cube and stores the cube states as it
 * works (for testing the display)
 */
import java.util.ArrayList;
import java.util.Random;

public class Scrambler {
    public static final String[] moves = {"F", "F'", "F2",
                                          "B", "B'", "B2",
                                          "R", "R'", "R2",
                                          "L", "L'", "L2",
                                          "U", "U'", "U2",
                                          "D", "D'", "D2"};
    public static Random rng = new Random();

    public static ArrayList<Cube> scramble(Cube c) {
        ArrayList<Cube> l = new ArrayList<Cube>();
        int m = rng.nextInt(13) + 12;
        while (m --> 0) {
            l.add(c.copy());
            c.rotate(Color.WHITE, moves[rng.nextInt(moves.length)]);
        }
        return l;
    }
}
