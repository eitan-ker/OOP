import biuoop.KeyboardSensor;

/**
 * The type Show high scores task.
 */
public class ShowHighScoresTask implements Task<Void> {
    private AnimationRunner animationRunner;
    private HighScoresTable table;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Show high scores task.
     *
     * @param runner the runner
     * @param board  the board
     * @param table  the table
     */
    public ShowHighScoresTask(AnimationRunner runner, KeyboardSensor board, HighScoresTable table) {
        this.animationRunner = runner;
        this.table = table;
        this.keyboardSensor = board;
    }
    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(table, keyboardSensor)));
        return null;
    }
}
