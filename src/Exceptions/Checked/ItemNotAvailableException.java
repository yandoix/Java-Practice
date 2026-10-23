package Exceptions.Checked;

import java.time.LocalDate;

public class ItemNotAvailableException extends zLibraryException {

    private static final long serialVersionUID = 1L;

    private final LocalDate expectedReturnDay;

    public ItemNotAvailableException(LocalDate expectedReturnDay) {
        super("Item is unavailable. Expected return: "
                + expectedReturnDay);
        this.expectedReturnDay = expectedReturnDay;
    }

    public LocalDate getExpectedReturnDay() {
        return expectedReturnDay;
    }
}