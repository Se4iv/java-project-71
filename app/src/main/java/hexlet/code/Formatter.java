package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {

    public static String chooseStyle(Map<String, Object> map, String style) throws IOException {

        switch (style) {
            case "plain":
                return Plain.formOutput(map);
            case "json":
                return Json.formOutput(map);
            default:
                return Stylish.formOutput(map);
        }

    }
}
