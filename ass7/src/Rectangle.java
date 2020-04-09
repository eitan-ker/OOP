/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

import java.awt.List;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upLeftP;
    private double wide;
    private double height;
    private Line[] interLine = new Line[4];
    private java.awt.Color color;


    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
//constructor 1
    // Create a new rectangle with location and width/height/color.
    public Rectangle(Point upperLeft, double width, double height,
                     java.awt.Color color) {
        this.upLeftP = upperLeft;
        this.wide = width;
        this.height = height;
        this.color = color;

        // upper line - left to right
        interLine[0] = new Line(this.upLeftP.getX(), this.upLeftP.getY(),
                this.upLeftP.getX() + this.wide, this.upLeftP.getY());
        // right line - up to down
        interLine[1] = new Line(this.upLeftP.getX() + this.wide,
                this.upLeftP.getY(), this.upLeftP.getX() + this.wide,
                this.upLeftP.getY() + this.height);
        // left line - up to down
        interLine[2] = new Line(this.upLeftP.getX(), this.upLeftP.getY(),
                this.upLeftP.getX(), this.upLeftP.getY() + this.height);
        //down line - left to right
        interLine[3] = new Line(this.upLeftP.getX(), this.upLeftP.getY()
                + this.height, this.upLeftP.getX() + this.wide,
                this.upLeftP.getY() + this.height);
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// constructor 2
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeftP = upperLeft;
        this.wide = width;
        this.height = height;

        // upper line - left to right
        interLine[0] = new Line(this.upLeftP.getX(), this.upLeftP.getY(),
                this.upLeftP.getX() + this.wide, this.upLeftP.getY());
        // right line - up to down
        interLine[1] = new Line(this.upLeftP.getX() + this.wide,
                this.upLeftP.getY(), this.upLeftP.getX() + this.wide,
                this.upLeftP.getY() + this.height);
        // left line - up to down
        interLine[2] = new Line(this.upLeftP.getX(), this.upLeftP.getY(),
                this.upLeftP.getX(), this.upLeftP.getY() + this.height);
        //down line - left to right
        interLine[3] = new Line(this.upLeftP.getX(), this.upLeftP.getY()
                + this.height, this.upLeftP.getX() + this.wide,
                this.upLeftP.getY() + this.height);
    }


    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets upper line.
     *
     * @return the upper line
     */
// returns upper line
    public Line getUpperLine() {
        return interLine[0];
    }

    /**
     * Gets lower line.
     *
     * @return the lower line
     */
// return lower line
    public Line getLowerLine() {
        return interLine[3];
    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
// return right line
    public Line getRightLine() {
        return interLine[1];
    }

    /**
     * Gets left line.
     *
     * @return the left line
     */
// return left line
    public Line getLeftLine() {
        return interLine[2];
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        int counter = 0;
        ArrayList<Point> list = new ArrayList<Point>();

        if (line.isIntersecting(this.interLine[0])) {
            list.add(counter, line.intersectionWith(this.interLine[0]));
            counter++;
        }
        if (line.isIntersecting(this.interLine[1])) {
            list.add(counter, line.intersectionWith(this.interLine[1]));
            counter++;
        }
        if (line.isIntersecting(this.interLine[2])) {
            list.add(counter, line.intersectionWith(this.interLine[2]));
            counter++;
        }
        if (line.isIntersecting(this.interLine[3])) {
            list.add(counter, line.intersectionWith(this.interLine[3]));
            counter++;
        }
        return list;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.wide;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upLeftP;
    }
}
