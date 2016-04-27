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
        cubes.put(position(1,1,1), new Cubelet(Color.WHITE,Color.ORANGE, Color.GREEN,position(0, 1, 0), position(0, 0, -1)));
        cubes.put(position(-1,-1,1), new Cubelet(Color.YELLOW,Color.BLUE, Color.RED,position(0, -1, 0), position(-1, 0, 0)));
        cubes.put(position(1,-1,1), new Cubelet(Color.RED,Color.GREEN, Color.YELLOW,position(0, 0, 1), position(1, 0, 0)));
        cubes.put(position(-1,-1,-1), new Cubelet(Color.YELLOW,Color.BLUE, Color.ORANGE,position(0, -1, 0), position(-1, 0, 0)));
        cubes.put(position(1,-1,-1), new Cubelet(Color.YELLOW,Color.GREEN, Color.ORANGE,position(0, -1, 0), position(1, 0, 0)));
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
    
    private void rotate(Position planeRot, String dir)
    {
        if (dir == "cc")
        {
            rotate(position(0,0,1));
        }
        else if (dir == "c")
        {
            rotate(position(0,0,1));
            rotate(position(0,0,1));
            rotate(position(0,0,1));
        }
        else
        {
            rotate(position(0,0,1));
            rotate(position(0,0,1));
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
            cubes = newCubes;
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
    }

    private static Position matxMultX(Position oldPosition) {
        int [][] rotMtx  = {{1,0,0},{0,0,-1},{0,1,0}};
        int xPos = oldPosition.x*rotMtx[0][0] + oldPosition.y*rotMtx[0][1] + oldPosition.z*rotMtx[0][2];
        int yPos = oldPosition.x*rotMtx[1][0] + oldPosition.y*rotMtx[1][1] + oldPosition.z*rotMtx[1][2];
        int zPos = oldPosition.x*rotMtx[2][0] + oldPosition.y*rotMtx[2][1] + oldPosition.z*rotMtx[2][2];
        return position(xPos, yPos, zPos);
    }

    private static Position matxMultY(Position oldPosition) {
        int [][] rotMtx  = {{0,0,1},{0,1,0},{-1,0,0}};
        int xPos = oldPosition.x*rotMtx[0][0] + oldPosition.y*rotMtx[0][1] + oldPosition.z*rotMtx[0][2];
        int yPos = oldPosition.x*rotMtx[1][0] + oldPosition.y*rotMtx[1][1] + oldPosition.z*rotMtx[1][2];
        int zPos = oldPosition.x*rotMtx[2][0] + oldPosition.y*rotMtx[2][1] + oldPosition.z*rotMtx[2][2];
        return position(xPos, yPos, zPos);
    }

    private static Position matxMultZ(Position oldPosition) {
        int [][] rotMtx  = {{0,-1,0},{1,0,0},{0,0,1}};
        int xPos = oldPosition.x*rotMtx[0][0] + oldPosition.y*rotMtx[0][1] + oldPosition.z*rotMtx[0][2];
        int yPos = oldPosition.x*rotMtx[1][0] + oldPosition.y*rotMtx[1][1] + oldPosition.z*rotMtx[1][2];
        int zPos = oldPosition.x*rotMtx[2][0] + oldPosition.y*rotMtx[2][1] + oldPosition.z*rotMtx[2][2];
        return position(xPos, yPos, zPos);
    }
}