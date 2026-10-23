package Exceptions.Checked;

public class zLibraryException extends Exception {
     private static final long serialVersionUID = 1L;

    public zLibraryException(String message) {
        super(message);
    }

    public zLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}