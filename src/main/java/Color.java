/**
 * A simple color enum, with floating point colors defined for OpenGL
 */
public enum Color {
    WHITE ("White" , 1.0f, 1.0f, 1.0f),
    BLUE  ("Blue"  , 0.0f, 0.0f, 1.0f),
    RED   ("Red"   , 1.0f, 0.0f, 0.0f),
    GREEN ("Green" , 0.0f, 1.0f, 0.0f),
    ORANGE("Orange", 1.0f, 0.6f, 0.0f),
    YELLOW("Yellow", 1.0f, 1.0f, 0.0f);

    private String name;
    public float r;
    public float g;
    public float b;

    private Color(String name, float r, float g, float b) {
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String toString() {
        return this.name;
    }
}