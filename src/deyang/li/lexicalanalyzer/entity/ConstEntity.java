package deyang.li.lexicalanalyzer.entity;

import deyang.li.lexicalanalyzer.error.Errors;

public class ConstEntity {
    private String name;
    private Type type;
    private String value;
    private Errors error;

    public ConstEntity() {
        super();
    }

    public ConstEntity(String name, Type type, String value, Errors error) {
        super();
        this.name = name;
        this.type = type;
        this.value = value;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstEntity that = (ConstEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return error == null ? name + "(" + type + "," + value + ")" : name + "(" + error + ")";
    }


}
