import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.util.List;

/**
 * The type Start game task.
 */
public class StartGameTask implements Task<Void> {
    private final AnimationRunner animation;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private List<LevelInformation> levels;
    private HighScoresTable table;
    private File file;

    /**
     * Instantiates a new Start game task.
     *
     * @param animation      the animation
     * @param keyboardSensor the keyboard sensor
     * @param gui            the gui
     * @param list           the list
     * @param table          the table
     * @param file           the file
     */
    public StartGameTask(AnimationRunner animation, KeyboardSensor keyboardSensor, GUI gui,
                         List<LevelInformation> list, HighScoresTable table, File file) {
        this.animation = animation;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
        this.levels = list;
        this.table = table;
        this.file = file;
    }
    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {

        GameFlow game = new GameFlow(animation, keyboardSensor, gui, file, table);
        game.runLevels(levels);
        try {
            table.save(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
