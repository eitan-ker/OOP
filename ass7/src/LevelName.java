import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private String name;
    private Block block;


    /**
     * Instantiates a new Level name.
     *
     * @param levelName the level name
     * @param block     the block
     */
// constructor
    public LevelName(String levelName, Block block) {
        this.name = levelName;
        this.block = block;
    }
    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        int textX = (int) (this.block.getCollisionRectangle().getUpperLeft().getX() + 550);
        int textY = (int) (this.block.getCollisionRectangle().getUpperLeft().getY()
                + (this.block.getCollisionRectangle().getHeight()) / 2) + 4;
        d.setColor(Color.black);
        d.drawText(textX, textY, "Level Name: " + this.name, 14);
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
