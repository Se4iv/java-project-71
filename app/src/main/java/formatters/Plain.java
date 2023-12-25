package formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String formOutput(List<Map<Object, Object>> list) {
        StringBuilder result = new StringBuilder();
        for (Map<Object, Object> element : list) {
            if (element.get("type").equals("removed")) {
                result.append(concatenateOutput(element, "' was removed", false, true));
            } else if (element.get("type").equals("added")) {
                result.append(concatenateOutput(element, "' was added with value: ", false, false));
            } else if (!element.get("type").equals("unchanged")) {
                result.append(concatenateOutput(element, "' was updated.", true, false));
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static String concatenateOutput(Map<Object, Object> map, String str, boolean isnew, boolean removed) {
        return "Property '" + map.get("key") + str
                + (isnew ? " From " + formatValue(map.get("value1")) + " to " + formatValue(map.get("value2"))
                : (removed ? "" : formatValue(map.get("value2")))) + "\n";
    }

    public static boolean isComplex(String str) {
        return str.charAt(0) == '[' || str.charAt(0) == '{';
    }

    public static String formatValue(Object object) {
        return isComplex(String.valueOf(object)) ? "[complex value]"
                : (object instanceof String ? "'" + object + "'" : String.valueOf(object));
    }
}
