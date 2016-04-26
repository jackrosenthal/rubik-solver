/**
 * A position structure to relate a cubelet's position to itself
 *
 * The coordinate system is defined as follows:
 *   z
 *   |
 *   .--x
 *  /
 * y
 *
 * With the center of the cube being addressed as (0, 0, 0)
 *
 * Each edge peice can be up to +-1 from the center on each axis.
 */
public class Position {
    public int x;
    public int y;
    public int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public boolean equals(Position other) {
        return this.x == other.x &&
               this.y == other.y &&
               this.z == other.z;
    }
}
