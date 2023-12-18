package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class Differ {

    public static String generate(Path path1, Path path2, String formatstyle) throws IOException {
        Map<String, Object> map1  = Parser.parseFile(path1);
        Map<String, Object> map2  = Parser.parseFile(path2);
        Map<String, Object> resultmap =
                new TreeMap<>(Comparator.comparing(x -> x.substring(x.indexOf("$") + 1)));
        //первый список
       // System.out.println(map1.toString());
       // System.out.println(map2.toString());
        //List<String> prepare1 = prepareList(map1, map2, "left");
        //второй список
        //List<String> prepare2 = prepareList(map1, map2, "right");
        resultmap(map1, map2, resultmap, "right");
        resultmap(map1, map2, resultmap, "left");
        //объединим списки
        //prepare1.addAll(prepare2);
        //сортировка
//        List<String> result = prepare1.stream()
//                .sorted(Comparator.comparing(x -> x.substring(x.indexOf("_"), x.indexOf(":"))))
//                .toList();
        return Formatter.chooseStyle(resultmap, formatstyle);
    }

    public static void resultmap(Map<String, Object> map1, Map<String, Object> map2,
                                 Map<String, Object> result, String type) {
        if (type.equals("right")) {
            for (Map.Entry element : map1.entrySet()) {
                if (!map2.containsKey(element.getKey())) {
                    result.put("remove$" + element.getKey() + "#first", element.getValue());
                } else if (map2.containsKey(element.getKey())
                        && !String.valueOf(map2.get(element.getKey()))
                        .equals(String.valueOf(element.getValue()))) {
                    result.put("changedfrom$" + element.getKey() + "#first", element.getValue());
                    result.put("changedto$" + element.getKey() + "#second", map2.get(element.getKey()));
                } else if (map2.containsKey(element.getKey())) {
                    result.put("same$" + element.getKey() + "#first", element.getValue());
                }
            }
        }
        if (type.equals("left")) {
            for (Map.Entry element : map2.entrySet()) {
                if (!map1.containsKey(element.getKey())) {
                    result.put("add$" + element.getKey() + "#second", element.getValue());
                }
            }
        }
    }

//    public static List<String> prepareList(Map<String, Object> map1, Map<String, Object> map2, String type) {
//        if (type.equals("left")) {
//            return new ArrayList<>(map1.entrySet().stream()
//                    .flatMap(element1 -> Stream.of(map2)
//                            .map(element2 -> {
//                                if (element2.containsKey(element1.getKey())
//                                        && String.valueOf(element2.get(element1.getKey()))
//                                        .equals(String.valueOf(element1.getValue()))) {
//                                    return "same_" + element1.getKey() + ": " + element1.getValue();
//                                } else if (element2.containsKey(element1.getKey())
//                                        && !String.valueOf(element2.get(element1.getKey()))
//                                        .equals(String.valueOf(element1.getValue()))) {
//                                    return "remove_" + element1.getKey() + ": " + element1.getValue()
//                                            + "\n" + "+add" + element1.getKey()
//                                            + "* " + element2.get(element1.getKey());
//                                }
//                                return "remove_" + element1.getKey() + ": " + element1.getValue();
//                            }))
//                    //.peek(System.out::println)
//                    .toList());
//        } else if (type.equals("right")) {
//            return new ArrayList<>(map2.entrySet().stream()
//                    .flatMap(element2 -> Stream.of(map1)
//                            .map(element1 -> {
//                                if (!element1.containsKey(element2.getKey())) {
//                                    return "add_" + element2.getKey() + ": " + element2.getValue();
//                                }
//                                return null;
//                            }))
//                    .filter(Objects::nonNull)
//                    .toList());
//        } else {
//            return null;
//        }
//    }



}
