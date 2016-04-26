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

    public Color orientation;

    public Cubelet(Color c1, Color c2, Color c3, Color orientation) {
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
        this.orientation = orientation;
    }
}
