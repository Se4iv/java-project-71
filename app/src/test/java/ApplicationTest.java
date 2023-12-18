import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private static final String USERDIR = System.getProperty("user.dir");
    private static final String STYLE_1 = "stylish";
    private static final String STYLE_2 = "plain";

    @Test
    public void testJsonDiff() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testDiff_file1.json");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testDiff_file2.json");
        String expected = "{\n"
                + "  host: hexlet.io\n"
                + "  ip: [192.1.1.1, 192.0.0.1, 168.168.1.1]\n"
                + "}";
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testJsonNull() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testNull_file1.json");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testNull_file2.json");
        String expected = "{\n"
                + "+ host: hexlet.io\n"
                + "+ ip: [192.1.1.1, 192.0.0.1, 168.168.1.1]\n"
                + "}";
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }
    @Test
    public void testJsonDiff2() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testDiff2_file1.json");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testDiff2_file2.json");
        String expected = "{\n"
                + "+ config: latest\n"
                + "- enable: true\n"
                + "+ enable: false\n"
                + "  host: hexlet.io\n"
                + "- ip: [192.1.1.1, 192.0.0.1, 168.168.1.1]\n"
                + "+ ip: [192.1.1.3, 192.0.0.1, 168.168.1.1]\n"
                + "- port: 1\n"
                + "+ port: 10\n"
                + "- wait: 4\n"
                + "}";
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testJsonDiff3() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testDiff3_file1.json");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testDiff3_file2.json");
        String expected =  "Property 'config' was added with value: 'latest'\n"
                + "Property 'enable' was updated. From true to false\n"
                + "Property 'good' was updated. From null to 13\n"
                + "Property 'ip' was updated. From [complex value] to [complex value]\n"
                + "Property 'port' was updated. From 1 to 10\n"
                + "Property 'wait' was removed";
        assertThat(Differ.generate(path1, path2, STYLE_2)).isEqualTo(expected);
    }


    @Test
    public void testYamlDiff() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testDiff_file1.yml");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testDiff_file2.yml");

        String expected = "{\n"
                + "  host: hexlet.io\n"
                + "  ip: [192.1.1.1, 192.0.0.1, 168.168.1.1]\n"
                + "}";
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testYamlNull() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testNull_file1.yml");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testNull_file2.yml");
        String expected = "{\n"
                + "+ host: hexlet.io\n"
                + "+ ip: [192.1.1.1, 192.0.0.1, 168.168.1.1]\n"
                + "}";
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }
    @Test
    public void testYamlDiff2() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testDiff2_file1.yml");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testDiff2_file2.yml");
        String expected = "{\n"
                + "+ config: latest\n"
                + "- enable: true\n"
                + "+ enable: false\n"
                + "  host: hexlet.io\n"
                + "- ip: [192.1.1.1, 192.0.0.1, 168.168.1.1]\n"
                + "+ ip: [192.1.1.3, 192.0.0.1, 168.168.1.1]\n"
                + "- port: 1\n"
                + "+ port: 10\n"
                + "- wait: 4\n"
                + "}";
        assertThat(Differ.generate(path1, path2, STYLE_1)).isEqualTo(expected);
    }

    @Test
    public void testYamlDiff3() throws IOException {
        Path path1 = Paths.get(USERDIR, "/src/test/resources/testDiff3_file1.yml");
        Path path2 = Paths.get(USERDIR, "/src/test/resources/testDiff3_file2.yml");
        String expected = "Property 'config' was added with value: 'latest'\n"
                + "Property 'enable' was updated. From true to false\n"
                + "Property 'good' was updated. From null to 13\n"
                + "Property 'ip' was updated. From [complex value] to [complex value]\n"
                + "Property 'port' was updated. From 1 to 10\n"
                + "Property 'wait' was removed";
        assertThat(Differ.generate(path1, path2, STYLE_2)).isEqualTo(expected);
    }
}
