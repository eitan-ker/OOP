/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private int numOfLevels;
    private GUI gui;
    private File file;
    private HighScoresTable table;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar    the ar
     * @param ks    the ks
     * @param gui   the gui
     * @param file  the file
     * @param table the table
     */
//constructors
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, File file,
                    HighScoresTable table) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.file = file;
        this.table = table;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {

        Counter score = new Counter(0);
        Counter life = new Counter(7);

        for (LevelInformation levelInfo : levels) {
            SpriteCollection sprites = new SpriteCollection();
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, life, score, sprites,
                    this.gui);
            sprites.addSprite(levelInfo.getBackground());
            level.initialize();

            while (life.getValue() > 0 && level.getNumBocksLeft() > 0) {
                level.playOneTurn();
            }
            if (life.getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(keyboardSensor, false, score.getValue())));
                break;
            }
            this.numOfLevels++;
        }
// saving data
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        try {
            this.table.add(new ScoreInfo(name, score.getValue()));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            table.save(this.file);
        } catch (Exception e) {
            System.out.println(e);
        }
// end screen
        if (this.numOfLevels == levels.size()) {
            this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new EndScreen(keyboardSensor, true, score.getValue())));
        }

        // table animation
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.table, keyboardSensor)));

    }
}
