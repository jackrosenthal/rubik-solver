/**
 * Cubelet class: represents a single cube on the Rubik's Cube
 *
 * There are three types of cubelets:
 *      - A center peice: this only has color1 defined, color2 and color3 are
 *                        null
 *      - A edge peice: this only has color1 and color2 defined
 *      - A corner peice: this has all three colors defined
 *
 * The orientation defines which color color1 faces.
 */

import java.util.Map;
import java.util.HashMap;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Cubelet {
    public Color color1;
    public Color color2;
    public Color color3;

    public Position orientation1;
    public Position orientation2;
    private Cube cube;

    public final static float glScale = 0.320f;
    public static Map<Position,float[][]> faceMap;

    static {
        faceMap = new HashMap<Position,float[][]>();
        // white
        faceMap.put(new Position(0,1,0), new float[][]
                {{-0.5f,0.5f,0.5f}, {0.5f,0.5f,0.5f},
                {0.5f,0.5f,-0.5f}, {-0.5f,0.5f,-0.5f}});
        // green
        faceMap.put(new Position(1,0,0), new float[][]
                {{0.5f,0.5f,0.5f}, {0.5f,-0.5f,0.5f},
                {0.5f,-0.5f,-0.5f}, {0.5f,0.5f,-0.5f}});
        // red
        faceMap.put(new Position(0,0,1), new float[][]
                {{-0.5f,-0.5f,0.5f}, {0.5f,-0.5f,0.5f},
                {0.5f,0.5f,0.5f}, {-0.5f,0.5f,0.5f}});
        // yellow
        faceMap.put(new Position(0,-1,0), new float[][]
                {{0.5f,-0.5f,0.5f}, {-0.5f,-0.5f,0.5f},
                {-0.5f,-0.5f,-0.5f}, {0.5f,-0.5f,-0.5f}});
        // blue
        faceMap.put(new Position(-1,0,0), new float[][]
                {{-0.5f,-0.5f,0.5f}, {-0.5f,0.5f,0.5f},
                {-0.5f,0.5f,-0.5f}, {-0.5f,-0.5f,-0.5f}});
        // orange
        faceMap.put(new Position(0,0,-1), new float[][]
                {{0.5f,0.5f,-0.5f}, {-0.5f,0.5f,-0.5f},
                {-0.5f,-0.5f,-0.5f}, {0.5f,-0.5f,-0.5f}});
    }

    public Cubelet(Color c1, Color c2, Color c3, Position orientation1, Position orientation2) {
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
        this.orientation1 = orientation1;
        this.orientation2 = orientation2;
    }

    public String toString() {
        return "Cubelet{"+color1+","+color2+","+color3+","+orientation1+","+orientation2+"}";
    }

    public boolean equals(Cubelet other) {
        return color1 == other.color1 &&
               color2 == other.color2 &&
               color3 == other.color3 &&
               orientation1.equals(other.orientation1) &&
               (orientation2 != null)?orientation2.equals(other.orientation2):true;
    }

    public void drawCubelet(Position coord) {
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glTranslatef(coord.x/3f, coord.y/3f, coord.z/3f);
        glScalef(glScale, glScale, glScale);

        glBegin(GL_QUADS);
        for (Map.Entry<Position, float[][]> entry : faceMap.entrySet()) {
            if (entry.getKey().equals(orientation(1, coord)))
                glColor3f(color1.r, color1.g, color1.b);
            else if (color2 != null && entry.getKey().equals(orientation(2, coord)))
                glColor3f(color2.r, color2.g, color2.b);
            else if (color3 != null && entry.getKey().equals(orientation(3, coord)))
                glColor3f(color3.r, color3.g, color3.b);
            else
                glColor3f(0f, 0f, 0f);

            for(int i = 0; i < 4; i++) {
                glVertex3f(faceMap.get(entry.getKey())[i][0],
                    faceMap.get(entry.getKey())[i][1],
                    faceMap.get(entry.getKey())[i][2]
                );
            }
        }
        glEnd();

        glPopMatrix();
    }
    public Position orientation(int i, Position pos) {
        if (i < 1 || i > 3)
            throw new RuntimeException("Invalid orientation: " + i);
        if (i == 1) return orientation1;
        if (i == 2 && orientation2 != null) return orientation2;
        if (i == 2) return cube.position(pos.x ^ orientation1.x,
                                         pos.y ^ orientation1.y,
                                         pos.z ^ orientation1.z);
        if (i == 3 && orientation2 == null) return null;
        if (i == 3) return cube.position(pos.x ^ orientation1.x ^ orientation2.x,
                                         pos.y ^ orientation1.y ^ orientation2.y,
                                         pos.z ^ orientation1.z ^ orientation2.z);
        return null;
    }
}
