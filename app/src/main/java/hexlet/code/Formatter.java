package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String chooseStyle(List<Map<Object, Object>> list, String style) throws IOException {
        switch (style) {
            case "plain":
                return Plain.formOutput(list);
            case "json":
                return Json.formOutput(list);
            default:
                return Stylish.formOutput(list);
        }
    }
}
