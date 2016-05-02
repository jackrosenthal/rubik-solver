
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) {
        Cube cube = new Cube();
        ArrayList<Cube> d = Scrambler.scramble(cube);
        Model myModel = new Model(d);
        myModel.glfwStart();
        myModel.mainLoop();
    }
}
