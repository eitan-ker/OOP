/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 *
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    /**
     * return the number of balls in the game.
     *
     * @return the number of balls in the game
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * initial the balls velocities.
     *
     * @return the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int j = -50; j <= 50; j += 10) {
            if (j == 0) {
                continue;
            }
            velocityList.add(Velocity.fromAngleAndSpeed(j, 4));
        }
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
        return 600;
    }

    /**
     * return the level name.
     *
     * @return the level name
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * return the background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    /**
     * create the blocks.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<Block>();
        Point p = new Point(25, 280);
        double j = 25;
        for (int i = 0; i < 15; i++) {
            if (i >= 0 && i < 2) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.RED);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 2 && i < 4) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.ORANGE);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 4 && i < 6) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.YELLOW);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 6 && i < 9) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.GREEN);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 6 && i < 9) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.GREEN);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 9 && i < 11) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.BLUE);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 11 && i < 13) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.PINK);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            if (i >= 13 && i < 15) {
                Rectangle rectangle = new Rectangle(p, 50, 20, Color.CYAN);
                Block block = new Block(rectangle, 1);
                listBlocks.add(block);
            }
            j = j + 50;
            p = new Point(j, 280);
        }
        return listBlocks;
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
