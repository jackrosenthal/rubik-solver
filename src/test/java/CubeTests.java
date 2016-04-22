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
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 9; j++)
                assertEquals(cube.cube[i][j], i);
    }

    @Test
    public void whiteF() {
        cube.rotate(0, "F");

        /* The orange row now on blue */
        assertEquals(cube.cube[1][6], 3);
        assertEquals(cube.cube[1][7], 3);
        assertEquals(cube.cube[1][8], 3);
        assertEquals(cube.cube[1][5], 1);

        /* None of the front face should have changed */
        for (int i = 0; i < 9; i++)
            assertEquals(cube.cube[0][i], 0);

        /* None of the back face should have changed */
        for (int i = 0; i < 9; i++)
            assertEquals(cube.cube[5][i], 5);

        /* Some scattered sanity checks */
        assertEquals(cube.cube[2][7], 1);
        assertEquals(cube.cube[2][3], 2);
        assertEquals(cube.cube[2][8], 1);
        assertEquals(cube.cube[4][0], 2);
        assertEquals(cube.cube[4][1], 2);
        assertEquals(cube.cube[3][0], 3);
        assertEquals(cube.cube[3][2], 4);
        assertEquals(cube.cube[3][7], 3);
        assertEquals(cube.cube[3][8], 4);
    }

    @Test
    public void whiteU() {
        cube.rotate(0, "U");

        /* The entire top layer (adj. blue face) should have changed */
        for (int i: new int[]{0, 2, 3, 5})
            for (int j = 0; j < 3; j++)
                assertThat(cube.cube[i][j], not(equalTo(i)));

        /* The blue and green faces should be the same */
        for (int i: new int[]{1, 4})
            for (int j = 0; j < 9; j++)
                assertEquals(cube.cube[i][j], i);

        /* The middle layer should be unchanged */
        for (int i: new int[]{0, 2, 3, 5})
            for (int j = 3; j < 6; j++)
                assertEquals(cube.cube[i][j], i);

        /* Some sanity checks */
        assertEquals(cube.cube[0][0], 2);
        assertEquals(cube.cube[0][2], 2);
        assertEquals(cube.cube[2][0], 5);
        assertEquals(cube.cube[2][1], 5);
        assertEquals(cube.cube[5][0], 3);
        assertEquals(cube.cube[5][2], 3);
        assertEquals(cube.cube[3][1], 0);
    }

    @Test
    public void white2D() {
        cube.rotate(0, "2D");

        int transform[] = new int[]{5, 1, 3, 2, 4, 0};
        /* All the bottom rows should be transformed to the matrix above */
        for (int i = 0; i < 6; i++)
            for (int j = 6; j < 9; j++)
                assertEquals(cube.cube[i][j], transform[i]);

        /* Everything else should be unchanged */
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                assertEquals(cube.cube[i][j], i);
    }

    @Test
    public void whiteLprimeRprime() {
        cube.rotate(0, "L'");
        cube.rotate(0, "R'");

        /* Sides 2 & 3 unchanged */
        for (int i = 2; i < 4; i++)
            for (int j = 0; j < 9; j++)
                assertEquals(cube.cube[i][j], i);

        int transformL[] = new int[]{4, 0, 2, 3, 5, 1};
        int transformR[] = new int[]{1, 5, 2, 3, 0, 4};
        /* Rows correspond to transformations */
        for (int i = 0; i < 6; i++) {
            for (int j: new int[]{0, 3, 6})
                assertEquals(cube.cube[i][j], transformL[i]);
            for (int j: new int[]{2, 5, 8})
                assertEquals(cube.cube[i][j], transformR[i]);
        }
    }

    @Test
    public void greenR() {
        cube.rotate(4, "R");
        int transform[] = new int[]{4, 0, 2, 3, 5, 1};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }
    }
}
