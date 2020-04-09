import biuoop.GUI;

/**
 * The type Quit game task.
 */
public class QuitGameTask implements Task<Void> {
    private GUI gui;

    /**
     * Instantiates a new Quit game task.
     *
     * @param gui the gui
     */
    public QuitGameTask(GUI gui) {
        this.gui = gui;
    }
    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
