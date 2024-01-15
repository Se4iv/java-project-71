import hexlet.code.Differ;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private final String userDir = System.getProperty("user.dir") + "/src/test/resources/fixtures/";
    private final String jsonPath1 = getPathToFile("file1.json");
    private final String jsonPath2 = getPathToFile("file2.json");
    private final String ymlPath1 = getPathToFile("file1.yml");
    private final String ymlPath2 = getPathToFile("file2.yml");

    @Test
    public void testJson()  {
        String expected = getTestFile("result_json.txt");
        assertThat(Differ.generate(jsonPath1, jsonPath2, "json")).isEqualTo(expected);
        assertThat(Differ.generate(ymlPath1, ymlPath2, "json")).isEqualTo(expected);
    }

    @Test
    public void testStylish() {
        String expected = getTestFile("result_stylish.txt");
        assertThat(Differ.generate(jsonPath1, jsonPath2)).isEqualTo(expected);
        assertThat(Differ.generate(ymlPath1, ymlPath2)).isEqualTo(expected);
    }
    @Test
    public void testPlain() {
        String expected = getTestFile("result_plain.txt");
        assertThat(Differ.generate(jsonPath1, jsonPath2, "plain")).isEqualTo(expected);
        assertThat(Differ.generate(ymlPath1, ymlPath2, "plain")).isEqualTo(expected);
    }

    @SneakyThrows
    private String getTestFile(String filename) {
        return Files.readString(Path.of(userDir + filename).toAbsolutePath().normalize());
    }

    private String getPathToFile(String filename) {
        return userDir + filename;
    }
}
