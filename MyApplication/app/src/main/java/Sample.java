public class Sample
{
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    private double height;
    private double length;
    private double width;

    Sample(double height, double length, double width) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    double getVolume() {
        return this.height*this.width*this.length;
    }
}
