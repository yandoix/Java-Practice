package Exceptions.Checked;

public class OutstandingFeesException extends zLibraryException {

    private static final long serialVersionUID = 1L;

    private final double amountOwed;

    public OutstandingFeesException(double amountOwed) {
        super("Outstanding fees: €" + amountOwed);
        this.amountOwed = amountOwed;
    }
    public OutstandingFeesException(double amountOwed, Throwable cause) {
        super("Outstanding fees: €" + amountOwed, cause);
        this.amountOwed = amountOwed;
    }

    public double getAmountOwed() {
        return amountOwed;
    }
}