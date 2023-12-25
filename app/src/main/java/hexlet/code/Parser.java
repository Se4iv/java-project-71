package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<Object, Object> parseFile(String file) throws JsonProcessingException {
        return new ObjectMapper(new YAMLFactory()).
        readValue(file, new TypeReference<>() { });

    }
}
