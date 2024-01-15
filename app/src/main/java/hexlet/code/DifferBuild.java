package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class DifferBuild {

    public static List<Map<Object, Object>> findDifference(Map<Object, Object> map1, Map<Object, Object> map2) {
        Set<Object> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        List<Map<Object, Object>> result = new ArrayList<>();
        keys.forEach(key -> {
            if (!map2.containsKey(key)) {
                result.add(makeNewMap(key, "removed", map1.get(key), ""));
            } else if (!map1.containsKey(key)) {
                result.add(makeNewMap(key, "added", "", map2.get(key)));
            } else if (String.valueOf(map1.get(key)).equals(String.valueOf(map2.get(key)))) {
                result.add(makeNewMap(key, "unchanged", map1.get(key), map2.get(key)));
            } else {
                result.add(makeNewMap(key, "changed", map1.get(key), map2.get(key)));
            }
        });
        return result;
    }
    public static Map<Object, Object> makeNewMap(Object key, String type, Object value1, Object value2) {
        Map<Object, Object> newmap = new TreeMap<>();
        newmap.put("key", key);
        newmap.put("type", type);
        newmap.put("value1", value1);
        newmap.put("value2", value2);
        return newmap;
    }
}
