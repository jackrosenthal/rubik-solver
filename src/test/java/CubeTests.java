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
    public void testGetPos() {
        assertEquals(cube.getPos(cube.position(0,1,0), cube.position(0,1,0)), Color.WHITE);
    }
}
