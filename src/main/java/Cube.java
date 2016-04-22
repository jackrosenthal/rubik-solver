public class Cube {
    public int cube[][];

    public static int[][] solvedCube() {
        int result[][] = new int[6][9];
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 9; j++)
                result[i][j] = i;
        return result;
    }

    public Cube(int cube[][]) {
        this.cube = cube;
    }

    public Cube() {
        this(solvedCube());
    }

    public void rotate(int face, String rotation) {
        return;
    }
}
