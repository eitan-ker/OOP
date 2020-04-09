import biuoop.DrawSurface;

/**
 * The type Background.
 */
public class Background implements Sprite {

    private ColorsParser background;

    /**
     * Instantiates a new Background.
     *
     * @param pic the pic
     */
    public Background(ColorsParser pic) {
        this.background = pic;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        if (background.isItImage()) {
            d.drawImage(0, 0, background.getImage());
        } else {
            d.setColor(background.getColor());
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    @Override
    public void timePassed() {
    }
}
