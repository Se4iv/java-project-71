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
        switch (fileFormat) {
            case "json":
                return new ObjectMapper(new JsonFactory()).readValue(file, new TypeReference<>() { });
            case "yml":
                return new ObjectMapper(new YAMLFactory()).readValue(file, new TypeReference<>() { });
            default:
                throw new WrongFormatFile("Wrong format of file! Need to be *.json or *.yml");
        }
    }
}
