import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private KeyboardSensor keyboard;
    private HighScoresTable table;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param key    the key
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor key) {
        this.stop = false;
        this.keyboard = key;
        this.table = scores;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(127, 224, 190));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(92, 132, 208));
        d.fillRectangle(400, 10, 10, 540);
        d.setColor(new Color(92, 132, 208));
        d.fillRectangle(150, 10, 10, 540);
        d.setColor(new Color(92, 132, 208));
        d.fillRectangle(650, 10, 10, 540);
        d.setColor(new Color(92, 132, 208));
        d.fillRectangle(150, 45, 500, 10);
        d.setColor(new Color(14, 0, 169));
        d.drawText(200, 40, "NAME", 35);
        d.setColor(new Color(14, 0, 169));
        d.drawText(460, 40, "SCORE", 35);
        List<ScoreInfo> list = this.table.getHighScores();
        if (!list.isEmpty()) {
            for (int i = 0; i < this.table.size(); i++) {
                d.drawText(120, 75 + (i * 50), i + 1 + ")", 15);
                d.drawText(200, 75 + (i * 50), list.get(i).getName(), 15);
                d.drawText(460, 75 + (i * 50), Integer.toString(list.get(i).getScore()), 15);
            }
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
