
import java.util.List;
import java.util.ArrayList;

import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Model {
    /**
     * Class that models the cube using lwjgl.
     * 
     * Displays this model to the user, so it is easy to see what the
     * program is doing to solve the cube. This class will only begin it's
     * work once the cube is solved.
     */

    public ArrayList<Cube> steps;
    int cubeIndex;

    public long window; // handler for window
    public IntBuffer width, height; // width / height of window
    public float rotZ, rotHoriz;

    // The following are callbacks for GLFW functions. These are required
    // for informative errors and gather user keyboard input
    private static GLFWErrorCallback errorCallback
        = GLFWErrorCallback.createPrint(System.err);

    private GLFWKeyCallback keyCallback = new myKeyCallback(this);

    public Model() {}

    public Model(ArrayList<Cube> newSteps) {
        this.steps = newSteps;
        cubeIndex = steps.size()-1;
        rotZ = 0f;
        rotHoriz = 0f;
    }

    public void glfwStart() {
        glfwSetErrorCallback(errorCallback);

        if (glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(640, 480, "Rubik's Cube", NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // center the window on the display
        glfwSetWindowPos(window,
                (vidMode.width() - 640) / 2,
                (vidMode.height() - 480) / 2
        );

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwSetKeyCallback(window, keyCallback);

        width = BufferUtils.createIntBuffer(1);
        height = BufferUtils.createIntBuffer(1);

        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
    }

    public void mainLoop() {
        Cube c = steps.get(cubeIndex);
        float time = (float)glfwGetTime(), lasttime;
        while (glfwWindowShouldClose(window) != GLFW_TRUE) {
            // These buffers are Java's solution to pass by reference for
            // primitve types. The C version of GLFW takes pointers for this
            // function, Java obviously does not support these, so we use
            // IntBuffers instead.
            glfwGetFramebufferSize(window, width, height);

            width.rewind();
            height.rewind();

            glViewport(0, 0, width.get(), height.get());
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // The following places the camera in the environment
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glFrustum(-.02f, .02f, -.02f, .02f, .1f, 1000f);
            glTranslatef(0f, 0f, -6f);

            lasttime = time;
            time = (float) glfwGetTime();
            glRotatef(rotZ, 1f, 0f, 0f);
            glRotatef(rotHoriz, 0f, 1f, 0f);

            /*
            if (steps.peekFirst() != null && (int)(time/2) != (int)(lasttime/2))
                c = steps.pollFirst(); */
            c = steps.get(cubeIndex);
            c.drawCube();

            glfwSwapBuffers(window);
            glfwPollEvents();
            width.flip();
            height.flip();

            sync(60);
        }
    }

    private class myKeyCallback extends GLFWKeyCallback {
        public Model model;

        public myKeyCallback(Model newModel) {
            this.model = newModel;
        }

        @Override
        public void invoke(long window, int key, int scancode, int action,
                int mods) {
            if (key == GLFW_KEY_Q && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, GLFW_TRUE);
            else if (key == GLFW_KEY_UP && action == GLFW_RELEASE)
                model.rotZ += 5f;
            else if (key == GLFW_KEY_DOWN && action == GLFW_RELEASE)
                model.rotZ -= 5f;
            else if (key == GLFW_KEY_RIGHT && action == GLFW_RELEASE)
                model.rotHoriz -= 5f;
            else if (key == GLFW_KEY_LEFT && action == GLFW_RELEASE)
                model.rotHoriz += 5f;
            else if (key == GLFW_KEY_A && action == GLFW_RELEASE) {
                if (model.cubeIndex < model.steps.size()-1) model.cubeIndex++;
            }
            else if (key == GLFW_KEY_D && action == GLFW_RELEASE) {
                if (model.cubeIndex > 0) model.cubeIndex--;
            }
        }
    }

    /* Source: http://forum.lwjgl.org/index.php?topic=5653.0
     * Causes thread to sleep so fps is capped at a certain value
     */
    private long variableYieldTime, lastTime;
    private void sync(int fps) {
        if (fps <= 0) return;

        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000*1000));
        long overSleep = 0; // time the sync goes over by

        try {
            while (true) {
                long t = System.nanoTime() - lastTime;

                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                } else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                } else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);

            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200*1000, sleepTime);
            }
            else if (overSleep < variableYieldTime - 200*1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2*1000, 0);
            }
        }
    }
}
