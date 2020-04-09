/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {

        /**
         * Number of balls int.
         *
         * @return the int
         */
        public int numberOfBalls() {
            return 1;
        }

        /**
         * Initial ball velocities list.
         *
         * @return the list
         */
        public List<Velocity> initialBallVelocities() {
            List<Velocity> velocityList = new ArrayList<Velocity>();
            Velocity v1 = Velocity.fromAngleAndSpeed(180, 5);
            velocityList.add(v1);
            return velocityList;
        }

        /**
         * return Paddle speed.
         *
         * @return the int
         */
        public int paddleSpeed() {
            return 5;
        }

        /**
         * Paddle width int.
         *
         * @return the int
         */
        public int paddleWidth() {
            return 100;
        }

        /**
         * Level name string.
         *
         * @return the string
         */
        public String levelName() {
            return "Direct Hit";
        }

        /**
         * Gets background.
         *
         * @return the background
         */
        public Sprite getBackground() {
            return new DirectHitBackground();
        }

        /**
         * Blocks list.
         *
         * @return the list
         */
        public List<Block> blocks() {
            List<Block> listBlocks = new ArrayList<Block>();
            Point p1 = new Point(380, 145);
            Rectangle rect = new Rectangle(p1, 40, 40, Color.RED);
            Block b1 = new Block(rect, 1);
            listBlocks.add(b1);
            return listBlocks;
        }

        /**
         * Number of blocks to remove int.
         *
         * @return the int
         */
        public int numberOfBlocksToRemove() {
            return 1;
        }
}
