/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private boolean stat;
    private int finalScore;
    private KeyboardSensor keyboard;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k         the k
     * @param winOrLose the win or lose
     * @param score     the score
     */
    public EndScreen(KeyboardSensor k, Boolean winOrLose, int score) {
        this.keyboard = k;
        this.stat = winOrLose;
        this.finalScore = score;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // lose
        if (!this.stat) {
            d.setColor(new Color(208, 50, 27));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.drawText(180, d.getHeight() / 2, "Game Over. Your score is " + this.finalScore, 32);
        } else {
            //win
            d.setColor(new Color(55, 210, 87));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.drawText(180, d.getHeight() / 2, "You Win! Your score is " + this.finalScore, 32);

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
