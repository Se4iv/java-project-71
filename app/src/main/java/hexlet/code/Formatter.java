package hexlet.code;

import hexlet.code.exceptions.WrongStyleType;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class Formatter {

    @SneakyThrows
    public static String chooseStyle(List<Map<Object, Object>> list, String style) {
        switch (style) {
            case "stylish":
                return Stylish.formOutput(list);
            case "plain":
                return Plain.formOutput(list);
            case "json":
                return Json.formOutput(list);
            default:
                throw new WrongStyleType("Wrong style! Choose another one");
        }
    }
}
