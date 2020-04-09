/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double seconds;
    private int countFrom;
    private int copyCount;
    private SpriteCollection sprites;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.countFrom = countFrom;
        this.sprites = gameScreen;
        this.copyCount = countFrom;
        this.stop = false;
    }
    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
  //      d.setColor(Color.gray);
    //    d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        this.sprites.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        if (this.copyCount == 3) {
            sleeper.sleepFor((long) (this.seconds * 10 / this.countFrom));
            d.setColor(Color.white);
        } else {
            sleeper.sleepFor((long) (this.seconds * 1000 / this.countFrom));
            d.setColor(Color.white);
        }
        d.drawText(d.getWidth() / 2, 350, String.valueOf(copyCount), 32);
        this.copyCount = this.copyCount - 1;
        if (this.copyCount == -1) {
            this.stop = true;
        }

    }
    /**
     * Should stop.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
