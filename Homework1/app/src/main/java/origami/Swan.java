package origami;

// public - everything
// protected default + anything that extends Swan.
// default (no word) visible to everything in the same package
// private (self only)

public class Swan {
    // static properties

    public static final String DEFAULT_MATERIAL = "paper";
    public static final double DEFAULT_WIDTH = 3.0;
    public static final double DEFAULT_HEIGHT = 5.0;
    public static final int STEPS = 7; // one int called STEPS ever.

    public static double areaOfRectangle(double height, double width) {
        return width * height;
    }

    public static final double DEFAULT_AREA = areaOfRectangle(DEFAULT_HEIGHT, DEFAULT_WIDTH);

    public String getMaterial() {
        return material;
    }

    public int getStep() {
        return step;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private String material;
    private int step; // one int called "step" for every new Swan(...)
    private double width;
    private double height;


    public Swan() {
        this(DEFAULT_MATERIAL, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Swan(String material, double width, double height) {
        this.material = material;
        this.step = 0; // redundant
        this.width = width;
        this.height = height;
    }

    // mutator
    public void advance() {
        if (step < STEPS) {
            ++step;
        }

    }

    public String directions() {
        switch (step) {
            case 0:
                return "get the paper";
            case 1:
                return "fold it up.";
            case 2:
                return "fold it more.";
            default:
                return "look up on oragami.org for step " + step;
        }
    }

    public boolean finished() {
        return step >= STEPS;
    }
}
