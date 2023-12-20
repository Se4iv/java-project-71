package hexlet.code;

import java.util.*;

public final class Node {
    private String key;
    private Object defaultvalue;
    private int filenumber;
    private String type;
    private Object newvalue;

    public Node(String key, Object defaultvalue, int filenumber, String type, Object newvalue) {
        this.key = key;
        this.defaultvalue = defaultvalue;
        this.filenumber = filenumber;
        this.type = type;
        this.newvalue = newvalue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Node node = (Node) object;
        return Objects.equals(key, node.key);
    }
    public static  Map<Character, List<String>> buildIndex(String text) {
        if (text.isEmpty()) {
            return null;
        }
        String[] words = text.replace(",", "").split(" ");
        Map<Character, List<String>> result = new HashMap<>();
        for (String word : words) {
            if (result.containsKey(word.charAt(0))) {
                result.get(word.charAt(0)).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                result.put(word.charAt(0), list);
            }
        }
        return result;
    }
    public static void printBalance(Map<String, Integer> warehouse, int minvalue) {
        warehouse.forEach((key, value) -> {
            if (value < minvalue) {
                System.out.println(key);
            }
        } );
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public String getKey() {
        return key;
    }

    public Object getDefaultvalue() {
        return defaultvalue;
    }


    public int getFilenumber() {
        return filenumber;
    }


    public String getType() {
        return type;
    }

    public Object getNewvalue() {
        return newvalue;
    }
}
