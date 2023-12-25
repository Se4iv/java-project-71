import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private static final String USERDIR = System.getProperty("user.dir") + "/src/test/resources/fixtures/";
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    @Test
    public void testJsonDiff() throws IOException {
        String path1 = getPathToFile("testDiff_file1.json");
        String path2 = getPathToFile("testDiff_file2.json");
        String expected = getTestFile("testDiff_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testJsonNull() throws IOException {
        String path1 = getPathToFile("testNull_file1.json");
        String path2 = getPathToFile("testNull_file2.json");
        String expected = getTestFile("testNull_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }
    @Test
    public void testJsonDiff2() throws IOException {
        String path1 = getPathToFile("testDiff2_file1.json");
        String path2 = getPathToFile("testDiff2_file2.json");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff3() throws IOException {
        String path1 = getPathToFile("testDiff3_file1.json");
        String path2 = getPathToFile("testDiff3_file2.json");
        String expected = getTestFile("testDiff3_result.txt");
        assertThat(Differ.generate(path1, path2, PLAIN)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff4() throws IOException {
        String path1 = getPathToFile("testDiff4_file1.json");
        String path2 = getPathToFile("testDiff4_file2.json");
        String expected = getTestFile("testDiff4_result_json.txt");
        assertThat(Differ.generate(path1, path2, JSON)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiffDefaultStyle() throws IOException {
        String path1 = getPathToFile("testDiff2_file1.json");
        String path2 = getPathToFile("testDiff2_file2.json");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }


    @Test
    public void testYamlDiff() throws IOException {
        String path1 = getPathToFile("testDiff_file1.yml");
        String path2 = getPathToFile("testDiff_file2.yml");
        String expected = getTestFile("testDiff_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testYamlNull() throws IOException {
        String path1 = getPathToFile("testNull_file1.yml");
        String path2 = getPathToFile("testNull_file2.yml");
        String expected = getTestFile("testNull_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }
    @Test
    public void testYamlDiff2() throws IOException {
        String path1 = getPathToFile("testDiff2_file1.yml");
        String path2 = getPathToFile("testDiff2_file2.yml");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2, STYLISH)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff3() throws IOException {
        String path1 = getPathToFile("testDiff3_file1.yml");
        String path2 = getPathToFile("testDiff3_file2.yml");
        String expected = getTestFile("testDiff3_result.txt");
        assertThat(Differ.generate(path1, path2, PLAIN)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff4() throws IOException {
        String path1 = getPathToFile("testDiff4_file1.yml");
        String path2 = getPathToFile("testDiff4_file2.yml");
        String expected = getTestFile("testDiff4_result_yml.txt");
        assertThat(Differ.generate(path1, path2, JSON)).isEqualTo(expected);
    }

    @Test
    public void testYamlDefaultStyle() throws IOException {
        String path1 = getPathToFile("testDiff2_file1.yml");
        String path2 = getPathToFile("testDiff2_file2.yml");
        String expected = getTestFile("testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }

    public static String getTestFile(String filename) throws IOException {
        return Files.readString(Path.of(USERDIR + filename).toAbsolutePath().normalize());
    }

    public static String getPathToFile(String filename) {
        return USERDIR + filename;
    }
}
