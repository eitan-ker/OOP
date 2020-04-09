/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */
import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {

    private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        int index = spriteList.size();
        ArrayList<Sprite> temp = new ArrayList<Sprite>(this.spriteList);

        for (int i = 0; i < index; i++) {
            temp.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        int index = spriteList.size();
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}
