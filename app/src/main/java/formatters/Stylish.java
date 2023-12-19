package formatters;

import hexlet.code.Node;

import java.util.List;

public class Stylish {

    public static String formOutput(List<Node> list) {
        StringBuilder result = new StringBuilder("{\n");
        for (Node element : list) {
            if (element.getFilenumber() == 2 && element.getType().equals("unchanged")) {
                continue;
            } else if (element.getType().equals("removed")) {
                result.append(concatenateOutput(element, "- ", false));
            } else if (element.getType().equals("added")) {
                result.append(concatenateOutput(element, "+ ", false));
            } else if (element.getType().equals("unchanged")) {
                result.append(concatenateOutput(element, "  ", false));
            } else {
                result.append(concatenateOutput(element, "- ", false)).append(concatenateOutput(element, "+ ", true));
            }
        }
        return result.append("}").toString();
    }

    public static String concatenateOutput(Node node, String str, boolean isnew) {
        return str + node.getKey() + ": " + (isnew ? node.getNewvalue() : node.getDefaultvalue()) + "\n";
    }
}
