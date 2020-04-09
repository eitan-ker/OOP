import java.awt.Color;
import java.util.Map;

/**
 * The type Block maker.
 */
public class BlockMaker implements BlockCreator {
    private int height;
    private int width;
    private int hits;
    private Color stroke;
    private Map<Integer, ColorsParser> filling;


    /**
     * Instantiates a new Block maker.
     *
     * @param height  the height
     * @param width   the width
     * @param hits    the hits
     * @param stroke  the stroke
     * @param filling the filling
     */
    public BlockMaker(int height, int width, int hits, Color stroke, Map<Integer, ColorsParser> filling) {
        this.height = height;
        this.width = width;
        this.hits = hits;
        this.stroke = stroke;
        this.filling = filling;
    }

    /**
     * create a block.
     *
     * @param xpos the x value of the upper left point of the rectangle
     * @param ypos the y value of the upper left point of the rectangle
     * @return new block
     */
    public Block create(int xpos, int ypos) {
        if (this.stroke == null) {
            return new Block(new Rectangle(new Point(xpos, ypos), this.width, this.height), this.hits, this.filling,
                    null);
        } else if (this.filling == null) {
            return new Block(new Rectangle(new Point(xpos, ypos), this.width, this.height), this.hits, null,
                    this.stroke);
        }
        return new Block(new Rectangle(new Point(xpos, ypos), this.width, this.height), this.hits, this.filling,
                this.stroke);
    }
}
