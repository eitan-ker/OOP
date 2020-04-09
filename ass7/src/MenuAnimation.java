import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stopMain;
    private boolean stopSub;


    private T statusMain;
    private Menu<T> statusSub;

    private KeyboardSensor keyboard;
    private String title;
    private List<String> keyListMain;
    private List<String> linesToPrintListMain;
    private List<T> returnListMain;

    private List<String> keyListSub;
    private List<String> linesToPrintListSub;
    private List<Menu<T>> returnListSub;
    private AnimationRunner animation;

    /**
     * Instantiates a new Menu animation.
     *
     * @param title     the title
     * @param key       the key
     * @param animation the animation
     */
    public MenuAnimation(String title, KeyboardSensor key, AnimationRunner animation) {
        this.title = title;
        this.keyboard = key;
        this.keyListMain = new ArrayList<>();
        this.linesToPrintListMain = new ArrayList<>();
        this.returnListMain = new ArrayList<>();
        this.animation = animation;

        this.keyListSub = new ArrayList<>();
        this.linesToPrintListSub = new ArrayList<>();
        this.returnListSub = new ArrayList<>();
        restart();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keyListMain.add(key);
        this.linesToPrintListMain.add(message);
        this.returnListMain.add(returnVal);
    }

    @Override
    public T getStatus() {
        return this.statusMain;
    }


    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(new Color(127, 224, 190));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(0, 0, 0));
        d.drawText(100 + 1, 150  + 1, title, 80);
        d.setColor(new Color(0, 0, 0));
        d.drawText(100 - 1, 150 - 1, title, 80);
        d.setColor(new Color(0, 0, 0));
        d.drawText(100 + 1, 150 - 1, title, 80);
        d.setColor(new Color(0, 0, 0));
        d.drawText(100 - 1, 150 + 1, title, 80);
        d.setColor(new Color(55, 210, 87));
        d.drawText(100, 150, title, 80);
        int i = 0;
        int size = linesToPrintListSub.size();

        for (i = 0; i < linesToPrintListSub.size(); i++) {
                String optionText = "(" + (String) keyListSub.get(i) + ") " + (String) linesToPrintListSub.get(i);
                d.setColor(new Color(144, 144, 144));
                d.drawText(150 + 1, 250 + 1 + (i * 80), optionText, 50);
                optionText = "(" + (String) keyListSub.get(i) + ") " + (String) linesToPrintListSub.get(i);
                d.setColor(new Color(144, 144, 144));
                d.drawText(150 + 2, 250 + 2 + (i * 80), optionText, 50);
                optionText = "(" + (String) keyListSub.get(i) + ") " + (String) linesToPrintListSub.get(i);
                d.setColor(new Color(144, 144, 144));
                d.drawText(150 + 3, 250 + 3 + (i * 80), optionText, 50);
                optionText = "(" + (String) keyListSub.get(i) + ") " + (String) linesToPrintListSub.get(i);
                d.setColor(new Color(92, 132, 208));
                d.drawText(150, 250 + (i * 80), optionText, 50);
        }

        for (; i < linesToPrintListMain.size() + size; i++) {
            String optionText = "(" + (String) keyListMain.get(i - size) + ") " + (String) linesToPrintListMain.
                    get(i - size);
            d.setColor(new Color(144, 144, 144));
            d.drawText(150 + 1, 250 + 1 + (i * 80), optionText, 50);
            optionText = "(" + (String) keyListMain.get(i - size) + ") " + (String) linesToPrintListMain.
                    get(i - size);
            d.setColor(new Color(144, 144, 144));
            d.drawText(150 + 2, 250 + 2 + (i * 80), optionText, 50);
            optionText = "(" + (String) keyListMain.get(i - size) + ") " + (String) linesToPrintListMain.
                    get(i - size);
            d.setColor(new Color(144, 144, 144));
            d.drawText(150 + 3, 250 + 3 + (i * 80), optionText, 50);
            optionText = "(" + (String) keyListMain.get(i - size) + ") " + (String) linesToPrintListMain.
                    get(i - size);
            d.setColor(new Color(92, 132, 208));
            d.drawText(150, 250 + (i * 80), optionText, 50);

        }

        for (i = 0; i < this.returnListMain.size(); i++) {
            if (keyboard.isPressed((String) keyListMain.get(i))) {
                this.statusMain = returnListMain.get(i);
                this.stopMain = true;
                break;
            }
        }
        for (i = 0; i < this.returnListSub.size(); i++) {
            if (keyboard.isPressed((String) keyListSub.get(i))) {
                Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("choose Difficulty:", keyboard, animation);
                animation.run(returnListSub.get(i));
                T task = (T) returnListSub.get(i).getStatus();
                this.statusMain = task;
                this.stopSub = true;
                break;
            }

        }
    }

    @Override
    public boolean shouldStop() {
        return statusMain != null;
    }

    /**
     * Restart.
     */
    public void restart() {
        this.statusMain = null;
        this.stopMain = false;
        this.statusSub = null;
        this.stopSub = false;
    }


    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keyListSub.add(key);
        this.linesToPrintListSub.add(message);
        this.returnListSub.add(subMenu);
    }
}
