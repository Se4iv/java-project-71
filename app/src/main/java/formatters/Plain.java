package formatters;

import java.util.Map;

public class Plain {

    public static String formOutput(Map<String, Object> map) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry element: map.entrySet()) {
            if (element.getKey().toString().contains("same$") || element.getKey().toString().contains("changedto$")) {
                continue;
            } else {
                result.append("Property '").append(element.getKey().toString()
                                .replace("changedfrom$", "")
                                .replace("remove$", "")
                                .replace("add$", "")
                                .replace("#first", "")
                                .replace("#second", "")).append("'");
            }
            if (element.getKey().toString().contains("changedfrom$")) {
                result.append(" was updated. From ")
                        .append(formatValue(element.getValue())).append(" to ")
                        .append(formatValue(map.get(element.getKey().toString()
                                .replace("changedfrom$", "changedto$")
                                .replace("#first", "#second"))));
            } else if (element.getKey().toString().contains("add$")) {
                result.append(" was added with value: ")
                        .append(formatValue(element.getValue()));
            } else {
                result.append(" was removed");
            }
            result.append("\n");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static boolean isComplex(String str) {
        return str.charAt(0) == '[' || str.charAt(0) == '{';
    }

    public static String formatValue(Object object) {
        return isComplex(String.valueOf(object)) ? "[complex value]"
                : (object instanceof String ? "'" + object + "'" : String.valueOf(object));
    }
}
