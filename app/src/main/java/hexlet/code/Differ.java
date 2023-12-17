package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Differ {

    public static String generate(Path path1, Path path2, String formatstyle) throws IOException {
        Map<String, Object> map1  = Parser.parseFile(path1);
        Map<String, Object> map2  = Parser.parseFile(path2);
        //первый список
        System.out.println(map1.toString());
        System.out.println(map2.toString());
        List<String> prepare1 = prepareList(map1, map2, "left");
        //второй список
        List<String> prepare2 = prepareList(map1, map2, "right");
        //объединим списки
        prepare1.addAll(prepare2);
        //сортировка
        List<String> result = prepare1.stream()
                .sorted(Comparator.comparing(x -> x.substring(2, x.indexOf(":"))))
                .toList();

        return Formatter.formOutput(result, formatstyle);
    }

    public static List<String> prepareList(Map<String, Object> map1, Map<String, Object> map2, String type) {
        if (type.equals("left")) {
            return new ArrayList<>(map1.entrySet().stream()
                    .flatMap(element1 -> Stream.of(map2)
                            .map(element2 -> {
                                if (element2.containsKey(element1.getKey())
                                        && String.valueOf(element2.get(element1.getKey()))
                                        .equals(String.valueOf(element1.getValue()))) {
                                    return "  " + element1.getKey() + ": " + element1.getValue();
                                } else if (element2.containsKey(element1.getKey())
                                        && !String.valueOf(element2.get(element1.getKey()))
                                        .equals(String.valueOf(element1.getValue()))) {
                                    return "- " + element1.getKey() + ": " + element1.getValue()
                                            + "\n" + "+ " + element1.getKey()
                                            + ": " + element2.get(element1.getKey());
                                }
                                return "- " + element1.getKey() + ": " + element1.getValue();
                            }))
                    .peek(System.out::println)
                    .toList());
        } else if (type.equals("right")) {
            return new ArrayList<>(map2.entrySet().stream()
                    .flatMap(element2 -> Stream.of(map1)
                            .map(element1 -> {
                                if (!element1.containsKey(element2.getKey())) {
                                    return "+ " + element2.getKey() + ": " + element2.getValue();
                                }
                                return null;
                            }))
                    .filter(Objects::nonNull)
                    .toList());
        } else {
            return null;
        }
    }



}
