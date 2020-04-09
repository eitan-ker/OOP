/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Direct hit background.
 */
public class DirectHitBackground implements Sprite {
    /**
     * fill background.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        int j = 50;
        for (int i = 0; i < 3; i++) {
            d.drawCircle(400, 165, j);
            j = j + 30;
        }
        d.drawLine(400, 0, 400, 140);
        d.drawLine(420, 165, 575, 165);
        d.drawLine(400, 190, 400, 330);
        d.drawLine(220, 165, 375, 165);
    }
    /**
     * Time passed.
     */
    public void timePassed() {
    }

    /**
     * add the background to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
