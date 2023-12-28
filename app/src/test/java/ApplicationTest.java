import hexlet.code.Differ;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private static final String USERDIR = System.getProperty("user.dir") + "/src/test/resources/fixtures/";
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    @Test
    public void testJsonDiff()  {
        String path1 = getPathToFile("testDiff_file1.json");
        String path2 = getPathToFile("testDiff_file2.json");
        String expected = getTestFile("testDiff_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testJsonNull() {
        String path1 = getPathToFile("testNull_file1.json");
        String path2 = getPathToFile("testNull_file2.json");
        String expected = getTestFile("testNull_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }
    @Test
    public void testJsonDiff2() {
        String path1 = getPathToFile("testDiff2_file1.json");
        String path2 = getPathToFile("testDiff2_file2.json");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff3() {
        String path1 = getPathToFile("testDiff3_file1.json");
        String path2 = getPathToFile("testDiff3_file2.json");
        String expected = getTestFile("testDiff3_result.txt");
        assertThat(Differ.generate(path1, path2, PLAIN)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff4() {
        String path1 = getPathToFile("testDiff4_file1.json");
        String path2 = getPathToFile("testDiff4_file2.json");
        String expected = getTestFile("testDiff4_result_json.txt");
        assertThat(Differ.generate(path1, path2, JSON)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiffDefaultStyle() {
        String path1 = getPathToFile("testDiff2_file1.json");
        String path2 = getPathToFile("testDiff2_file2.json");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }


    @Test
    public void testYamlDiff() {
        String path1 = getPathToFile("testDiff_file1.yml");
        String path2 = getPathToFile("testDiff_file2.yml");
        String expected = getTestFile("testDiff_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testYamlNull() {
        String path1 = getPathToFile("testNull_file1.yml");
        String path2 = getPathToFile("testNull_file2.yml");
        String expected = getTestFile("testNull_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }
    @Test
    public void testYamlDiff2() {
        String path1 = getPathToFile("testDiff2_file1.yml");
        String path2 = getPathToFile("testDiff2_file2.yml");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff3() {
        String path1 = getPathToFile("testDiff3_file1.yml");
        String path2 = getPathToFile("testDiff3_file2.yml");
        String expected = getTestFile("testDiff3_result.txt");
        assertThat(Differ.generate(path1, path2, PLAIN)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff4() {
        String path1 = getPathToFile("testDiff4_file1.yml");
        String path2 = getPathToFile("testDiff4_file2.yml");
        String expected = getTestFile("testDiff4_result_yml.txt");
        assertThat(Differ.generate(path1, path2, JSON)).isEqualTo(expected);
    }

    @Test
    public void testYamlDefaultStyle()  {
        String path1 = getPathToFile("testDiff2_file1.yml");
        String path2 = getPathToFile("testDiff2_file2.yml");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }

    @SneakyThrows
    public static String getTestFile(String filename) {
        return Files.readString(Path.of(USERDIR + filename).toAbsolutePath().normalize());
    }

    public static String getPathToFile(String filename) {
        return USERDIR + filename;
    }
}
