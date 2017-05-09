package deyang.li.lexicalanalyzer.entity;

/**
 * Created by lidey on 2017/4/10.
 */
public enum Type {
    CHAR("char"),
    STRING("string"),
    INTEGER("integer"),
    FLOAT("float"),
    BOOLEAN("boolean");

    private String type;

    private Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
