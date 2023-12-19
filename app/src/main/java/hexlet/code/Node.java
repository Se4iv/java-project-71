package hexlet.code;

import java.util.Objects;

public class Node {
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
