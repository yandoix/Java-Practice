package Exceptions.Checked;

public class ImportFailedException extends zLibraryException {

    private static final long serialVersionUID = 1L;

    public ImportFailedException(String message) {
        super(message);
    }

    public ImportFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}