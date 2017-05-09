package deyang.li.lexicalanalyzer.error;

import deyang.li.lexicalanalyzer.error.Exceptions;

public class IllegalDeclaration extends Exception {

    /**
     * Parse constant declaration error
     */
    private static final long serialVersionUID = 1L;


    public IllegalDeclaration(Exceptions exception) {
        super(exception.toString());
    }

    public IllegalDeclaration(Exceptions exception, String place) {
        super("Declare analytical error[" + place + "]," + exception);
    }
}
