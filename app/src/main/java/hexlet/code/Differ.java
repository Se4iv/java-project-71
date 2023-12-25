package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Differ {

    private static final int VALUES = 2;
    private static final int DEFAULTVALUE = 3;
    public static String generate(String path1, String path2, String style) throws IOException {
        Map<Object, Object> map1 = Parser.parseFile(readFile(path1));
        Map<Object, Object> map2 = Parser.parseFile(readFile(path2));
        List<Map<Object, Object>> preparelist = new ArrayList<>();
        preparelist.add(map1);
        preparelist.add(map2);
        Map<Object, List<Object>> groupmap = groupKeys(preparelist);
        List<Map<Object, Object>> preparelist2 = new ArrayList<>();
        addMapToList(preparelist2, map1, groupmap, 1);
        addMapToList(preparelist2, map2, groupmap, 2);
        List<Map<Object, Object>> resultlist = updateType(preparelist2);
        return Formatter.chooseStyle(resultlist, style);
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }

    public static Map<Object, Object> makeMap(Object key, Object value1, Object value2) {
        return makeMap(key, value1, value2, DEFAULTVALUE);
    }

    public static Map<Object, Object> makeMap(Object key, Object value1, Object value2, int number) {
        Map<Object, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("type", "default");
        result.put("value1", number == 1 || number == DEFAULTVALUE ? value1 : "");
        result.put("value2", number == 2 || number == DEFAULTVALUE ? value2 : "");
        return result;
    }

    public static List<Map<Object, Object>> updateType(List<Map<Object, Object>> list) {
        return list.stream()
                .sorted(Comparator.comparing(x -> x.get("key").toString()))
                .map(element -> {
                    if (String.valueOf(element.get("value1")).isEmpty()) {
                        element.put("type", "added");
                    } else if (String.valueOf(element.get("value2")).isEmpty()) {
                        element.put("type", "removed");
                    } else if (String.valueOf(element.get("value1")).equals(String.valueOf(element.get("value2")))) {
                        element.put("type", "unchanged");
                    } else {
                        element.put("type", "changed");
                    }
                    return element;
                })
                .toList();
    }

    public static void addMapToList(List<Map<Object, Object>> list, Map<Object, Object> map,
                                    Map<Object, List<Object>> groupmap, int number) {
        for (Map.Entry<Object, Object> element : map.entrySet()) {
            if (groupmap.get(element.getKey()).size() != VALUES) {
                list.add(makeMap(element.getKey(), element.getValue(), element.getValue(), number));
            } else if (number != VALUES) {
                list.add(makeMap(element.getKey(), groupmap.get(element.getKey()).get(0),
                        groupmap.get(element.getKey()).get(1)));
            }
        }
    }

    public static Map<Object, List<Object>> groupKeys(List<Map<Object, Object>> list) {
        return list.stream()
                .flatMap(element -> element.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path).toAbsolutePath().normalize());
    }
}
