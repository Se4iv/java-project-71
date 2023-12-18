package formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonEncoding.UTF8;

public class Json {

    public static String formOutput(Map<String, Object> map) throws IOException {
        JsonGenerator generator = new JsonFactory().createGenerator(new File("json_output.json"), UTF8);
        generator.writeStartObject();
        generator.setCodec(new ObjectMapper()).useDefaultPrettyPrinter();
        for (Map.Entry element: map.entrySet()) {
            if (element.getKey().toString().contains("same$")) {
                continue;
            } else {
                generator.writeFieldName(element.getKey().toString()
                        .replace("changedfrom$", "old_")
                        .replace("changedto$", "new_")
                        .replace("remove$", "deleted_")
                        .replace("add$", "added_")
                        .replace("#first", "")
                        .replace("#second", ""));
                generator.writeObject(element.getValue());
            }
        }
        generator.writeEndObject();
        generator.close();
        return Files.readString(Paths.get("json_output.json").toAbsolutePath().normalize());
    }
}
