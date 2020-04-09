/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Final 4 background.
 */
public class Final4Background implements Sprite {
    /**
     * draw background.
     * @param d a draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0x1691E2));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        // create lines
        for (int i = 0; i < 10; i++) {
            d.drawLine(150 + 10 * i, 300, 110 + 10 * i, 600);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(600 + 10 * i, 450, 560 + 10 * i, 600);
        }

        d.setColor(new Color(0xBFBFBF));
        d.fillCircle(175, 330, 30);
        d.fillCircle(160, 300, 25);
        d.fillCircle(625, 480, 30);
        d.fillCircle(610, 450, 25);

        d.setColor(new Color(0xCBCBCB));
        d.fillCircle(200, 295, 25);
        d.fillCircle(650, 445, 25);

        d.setColor(new Color(0x909090));
        d.fillCircle(230, 300, 30);
        d.fillCircle(215, 325, 20);
        d.fillCircle(680, 450, 30);
        d.fillCircle(665, 475, 20);


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
