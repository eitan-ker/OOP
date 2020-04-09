/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Rectangle rect;
    private Counter hp;
    private Color color;
    private Color stroke;
    private Map<Integer, ColorsParser> filling;


    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     * @param hp   the hp
     */
    public Block(Rectangle rect, int hp) {
        this.rect = rect;
        this.hp = new Counter(hp);
    }

    /**
     * Instantiates a new Block.
     *
     * @param rect        the rect
     * @param hp          the hp
     * @param fillingInfo the filling info
     * @param strokeInfo  the stroke info
     */
    public Block(Rectangle rect, int hp, Map<Integer, ColorsParser> fillingInfo, Color strokeInfo) {
        this.rect = rect;
        this.hp = new Counter(hp);
        if (strokeInfo != null) {
            this.stroke = stroke;
        }
        this.color = null;
        if (fillingInfo != null) {
            this.filling = fillingInfo;
        }
    }

    /**
     * Sets hp.
     */
    public void setHp() {
        if (this.hp.getValue() > 0) {
            this.hp.decrease(1);
        }
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
        return this.hp.getValue();
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.rect.getColor();
    }

    /**
     * Draw figures.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        // fill rectangle;
        Point point = this.rect.getUpperLeft();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();

        if (this.filling != null) {
            if (this.filling.containsKey(this.hp.getValue())) {
                if (this.filling.get(this.hp.getValue()).isItColor()) {
                    d.setColor(this.filling.get(this.hp.getValue()).getColor());
                    d.fillRectangle((int) point.getX(), (int) point.getY(), (int) width,
                            (int) height);
                    d.setColor(Color.black);
                    d.drawRectangle((int) point.getX(), (int) point.getY(), (int) width,
                            (int) height);
                } else if (this.filling.get(this.hp.getValue()).isItImage()) {
                    d.drawImage((int) point.getX(), (int) point.getY(), this.filling.get(this.hp.getValue()).
                            getImage());
                }
            } else if (this.filling.containsKey(0)) {
                if (this.filling.get(0).isItColor()) {
                    d.setColor(this.filling.get(0).getColor());
                    d.fillRectangle((int) point.getX(), (int) point.getY(), (int) width,
                            (int) height);
                    d.setColor(Color.black);

                    d.drawRectangle((int) point.getX(), (int) point.getY(), (int) width,
                            (int) height);
                } else if (this.filling.get(0).isItImage()) {
                    d.drawImage((int) point.getX(), (int) point.getY(), this.filling.get(0).getImage());
                }
            }
            if (this.stroke != null) {
                d.setColor(getColor());
                d.fillRectangle((int) point.getX(), (int) point.getY(), (int) width,
                        (int) height);
                d.setColor(Color.black);
                d.drawRectangle((int) point.getX(), (int) point.getY(), (int) width,
                        (int) height);
            }
        } else {
            d.setColor(getColor());
            d.fillRectangle((int) point.getX(), (int) point.getY(), (int) width,
                    (int) height);
            d.setColor(Color.black);
            d.drawRectangle((int) point.getX(), (int) point.getY(), (int) width,
                    (int) height);
        }
    }

    /**
     * Time passed.
     */
    public void timePassed() {
    }

    /**
     * @return the  rectangle
     */
// Return t
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the ball that hitts.
     * @return the velocity after hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int counter = 0;
        double dx = currentVelocity.getDx();
        double x = collisionPoint.getX();
        double dy = currentVelocity.getDy();
        double y = collisionPoint.getY();
        double wide = this.rect.getWidth();
        double height = this.rect.getHeight();
        double upperLeftY = this.rect.getUpperLeft().getY();
        double upperLeftX = this.rect.getUpperLeft().getX();
        if (Math.abs(y - upperLeftY) <= 0.01 || Math.abs(y - upperLeftY
                - height) <= 0.01) {
            dy = -dy;
            counter++;
        }
        if (Math.abs(x - upperLeftX) <= 0.01 || Math.abs(x - upperLeftX
                - wide) <= 0.01) {
            dx = -dx;
            counter++;
        }
        if (counter > 0) {
            this.notifyHit(hitter);
            this.setHp();

        }
        Velocity newV = new Velocity(dx, dy);
        return newV;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
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

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify when a hitt occur.
     *
     * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
