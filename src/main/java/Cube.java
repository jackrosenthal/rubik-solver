/**
 * The data structure to store the cube
 */
import java.util.HashMap;

public class Cube {
    public HashMap<Position, Cubelet> cubes;

    /**
     * We keep a table of positions so we have the same static
     * refercences to a position
     */
    private static Position[][][] posTbl;

    static {
        posTbl = new Position[3][3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    posTbl[i][j][k] = new Position(i - 1, j - 1, k - 1);
    }

    /**
     * Access the static position reference through this method
     */
    public static Position position(int x, int y, int z) {
        if (x < -1 || x > 1 || y < -1 || y > 1 || z < -1 || z > 1)
            throw new RuntimeException("Attempt to access position at " +
                    new Position(x,y,z));
        return posTbl[x + 1][y + 1][z + 1];
    }

    /**
     * The default constructor for a solved cube
     */
    public Cube() {
        this.cubes = new HashMap<Position, Cubelet>(26);

        /* Center pieces */
        cubes.put(position(0, 1, 0), new Cubelet(Color.WHITE, null, null, Color.WHITE, null));
        cubes.put(position(1, 0, 0), new Cubelet(Color.GREEN, null, null, Color.GREEN, null));
        cubes.put(position(0, 0, 1), new Cubelet(Color.RED, null, null, Color.RED, null));
        cubes.put(position(0, -1, 0), new Cubelet(Color.YELLOW, null, null, Color.YELLOW, null));
        cubes.put(position(-1, 0, 0), new Cubelet(Color.BLUE, null, null, Color.BLUE, null));
        cubes.put(position(0, 0, -1), new Cubelet(Color.ORANGE, null, null, Color.ORANGE, null));

        /* Edge pieces */
        cubes.put(position(-1,1,0), new Cubelet(Color.WHITE,Color.BLUE,null,Color.WHITE, null));
        cubes.put(position(0,1,1), new Cubelet(Color.WHITE,Color.RED,null,Color.WHITE, null));
        cubes.put(position(1,1,0), new Cubelet(Color.WHITE,Color.GREEN,null,Color.WHITE, null));
        cubes.put(position(0,1,-1), new Cubelet(Color.WHITE,Color.ORANGE,null,Color.WHITE, null));  
        cubes.put(position(1,0,1), new Cubelet(Color.RED,Color.GREEN,null,Color.RED, null));
        cubes.put(position(1,-1,0), new Cubelet(Color.GREEN,Color.YELLOW,null,Color.GREEN, null));
        cubes.put(position(1,0,-1), new Cubelet(Color.GREEN,Color.ORANGE,null,Color.GREEN, null));
        cubes.put(position(-1,0,1), new Cubelet(Color.BLUE,Color.RED,null,Color.BLUE, null));
        cubes.put(position(-1,-1,0), new Cubelet(Color.BLUE,Color.YELLOW,null,Color.BLUE, null));
        cubes.put(position(-1,0,-1), new Cubelet(Color.BLUE,Color.ORANGE,null,Color.BLUE, null)); 
        cubes.put(position(0,-1,1), new Cubelet(Color.RED,Color.YELLOW,null,Color.RED, null));
        cubes.put(position(0,-1,-1), new Cubelet(Color.ORANGE,Color.YELLOW,null,Color.ORANGE, null));

        /* Corner pieces */
        cubes.put(position(-1,1,1), new Cubelet(Color.WHITE,Color.BLUE, Color.RED,Color.WHITE, Color.BLUE));
        cubes.put(position(1,1,1), new Cubelet(Color.WHITE,Color.GREEN, Color.RED,Color.WHITE, Color.GREEN));
        cubes.put(position(-1,1,-1), new Cubelet(Color.WHITE,Color.BLUE, Color.ORANGE,Color.WHITE, Color.BLUE));
        cubes.put(position(1,1,1), new Cubelet(Color.WHITE,Color.ORANGE, Color.GREEN,Color.WHITE, Color.ORANGE));
        cubes.put(position(-1,-1,1), new Cubelet(Color.YELLOW,Color.BLUE, Color.RED,Color.YELLOW, Color.BLUE));
        cubes.put(position(1,-1,1), new Cubelet(Color.RED,Color.GREEN, Color.YELLOW,Color.RED, Color.GREEN));
        cubes.put(position(-1,-1,-1), new Cubelet(Color.YELLOW,Color.BLUE, Color.ORANGE,Color.YELLOW, Color.BLUE));
        cubes.put(position(1,-1,-1), new Cubelet(Color.YELLOW,Color.GREEN, Color.ORANGE,Color.YELLOW, Color.GREEN));
    }


}
