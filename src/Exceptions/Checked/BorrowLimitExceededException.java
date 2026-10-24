package Exceptions.Checked;

public class BorrowLimitExceededException extends zLibraryException {

    private static final long serialVersionUID = 1L;

    private final int limit;

    public BorrowLimitExceededException(int limit) {
        super("Borrowing limit exceeded. Maximum allowed: " + limit);
        this.limit = limit;
    }

    public BorrowLimitExceededException(int limit, Throwable cause ) {
        super("Borrowing limit exceeded. Maximum allowed: "+limit, cause);
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}