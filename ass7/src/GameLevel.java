/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game level.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlock;
    private Counter remainingBalls;
    private Paddle paddle;
    private final int upperBorder = 0;
    private final int lowerBorder = 600;
    private final int rightBorder = 800;
    private final int leftBorder = 0;
    private boolean running;
    private GUI gui;
    private Counter score;
    private Counter life;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInfo;


    /**
     * Instantiates a new Game.
     *
     * @param info             the level info
     * @param keyboard         the keyboard
     * @param animation        the animation
     * @param life             the life
     * @param score            the score
     * @param spriteCollection the sprite collection
     * @param gui              the gui
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboard, AnimationRunner animation, Counter life,
                     Counter score, SpriteCollection spriteCollection, GUI gui) {
        this.keyboardSensor = keyboard;
        this.runner = animation;
        this.life = life;
        this.score = score;
        this.levelInfo = info;
        this.sprites = spriteCollection;
        this.environment = new GameEnvironment();
    }

    /**
     * Gets num bocks left.
     *
     * @return the num bocks left
     */
    public int getNumBocksLeft() {
        return this.remainingBlock.getValue();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {

        // initializes
        ScoreTrackingListener countingScore = new ScoreTrackingListener(this.score);


        this.remainingBalls = new Counter(0);
        BallRemover remover2 = new BallRemover(this, this.remainingBalls);

        this.remainingBlock = new Counter(0);
        BlockRemover remover = new BlockRemover(this, this.remainingBlock);

        // date block
        Point data = new Point(leftBorder, upperBorder);
        Rectangle dataRec = new Rectangle(data, rightBorder, leftBorder + 25, Color.LIGHT_GRAY);
        Block dataBlock = new Block(dataRec, 0);
        dataBlock.addToGame(this);
        ScoreIndicator index = new ScoreIndicator(this.score, dataBlock);
        index.addToGame(this);
        LivesIndicator lives = new LivesIndicator(this.life, dataBlock);
        lives.addToGame(this);
        LevelName name = new LevelName(this.levelInfo.levelName(), dataBlock);
        name.addToGame(this);

        // walls initialize
        Point w1 = new Point(leftBorder, upperBorder + 25);
        Rectangle rw1 = new Rectangle(w1, 800, 25, Color.darkGray);
        Block wall1 = new Block(rw1, 0);
        wall1.addToGame(this);

        Point w2 = new Point(leftBorder, upperBorder + 25);
        Rectangle rw2 = new Rectangle(w2, 25, 800, java.awt.Color.darkGray);
        Block wall2 = new Block(rw2, 0);
        wall2.addToGame(this);

        Point w3 = new Point(rightBorder - 25, upperBorder + 25);
        Rectangle rw3 = new Rectangle(w3, 25, 800, Color.darkGray);
        Block wall3 = new Block(rw3, 0);
        wall3.addToGame(this);

        // lower wall - death region
        Point w4 = new Point(leftBorder, lowerBorder - 10);
        Rectangle rw4 = new Rectangle(w4, 800, 20, Color.darkGray);
        Block wall4 = new Block(rw4, 0);
        wall4.addToGame(this);
        wall4.addHitListener(remover2);

        // blocs initialize
        List<Block> listBlocks = this.levelInfo.blocks();
        for (int i = 0; i < listBlocks.size(); i++) {
            listBlocks.get(i).addToGame(this);
            listBlocks.get(i).addHitListener(remover);
            listBlocks.get(i).addHitListener(countingScore);
            this.remainingBlock.increase(1);
        }
    }

    /**
     * playOneTurn.
     */
// Run the game -- start the animation loop.
    public void playOneTurn() {
        makeBalls();
        makePaddle();
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Instantiates a new Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Instantiates a new Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Make balls.
     */
    public void makeBalls() {
        List<Ball> ballsList = new ArrayList<Ball>();
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 550, 5, new Color(0x91FFE2));
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
            ball.setEnvironment(this.environment);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
            ballsList.add(ball);
        }
    }

    /**
     * Make paddle.
     */
    public void makePaddle() {
        Point uperLeftPaddle = new Point((this.rightBorder - this.levelInfo.paddleWidth()) / 2, lowerBorder - 25);
        Rectangle rect = new Rectangle(uperLeftPaddle, this.levelInfo.paddleWidth(), 10,
                Color.yellow);
        this.paddle = new Paddle(this.keyboardSensor, rect, this.levelInfo.paddleSpeed());
        paddle.setBorders(leftBorder + 25, rightBorder - 25);
        paddle.addToGame(this);
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return this.life.getValue();
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlock.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.keyboardSensor.isPressed("n")) {
        	int x = this.remainingBlock.getValue();
        }
        if (keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(keyboardSensor)));
        }
        if (this.remainingBalls.getValue() == 0) {
            this.life.decrease(1);
            this.paddle.removeFromGame(this);
            this.running = false;
            if (this.life.getValue() == 0) {
                this.running = false;
            }
        }
    }

    /**
     * Should stop.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

}
