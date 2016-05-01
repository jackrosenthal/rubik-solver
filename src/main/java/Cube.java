/**
 * The data structure to store the cube
 */
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL;

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
        cubes.put(position(0, 1, 0), new Cubelet(Color.WHITE, null, null, position(0, 1, 0), null));
        cubes.put(position(1, 0, 0), new Cubelet(Color.GREEN, null, null, position(1, 0, 0), null));
        cubes.put(position(0, 0, 1), new Cubelet(Color.RED, null, null, position(0, 0, 1), null));
        cubes.put(position(0, -1, 0), new Cubelet(Color.YELLOW, null, null, position(0, -1, 0), null));
        cubes.put(position(-1, 0, 0), new Cubelet(Color.BLUE, null, null, position(-1, 0, 0), null));
        cubes.put(position(0, 0, -1), new Cubelet(Color.ORANGE, null, null, position(0, 0, -1), null));

        /* Edge pieces */
        cubes.put(position(-1,1,0), new Cubelet(Color.WHITE,Color.BLUE,null,position(0, 1, 0), null));
        cubes.put(position(0,1,1), new Cubelet(Color.WHITE,Color.RED,null,position(0, 1, 0), null));
        cubes.put(position(1,1,0), new Cubelet(Color.WHITE,Color.GREEN,null,position(0, 1, 0), null));
        cubes.put(position(0,1,-1), new Cubelet(Color.WHITE,Color.ORANGE,null,position(0, 1, 0), null));
        cubes.put(position(1,0,1), new Cubelet(Color.RED,Color.GREEN,null,position(0, 0, 1), null));
        cubes.put(position(1,-1,0), new Cubelet(Color.GREEN,Color.YELLOW,null,position(1, 0, 0), null));
        cubes.put(position(1,0,-1), new Cubelet(Color.GREEN,Color.ORANGE,null,position(1, 0, 0), null));
        cubes.put(position(-1,0,1), new Cubelet(Color.BLUE,Color.RED,null,position(-1, 0, 0), null));
        cubes.put(position(-1,-1,0), new Cubelet(Color.BLUE,Color.YELLOW,null,position(-1, 0, 0), null));
        cubes.put(position(-1,0,-1), new Cubelet(Color.BLUE,Color.ORANGE,null,position(-1, 0, 0), null));
        cubes.put(position(0,-1,1), new Cubelet(Color.RED,Color.YELLOW,null,position(0, 0, 1), null));
        cubes.put(position(0,-1,-1), new Cubelet(Color.ORANGE,Color.YELLOW,null,position(0, 0, -1), null));

        /* Corner pieces */
        cubes.put(position(-1,1,1), new Cubelet(Color.WHITE,Color.BLUE, Color.RED,position(0, 1, 0), position(-1, 0, 0)));
        cubes.put(position(1,1,1), new Cubelet(Color.WHITE,Color.GREEN, Color.RED,position(0, 1, 0), position(1, 0, 0)));
        cubes.put(position(-1,1,-1), new Cubelet(Color.WHITE,Color.BLUE, Color.ORANGE,position(0, 1, 0), position(-1, 0, 0)));
        cubes.put(position(1,1,-1), new Cubelet(Color.WHITE,Color.ORANGE, Color.GREEN,position(0, 1, 0), position(0, 0, -1)));
        cubes.put(position(-1,-1,1), new Cubelet(Color.YELLOW,Color.BLUE, Color.RED,position(0, -1, 0), position(-1, 0, 0)));
        cubes.put(position(1,-1,1), new Cubelet(Color.RED,Color.GREEN, Color.YELLOW,position(0, 0, 1), position(1, 0, 0)));
        cubes.put(position(-1,-1,-1), new Cubelet(Color.YELLOW,Color.BLUE, Color.ORANGE,position(0, -1, 0), position(-1, 0, 0)));
        cubes.put(position(1,-1,-1), new Cubelet(Color.YELLOW,Color.GREEN, Color.ORANGE,position(0, -1, 0), position(1, 0, 0)));
    }

    public void rotate(Color refFace, String rotation) {
        String newRot;
        if (rotation.length()!=1) {
            if (rotation.charAt(1) == '\'') {
                rotation = rotation.charAt(0) + "cc";
            }
        }
        else {
            rotation = rotation.charAt(0) + "c";
        }
        if (refFace ==  Color.RED) {
            newRot = redRefFace(rotation.charAt(0), rotation);
        }
        else if (refFace ==  Color.GREEN) {
            newRot = greenRefFace(rotation.charAt(0), rotation);
        }
        else if (refFace ==  Color.BLUE) {
            newRot = blueRefFace(rotation.charAt(0), rotation);
        }
        else if (refFace ==  Color.YELLOW) {
            newRot = yellowRefFace(rotation.charAt(0), rotation);
        }
        else if (refFace ==  Color.ORANGE) {
            newRot = orangeRefFace(rotation.charAt(0), rotation);
        }
        else {
            newRot = rotation;
        }
        rotate(newRot.charAt(0), newRot.substring(1));
    }

    private String redRefFace(char rotFace, String direct) {
            if (rotFace == 'U') {
                if(direct == "c") { 
                    return'B'+"cc";
                }
                else if(direct == "cc") {
                    return 'B'+"c";
                }
                else {
                    return 'B'+"2";
                }
            }
            else if (rotFace == 'D') {
                if(direct == "c") { 
                    return'F'+"cc";
                }
                else if(direct == "cc") {
                    return 'F'+"c";
                }
                else {
                    return 'F'+"2";
                }
            }
            else if (rotFace == 'F') {
               return 'U'+ direct;
            }
            else if (rotFace == 'B') {
                return 'D'+ direct;
             }
            else {
               return rotFace + direct;
            }
        }
    
    private String greenRefFace(char rotFace, String direct) {
        if (rotFace == 'F') {
            if(direct == "c") { 
                return'R'+"cc";
            }
            else if(direct == "cc") {
                return 'R'+"c";
            }
            else {
                return 'R'+"2";
            }
        }
        else if (rotFace == 'B') {
            if(direct == "c") { 
                return'L'+"cc";
            }
            else if(direct == "cc") {
                return 'L'+"c";
            }
            else {
                return 'L'+"2";
            }
        }
        else if (rotFace == 'L') {
           return 'F'+ direct;
        }
        else if (rotFace == 'R') {
            return 'B'+ direct;
         }
        else {
           return rotFace + direct;
        }
    }
    
    private String blueRefFace(char rotFace, String direct) {
        if (rotFace == 'L') {
            if(direct == "c") { 
                return'B'+"cc";
            }
            else if(direct == "cc") {
                return 'B'+"c";
            }
            else {
                return 'B'+"2";
            }
        }
        else if (rotFace == 'R') {
            if(direct == "c") { 
                return'F'+"cc";
            }
            else if(direct == "cc") {
                return 'F'+"c";
            }
            else {
                return 'F'+"2";
            }
        }
        else if (rotFace == 'F') {
           return 'L'+ direct;
        }
        else if (rotFace == 'B') {
            return 'R'+ direct;
         }
        else {
           return rotFace + direct;
        }
    }
    
    private String yellowRefFace(char rotFace, String direct) {
        if (rotFace == 'F') {
            if(direct == "c") { 
                return'B'+"cc";
            }
            else if(direct == "cc") {
                return 'B'+"c";
            }
            else {
                return 'B'+"2";
            }
        }
        else if (rotFace == 'B') {
            if(direct == "c") { 
                return'F'+"cc";
            }
            else if(direct == "cc") {
                return 'F'+"c";
            }
            else {
                return 'F'+"2";
            }
        }
        else if (rotFace == 'L') {
            if(direct == "c") { 
                return'R'+"cc";
            }
            else if(direct == "cc") {
                return 'R'+"c";
            }
            else {
                return 'R'+"2";
            }
        }
        else if (rotFace == 'R') {
            if(direct == "c") { 
                return'L'+"cc";
            }
            else if(direct == "cc") {
                return 'L'+"c";
            }
            else {
                return 'L'+"2";
            }
        }
        else {
           return rotFace + direct;
        }
    }
 
    private String orangeRefFace(char rotFace, String direct) {
        if (rotFace == 'F') {
            if(direct == "c") { 
                return'D'+"cc";
            }
            else if(direct == "cc") {
                return 'D'+"c";
            }
            else {
                return 'D'+"2";
            }
        }
        else if (rotFace == 'B') {
            if(direct == "c") { 
                return'U'+"cc";
            }
            else if(direct == "cc") {
                return 'U'+"c";
            }
            else {
                return 'U'+"2";
            }
        }
        else if (rotFace == 'U') {
           return 'F'+ direct;
        }
        else if (rotFace == 'D') {
            return 'B'+ direct;
         }
        else {
           return rotFace + direct;
        }
    }
    
    public void rotate(char planeRot, String dir) {
        if (planeRot == 'U') {
            rotate(position(0,0,1), dir);
        }
        else if (planeRot == 'D'){
            rotate(position(0,0,-1), dir);
        }
        else if (planeRot == 'L'){
            rotate(position(-1,0,0), dir);
        }
        else if (planeRot == 'R'){
            rotate(position(1,0,0), dir);
        }
        else if (planeRot == 'F'){
            rotate(position(0,1,0), dir);
        }
        else if (planeRot == 'B'){
            rotate(position(0,-1,0), dir);
        }
    }

    private void rotate(Position planeRot, String dir) {
        if (dir == "cc") {
            rotate(planeRot);
        }
        else if (dir == "c") {
            rotate(planeRot);
            rotate(planeRot);
            rotate(planeRot);
        }
        else {
            rotate(planeRot);
            rotate(planeRot);
        }
    }

    private void rotate(Position planeRot) {
        HashMap<Position, Cubelet> newCubes = new HashMap<Position, Cubelet>(cubes);
        if (planeRot.z != 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Position newPosition = matxMultZ(position(i,j,planeRot.z));
                    Cubelet mCubelet = cubes.get(position(i,j,planeRot.z));
                    mCubelet.orientation1 = matxMultZ(mCubelet.orientation1);
                    if (mCubelet.orientation2 != null) {
                        mCubelet.orientation2 = matxMultZ(mCubelet.orientation2);
                    }
                    newCubes.put(newPosition, mCubelet);
                }
            }
        }
        if (planeRot.y != 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Position newPosition = matxMultY(position(i,planeRot.y,j));
                    Cubelet mCubelet = cubes.get(position(i,planeRot.y,j));
                    mCubelet.orientation1 = matxMultY(mCubelet.orientation1);
                    if (mCubelet.orientation2 != null) {
                        mCubelet.orientation2 = matxMultY(mCubelet.orientation2);
                    }
                    newCubes.put(newPosition, mCubelet);
                }
            }
        }
        if (planeRot.x != 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Position newPosition = matxMultX(position(planeRot.x,i,j));
                    Cubelet mCubelet = cubes.get(position(planeRot.x,i,j));
                    mCubelet.orientation1 = matxMultX(mCubelet.orientation1);
                    if (mCubelet.orientation2 != null) {
                        mCubelet.orientation2 = matxMultX(mCubelet.orientation2);
                    }
                    newCubes.put(newPosition, mCubelet);
                }
            }
        }
        cubes = newCubes;
    }

    private static Position matxMultX(Position oldPosition) {
        int [][] rotMtx  = {{1,0,0},{0,0,-1},{0,1,0}};
        int xPos = oldPosition.x*rotMtx[0][0] + oldPosition.y*rotMtx[0][1] + oldPosition.z*rotMtx[0][2];
        int yPos = oldPosition.x*rotMtx[1][0] + oldPosition.y*rotMtx[1][1] + oldPosition.z*rotMtx[1][2];
        int zPos = oldPosition.x*rotMtx[2][0] + oldPosition.y*rotMtx[2][1] + oldPosition.z*rotMtx[2][2];
        return position(xPos, yPos, zPos);
    }

    private static Position matxMultY(Position oldPosition) {
        int [][] rotMtx  = {{0,0,-1},{0,1,0},{1,0,0}};
        int xPos = oldPosition.x*rotMtx[0][0] + oldPosition.y*rotMtx[0][1] + oldPosition.z*rotMtx[0][2];
        int yPos = oldPosition.x*rotMtx[1][0] + oldPosition.y*rotMtx[1][1] + oldPosition.z*rotMtx[1][2];
        int zPos = oldPosition.x*rotMtx[2][0] + oldPosition.y*rotMtx[2][1] + oldPosition.z*rotMtx[2][2];
        return position(xPos, yPos, zPos);
    }

    private static Position matxMultZ(Position oldPosition) {
        int [][] rotMtx  = {{0,1,0},{-1,0,0},{0,0,1}};
        int xPos = oldPosition.x*rotMtx[0][0] + oldPosition.y*rotMtx[0][1] + oldPosition.z*rotMtx[0][2];
        int yPos = oldPosition.x*rotMtx[1][0] + oldPosition.y*rotMtx[1][1] + oldPosition.z*rotMtx[1][2];
        int zPos = oldPosition.x*rotMtx[2][0] + oldPosition.y*rotMtx[2][1] + oldPosition.z*rotMtx[2][2];
        return position(xPos, yPos, zPos);
    }

    public Color getPos(Position pos, Position face) {
        Cubelet c = cubes.get(pos);
        if (c.orientation1.equals(face)) return c.color1;
        if (c.orientation2 == null) return c.color2;
        if (c.orientation2.equals(face)) return c.color2;
        return c.color3;
    }

    /**
     * Draw the cube to screen
     *
     * GLFW must be iniitialized first! See main/java/Model.java
     */
    public void drawCube() {
        for (Map.Entry<Position, Cubelet> entry : cubes.entrySet()) {
            entry.getValue().drawCubelet(entry.getKey());
        }
    }

    /**
     * Perform a copy of the cube
     */
    public Cube copy() {
        Cube cpy = new Cube();
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                for (int k = -1; k < 2; k++)
                    cpy.cubes.put(position(i,j,k), cubes.get(position(i,j,k)).copy());
        return cpy;
    }

}
