/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Ball.
 */
public class Ball implements Sprite {

    private Point center;
    private int r;
    private Velocity v;
    private GameEnvironment environment;
    /**
     * The Color.
     */
    private java.awt.Color color;
    private int xLowBorder;
    private int xHighBorder;
    private int yLowBorder;
    private int yHighBorder;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
//constructor
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Sets borders.
     *
     * @param lowX  the low x
     * @param highX the high x
     * @param lowY  the low y
     * @param highY the high y
     */
    public void setBorders(int lowX, int highX, int lowY, int highY) {
        this.xLowBorder = lowX;
        this.xHighBorder = highX;
        this.yLowBorder = lowY;
        this.yHighBorder = highY;
    }

    /**
     * Gets low.
     *
     * @return the low
     */
    public int getxLow() {
        return this.xLowBorder;
    }

    /**
     * Gets high.
     *
     * @return the high
     */
    public int getxHigh() {
        return this.xHighBorder;
    }

    /**
     * Gets low.
     *
     * @return the low
     */
    public int getyLow() {
        return this.yLowBorder;
    }

    /**
     * Gets high.
     *
     * @return the high
     */
    public int getyHigh() {
        return this.yHighBorder;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets v.
     *
     * @return the v
     */
    public Velocity getV() {
        return this.v;
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
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);

        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);

        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                getSize());
    }

    /**
     * Sets velocity.
     * <p>
     * starting move one step method
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets velocity.
     *
     * @param v1 the v
     */
    public void setVelocity(Velocity v1) {
        setVelocity(v1.getDx(), v1.getDy());
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Sets environment.
     *
     * @param e the environment
     */
    public void setEnvironment(GameEnvironment e) {
        this.environment = e;
    }

    /**
     * Moving the ball until it gets close enoguh to the wall.
     */
    public void moveOneStep() {
        // line trajectory
        if (this.v == null) {
            return;
        }
        Line moveLine = new Line(this.center, this.v.applyToPoint(this.center));
        CollisionInfo collisionInfo = this.environment.getClosestCollision(moveLine);
        if (collisionInfo != null) {
            setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo
                    .collisionPoint(), this.v));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

