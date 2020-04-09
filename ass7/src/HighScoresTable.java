import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type High scores table.
 */
public class HighScoresTable {
    private List<ScoreInfo> table;
    private int tableSize;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.table = new ArrayList<ScoreInfo>(size);
        this.tableSize = size;
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        if (this.table.size() == 0) {
            this.table.add(score);
        } else {
            int place = this.getRank(score.getScore());
            if (place < 11) {
                List<ScoreInfo> temp = new ArrayList<>();
                int tempSize = this.table.size();
                // sublist of all scores smaller than score.getscore
                for (int i = place - 1; i < tempSize; i++) {
                    temp.add(this.table.get(place - 1));
                    this.table.remove(place - 1);
                }
                this.table.add(score);
                if (this.table.size() + temp.size() >= this.size()) {
                    for (int i = 0; i < this.tableSize - place; i++) {
                        this.table.add(temp.get(i));
                    }
                } else {
                    for (int i = 0; i < temp.size(); i++) {
                        this.table.add(temp.get(i));
                    }
                }
            }
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.table.size();
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getScore() < score) {
                return i + 1;
            }
        }
        return i + 1; // i = 11
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.table.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        List<String> stringList = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(filename);
            while (sc.hasNextLine()) {
                stringList.add(sc.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < stringList.size(); i++) {
            String[] parts = stringList.get(i).split(",", 2);
            ScoreInfo info = new ScoreInfo(parts[0], Integer.parseInt(parts[1]));
            this.table.add(info);
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        try {
            // If file doesn't exists, then create it
            if (!filename.exists()) {
                filename.createNewFile();
            }

            FileWriter fw = new FileWriter(filename.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            for (int i = 0; i < this.table.size(); i++) {
                bw.write(this.table.get(i).getName());
                bw.write(",");
                bw.write(Integer.toString(this.table.get(i).getScore()));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        try {
            HighScoresTable newTable = new HighScoresTable(10);
            newTable.load(filename);
            if (filename.canRead()) {
                return newTable;
            }
        } catch (Exception e) {
            return new HighScoresTable(0);
        }
        return new HighScoresTable(0);
    }
}
