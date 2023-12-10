package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFile(Path path) throws IOException {
        return new ObjectMapper(new YAMLFactory()).
        readValue(Files.readString(path.toAbsolutePath().normalize()), new TypeReference<>() { });

    }
}
