package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Differ {

    public static String generate(Path path1, Path path2) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map1  = objectMapper.readValue(
                Files.readString(path1.toAbsolutePath().normalize()), new TypeReference<>() { });

        Map<String, Object> map2  = objectMapper.readValue(
                Files.readString(path2.toAbsolutePath().normalize()), new TypeReference<>() { });
        //первый список
        List<String> prepare1 = new ArrayList<>(map1.entrySet().stream()
                .flatMap(element1 -> Stream.of(map2)
                        .map(element2 -> {
                            if (element2.containsKey(element1.getKey())
                                    && element2.get(element1.getKey()).equals(element1.getValue())) {
                                return "  " + element1.getKey() + ": " + element1.getValue();
                            } else if (element2.containsKey(element1.getKey())
                                    && !element2.get(element1.getKey()).equals(element1.getValue())) {
                                return "- " + element1.getKey() + ": " + element1.getValue()//;
                                        + "\n" + "+ " + element1.getKey() + ": " + element2.get(element1.getKey());
                            }
                            return "- " + element1.getKey() + ": " + element1.getValue();
                        }))
                .toList());
        //второй список
        List<String> prepare2 = new ArrayList<>(map2.entrySet().stream()
                .flatMap(element2 -> Stream.of(map1)
                        .map(element1 -> {
                            if (!element1.containsKey(element2.getKey())) {
                                return "+ " + element2.getKey() + ": " + element2.getValue();
                            }
                            return null;
                        }))
                .filter(Objects::nonNull)
                .toList());
        //объединим списки
        prepare1.addAll(prepare2);

        //сортировка
        List<String> result = prepare1.stream()
                .sorted(Comparator.comparing(x -> x.substring(2, x.indexOf(":"))))
                .toList();

        //соберем стрингу
        StringBuilder stringBuilder = new StringBuilder("{\n");

        for (String str: result
             ) {
            stringBuilder.append(str).append("\n");
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }


}
