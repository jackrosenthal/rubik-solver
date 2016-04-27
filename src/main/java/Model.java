

public class Model {
    /**
     * Class that models the cube using lwjgl.
     * 
     * Displays this model to the user, so it is easy to see what the
     * program is doing to solve the cube. This class will only begin it's
     * work once the cube is solved.
     */

    public List<Cube> steps;

    public long window; // handler for window
    public Intbuffer width, height; // width / height of window

    // The following are callbacks for GLFW functions. These are required
    // for informative errors and gather user keyboard input
    private static GLFWErrorCallback errorCallback
        = GLFWErrorCallback.createPrint(System.err);

    private static GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action,
                int mods) {
            if (key == GLFW_KEY_Q && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, GLFW_TRUE);
            }
        }
    }

    public Model(List<Cube> steps) {
        this.steps = steps;
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

        GLFWVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
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
        while (glfwWindowShouldClose(window) != GLFW_TRUE) {
            // These buffers are Java's solution to pass by reference for
            // primitve types. The C version of GLFW takes pointers for this
            // function, Java obviously does not support these, so we use
            // IntBuffers instead.
            glfwGetFramebufferSize(window, width, height);

            width.rewind();
            height.rewind();

            glViewPort(0, 0, width.get(). height.get());
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // The following places the camera in the environment
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glFrustum(-.02f, .02f, -.02f, .02f, .1f, 1000f);
            glTranslatef(0f, 0f, -5f);

            float time = (float) glfwGetTime();
            glRotatef(time * 50f, 0f, 1f, 0f);
            glRotatef(45f, 1f, 0f, 0f);

            for (int i = -1; i < 
        }
    }

}