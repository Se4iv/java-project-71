package hexlet.code;

import formatters.Plain;
import formatters.Stylish;
import java.util.Map;

public class Formatter {

    public static String chooseStyle(Map<String, Object> map, String style) {

        switch (style) {
            case "plain":
                return Plain.formOutput(map);
            default:
                return Stylish.formOutput(map);
        }

    }
}
