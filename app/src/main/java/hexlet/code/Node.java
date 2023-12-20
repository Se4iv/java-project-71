package hexlet.code;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Node {
    @EqualsAndHashCode.Include
    private String key;
    private Object defaultvalue;
    private int filenumber;
    private String type;
    private Object newvalue;

}
