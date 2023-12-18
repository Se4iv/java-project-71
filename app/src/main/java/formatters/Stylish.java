package formatters;

import java.util.Map;

public class Stylish {

    public static String formOutput(Map<String, Object> map) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry element: map.entrySet()) {
            result.append(element.getKey().toString()
                            .replace("same$", "  ")
                            .replace("changedfrom$", "- ")
                            .replace("changedto$", "+ ")
                            .replace("remove$", "- ")
                            .replace("add$", "+ ")
                            .replace("#first", "")
                            .replace("#second", ""))
                    .append(": ").append(String.valueOf(element.getValue())).append("\n");
        }
        return result.append("}").toString();
    }
}
