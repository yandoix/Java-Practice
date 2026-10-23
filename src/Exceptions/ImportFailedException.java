package Exceptions;

public class ImportFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public ImportFailedException(String message) {
        super(message);
    }

    public ImportFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}