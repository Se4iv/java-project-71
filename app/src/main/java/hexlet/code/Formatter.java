package hexlet.code;

import java.util.List;

public class Formatter {

    public static String formOutput(List<String> list, String style) {
        StringBuilder stringBuilder = new StringBuilder();
        if (style.equals("stylish")) {
            stringBuilder.append("{\n");
            for (String str : list) {
                stringBuilder.append(str).append("\n");
            }
            stringBuilder.append("}");
        }
        return stringBuilder.toString();
    }
}
