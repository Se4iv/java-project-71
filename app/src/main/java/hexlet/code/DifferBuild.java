package hexlet.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DifferBuild {
    //Make merged map of two incoming maps
    public static Map<Object, List<Object>> mergeMaps(Map<Object, Object> map1, Map<Object, Object> map2) {
        Map<Object, List<Object>> mergedMap = new HashMap<>();
        map1.forEach((key, value) -> mergedMap.put(key, makeList(value, "")));
        map2.forEach((key, value) -> mergedMap.merge(key, makeList("", value),
                (prev, next) -> makeList(prev.get(0), next.get(1))));
        return mergedMap;
    }

    //Make list to avoid problems with null values
    public static ArrayList<Object> makeList(Object value1, Object value2) {
        return Stream.of(value1, value2).collect(Collectors.toCollection(ArrayList::new));
    }
    //Forming list of maps with all keys and values from first and second files
    public static List<Map<Object, Object>> addMergedMapToList(Map<Object, List<Object>> mergedMap) {
        List<Map<Object, Object>> differList = new ArrayList<>();
        mergedMap.forEach((key, value) -> differList.add(makeNewMap(key, value.get(0), value.get(1))));
        return differList;
    }
    //to avoid problems with null values
    public static Map<Object, Object> makeNewMap(Object key, Object value1, Object value2) {
        Map<Object, Object> newmap = new TreeMap<>();
        newmap.put("key", key);
        newmap.put("type", "default");
        newmap.put("value1", value1);
        newmap.put("value2", value2);
        return newmap;
    }

    public static void sortList(List<Map<Object, Object>> differList) {
        differList.sort(Comparator.comparing(x -> x.get("key").toString()));
    }

    public static void updateTypeOfDiff(List<Map<Object, Object>> differList) {
        differList.forEach(element -> {
            if (String.valueOf(element.get("value1")).isEmpty()) {
                element.put("type", "added");
            } else if (String.valueOf(element.get("value2")).isEmpty()) {
                element.put("type", "removed");
            } else if (String.valueOf(element.get("value1")).equals(String.valueOf(element.get("value2")))) {
                element.put("type", "unchanged");
            } else {
                element.put("type", "changed");
            }
        });
    }
}
