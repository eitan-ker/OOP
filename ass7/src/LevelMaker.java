import java.util.List;
import java.util.Map;

/**
 * The type Level maker.
 */
public class LevelMaker implements LevelInformation {

    private int ballsNum;
    private List<Velocity> velocityList;
    private int paddleSped;
    private int width;
    private String name;
    private ColorsParser background;
    private List<Block> listBlocks;
    private int toRemove;

    /**
     * Instantiates a new Level maker.
     *
     * @param name       the name
     * @param ballsSpeed the balls speed
     * @param numBalls   the num balls
     * @param pic        the pic
     * @param paddle     the paddle
     * @param blocksInfo the blocks info
     * @param numBlocks  the num blocks
     */
    public LevelMaker(String name, List<Velocity> ballsSpeed, int numBalls, ColorsParser pic, Map<String,
            Integer> paddle, List<Block> blocksInfo, int numBlocks) {
        this.name = name;
        this.velocityList = ballsSpeed;
        this.ballsNum = numBalls;
        this.background = pic;
        this.listBlocks = blocksInfo;
        this.toRemove = numBlocks;
        this.width = paddle.get("paddle width");
        this.paddleSped = paddle.get("paddle speed");
    }

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSped;
    }

    @Override
    public int paddleWidth() {
        return this.width;
    }

    @Override
    public String levelName() {
        return this.name;
    }

    @Override
    public Sprite getBackground() {
        return new Background(this.background);
    }

    @Override
    public List<Block> blocks() {
        return this.listBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.toRemove;
    }
}
