package formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonEncoding.UTF8;

public class Json {

    public static String formOutput(List<Map<Object, Object>> list) throws IOException {
        JsonGenerator generator = new JsonFactory().createGenerator(new File("json_output.json"), UTF8)
                .setCodec(new ObjectMapper()).useDefaultPrettyPrinter();
        generator.writeStartObject();
        for (Map<Object, Object> element : list) {
            if (element.get("type").equals("removed")) {
                generator.writeFieldName("deleted_" + element.get("key"));
                generator.writeObject(element.get("value1"));
            } else if (element.get("type").equals("added")) {
                generator.writeFieldName("added_" + element.get("key"));
                generator.writeObject(element.get("value2"));
            } else if (!element.get("type").equals("unchanged")) {
                generator.writeFieldName("old_" + element.get("key"));
                generator.writeObject(element.get("value1"));
                generator.writeFieldName("new_" + element.get("key"));
                generator.writeObject(element.get("value2"));
            }
        }
        generator.writeEndObject();
        generator.close();
        return Files.readString(Paths.get("json_output.json").toAbsolutePath().normalize());
    }
}
