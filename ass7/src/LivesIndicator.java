/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter life;
    private Block block;

    /**
     * Instantiates a new Score indicator.
     *
     * @param count     the count
     * @param rectangle the rectangle
     */
    public LivesIndicator(Counter count, Block rectangle) {
        this.life = count;
        this.block = rectangle;
    }
    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        int textX = (int) (this.block.getCollisionRectangle().getUpperLeft().getX() + 100);
        int textY = (int) (this.block.getCollisionRectangle().getUpperLeft().getY()
                + (this.block.getCollisionRectangle().getHeight()) / 2) + 4;
        d.setColor(Color.black);
        d.drawText(textX, textY, "Lives:" + this.life.getValue(), 14);
    }
    /**
     * Sets velocity.
     *
     * starting move one step method
     */
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
