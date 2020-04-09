import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, BlockCreator> symbolAndblocks = new HashMap<String, BlockCreator>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        Map<String, Integer> spacers = new HashMap<String, Integer>();
        Map<Integer, ColorsParser> filling = new HashMap<Integer, ColorsParser>();
        int height = 0, width = 0, hitPoints = 0, spacerWidth = 0;
        Color stroke = null;
        String line, symbol = null, spacerSymbol = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] param = line.split(" ");
                if (!param[0].equals("") && !param[0].equals("#")) {
                    if (param[0].equals("default")) {
                        for (int i = 0; i < param.length; i++) {
                            String[] param2 = param[i].split(":");
                            switch (param2[0]) {
                                case "height":
                                    height = Integer.parseInt(param2[1]);
                                    continue;
                                case "width":
                                    width = Integer.parseInt(param2[1]);
                                    continue;
                                case "hit_points":
                                    hitPoints = Integer.parseInt(param2[1]);
                                    continue;
                                case "fill":
                                    if (param2[1].contains("image")) {
                                        String path = BlocksDefinitionReader.splitString(param2[1]);
                                        filling.put(0, new ColorsParser(null, ColorsParser.imageFromString(path)));
                                    } else {
                                        filling.put(0, new ColorsParser(ColorsParser.colorFromString(param2[1]), null));
                                    }
                                    continue;
                                case "stroke":
                                    stroke = ColorsParser.colorFromString(param2[1]);
                                    continue;
                                default:
                            }
                            if (param2[0].contains("fill-")) {
                                if (param2[1].contains("image")) {
                                    String path = BlocksDefinitionReader.splitString(param2[1]);
                                    int num = Integer.parseInt(param2[0].split("\\-")[1]);
                                    filling.put(num, new ColorsParser(null, ColorsParser.imageFromString(path)));
                                } else {
                                    int num = Integer.parseInt(param2[0].split("\\-")[1]);
                                    filling.put(num, new ColorsParser(ColorsParser.colorFromString(param2[1]), null));
                                }
                                continue;
                            }
                        }
                        continue;
                    } else if (param[0].equals("bdef")) {
                        Map<Integer, ColorsParser> privateFilling = new HashMap<Integer, ColorsParser>();
                        java.util.List<Integer> keys = new ArrayList<Integer>(filling.keySet());
                        int height2 = height;
                        int width2 = width;
                        int hitPoints2 = hitPoints;
                        Color stroke2 = stroke;
                        for (int i = 0; i < filling.size(); i++) {
                            privateFilling.put(keys.get(i), filling.get(i));
                        }
                        for (int i = 1; i < param.length; i++) {
                            String[] param2 = param[i].split(":");
                            switch (param2[0]) {
                                case "symbol":
                                    symbol = param2[1];
                                    continue;
                                case "width":
                                    width2 = Integer.parseInt(param2[1]);
                                    continue;
                                case "height":
                                    height2 = Integer.parseInt(param2[1]);
                                    continue;
                                case "hit_points":
                                    hitPoints2 = Integer.parseInt(param2[1]);
                                    continue;
                                case "fill":
                                    if (param2[1].contains("image")) {
                                        String path = BlocksDefinitionReader.splitString(param2[1]);
                                        privateFilling.put(0, new ColorsParser(null, ColorsParser.
                                                imageFromString(path)));
                                    } else {
                                        privateFilling.put(0, new ColorsParser(ColorsParser.colorFromString(param2[1]),
                                                null));
                                    }
                                    continue;
                                case "stroke":
                                    stroke2 = ColorsParser.colorFromString(param2[1]);
                                    continue;
                                default:
                            }
                            if (param2[0].contains("fill-")) {
                                if (param2[1].contains("image")) {
                                    String path = BlocksDefinitionReader.splitString(param2[1]);
                                    int num = Integer.parseInt(param2[0].split("\\-")[1]);
                                    privateFilling.put(num, new ColorsParser(null, ColorsParser.
                                            imageFromString(path)));
                                } else {
                                    int num = Integer.parseInt(param2[0].split("\\-")[1]);
                                    privateFilling.put(num, new ColorsParser(ColorsParser.colorFromString(param2[1]),
                                            null));
                                }
                                continue;
                            }
                        }
                        if (height2 <= 0 || width2 <= 0 || hitPoints2 <= 0 || privateFilling.isEmpty()
                                || symbol == null) {
                            System.out.print("Not enough parameters.");
                            System.exit(1);
                        } else {
                            symbolAndblocks.put(symbol, new BlockMaker(height2, width2, hitPoints2, stroke2,
                                    privateFilling));
                        }
                    } else if (param[0].equals("sdef")) {
                        for (int i = 1; i < param.length; i++) {
                            String[] param2 = param[i].split(":");
                            if (param2[0].equals("symbol")) {
                                spacerSymbol = param2[1];
                            } else if (param2[0].equals("width")) {
                                spacerWidth = Integer.parseInt(param2[1]);
                            }
                        }
                        // add to spacers map.
                        spacers.put(spacerSymbol, spacerWidth);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (bufferedReader != null) {
                return new BlocksFromSymbolsFactory(spacers, symbolAndblocks);
            }
        }
        return new BlocksFromSymbolsFactory(spacers, symbolAndblocks);
    }

    /**
     * Split string string.
     *
     * @param pathString the path string
     * @return the string
     */
    public static String splitString(String pathString) {
        String[] param = pathString.split("\\(");
        String path = param[1].split("\\)")[0];
        return path;
    }
}
