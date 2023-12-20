import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private static final String USERDIR = System.getProperty("user.dir") + "/src/test/resources/fixtures/";
    private static final String STYLE_1 = "stylish";
    private static final String STYLE_2 = "plain";
    private static final String STYLE_3 = "json";

    @Test
    public void testJsonDiff() throws IOException {
        String path1 = USERDIR + "testDiff_file1.json";
        String path2 = USERDIR + "testDiff_file2.json";
        String expected = getTestFile(USERDIR + "testDiff_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testJsonNull() throws IOException {
        String path1 = USERDIR + "testNull_file1.json";
        String path2 = USERDIR + "testNull_file2.json";
        String expected = getTestFile(USERDIR + "testNull_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }
    @Test
    public void testJsonDiff2() throws IOException {
        String path1 = USERDIR + "testDiff2_file1.json";
        String path2 = USERDIR + "testDiff2_file2.json";
        String expected = getTestFile(USERDIR + "testDiff2_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff3() throws IOException {
        String path1 = USERDIR + "testDiff3_file1.json";
        String path2 = USERDIR + "testDiff3_file2.json";
        String expected = getTestFile(USERDIR + "testDiff3_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_2)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff4() throws IOException {
        String path1 = USERDIR + "testDiff4_file1.json";
        String path2 = USERDIR + "testDiff4_file2.json";
        String expected = getTestFile(USERDIR + "testDiff4_result_json.txt");
        assertThat(Differ.generate(path1, path2, STYLE_3)).isEqualTo(expected);
    }


    @Test
    public void testYamlDiff() throws IOException {
        String path1 = USERDIR + "testDiff_file1.yml";
        String path2 = USERDIR + "testDiff_file2.yml";
        String expected = getTestFile(USERDIR + "testDiff_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testYamlNull() throws IOException {
        String path1 = USERDIR + "testNull_file1.yml";
        String path2 = USERDIR + "testNull_file2.yml";
        String expected = getTestFile(USERDIR + "testNull_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }
    @Test
    public void testYamlDiff2() throws IOException {
        String path1 = USERDIR + "testDiff2_file1.yml";
        String path2 = USERDIR + "testDiff2_file2.yml";
        String expected = getTestFile(USERDIR + "testDiff2_result.txt");;
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff3() throws IOException {
        String path1 = USERDIR + "testDiff3_file1.yml";
        String path2 = USERDIR + "testDiff3_file2.yml";
        String expected = getTestFile(USERDIR + "testDiff3_result.txt");
        assertThat(Differ.generate(path1, path2, STYLE_2)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff4() throws IOException {
        String path1 = USERDIR + "testDiff4_file1.yml";
        String path2 = USERDIR + "testDiff4_file2.yml";
        String expected = getTestFile(USERDIR + "testDiff4_result_yml.txt");
        assertThat(Differ.generate(path1, path2, STYLE_3)).isEqualTo(expected);
    }

    public static String getTestFile(String path) throws IOException {
       return Files.readString(Path.of(path).toAbsolutePath().normalize());
    }
}
