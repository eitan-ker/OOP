/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHp() > 1) {
            this.currentScore.increase(5);
        }
        if (beingHit.getHp() == 1) {
            this.currentScore.increase(10);
        }
    }
}
