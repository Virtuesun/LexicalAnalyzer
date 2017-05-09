package deyang.li.lexicalanalyzer.error;

/**
 * Created by lidey on 2017/4/10.
 */
public enum Exceptions {
    NOT_CONSTANT_STATEMENT("It is not a constant declaration statement!"),
    INCORRECT_ENDS("A constant declaration statement should end with ';'!"),
    NO_EQUALS("each statement should be connected the name and value with '='!"),
    NO_NAME("each declaration should contain a constant name!");

    private String message;

    private Exceptions(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
