/**
 * The interface Block creator.
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
// Create a block at the specified location.
    Block create(int xpos, int ypos);
}
