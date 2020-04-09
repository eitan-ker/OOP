import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStreamReader;
import java.io.BufferedReader;


/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    private int numOfFields = 8;


    /**
     * From level set map.
     *
     * @param reader the reader
     * @return the map
     */
    public Map<String, List<LevelInformation>> fromLevelSet(java.io.Reader reader) {
        Map<String, List<LevelInformation>> levelsMap = new HashMap<String, List<LevelInformation>>();
        String line, levelSort = null, path = null;
        int counter = 0;
        java.io.LineNumberReader readLine = new java.io.LineNumberReader(reader);
        try {
            while ((line = readLine.readLine()) != null) {
                if ((readLine.getLineNumber() % 2) != 0) {
                    levelSort = line;
                    counter++;
                    continue;
                } else {
                    path = line;
                    counter++;
                }
                if (counter == 2) {
                    // make list according to path
                    java.io.Reader re = new BufferedReader(new InputStreamReader(ClassLoader
                            .getSystemClassLoader().getResourceAsStream(path)));
                    List<LevelInformation> levelList = fromReader(re);
                    levelsMap.put(levelSort, levelList);
                    counter = 0;
                    levelSort = null;
                    path = null;
                    continue;
                } else {
                    counter = 0;
                    levelSort = null;
                    path = null;
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return levelsMap;
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelList = new ArrayList<>();
        // helpers
        String line;
        java.io.LineNumberReader readLine = new java.io.LineNumberReader(reader);
        boolean flag = true;
        String blockDefString = null, image, color = null;
        Image newImage;
        Color newColor;
        Map<Integer, String> blocks = new HashMap<Integer, String>();
        int j = 0;
        // level info
        int numOfBalls = 0, paddleSpeed = 0, paddleWidth = 0;
        int numberOfBlocksToRemove = 0, blockXvalue = 0, blockYvalue = 0, rowHeight = 0;
        ColorsParser backGround = null;
        List<Block> blockList = new ArrayList<>();
        List<Velocity> ballsspeed = new ArrayList<>();
        String levelName = null;
        try {
            while ((line = readLine.readLine()) != null) {
                String[] levelstarter = line.split(" ");
                if (levelstarter[0].equals("#")) {
                    continue;
                }
                if (line.equals("START_BLOCKS")) {
                    j = 1;
                    flag = false;
                    continue;
                }
                if (line.equals("END_BLOCKS")) {
                    this.numOfFields--;
                    flag = true;
                    continue;
                }
                if (line.equals("START_LEVEL")) {
                    // reseting the map and list for a new level
                    this.numOfFields = 8;
                    levelName = null;
                    ballsspeed = new ArrayList<Velocity>();
                    backGround = null;
                    image = "";
                    newImage = null;
                    color = "";
                    newColor = null;
                    paddleSpeed = 0;
                    paddleWidth = 0;
                    numberOfBlocksToRemove = 0;
                    numOfBalls = 0;
                    blockXvalue = 0;
                    blockYvalue = 0;
                    rowHeight = 0;
                    blockDefString = null;
                    blocks = new HashMap<Integer, String>();
                    blockList = new ArrayList<>();
                    continue;
                }
                if (line.equals("END_LEVEL")) {
                    // blocks List
                    blockList = this.makeBlocks(blockDefString, blocks, blockXvalue, blockYvalue, rowHeight, readLine);
                    if (this.numOfFields == 0) {
                        Map<String, Integer> paddleDetail = new HashMap<String, Integer>();
                        paddleDetail.put("paddle width", paddleWidth);
                        paddleDetail.put("paddle speed", paddleSpeed);
                        levelList.add(new LevelMaker(levelName, ballsspeed, numOfBalls, backGround, paddleDetail,
                                blockList, numberOfBlocksToRemove));
                        flag = true;
                        continue;
                    }
                }
                if (line.equals("")) {
                    continue;
                }
                if (flag) {
                    String[] parts = line.split(":", 2);
                    switch (parts[0]) {
                        case "block_definitions":
                            blockDefString = parts[1];
                            continue;
                        case "num_blocks":
                            numberOfBlocksToRemove = Integer.parseInt(parts[1]);
                            this.numOfFields--;
                            continue;
                        case "blocks_start_x":
                            blockXvalue = Integer.parseInt(parts[1]);
                            continue;
                        case "blocks_start_y":
                            blockYvalue = Integer.parseInt(parts[1]);
                            continue;
                        case "row_height":
                            rowHeight = Integer.parseInt(parts[1]);
                            continue;
                        case "level_name":
                            levelName = parts[1];
                            this.numOfFields--;
                            continue;
                        case "ball_velocities":
                            String[] parts2 = parts[1].split(" ");
                            for (int i = 0; i < parts2.length; i++) {
                                String[] parts3 = parts2[i].split(",");
                                Velocity newVelocity = Velocity.fromAngleAndSpeed(Double.parseDouble(parts3[0]),
                                        Double.parseDouble((parts3[1])));
                                ballsspeed.add(newVelocity);
                                numOfBalls++;
                            }
                            this.numOfFields--;
                            this.numOfFields--;
                            continue;
                        case "background":
                            String[] param = parts[1].split("\\(");
                            String[] param2 = param[1].split("\\)");
                            image = param2[0];
                            // image
                            if (param[0].equals("image")) {
                                newImage = ColorsParser.imageFromString(image);
                                backGround = new ColorsParser(null, newImage);
                                // color.
                            } else if (param[0].equals("color")) {
                                if (param[1].equals("RGB")) {
                                    color = "(" + param[1] + "(" + param[2];
                                } else {
                                    color = param[1].split("\\)")[0];
                                    color = "(" + color + ")";
                                }
                                newColor = ColorsParser.colorFromString(color);
                                backGround = new ColorsParser(newColor, null);
                            }
                            this.numOfFields--;
                            continue;
                        case "paddle_speed":
                            paddleSpeed = Integer.parseInt(parts[1]);
                            this.numOfFields--;
                            continue;
                        case "paddle_width":
                            paddleWidth = Integer.parseInt(parts[1]);
                            this.numOfFields--;
                            break;
                        default:
                    }
                } else {
                    blocks.put(j, line);
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return levelList;
    }

    /**
     * Make blocks list.
     *
     * @param blocksDef      the blocks def
     * @param blocksLocation the blocks location
     * @param x              the x
     * @param y              the y
     * @param height         the height
     * @param reader         the reader
     * @return the list
     */
    public List<Block> makeBlocks(String blocksDef, Map<Integer, String> blocksLocation, int x, int y, int height,
                                  java.io.Reader reader) {
        List<Block> blockList = new ArrayList<Block>();
        int x2 = x;
        int y2 = y;

        try {
            reader = new BufferedReader(new InputStreamReader(ClassLoader
                    .getSystemClassLoader().getResourceAsStream(blocksDef)));
            BlocksFromSymbolsFactory blocksList = BlocksDefinitionReader
                    .fromReader(reader);
            for (Integer key : blocksLocation.keySet()) {
                String data = blocksLocation.get(key);
                for (int i = 0; i < data.length(); i++) {
                    String symbol = String.valueOf(data.charAt(i));
                    if (blocksList.isBlockSymbol(symbol)) {
                        Block block = blocksList.getBlock(symbol, x2, y2);
                        blockList.add(block);
                        x2 = x2 + (int) block.getCollisionRectangle().getWidth();
                    } else if (blocksList.isSpaceSymbol(symbol)) {
                        x2 = x2 + blocksList.getSpaceWidth(symbol);
                    }
                }
                x2 = x;
                y2 += height;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return blockList;
    }
}
