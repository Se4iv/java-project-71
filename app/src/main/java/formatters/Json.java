package formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.fasterxml.jackson.core.JsonEncoding.UTF8;

public class Json {

    public static String formOutput(List<Node> list) throws IOException {
        JsonGenerator generator = new JsonFactory().createGenerator(new File("json_output.json"), UTF8)
                .setCodec(new ObjectMapper()).useDefaultPrettyPrinter();
        generator.writeStartObject();
        for (Node element : list) {
            if (element.getType().equals("unchanged")) {
                continue;
            } else if (element.getType().equals("removed")) {
                generator.writeFieldName("deleted_" + element.getKey());
                generator.writeObject(element.getDefaultvalue());
            } else if (element.getType().equals("added")) {
                generator.writeFieldName("added_" + element.getKey());
                generator.writeObject(element.getDefaultvalue());
            } else {
                generator.writeFieldName("old_" + element.getKey());
                generator.writeObject(element.getDefaultvalue());
                generator.writeFieldName("new_" + element.getKey());
                generator.writeObject(element.getNewvalue());
            }
        }
        generator.writeEndObject();
        generator.close();
        return Files.readString(Paths.get("json_output.json").toAbsolutePath().normalize());
    }
}
