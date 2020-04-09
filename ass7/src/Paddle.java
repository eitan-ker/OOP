/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int xLowBorder;
    private int xHighBorder;
    private int paddleSpeed;

    /**
     * Instantiates a new Paddle.
     *
     * @param key         the key
     * @param rectangle   the rectangle
     * @param paddleSpeed the paddle speed
     */
    public Paddle(biuoop.KeyboardSensor key, Rectangle rectangle, int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
        this.keyboard = key;
        this.rect = rectangle;
    }

    /**
     * Sets borders.
     *
     * @param lowX  the low x
     * @param highX the high x
     */
    public void setBorders(int lowX, int highX) {
        this.xLowBorder = lowX;
        this.xHighBorder = highX;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() > this.paddleSpeed + this.xLowBorder) {
            Point point = new Point(this.rect.getUpperLeft().getX()
                    - this.paddleSpeed, this.rect.getUpperLeft().getY());
            this.rect = new Rectangle(point, this.rect.getWidth(),
                    this.rect.getHeight(), this.rect.getColor());
        } else {
            Point point = new Point(this.xLowBorder, this.rect.
                    getUpperLeft().getY());
            this.rect = new Rectangle(point, this.rect.getWidth(),
                    this.rect.getHeight(), this.rect.getColor());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.rect.getUpperLeft().getX() + this.rect.getWidth()
                < this.xHighBorder - this.paddleSpeed) {
            Point point = new Point(this.rect.getUpperLeft().getX()
                    + this.paddleSpeed, this.rect.getUpperLeft().getY());
            this.rect = new Rectangle(point, this.rect.getWidth(),
                    this.rect.getHeight(), this.rect.getColor());
        } else {
            Point point = new Point(this.xHighBorder
                    - this.rect.getWidth(), this.rect.getUpperLeft().
                    getY());
            this.rect = new Rectangle(point, this.rect.getWidth(),
                    this.rect.getHeight(), this.rect.getColor());
        }
    }

    /**
     * Time passed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.rect.getColor());
        Point point = this.rect.getUpperLeft();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        d.fillRectangle((int) point.getX(), (int) point.getY(), (int) width,
                (int) height);
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param hitter the hitting ball
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double y = collisionPoint.getY();
        double height = this.rect.getHeight();
        double upperLeftY = this.rect.getUpperLeft().getY();

        if (Math.abs(y - upperLeftY) < 0.01 || Math.abs(y - upperLeftY
                - height) < 0.01) {
            dx = correctVelocity(collisionPoint, currentVelocity, "dx");
            dy = correctVelocity(collisionPoint, currentVelocity, "dy");
        }
        Velocity newV = new Velocity(dx, dy);
        return newV;
    }

    /**
     * Correct velocity double.
     *
     * @param collisionPoint the collision point
     * @param velocity       the velocity
     * @param v              the v
     * @return the double
     */
// returns the correct velocity of the ball by hitting specific zone
    public double correctVelocity(Point collisionPoint, Velocity velocity,
                                  String v) {
        double x = collisionPoint.getX();
        double upperLeftX = this.rect.getUpperLeft().getX();
        double wide = this.rect.getWidth();
        double zone = wide / 5;
        double speed = ((velocity.getDx()) * (velocity.getDx()))
                + (((velocity.getDy()) * (velocity.getDy())));
        speed = Math.sqrt(speed);
        for (int i = 1; i < 5; i++) {
            // in case hit zone 1
            if (x >= upperLeftX && x < upperLeftX + zone) {
                if (v == "dx") {
                    double dx = speed * (Math.sin(Math.toRadians(300)));
                    return dx;
                }
                if (v == "dy") {
                    double dy = -(speed * (Math.cos(Math.toRadians(300))));
                    return dy;
                }
            }
            // in case hit zone 2
            if (x >= upperLeftX + zone && x < upperLeftX + (2 * zone)) {
                if (v == "dx") {
                    double dx = speed * (Math.sin(Math.toRadians(330)));
                    return dx;
                }
                if (v == "dy") {
                    double dy = -(speed * (Math.cos(Math.toRadians(330))));
                    return dy;
                }
            }
            // in case hit zone 3
            if (x >= upperLeftX + (2 * zone) && x < upperLeftX + (3 * zone)) {
                if (v == "dx") {
                    return velocity.getDx();
                }
                if (v == "dy") {
                    return -(velocity.getDy());
                }
            }
            // in case hit zone 4
            if (x >= upperLeftX + (3 * zone)
                    && x <= upperLeftX + (4 * zone)) {
                if (v == "dx") {
                    double dx = speed * (Math.sin(Math.toRadians(30)));
                    return dx;
                }
                if (v == "dy") {
                    double dy = -(speed * (Math.cos(Math.toRadians(30))));
                    return dy;
                }
            }
            // in case hit zone 5
            if (v == "dx") {
                double dx = speed * (Math.sin(Math.toRadians(60)));
                return dx;
            }
            if (v == "dy") {
                double dy = -(speed * (Math.cos(Math.toRadians(60))));
                return dy;
            }
        }
        return 0;

    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

}
