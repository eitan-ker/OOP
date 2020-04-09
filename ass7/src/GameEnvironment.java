/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import java.util.ArrayList;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private ArrayList<Collidable> objectList = new ArrayList<Collidable>();

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.objectList.add(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int counter = 0;
        if (this.objectList.isEmpty()) {
            return null;
        }
        // will be used to check min distance to collision
        double min = trajectory.end().distance(trajectory.start());

        // will be used to save the min point of collision
        Point minCollisionPoint = trajectory.end();

        // will be used to save min distance collision object data
        Collidable object = this.objectList.get(0);

        // checks each object on the list if it is collided with the line.
        // checking what is the minimum distance which a collision occurred
        // and send back info about this object
        for (int i = 0; i < this.objectList.size(); i++) {
            double temp = min;
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(
                    this.objectList.get(i).getCollisionRectangle());
            if (collisionPoint != null) {
                if (Math.abs(collisionPoint.getX() - trajectory.end().getX()) <= 0.001) {
                    counter++;
                    minCollisionPoint = trajectory.end();
                    object = this.objectList.get(i);

                } else {
                    if (collisionPoint.distance(trajectory.start()) <= temp) {
                        counter++;
                        min = collisionPoint.distance(trajectory.start());
                        // sets the minimum collision point
                        minCollisionPoint = collisionPoint;
                        object = this.objectList.get(i);
                    }
                }
            }

        }
        if (counter == 0) {
            return null;
        }
        CollisionInfo collisionObject = new CollisionInfo(minCollisionPoint, object);
        return collisionObject;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.objectList.remove(c);
    }
}
