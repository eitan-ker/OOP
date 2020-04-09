/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */

import java.util.List;
import java.util.ArrayList;

/**
 * The type Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        double dx = this.end.getX() - this.start.getX();
        double dy = this.end.getY() - this.start.getY();
        double sum = 0;
        sum = (dx * dx) + (dy * dy);
        sum = Math.sqrt(sum);
        return sum;
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double x = ((this.end.getX() - this.start.getX()) / 2) + this.start.getX();
        double y = ((this.end.getY() - this.start.getY()) / 2) + this.start.getY();
        Point middle = new Point(x, y);
        return middle;
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Line slope double.
     *
     * @param start1 the start
     * @param end1   the end
     * @return the double
     */
// checks the angle of the line
    public double lineSlope(Point start1, Point end1) {
        double dx = 0;
        double dy = 0;
        dx = end1.getX() - start1.getX();
        dy = end1.getY() - start1.getY();
        return (dy / dx);
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        // checking if x is sent by correct order.
        if ((this.start.getX() != this.end.getX())
                && (other.start.getX() != other.end.getX())) {

            double m1 = lineSlope(this.start, this.end);
            double m2 = lineSlope(other.start, other.end);
            double b1 = this.start.getY() - (m1 * this.start.getX());
            double b2 = other.start.getY() - (m2 * other.start.getX());
            if (m1 == m2) {
                // if both lines are parallel to the X axis
                return false;
            } else {
                double x = (b2 - b1) / (m1 - m2);
                if (x < 0) {
                    x = -x;
                }
                // max x point for this.line
                double xMax1 = max(this.start.getX(), this.end.getX());
                // min x point for this.line
                double xMin1 = min(this.start.getX(), this.end.getX());
                // max x point for other.line
                double xMax2 = max(other.start.getX(), other.end.getX());
                // min x point for other.line
                double xMin2 = min(other.start.getX(), other.end.getX());

                if ((x >= max(xMin1, xMin2)) && (x <= min(xMax1, xMax2))) {
                    double y1 = ((m1 * x) + b1);
                    double y2 = ((m2 * x) + b2);
                    if (Math.abs(y1 - y2) <= 0.001) {
                        return true;
                    }
                        return false;
                }
                return false;
            }

        } else if ((Math.abs(this.start.getX() - this.end.getX()) <= 0.001)
                && (Math.abs(other.start.getX() - other.end.getX())
                <= 0.001)) {
            // if both lines parallel to the Y axis
            return false;
        } else if (Math.abs(this.start.getX() - this.end.getX()) <= 0.001) {
            double minX = other.start.getX();
            double maxX = other.end.getX();
            if (other.start.getX() >= other.end.getX()) {
                maxX = other.start.getX();
                minX = other.end.getX();
            }
            // in case "this" line parallel to Y axis
            if ((this.start.getX() >= minX) && (this.start.getX() <= maxX)) {
                double m = lineSlope(other.start, other.end);
                double b = other.start.getY() - (m * other.start.getX());
                double y = (this.start.getX() * m) + b;
                if (((max(this.start.getY(), this.end.getY()) >= y))
                        && ((min(this.start.getY(), this.end.getY()) <= y))) {
                    return true;
                }
                    return false;
            } else {
                return false;
            }
        } else if (Math.abs(other.start.getX() - other.end.getX()) <= 0.001) {
            //in case "other" line parallel to Y axis
            double minX = this.start.getX();
            double maxX = this.end.getX();
            // checking which is bigger
            if (this.start.getX() >= this.end.getX()) {
                maxX = this.start.getX();
                minX = this.end.getX();
            }
            if ((other.start.getX() >= minX) && (other.start.getX() <= maxX)) {
                double m = lineSlope(this.start, this.end);
                double b = this.start.getY() - (m * this.start.getX());
                double y = (other.start.getX() * m) + b;
                if (((max(other.start.getY(), other.end.getY()) >= y))
                        && ((min(other.start.getY(), other.end.getY())
                        <= y))) {
                    return true;
                }
                    return false;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Intersection with lines.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if ((this.start.getX() != this.end.getX())
                && (other.start.getX() != other.end.getX())) {
            double m1 = lineSlope(this.start, this.end);
            double m2 = lineSlope(other.start, other.end);
            double b1 = this.start.getY() - (m1 * this.start.getX());
            double b2 = other.start.getY() - (m2 * other.start.getX());
            double x = (b2 - b1) / (m1 - m2);

            // max x point for this.line
            double xMax1 = max(this.start.getX(), this.end.getX());
            // min x point for this.line
            double xMin1 = min(this.start.getX(), this.end.getX());
            // max x point for other.line
            double xMax2 = max(other.start.getX(), other.end.getX());
            // min x point for other.line
            double xMin2 = min(other.start.getX(), other.end.getX());

            if ((x >= max(xMin1, xMin2)) && (x <= min(xMax1, xMax2))) {
                double y1 = ((m1 * x) + b1);
                double y2 = ((m2 * x) + b2);
                if (Math.abs(y1 - y2) <= 0.001) {
                    double y = (m1 * x) + b1;
                    Point interPoint = new Point(x, y);
                    return interPoint;
                }
            }
        } else {
            if (this.start.getX() == this.end.getX()) {
                // in case one of the lines is parallel to Y axis.
                double m = lineSlope(other.start, other.end);
                double b = other.start.getY() - (m * other.start.getX());
                double y = (m * this.start.getX()) + b;
                Point interPoint = new Point(this.start.getX(), y);
                return interPoint;
            } else if (other.start.getX() == other.end.getX()) {
                // in case one of the lines is parallel to Y axis.
                double m = lineSlope(this.start, this.end);
                double b = this.start.getY() - (m * this.start.getX());
                double y = (m * other.start.getX()) + b;
                Point interPoint = new Point(other.start.getX(), y);
                return interPoint;
            }
        }
        return null;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        double m1 = lineSlope(this.start, this.end);
        double m2 = lineSlope(other.start, other.end);
        if (m1 == m2) {
            double b1 = this.start.getY() - (m1 * this.start.getX());
            double b2 = other.start.getY() - (m2 * other.start.getX());
            double y1 = (m1 * this.start().getX()) + b1;
            double y2 = (m2 * this.start().getX()) + b2;
            if (y1 == y2) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Min double.
     *
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the double
     */
    public double min(double n1, double n2) {
        if (n1 < n2) {
            return n1;
        } else {
            return n2;
        }
    }

    /**
     * Max double.
     *
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the double
     */
    public double max(double n1, double n2) {
        if (n1 > n2) {
            return n1;
        } else {
            return n2;
        }
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = new ArrayList<Point>();
        Line line = new Line(this.start, this.end);

        list = rect.intersectionPoints(line); // might be problematic

        if (list.isEmpty()) {
            return null;
        }
        int listIndex = list.size();
        double distance = list.get(0).distance(this.start);
        Point minDistancePoint = list.get(0);
        for (int i = 0; i < listIndex; i++) {
            double temp = list.get(i).distance(this.start);
            if (temp <= distance) {
                distance = temp;
                minDistancePoint = list.get(i);
            }
        }
        return minDistancePoint;
    }
}
