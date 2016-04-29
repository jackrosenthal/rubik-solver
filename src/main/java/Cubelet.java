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
public class Cubelet {
    public Color color1;
    public Color color2;
    public Color color3;

    public Position orientation1;
    public Position orientation2;
    private Cube cube;

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

    public Position orientation(int i, Position pos) {
        if (i < 1 || i > 3)
            throw new RuntimeException("Invalid orinetation: " + i);
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
