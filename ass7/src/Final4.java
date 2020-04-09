/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The type Final 4.
 */
public class Final4 implements LevelInformation {
    /**
     * return the number of balls in the game.
     *
     * @return the number of balls in the game
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * initial the balls velocities.
     *
     * @return the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        velocityList.add(Velocity.fromAngleAndSpeed(35, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(325, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(0, 5));
        return velocityList;
    }

    /**
     * return the paddle speed.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * return the paddle width.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return 140;
    }

    /**
     * return the level name.
     *
     * @return the level name
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * return the background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new Final4Background();
    }

    /**
     * create the blocks.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int y = 120;
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        int[] hitPoints = {2, 1, 1, 1, 1, 1, 1};
        for (int i = 0; i < colors.length; ++i) {
            for (int j = 0; j < 15; ++j) {
                Point p = new Point((j * 50) + 25, y);
                Rectangle rect = new Rectangle(p, 50, 20, colors[i]);
                Block block = new Block(rect, hitPoints[i]);
                blockList.add(block);
            }
            y += 20;
        }
        return blockList;
    }


    /**
     * return the number of blocks.
     *
     * @return the the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
