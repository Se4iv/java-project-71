package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class Differ {
    private static final int STYLECOUNT = 3;

    public static String generate(String... params) throws IOException {
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        List<Node> resultlist = new ArrayList<>();
        addMapToList(list1, Parser.parseFile(Path.of(params[0])), 1);
        addMapToList(list2, Parser.parseFile(Path.of(params[1])), 2);
        compareNodeLeft(list1, list2, resultlist);
        compareNodeRight(list1, list2, resultlist);
        resultlist.sort(Comparator.comparing(x -> x.getKey() + x.getFilenumber()));
        return Formatter.chooseStyle(resultlist, params.length == STYLECOUNT ? params[2] : "stylish");
    }

    public static void addMapToList(List<Node> list, Map<String, Object> map, int number) {
        for (Map.Entry element: map.entrySet()) {
            list.add(new Node(element.getKey().toString(), element.getValue(), number, "", ""));
        }
    }
    public static void compareNodeLeft(List<Node> list1, List<Node> list2, List<Node> result) {
        for (Node element: list1) {
            if (list2.contains(element)
                        && !String.valueOf(list2.get(list2.indexOf(element)).getDefaultvalue())
                    .equals(String.valueOf(element.getDefaultvalue()))) {
                result.add(new Node(element.getKey(), element.getDefaultvalue(),
                            element.getFilenumber(), "changed", list2.get(list2.indexOf(element)).getDefaultvalue()));
            } else if (!list2.contains(element)) {
                result.add(new Node(element.getKey(), element.getDefaultvalue(),
                            element.getFilenumber(), "removed", ""));
            } else {
                result.add(new Node(element.getKey(), element.getDefaultvalue(),
                            element.getFilenumber(), "unchanged", ""));
            }
        }
    }

    public static void compareNodeRight(List<Node> list1, List<Node> list2, List<Node> result) {
        for (Node element : list2) {
            if (!list1.contains(element)) {
                result.add(new Node(element.getKey(), element.getDefaultvalue(),
                        element.getFilenumber(), "added", ""));
            }
        }
    }
}
