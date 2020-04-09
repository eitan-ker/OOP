/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

import biuoop.GUI;

import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * The type Ass6Game.
 */
public class Ass7Game {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        LevelSpecificationReader reader = new LevelSpecificationReader();
        java.io.Reader is = new InputStreamReader(ClassLoader.getSystemClassLoader().
                getResourceAsStream("level_sets.txt"));

        Map<String, List<LevelInformation>> levelsMap = reader.fromLevelSet(is);

        // initializers
        GUI gui = new GUI("run", 800, 600);
        AnimationRunner animation = new AnimationRunner(gui);
        biuoop.KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keyboardSensor, animation);

        // loaing or making file
        HighScoresTable table = new HighScoresTable(10);
        File file = new File("highscores.txt");
        try {
            table.load(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        // if table is empty
        if (table.getHighScores().isEmpty()) {
            try {
                table.save(file);
            } catch (Exception e) {
                System.out.println(e);

            }
        }

        // sub menu
        Menu<Task<Void>> subMenu = new MenuAnimation<>("choose Difficulty:", keyboardSensor, animation);
        for (String m : levelsMap.keySet()) {
            subMenu.addSelection(m.split(":")[0], m.split(":")[1], new StartGameTask(animation,
                    keyboardSensor, gui, levelsMap.get(m), table, file));
        }

        // making the manu
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "Show high scores table", new ShowHighScoresTask(animation, keyboardSensor,
                table));
        menu.addSelection("q", "Quit Game", new QuitGameTask(gui));

        // running the menu
        while (true) {
            animation.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            ((MenuAnimation<Task<Void>>) menu).restart();
            ((MenuAnimation<Task<Void>>) subMenu).restart();

        }

    }
}