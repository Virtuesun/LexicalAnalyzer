package deyang.li.lexicalanalyzer.error;

/**
 * Created by lidey on 2017/4/10.
 */
public enum Errors {
    NOT_IDENTIFIER("Wrong! It is not an identifier."),
    EXIST_NAME("Wrong! The constant name already exists."),
    NO_VALUE("Wrong! A constant declaration must contain a value."),
    EXTRA_CHARACTER("Wrong! There are more than one char in ''."),
    INCORRECT_CHAR_ENDS("Wrong! The char type must end with a \"'\"."),
    INCORRECT_STRING_ENDS("Wrong! The String type must end with a \"\"\"."),
    INCORRECT_INTEGER_ZERO_START("Wrong! The integer can’t be started with '0'."),
    INCORRECT_FLOAT_ZERO_START("Wrong! The float can’t be started with a extra '0'."),
    NOT_ANY_TYPE("Wrong! This value does not belong to any type.");

    private String message;

    private Errors(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
