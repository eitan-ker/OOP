/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**
     * Instantiates a new Collision info.
     *
     * @param collision the collision
     * @param object    the object
     */
// constructor
    public CollisionInfo(Point collision, Collidable object) {
        this.collisionPoint = collision;
        this.object = object;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.object;
    }
}
