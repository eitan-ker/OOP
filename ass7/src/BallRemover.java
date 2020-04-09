/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    // constructor

    /**
     * Instantiates a new Block remover.
     *
     * @param game         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Hit event for ball.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
