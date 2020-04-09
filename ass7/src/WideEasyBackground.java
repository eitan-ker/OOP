/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Wide easy background.
 */
public class WideEasyBackground implements Sprite {
    /**
     * draw background.
     *
     * @param d a draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(197, 202, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(new Color(234, 230, 49));
        d.fillCircle(150, 130, 60);

        for (int i = 1; i <= 100; i++) {
            d.drawLine(150, 130, 8 * i, 280);
        }

        d.setColor(new Color(211, 194, 55));
        d.fillCircle(150, 130, 52);

        d.setColor(new Color(255, 229, 43));
        d.fillCircle(150, 130, 45);
    }

    /**
     * do nothing.
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
