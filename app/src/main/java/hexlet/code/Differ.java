package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class Differ {

    public static String generate(Path path1, Path path2, String formatstyle) throws IOException {
        Map<String, Object> map1  = Parser.parseFile(path1);
        Map<String, Object> map2  = Parser.parseFile(path2);
        Map<String, Object> resultmap =
                new TreeMap<>(Comparator.comparing(x -> x.substring(x.indexOf("$") + 1)));
        resultmap(map1, map2, resultmap, "right");
        resultmap(map1, map2, resultmap, "left");
        return Formatter.chooseStyle(resultmap, formatstyle);
    }

    public static void resultmap(Map<String, Object> map1, Map<String, Object> map2,
                                 Map<String, Object> result, String type) {
        if (type.equals("right")) {
            for (Map.Entry element : map1.entrySet()) {
                if (!map2.containsKey(element.getKey())) {
                    result.put("remove$" + element.getKey() + "#first", element.getValue());
                } else if (map2.containsKey(element.getKey())
                        && !String.valueOf(map2.get(element.getKey())).equals(String.valueOf(element.getValue()))) {
                    result.put("changedfrom$" + element.getKey() + "#first", element.getValue());
                    result.put("changedto$" + element.getKey() + "#second", map2.get(element.getKey()));
                } else if (map2.containsKey(element.getKey())) {
                    result.put("same$" + element.getKey() + "#first", element.getValue());
                }
            }
        }
        if (type.equals("left")) {
            for (Map.Entry element : map2.entrySet()) {
                if (!map1.containsKey(element.getKey())) {
                    result.put("add$" + element.getKey() + "#second", element.getValue());
                }
            }
        }
    }
}
