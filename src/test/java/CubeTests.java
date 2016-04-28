import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class CubeTests {
    private static Cube cube;

    @Before
    public void setUp() {
        cube = new Cube();
    }

    @Test
    public void cubeInit() {
        /* The top should still be RED */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,1), cube.position(0,0,1)), Color.RED);

        /* The bottom should still be ORANGE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,-1), cube.position(0,0,-1)), Color.ORANGE);

        /* The front side should still be WHITE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,1,j), cube.position(0,1,0)), Color.WHITE);

        /* The back side should still be YELLOW */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,-1,j), cube.position(0,-1,0)), Color.YELLOW);

        /* The left side should still be BLUE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(-1,i,j), cube.position(-1,0,0)), Color.BLUE);

        /* The right side should still be GREEN */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(1,i,j), cube.position(1,0,0)), Color.GREEN);
    }

    @Test
    public void testGetPos() {
        assertEquals(cube.getPos(cube.position(0,1,0), cube.position(0,1,0)), Color.WHITE);
        assertEquals(cube.getPos(cube.position(-1,-1,1), cube.position(0,0,1)), Color.RED);
        assertEquals(cube.getPos(cube.position(-1,-1,-1), cube.position(-1,0,0)), Color.BLUE);
        assertEquals(cube.getPos(cube.position(1,-1,-1), cube.position(0,-1,0)), Color.YELLOW);
        assertEquals(cube.getPos(cube.position(1,-1,-1), cube.position(0,0,-1)), Color.ORANGE);
        assertEquals(cube.getPos(cube.position(1,-1,-1), cube.position(1,0,0)), Color.GREEN);
    }

    @Test
    public void testUCCW() {
        cube.rotate('U', "cc");
        /* The top should still be RED */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,1), cube.position(0,0,1)), Color.RED);

        /* The bottom should still be ORANGE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,-1), cube.position(0,0,-1)), Color.ORANGE);

        /* Some random spot checks */
        assertEquals(cube.getPos(cube.position(0,1,1), cube.position(0,1,0)), Color.BLUE);
        assertEquals(cube.getPos(cube.position(-1,-1,1), cube.position(-1,0,0)), Color.YELLOW);
        assertEquals(cube.getPos(cube.position(-1,-1,1), cube.position(0,-1,0)), Color.GREEN);
    }

    @Test
    public void testLCCW() {
        cube.rotate('L', "cc");
        /* The left side should still be BLUE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(-1,i,j), cube.position(-1,0,0)), Color.BLUE);

        /* The right side should still be GREEN */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(1,i,j), cube.position(1,0,0)), Color.GREEN);

        /* The top face should have WHITE in -1, RED otherwise */
        for (int i = 0; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,1), cube.position(0,0,1)), Color.RED);
        for (int i = -1; i < 2; i++)
            assertEquals(cube.getPos(cube.position(-1,i,1), cube.position(0,0,1)), Color.WHITE);
    }

    @Test
    public void testFCCW() {
        cube.rotate('F', "cc");
        /* The front side should still be WHITE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,1,j), cube.position(0,1,0)), Color.WHITE);

        /* The back side should still be YELLOW */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,-1,j), cube.position(0,-1,0)), Color.YELLOW);

        /* The top face should have WHITE in -1, RED otherwise */
        for (int i = -1; i < 1; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(j,i,-1), cube.position(0,0,-1)), Color.ORANGE);
        for (int i = -1; i < 2; i++)
            assertEquals(cube.getPos(cube.position(i,1,-1), cube.position(0,0,-1)), Color.BLUE);
    }


    /**
     * Do a lot of rotations, then rotate back to where we started
     * and make sure the cube is the same
     */
    @Test
    public void testLotsOfRotations() {
        cube.rotate('F', "cc");
        cube.rotate('L', "cc");
        cube.rotate('B', "cc");
        cube.rotate('U', "cc");
        cube.rotate('R', "cc");
        cube.rotate('D', "cc");
        cube.rotate('D', "c");
        cube.rotate('R', "c");
        cube.rotate('U', "c");
        cube.rotate('B', "c");
        cube.rotate('L', "c");
        cube.rotate('F', "c");

        /* The top should still be RED */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,1), cube.position(0,0,1)), Color.RED);

        /* The bottom should still be ORANGE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,j,-1), cube.position(0,0,-1)), Color.ORANGE);

        /* The front side should still be WHITE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,1,j), cube.position(0,1,0)), Color.WHITE);

        /* The back side should still be YELLOW */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(i,-1,j), cube.position(0,-1,0)), Color.YELLOW);

        /* The left side should still be BLUE */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(-1,i,j), cube.position(-1,0,0)), Color.BLUE);

        /* The right side should still be GREEN */
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                assertEquals(cube.getPos(cube.position(1,i,j), cube.position(1,0,0)), Color.GREEN);
    }

}
