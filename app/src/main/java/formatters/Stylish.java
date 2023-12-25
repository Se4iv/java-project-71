package formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String formOutput(List<Map<Object, Object>> list) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<Object, Object> element : list) {
            if (element.get("type").equals("removed")) {
                result.append(concatenateOutput(element, "  - ", false));
            } else if (element.get("type").equals("added")) {
                result.append(concatenateOutput(element, "  + ", true));
            } else if (element.get("type").equals("unchanged")) {
                result.append(concatenateOutput(element, "    ", false));
            } else {
                result.append(concatenateOutput(element, "  - ", false))
                        .append(concatenateOutput(element, "  + ", true));
            }
        }
        return result.append("}").toString();
    }

    public static String concatenateOutput(Map<Object, Object> map, String str, boolean isnew) {
        return str + map.get("key") + ": " + (isnew ? map.get("value2") : map.get("value1")) + "\n";
    }
}
