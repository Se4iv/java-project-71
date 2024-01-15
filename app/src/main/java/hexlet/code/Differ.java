package hexlet.code;

import lombok.SneakyThrows;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String path1, String path2, String style)  {
        Map<Object, Object> map1 = Parser.parseFile(readFile(path1), getFormat(path1));
        Map<Object, Object> map2 = Parser.parseFile(readFile(path2), getFormat(path2));
        List<Map<Object, Object>> differList = DifferBuild.findDifference(map1, map2);
        return Formatter.chooseStyle(differList, style);
    }
    public static String generate(String path1, String path2) {
        return generate(path1, path2, "stylish");
    }
    @SneakyThrows
    public static String readFile(String path) {
        return Files.readString(Path.of(path).toAbsolutePath().normalize());
    }

    public static String getFormat(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
