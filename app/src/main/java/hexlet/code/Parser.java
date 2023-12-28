package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.exceptions.WrongFormatFile;
import lombok.SneakyThrows;

import java.util.Map;

public class Parser {
    @SneakyThrows
    public static Map<Object, Object> parseFile(String file, String fileFormat) {
        if (fileFormat.endsWith(".json")) {
            return new ObjectMapper(new JsonFactory()).readValue(file, new TypeReference<>() { });
        } else if (fileFormat.endsWith(".yml")) {
            return new ObjectMapper(new YAMLFactory()).readValue(file, new TypeReference<>() { });
        } else {
            throw new WrongFormatFile("Wrong format of file! Need to be .json or .yml");
        }
    }
}
