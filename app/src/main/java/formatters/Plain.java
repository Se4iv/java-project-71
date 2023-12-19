package formatters;

import hexlet.code.Node;

import java.util.List;
public class Plain {

    public static String formOutput(List<Node> list) {
        StringBuilder result = new StringBuilder();
        for (Node element : list) {
            if (element.getType().equals("unchanged")) {
                continue;
            } else if (element.getType().equals("removed")) {
                result.append(concatenateOutput(element, "' was removed", false, true));
            } else if (element.getType().equals("added")) {
                result.append(concatenateOutput(element, "' was added with value: ", false, false));
            } else {
                result.append(concatenateOutput(element, "' was updated.", true, false));
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static String concatenateOutput(Node node, String str, boolean isnew, boolean removed) {
        return "Property '" + node.getKey() + str
                + (isnew ? " From " + formatValue(node.getDefaultvalue()) + " to " + formatValue(node.getNewvalue())
                : (removed ? "" : formatValue(node.getDefaultvalue()))) + "\n";
    }

    public static boolean isComplex(String str) {
        return str.charAt(0) == '[' || str.charAt(0) == '{';
    }

    public static String formatValue(Object object) {
        return isComplex(String.valueOf(object)) ? "[complex value]"
                : (object instanceof String ? "'" + object + "'" : String.valueOf(object));
    }
}
