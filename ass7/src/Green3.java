/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    /**
     * return the number of balls in the game.
     *
     * @return the number of balls in the game
     */
    public int numberOfBalls() {
        return 2;
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
        return velocityList;
    }

    /**
     * return the paddle speed.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * return the paddle width.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return 120;
    }

    /**
     * return the level name.
     *
     * @return the level name
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * return the background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new Green3Background();
    }

    /**
     * create the blocks.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();

        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.blue, Color.white};
        int[] hitPoints = {2, 1, 1, 1, 1};
        int y = 140;
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 5; j < 15; j++) {
                Point p = new Point(j * 50 + 25, y);
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
