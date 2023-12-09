import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private final String userdir = System.getProperty("user.dir");

    @Test
    public void testDiff() throws IOException {
        Path path1 = Paths.get(userdir, "/src/test/resources/testDiff_file1.json");
        Path path2 = Paths.get(userdir, "/src/test/resources/testDiff_file2.json");

        String expected = "{\n"
                + "  host: hexlet.io\n"
                + "  ip: 192.1.1.1\n"
                + "}";
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }

    @Test
    public void testNull() throws IOException {
        Path path1 = Paths.get(userdir, "/src/test/resources/testNull_file1.json");
        Path path2 = Paths.get(userdir, "/src/test/resources/testNull_file2.json");
        String expected = "{\n"
                + "+ host: hexlet.io\n"
                + "+ ip: 192.1.1.1\n"
                + "}";
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }
    @Test
    public void testDiff2() throws IOException {
        Path path1 = Paths.get(userdir, "/src/test/resources/testDiff2_file1.json");
        Path path2 = Paths.get(userdir, "/src/test/resources/testDiff2_file2.json");
        String expected = "{\n"
                + "+ config: latest\n"
                + "- enable: true\n"
                + "+ enable: false\n"
                + "  host: hexlet.io\n"
                + "- ip: 192.1.1.1\n"
                + "+ ip: 192.1.1.3\n"
                + "- port: 1\n"
                + "+ port: 10\n"
                + "- wait: 4\n"
                + "}";
        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }
}
